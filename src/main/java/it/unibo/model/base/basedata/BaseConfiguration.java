package it.unibo.model.base.basedata;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import it.unibo.model.data.Resource;
import it.unibo.model.data.TroopType;

/**
 * Generic configuration for the Base Model of the game.
 */
public final class BaseConfiguration {
    private static final int DEFAULT_MAX_TROOP_LEVEL = 3;
    private static final int DEFAULT_TROOP_COST_INCREMENT_PERCENTAGE = 25;

    private final Map<TroopType, Map<Integer, Set<Resource>>> costPerTroop;
    private final int maximumTroopLevel;
    private final int troopCostIncrementPercentage;

    /**
     * Creates a configuration for the Base Model with default values.
     */
    public BaseConfiguration() {
        this.maximumTroopLevel = DEFAULT_MAX_TROOP_LEVEL;
        this.troopCostIncrementPercentage = DEFAULT_TROOP_COST_INCREMENT_PERCENTAGE;
        costPerTroop = new EnumMap<>(TroopType.class);
        Arrays.stream(TroopType.values())
            .forEach(singleTroop -> {
                final Map<Integer, Set<Resource>> levelCostMap = new HashMap<>();
                IntStream.range(0, DEFAULT_MAX_TROOP_LEVEL)
                    .forEach(singleTroopLevel -> 
                        levelCostMap.put(singleTroopLevel, 
                            Resource.checkAndAddMissingResources(new HashSet<>())));
                costPerTroop.put(singleTroop, levelCostMap);
            });
    }
    /**
     * @return the maximum level that the troops can reach
     */
    public int getMaximumTroopLevel() {
        return maximumTroopLevel;
    }
    /**
     * @return A percentage that represents by how much the troop cost
     *         increase by every level
     */
    public int getTroopCostIncrementPercentage() {
        return troopCostIncrementPercentage;
    }
    /**
     * @return A map that contains the cost of every single type of troop
     */
    public Map<TroopType, Map<Integer, Set<Resource>>> getCostPerTroop() {
        return Collections.unmodifiableMap(costPerTroop);
    }
}
