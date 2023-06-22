package it.unibo.model.base;

import java.util.Set;

import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.model.data.GameData;
import it.unibo.model.data.Resource;
/**
 * Utility functions to simplify testing for the Base part of the game.
 */
public interface BaseTestUtils {
    /**
     * Standard time tolerance for checkElapsedTime.
     * @see {@link #checkElapsedTime(long, long, long)}
     */
    long STANDARD_TIME_TOLERANCE = 500L;
    /**
     * Applies the resources necessary to build a specific building.
     * @param gameData  the game data to edit
     * @param type      the reference building type
     * @param level     the reference building level
     */
    static void applyBuildingResources(final GameData gameData, final BuildingTypes type, final int level) {
        Set<Resource> cost = type.getCost(level);
        cost.forEach(resourceType -> {
            gameData.getResources().remove(resourceType);
            gameData.getResources().add(resourceType);
        });
    }
    /**
     * Checks if the elapsed time is correct.
     * @param elapsedTime       a long representing the elapsed time
     * @param timeToConfront    a long representing the expected time
     * @param tolerance         a long representing the tolerance
     * @return                  true if the time is within the given tolerance
     */
    static boolean checkElapsedTime(final long elapsedTime, final long timeToConfront, final long tolerance) {
        return (elapsedTime < (timeToConfront + tolerance)) && (elapsedTime > (timeToConfront - tolerance));
    }
    /**
     * Checks if the elapsed time is correct.
     * @param elapsedTime       a long representing the elapsed time
     * @param timeToConfront    a long representing the expected time
     * @return                  true if the elapsed time is exactly the same
     *                          as the time to confront
     */
    static boolean checkElapsedTime(final long elapsedTime, final long timeToConfront) {
        return checkElapsedTime(elapsedTime, timeToConfront, 0L);
    }
}
