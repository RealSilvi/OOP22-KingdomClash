package it.unibo.model.base.basedata;

/**
 * Generic configuration for the Base Model of the game.
 */
public final class BaseConfiguration {
    private int maximumTroopLevel;
    private int troopCostIncrementPercentage;
    /**
     * Creates a configuration for the Base Model with default values.
     */
    public BaseConfiguration () {
        this.maximumTroopLevel = 3;
        this.troopCostIncrementPercentage = 25;
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
