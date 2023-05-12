package it.unibo.model.base;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import it.unibo.model.base.basedata.Building;

public interface ThreadManager {
    /**
     * A simple enum to select only on a kind of thread
     */
    public enum ThreadSelector {
        /**
         * Selects threads dedicated to building buildings
         */
        CONSTRUCTION,
        /**
         * Selects threads dedicated to generate resources for buildings
         */
        PRODUCTION,
        /**
         * Selects all kind of threads
         */
        ALL
    }
    /**
     * Starts the threads dedicated to a kind of resource
     * @param threadType the type of threads that needs to be started
     */
    public void startThreads(ThreadSelector threadType);
    /**
     * Starts all threads
     */
    public void startThreads();
        /**
     * Pauses the threads dedicated to a kind of resource
     * @param threadType the type of threads that needs to be paused
     */
    public void pauseThreads(ThreadSelector threadType);
    /**
     * Pauses all threads
     */
    public void pauseThreads();

    /**
     * Checks if the given thread types are currently running
     * @param threadType the type of thread that needs to be checked
     * @return true if the thread is running
     */
    public boolean areThreadsRunning(ThreadSelector threadType);
    /**
     * Checks if all thread types are currently running
     * @return true if ALL threads are running, false if one or more are paused
     */
    public boolean areThreadsRunning();

    /**
     * Registers a map of buildings to keep track of time
     * @param buildingMap the map of buildings to keep track of
     */
    public void addBuildings(Map<UUID, Building> buildingMap);
    /**
     * Unre a map of buildings to keep track of time
     * @param buildingMap the map of buildings to keep track of
     */
    public void removeBuildings(Map<UUID, Building> buildingMap);
    /**
     * Unregisters a set of buildings with the corresponding ID
     * to keep track of time
     * @param buildingMap the id set of buildings to unregister
     */
    public void removeBuildings(Set<UUID> buildingMap);
    /**
     * Removes all tasks from buildings
     */
    public void clearBuildings();
}