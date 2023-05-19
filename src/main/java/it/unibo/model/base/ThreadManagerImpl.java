package it.unibo.model.base;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.logging.Logger;

import javax.annotation.Nonnull;

import it.unibo.model.base.basedata.Building;
import it.unibo.model.base.exceptions.NotEnoughResourceException;
import it.unibo.model.base.internal.BuildingBuilder;
import it.unibo.model.base.internal.BuildingBuilderImpl;

public class ThreadManagerImpl implements ThreadManager {
    private boolean keepAliveThreads = true;

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
            this.threadsRunning.put(selection, false);
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
        public WorkerThread createBuildingThread(UUID buildingToBuildIdentifier) {
            Function<UUID, Boolean> constructionAction = new Function<>() {
                @Override
                public Boolean apply(UUID buildingToConstruct) {
                    try {
                        Thread.sleep((long) buildingMapRef.get(buildingToConstruct)
                            .getBuildingTime()-REFRESH_RATE_MS);
                        int currentBuildProgress = buildingMapRef
                            .get(buildingToConstruct).getBuildingProgress();
                        if (currentBuildProgress < 100) {
                            buildingMapRef.get(buildingToConstruct)
                                .setBuildingProgress(currentBuildProgress+1);
                        } else {
                            buildingMapRef.get(buildingToConstruct)
                                .setBuildingProgress(0);
                            buildingMapRef.get(buildingToConstruct)
                                .setBeingBuilt(false);
                            buildingMapRef.get(buildingToConstruct)
                                .setLevel(buildingMapRef.get(buildingToConstruct).getLevel()+1);
                            baseModel.notifyBuildingStateChangedObservers(buildingToConstruct);
                            BuildingBuilder buildingBuilder = new BuildingBuilderImpl();
                            buildingBuilder.upgradeBuildingByLevel(buildingMapRef.get(buildingToConstruct),
                                buildingMapRef.get(buildingToConstruct).getLevel());
                            return false;
                        }
                        baseModel.notifyBuildingStateChangedObservers(buildingToConstruct);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return true;
                }
            };
            return new WorkerThread(ThreadSelector.CONSTRUCTION, constructionAction,
                buildingToBuildIdentifier);
        }
        public WorkerThread createProductionThread(UUID buildingToBuildIdentifier) {
            Function<UUID, Boolean> constructionAction = new Function<UUID, Boolean>() {
                @Override
                public Boolean apply(UUID productionBuilding) {
                    try {
                        Thread.sleep((long) buildingMapRef.get(productionBuilding).getProductionTime()-REFRESH_RATE_MS);
                        int currentProductionProgress = buildingMapRef.get(productionBuilding).getProductionProgress();
                        if (currentProductionProgress < 100) {
                            buildingMapRef.get(productionBuilding).setProductionProgress(currentProductionProgress+1);
                        } else {
                            buildingMapRef.get(productionBuilding).setProductionProgress(0);
                            baseModel.applyResources(buildingMapRef.get(productionBuilding).getProductionAmount());
                        }
                        baseModel.notifyBuildingProductionObservers(productionBuilding);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } catch (NotEnoughResourceException e) {
                        logger.severe("Not enough resources Exception thrown when adding resources,"
                        +" this implies a broken state of the player resource set and should be fixed!"
                        +" dumping stacktrace:\n"+e.getStackTrace());
                    }
                    return true;
                }
            };
            return new WorkerThread(ThreadSelector.CONSTRUCTION, constructionAction,
                buildingToBuildIdentifier);
        }
    }

    private class WorkerThread extends Thread {
        private boolean runThread = true;
        private Function<UUID, Boolean> task;
        private ThreadSelector threadType;
        private UUID associatedBuildingIdentifier;
        public WorkerThread(ThreadSelector threadType, Function<UUID, Boolean> task,
            UUID associatedBuildingIdentifier) {
            super();
            this.threadType = threadType;
            this.task = task;
            this.associatedBuildingIdentifier = associatedBuildingIdentifier;
        }

        @Override
        public void run() {
            while(isThreadRunning()) {
                while(Boolean.TRUE.equals(threadsRunning.get(this.threadType))) {
                    try {
                        synchronized(threadMap.get(this.threadType).get(associatedBuildingIdentifier)) {
                            if (Boolean.FALSE.equals(task.apply(associatedBuildingIdentifier))) {
                                setRunThread(false);
                                return;
                            }
                        }
                    //Thrown when the building is null
                    } catch (NullPointerException e) {
                        return;
                    }
                    try {
                        sleep(REFRESH_RATE_MS);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                try {
                    threadLocks.get(this.threadType).wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        public synchronized void setRunThread(boolean runThread) {
            this.runThread = runThread;
        }
        public synchronized boolean isThreadRunning() {
            return this.runThread;
        }
    }
}