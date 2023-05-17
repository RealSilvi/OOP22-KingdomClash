package it.unibo.model.base;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;

import it.unibo.model.base.basedata.Building;

public class ThreadManagerImpl implements ThreadManager {
    //TODO: Fire event when updating building state and test implementation
    private boolean keepAliveThreads = true;

    private ConcurrentMap<ThreadSelector, ConcurrentMap<UUID, Thread>> threadMap;
    private ConcurrentMap<ThreadSelector, Boolean> threadsRunning;
    private ConcurrentMap<ThreadSelector, Object> threadLocks;

    public ThreadManagerImpl() {
        this.threadMap = new ConcurrentHashMap<>();
        this.threadsRunning = new ConcurrentHashMap<>();
        this.threadLocks = new ConcurrentHashMap<>();
        for (ThreadSelector selection : ThreadSelector.values()) {
            this.threadMap.put(selection, new ConcurrentHashMap<>());
            this.threadsRunning.put(selection, false);
            this.threadLocks.put(selection, new Object());
        }
    }

    public ThreadManagerImpl(ConcurrentMap<ThreadSelector, ConcurrentMap<UUID, Thread>> threadMap) {
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
    public void addBuildings(final ConcurrentMap<UUID, Building> buildingMap) {
        buildingMap.forEach((identifier, building)->{
            if (building.isBeingBuilt()) {
                threadMap.get(ThreadSelector.CONSTRUCTION).put(identifier, new ThreadFactory().createBuildingThread(identifier, building));
            } else {
                threadMap.get(ThreadSelector.PRODUCTION).put(identifier, new ThreadFactory().createProductionThread(identifier, building));
            }
        });
    }

    @Override
    public void removeBuildings(ConcurrentMap<UUID, Building> buildingMap) {
        removeBuildings(buildingMap.keySet());
    }
    @Override
    public void removeBuildings(Set<UUID> buildingMap) {
        buildingMap.forEach(
            identifier->threadMap.forEach(
                (typeOfThreads, mapOfThreads)->mapOfThreads.remove(identifier)));
    }

    @Override
    public void clearBuildings() {
        pauseThreads();
        threadMap.forEach((selection, idThreadmap)->idThreadmap.clear());
    }

    private synchronized boolean shouldThreadsBeAlive() {
        return this.keepAliveThreads;
    }
    private synchronized void setKeepAliveThreads(boolean keepAliveThreads) {
        this.keepAliveThreads = keepAliveThreads;
    }

    private class ThreadFactory {
        public Thread createBuildingThread(UUID buildingToBuildIdentifier, Building buildingToBuild) {
            Consumer<Building> constructionAction = new Consumer<>() {
                @Override
                public void accept(Building buildingToConstruct) {
                    try {
                        Thread.sleep((long) buildingToBuild.getBuildingTime()-REFRESH_RATE_MS);
                        buildingToBuild.setBuildingProgress(buildingToBuild.getBuildingProgress()+1);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            };
            return new WorkerThread(ThreadSelector.CONSTRUCTION, constructionAction,
            buildingToBuildIdentifier, buildingToBuild);
        }
        public Thread createProductionThread(UUID buildingToBuildIdentifier, Building productionBuilding) {
            Consumer<Building> constructionAction = new Consumer<>() {
                @Override
                public void accept(Building buildingToConstruct) {
                    try {
                        Thread.sleep((long) productionBuilding.getBuildingTime()-REFRESH_RATE_MS);
                        productionBuilding.setBuildingProgress(productionBuilding.getProductionProgress()+1);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            };
            return new WorkerThread(ThreadSelector.CONSTRUCTION, constructionAction,
            buildingToBuildIdentifier, productionBuilding);
        }
    }

    private class WorkerThread extends Thread {
        private Consumer<Building> task;
        private ThreadSelector threadType;
        private UUID associatedBuildingIdentifier;
        private Building associatedBuilding;

        public WorkerThread(ThreadSelector threadType, Consumer<Building> task,
            UUID associatedBuildingIdentifier, Building associatedBuilding) {
            super();
            this.threadType = threadType;
            this.task = task;
            this.associatedBuildingIdentifier = associatedBuildingIdentifier;
            this.associatedBuilding = associatedBuilding;
        }

        @Override
        public void run() {
            while(shouldThreadsBeAlive()) {
                while(Boolean.TRUE.equals(threadsRunning.get(this.threadType))) {
                    try {
                        synchronized(threadMap.get(this.threadType).get(associatedBuildingIdentifier)) {
                            task.accept(this.associatedBuilding);
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
    }
}