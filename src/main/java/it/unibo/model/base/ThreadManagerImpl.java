package it.unibo.model.base;

import java.awt.geom.Point2D;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Nonnull;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.base.basedata.Building;
import it.unibo.model.base.exceptions.NotEnoughResourceException;
import it.unibo.model.base.internal.BuildingBuilder;
import it.unibo.model.base.internal.BuildingBuilderImpl;

/**
 * Simple thread manager implementation that can handle multiple threads
 * given action and timing functions.
 */
//threadsRuning boolean map is always initialized
@SuppressWarnings("java:S5411")
@SuppressFBWarnings(value = "DCN",
    justification = "NullPointerExceptions are purposely thrown to skip non existing threads")
public final class ThreadManagerImpl implements ThreadManager {
    private boolean keepAliveThreads = true;

    private final BuildingBuilder buildingBuilder = new BuildingBuilderImpl();

    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final BaseModel baseModel;
    private final ConcurrentMap<UUID, Building> buildingMapRef;

    private final ConcurrentMap<ThreadSelector, ConcurrentMap<UUID, WorkerThread>> threadMap;
    private final ConcurrentMap<ThreadSelector, Boolean> threadsRunning;
    private final ConcurrentMap<ThreadSelector, Object> threadLocks;

    /**
     * Constructs a new instance of ThreadManagerImpl given a reference model
     * and a map of building to work on.
     * @param baseModel the base model to interact with
     * @param buildingMapRef a concurrent map of buildings
     */
    @SuppressFBWarnings(value = "EI2",
    justification = "All data in here is always safely handled")
    public ThreadManagerImpl(final @Nonnull BaseModel baseModel,
        final ConcurrentMap<UUID, Building> buildingMapRef) {
        this.baseModel = baseModel;
        this.threadMap = new ConcurrentHashMap<>();
        this.threadsRunning = new ConcurrentHashMap<>();
        this.threadLocks = new ConcurrentHashMap<>();
        this.buildingMapRef = buildingMapRef;
        for (final ThreadSelector selection : ThreadSelector.values()) {
            this.threadMap.put(selection, new ConcurrentHashMap<>());
            this.threadsRunning.put(selection, true);
            this.threadLocks.put(selection, new Object());
        }
        buildingMapRef.forEach((id, building) -> 
            this.addBuilding(id));
    }

    @Override
    public void startThreads(final ThreadSelector threadType) {
        setKeepAliveThreads(true);
        threadsRunning.put(threadType, true);
        synchronized (threadLocks.get(threadType)) {
            threadLocks.get(threadType).notifyAll();
        }
    }

    @Override
    public void startThreads() {
        for (final ThreadSelector selection : ThreadSelector.values()) {
            startThreads(selection);
        }
    }

    @Override
    public void pauseThreads(final ThreadSelector threadType) {
        threadsRunning.put(threadType, false);
    }

    @Override
    public void pauseThreads() {
        for (final ThreadSelector selection : ThreadSelector.values()) {
            pauseThreads(selection);
        }
    }

    @Override
    public boolean areThreadsRunning(final ThreadSelector threadType) {
        return this.threadsRunning.get(threadType);
    }

    @Override
    public boolean areThreadsRunning() {
        boolean allThreadsRunning = false;
        for (final ThreadSelector selection : ThreadSelector.values()) {
            allThreadsRunning = this.threadsRunning.get(selection);
        }
        return allThreadsRunning;
    }

    @Override
    public void addBuilding(final UUID buildingIdentifier) {
        ThreadSelector selection = ThreadSelector.PRODUCTION;
        if (buildingMapRef.get(buildingIdentifier).isBeingBuilt()) {
            selection = ThreadSelector.CONSTRUCTION;
            removeBuilding(buildingIdentifier);
            threadMap.get(selection).put(buildingIdentifier,
                    new ThreadBuilder().createBuildingThread(buildingIdentifier));
        } else {
            threadMap.get(selection).put(buildingIdentifier,
                    new ThreadBuilder().createProductionThread(buildingIdentifier));
        }
        threadMap.get(selection).get(buildingIdentifier).start();
    }

    @Override
    //Exception purposefully thrown
    @SuppressWarnings({"PMD.AvoidCatchingGenericException", "PMD.AvoidCatchingNPE"})
    public void removeBuilding(final UUID buildingToRemove) {
        if (!isThreadPresent(buildingToRemove)) {
            return;
        }
        threadMap.forEach((selection, mapOfThreads) -> {
            try {
                mapOfThreads.get(buildingToRemove).setThreadRunning(false);
                mapOfThreads.get(buildingToRemove).join();
                mapOfThreads.remove(buildingToRemove);
            } catch (NullPointerException exc) {
                logger.log(Level.FINE, 
                "Skipping non-existing thread in {0}", selection.name());
            } catch (InterruptedException exc) {
                Thread.currentThread().interrupt();
            }
        });
    }

    @Override
    public void removeBuildings(final Set<UUID> buildingMap) {
        buildingMap.forEach(this::removeBuilding);
    }

    @Override
    public void clearBuildings() {
        threadMap.forEach((selection, idThreadMap) -> removeBuildings(idThreadMap.keySet()));
    }

    // Unused function, might be used in the future
    @SuppressWarnings("unused")
    private synchronized boolean shouldThreadsBeAlive() {
        return this.keepAliveThreads;
    }

    private synchronized void setKeepAliveThreads(final boolean keepAliveThreads) {
        this.keepAliveThreads = keepAliveThreads;
    }

    private class ThreadBuilder {
        public WorkerThread createBuildingThread(final UUID identifier) {
            final Function<UUID, Integer> buildingOperation = new Function<>() {
                @Override
                public Integer apply(final UUID buildingToBuildIdentifier) {
                    int constructionPercentage = buildingMapRef.get(buildingToBuildIdentifier)
                            .getBuildingProgress();
                    constructionPercentage++;
                    buildingMapRef.get(buildingToBuildIdentifier).setBuildingProgress(constructionPercentage);
                    if (constructionPercentage == 100) {
                        final Point2D buildingPosition = 
                            buildingMapRef.get(buildingToBuildIdentifier).getStructurePos();
                        buildingMapRef.put(buildingToBuildIdentifier,
                            buildingBuilder.makeStandardBuilding(
                                buildingMapRef.get(
                                    buildingToBuildIdentifier).getType(),
                                    buildingMapRef.get(
                                        buildingToBuildIdentifier).getLevel() + 1));
                        buildingMapRef.get(buildingToBuildIdentifier)
                            .setStructurePos(buildingPosition);
                    }
                    baseModel.notifyBuildingStateChangedObservers(buildingToBuildIdentifier);
                    return constructionPercentage;
                }
            };
            return new WorkerThread(ThreadSelector.CONSTRUCTION,
                    () -> buildingMapRef.get(identifier).getBuildingTime(),
                    time -> buildingMapRef.get(identifier).setBuildingTime(time),
                    buildingOperation,
                    identifier);
        }

        public WorkerThread createProductionThread(final UUID identifier) {
            final Function<UUID, Integer> productionOperation = new Function<>() {
                @Override
                public Integer apply(final UUID buildingForProductionIdentifier) {
                    int productionPercentage = buildingMapRef.get(buildingForProductionIdentifier)
                            .getProductionProgress();
                    productionPercentage++;
                    buildingMapRef.get(buildingForProductionIdentifier).setProductionProgress(productionPercentage);
                    logger.log(Level.FINEST, "productionPercentage {0}", productionPercentage);
                    if (productionPercentage == 100) {
                        try {
                            baseModel.applyResources(
                                buildingMapRef.get(buildingForProductionIdentifier)
                                .getProductionAmount());
                            buildingMapRef.get(identifier)
                                .setProductionTime(
                                    buildingMapRef.get(buildingForProductionIdentifier)
                                    .getType().getProductionTime());
                        } catch (NotEnoughResourceException e) {
                            logger.severe("Error adding resources!");
                        }
                        baseModel.notifyBuildingProductionObservers(buildingForProductionIdentifier);
                        buildingMapRef.get(buildingForProductionIdentifier).setProductionProgress(0);
                        buildingMapRef.get(buildingForProductionIdentifier)
                            .setProductionTime(buildingBuilder.makeStandardBuilding(
                                buildingMapRef
                                    .get(buildingForProductionIdentifier).getType(),
                                buildingMapRef
                                    .get(buildingForProductionIdentifier).getLevel())
                            .getProductionTime());
                        return 0;
                    }
                    baseModel.notifyBuildingProductionObservers(buildingForProductionIdentifier);
                    return productionPercentage;
                }
            };
            return new WorkerThread(ThreadSelector.CONSTRUCTION,
                    () -> buildingMapRef.get(identifier).getProductionTime(),
                    time -> buildingMapRef.get(identifier).setProductionTime(time),
                    productionOperation,
                    identifier);
        }
    }

    private final class WorkerThread extends Thread {
        private boolean threadRunning = true;
        private final ThreadSelector threadType;
        private final Supplier<Long> remainingTimeGetter;
        private final Consumer<Long> remainingTimeSetter;
        private final Function<UUID, Integer> operation;
        private final UUID assignedBuilding;

        WorkerThread(final ThreadSelector threadType,
                final Supplier<Long> remainingTimeGetter, final Consumer<Long> remainingTimeSetter,
                final Function<UUID, Integer> operation, final UUID assignedBuilding) {
            super();
            this.threadType = threadType;
            this.remainingTimeGetter = remainingTimeGetter;
            this.remainingTimeSetter = remainingTimeSetter;
            this.operation = operation;
            this.assignedBuilding = assignedBuilding;
            switch (this.threadType) {
                case PRODUCTION:
                    this.setName("Production Thread");
                    break;
                case CONSTRUCTION:
                    this.setName("Construction Thread");
                    break;
                default:
            }
        }

        @Override
        //remainingWork is checked
        @SuppressWarnings({"java:S3518", "PMD.PrematureDeclaration"})
        @SuppressFBWarnings(value = "UW",
        justification = "Not an unconditional wait as the thread can be woken up using the wait object")
        public void run() {
            while (isThreadRunning()) {
                final long operationStartTime = System.currentTimeMillis();
                final int remainingWork = 100 - operation.apply(assignedBuilding);
                if (remainingWork == 0 && threadType.equals(ThreadSelector.CONSTRUCTION)) {
                    logger.log(Level.FINEST, "Operations on building id {0} completed!", assignedBuilding);
                    threadClosureOperation();
                    return;
                }
                logger.log(Level.FINEST, "Remaining work to do: {0}%", remainingWork);
                final long elapsedTime = System.currentTimeMillis() - operationStartTime;
                final long remainingAvailableTime = remainingTimeGetter.get() - elapsedTime;
                remainingTimeSetter.accept(remainingAvailableTime > 0 ? remainingAvailableTime : 0);
                final long waitTime = remainingAvailableTime > 0 ? remainingAvailableTime / remainingWork : 0;
                logger.log(Level.FINEST, "Sleeping for: {0}ms", waitTime);
                try {
                    sleep(waitTime);
                } catch (InterruptedException e) {
                    logger.severe("Thread killed!");
                    threadClosureOperation();
                    Thread.currentThread().interrupt();
                }
                remainingTimeSetter.accept(remainingTimeGetter.get()-waitTime);
                if (!threadsRunning.get(threadType)) {
                    synchronized (threadLocks.get(threadType)) {
                        try {
                            threadLocks.get(threadType).wait();
                        } catch (InterruptedException e) {
                            threadClosureOperation();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
            threadClosureOperation();
        }

        public synchronized boolean isThreadRunning() {
            return this.threadRunning;
        }

        public synchronized void setThreadRunning(final boolean threadRunning) {
            this.threadRunning = threadRunning;
        }

        private void threadClosureOperation() {
            threadMap.get(threadType).remove(assignedBuilding);
        }
    }

    private boolean isThreadPresent(final UUID buildingId) {
        boolean threadFound = false;
        for (final ThreadSelector selector : ThreadSelector.values()) {
            threadFound = threadMap.get(selector).containsKey(buildingId);
        }
        return threadFound;
    }
}
