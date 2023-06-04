package it.unibo.model.base;

import java.util.Set;
import java.util.UUID;

/**
 * Interface for a class that can manage multiple threads with given tasks
 * and timings.
 */
public interface ThreadManager {
    /**
     * Time in milliseconds that the thread will wait for before doing another cycle.
     */
    int REFRESH_RATE_MS = 60;

    /**
     * A simple enum to select only on a kind of thread.
     */
    enum ThreadSelector {
        /**
         * Selects threads dedicated to building buildings.
         */
        CONSTRUCTION,
        /**
         * Selects threads dedicated to generate resources for buildings.
         */
        PRODUCTION
    }

    /**
     * Starts the threads dedicated to a kind of resource.
     *
     * @param threadType the type of threads that needs to be started
     */
    void startThreads(ThreadSelector threadType);

    /**
     * Starts all threads.
     */
    void startThreads();

    /**
     * Pauses the threads dedicated to a kind of resource.
     *
     * @param threadType the type of threads that needs to be paused
     */
    void pauseThreads(ThreadSelector threadType);

    /**
     * Pauses all threads.
     */
    void pauseThreads();

    /**
     * Checks if the given thread types are currently running.
     *
     * @param threadType the type of thread that needs to be checked
     * @return true if the thread is running
     */
    boolean areThreadsRunning(ThreadSelector threadType);

    /**
     * Checks if all thread types are currently running.
     *
     * @return true if ALL threads are running, false if one or more are paused
     */
    boolean areThreadsRunning();

    /**
     * Registers a map of buildings to keep track of time.
     *
     * @param buildingToAdd the map of buildings to keep track of
     */
    void addBuilding(UUID buildingToAdd);

    /**
     * Unre a map of buildings to keep track of time.
     *
     * @param buildingToRemove the map of buildings to keep track of
     */
    void removeBuilding(UUID buildingToRemove);

    /**
     * Unregisters a set of buildings with the corresponding ID
     * to keep track of time.
     *
     * @param buildingMap the id set of buildings to unregister
     */
    void removeBuildings(Set<UUID> buildingMap);

    /**
     * Removes all tasks from buildings.
     */
    void clearBuildings();
}
