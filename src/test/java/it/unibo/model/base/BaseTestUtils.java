package it.unibo.model.base;

import java.util.Set;

import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.model.data.GameData;
import it.unibo.model.data.Resource;

public class BaseTestUtils {
    public static final long STANDARD_TIME_TOLERANCE = 500L;

    public static void applyBuildingResources(GameData gameData, BuildingTypes type, int level) {
        Set<Resource> cost = type.getCost(level);
        cost.forEach(resourceType -> {
            gameData.getResources().remove(resourceType);
            gameData.getResources().add(resourceType);
        });
    }

    public static boolean checkElapsedTime(long elapsedTime, long timeToConfront, long tolerance) {
        return (elapsedTime < (timeToConfront + tolerance)) && (elapsedTime > (timeToConfront - tolerance));
    }

    public static boolean checkElapsedTime(long elapsedTime, long timeToConfront) {
        return checkElapsedTime(elapsedTime, timeToConfront, 0L);
    }
}