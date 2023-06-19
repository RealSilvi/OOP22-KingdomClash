package it.unibo.model.base.basedata;

/**
 * Generic configuration for the Base Model of the game.
 */
public final class BaseConfiguration {
    private static final int DEFAULT_MAX_TROOP_LEVEL = 3;
    private static final int DEFAULT_TROOP_COST_INCREMENT_PERCENTAGE = 25;
    private int maximumTroopLevel;
    private int troopCostIncrementPercentage;
    /**
     * Creates a configuration for the Base Model with default values.
     */
    public BaseConfiguration() {
        this.maximumTroopLevel = DEFAULT_MAX_TROOP_LEVEL;
        this.troopCostIncrementPercentage = DEFAULT_TROOP_COST_INCREMENT_PERCENTAGE;
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
}
