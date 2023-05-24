package it.unibo.model.base;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Nonnull;

import it.unibo.model.base.basedata.Building;
import it.unibo.model.base.exceptions.NotEnoughResourceException;
import it.unibo.model.base.internal.BuildingBuilder;
import it.unibo.model.base.internal.BuildingBuilderImpl;

//threadsRuning boolean map is always initialized
@SuppressWarnings("java:S5411")
public class ThreadManagerImpl implements ThreadManager {
    private boolean keepAliveThreads = true;

    private BuildingBuilder buildingBuilder = new BuildingBuilderImpl();

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private BaseModel baseModel;
    private ConcurrentMap<UUID, Building> buildingMapRef;

    private ConcurrentMap<ThreadSelector, ConcurrentMap<UUID, WorkerThread>> threadMap;
    private ConcurrentMap<ThreadSelector, Boolean> threadsRunning;
    private ConcurrentMap<ThreadSelector, Object> threadLocks;

    public ThreadManagerImpl(@Nonnull BaseModel baseModel, ConcurrentMap<UUID, Building> buildingMapRef) {
        this.baseModel = baseModel;
        this.threadMap = new ConcurrentHashMap<>();
        this.threadsRunning = new ConcurrentHashMap<>();
        this.threadLocks = new ConcurrentHashMap<>();
        this.buildingMapRef = buildingMapRef;
        for (ThreadSelector selection : ThreadSelector.values()) {
            this.threadMap.put(selection, new ConcurrentHashMap<>());
            this.threadsRunning.put(selection, true);
            this.threadLocks.put(selection, new Object());
        }
    }

    public ThreadManagerImpl(ConcurrentMap<ThreadSelector, ConcurrentMap<UUID, WorkerThread>> threadMap) {
        this.threadMap = threadMap;
    }

    @Override
    public void startThreads(final ThreadSelector threadType) {
        setKeepAliveThreads(true);
        threadsRunning.put(threadType, true);
        threadLocks.get(threadType).notifyAll();
    }

    @Override
    public void startThreads() {
        for (ThreadSelector selection : ThreadSelector.values()) {
            startThreads(selection);
        }
    }

    @Override
    public void pauseThreads(ThreadSelector threadType) {
        threadsRunning.put(threadType, false);
    }

    @Override
    public void pauseThreads() {
        for (ThreadSelector selection : ThreadSelector.values()) {
            pauseThreads(selection);
        }
    }

    @Override
    public boolean areThreadsRunning(ThreadSelector threadType) {
        return this.threadsRunning.get(threadType);
    }

    @Override
    public boolean areThreadsRunning() {
        boolean allThreadsRunning = true;
        for (ThreadSelector selection : ThreadSelector.values()) {
            allThreadsRunning = allThreadsRunning && this.threadsRunning.get(selection);
        }
        return allThreadsRunning;
    }
    @Override
    public void addBuilding(UUID buildingIdentifier) {
            ThreadSelector selection = ThreadSelector.PRODUCTION;
        if (buildingMapRef.get(buildingIdentifier).isBeingBuilt()) {
            selection = ThreadSelector.CONSTRUCTION;
            threadMap.get(selection).put(buildingIdentifier, new ThreadFactory().createBuildingThread(buildingIdentifier));
        } else {
            threadMap.get(selection).put(buildingIdentifier, new ThreadFactory().createProductionThread(buildingIdentifier));
        }
        threadMap.get(selection).get(buildingIdentifier).start();
    }

    @Override
    public void removeBuilding(UUID buildingToRemove) {
        threadMap.forEach((selection, mapOfThreads)->{
            mapOfThreads.get(buildingToRemove).setRunThread(false);
            try {
                mapOfThreads.get(buildingToRemove).join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
    @Override
    public void removeBuildings(Set<UUID> buildingMap) {
        buildingMap.forEach(this::removeBuilding);
    }

    @Override
    public void clearBuildings() {
        threadMap.forEach((selection, idThreadMap)->removeBuildings(idThreadMap.keySet()));
    }
    //Unused function, might be used in the future
    @SuppressWarnings("unused")
    private synchronized boolean shouldThreadsBeAlive() {
        return this.keepAliveThreads;
    }
    private synchronized void setKeepAliveThreads(boolean keepAliveThreads) {
        this.keepAliveThreads = keepAliveThreads;
    }

    private class ThreadFactory {
        public WorkerThread createGenericThread(ThreadSelector specialization,
            UUID buildingIdentifier, Supplier<Long> timingSupplier, Function<UUID, Boolean> operation) {
            return new WorkerThread(specialization, buildingIdentifier) {
                @Override
                public void run() {
                    while(isThreadRunning()) {
                        while(threadsRunning.get(specialization)) {
                            synchronized(threadMap.get(specialization).get(buildingIdentifier)) {
                                try {
                                    long startingTime = System.currentTimeMillis();
                                    if (!operation.apply(buildingIdentifier)) {
                                        terminateWorkerAction();
                                        logger.log(Level.INFO, "Operation on building: {0} completed! Thread terminated", buildingIdentifier);
                                        return;
                                    }
                                    long productionTime = timingSupplier.get();
                                    long endingTime = System.currentTimeMillis();
                                    long elapsedTime = endingTime-startingTime;
                                    long sleepTime = productionTime-elapsedTime;
                                    logger.log(Level.INFO, "Waiting for: {0} ms", (sleepTime > 0) ? sleepTime : 0);
                                    wait((sleepTime > 0) ? sleepTime : 0);
                                } catch (InterruptedException e) {
                                    terminateWorkerAction();
                                    Thread.currentThread().interrupt();
                                }
                            }
                        }
                    }
                }
            };
        }

        public WorkerThread createBuildingThread(UUID buildingToBuildIdentifier) {
            return createGenericThread(ThreadSelector.CONSTRUCTION,
                buildingToBuildIdentifier,
                ()->(long) buildingBuilder
                        .makeStandardBuilding(buildingMapRef
                            .get(buildingToBuildIdentifier)
                            .getType(),
                        buildingMapRef.get(buildingToBuildIdentifier)
                        .getLevel())
                    .getBuildingTime()/100,
                    buildingToConstruct-> {
                        int currentBuildProgress = buildingMapRef
                            .get(buildingToConstruct).getBuildingProgress();
                        if (currentBuildProgress < 100) {
                            buildingMapRef.get(buildingToConstruct)
                                .setBuildingProgress(currentBuildProgress+1);
                            baseModel.notifyBuildingStateChangedObservers(buildingToBuildIdentifier);
                            return true;
                        } else {
                            buildingMapRef.get(buildingToConstruct)
                                .setBuildingProgress(0);
                            buildingMapRef.get(buildingToConstruct)
                                .setBeingBuilt(false);
                            buildingMapRef.get(buildingToConstruct)
                                .setLevel(buildingMapRef.get(buildingToConstruct).getLevel()+1);
                            baseModel.notifyBuildingStateChangedObservers(buildingToConstruct);
                            buildingBuilder.upgradeBuildingByLevel(buildingMapRef.get(buildingToConstruct),
                                buildingMapRef.get(buildingToConstruct).getLevel());
                            baseModel.notifyBuildingStateChangedObservers(buildingToBuildIdentifier);
                            return false;
                        }
                    });
        }
        public WorkerThread createProductionThread(UUID productionBuildingIdentifier) {
            return createGenericThread(ThreadSelector.CONSTRUCTION,
            productionBuildingIdentifier,
                ()->(long) buildingBuilder
                        .makeStandardBuilding(buildingMapRef
                            .get(productionBuildingIdentifier)
                            .getType(),
                        buildingMapRef.get(productionBuildingIdentifier)
                        .getLevel())
                    .getProductionTime()/100,
                    productionBuilding-> {
                        int currentProductionProgress = buildingMapRef
                            .get(productionBuilding).getProductionProgress();
                        if (currentProductionProgress < 100) {
                            buildingMapRef.get(productionBuilding)
                                .setBuildingProgress(currentProductionProgress+1);
                            baseModel.notifyBuildingStateChangedObservers(productionBuildingIdentifier);
                            return true;
                        } else {
                            try {
                                buildingMapRef.get(productionBuildingIdentifier)
                                    .setProductionProgress(0);
                                baseModel.applyResources(
                                    buildingMapRef.get(productionBuildingIdentifier).getProductionAmount());
                                return false;
                            } catch (NotEnoughResourceException e) {
                                logger.severe("Not enough resources Exception thrown when adding resources,"
                                +" this implies a broken state of the player resource set and should be fixed!"
                                +" dumping stacktrace:\n"+e.getStackTrace());
                                return false;
                            }
                        }
                    });
        }
    }

    private class WorkerThread extends Thread {
        private boolean runThread = true;
        private ThreadSelector threadType;
        private UUID associatedBuildingIdentifier;

        public WorkerThread(ThreadSelector threadType, UUID associatedBuildingIdentifier) {
            super();
            this.threadType = threadType;
            this.associatedBuildingIdentifier = associatedBuildingIdentifier;
        }
        
        @Override
        public void run() {
          // This empty method is going to be replaced by the nested factory class
        }
        public synchronized void setRunThread(boolean runThread) {
            this.runThread = runThread;
        }
        public synchronized boolean isThreadRunning() {
            return this.runThread;
        }
        public void terminateWorkerAction() {
            setRunThread(false);
            threadMap.get(threadType).remove(associatedBuildingIdentifier);
        }
    }
}