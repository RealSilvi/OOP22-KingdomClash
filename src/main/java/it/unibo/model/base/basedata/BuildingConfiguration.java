package it.unibo.model.base.basedata;

/**
 * A data class that contains parameters that configure some actions.
 */
public final class BuildingConfiguration {
    /**
     * Creates a configuration for buildings with default values.
     */
    public BuildingConfiguration() {
        fallbackConfiguration();
    }

    private int maxLevel;
    private int maxBuildings;
    private int refundTaxPercentage;
    private int upgradeTaxPercentage;
    private int productionMultiplierPercentage;
    private int productionTimeReductionPercentage;

    /**
     * @return  The max level that the buildings can reach
     */
    public int getMaxLevel() {
        return maxLevel;
    }
    /**
     * @return  The maximum number of buildings that the player can build
     */
    public int getMaxBuildings() {
        return maxBuildings;
    }
    /**
     * @return  The percentage of resources that will be returned when
     *          demolishing a building
     */
    public int getRefundTaxPercentage() {
        return refundTaxPercentage;
    }
    /**
     * @return  A percentage that represent by how much the cost of the building
     *          increasaes by every level
     */
    public int getUpgradeTaxPercentage() {
        return upgradeTaxPercentage;
    }
    /**
     * @return  A percentage that represents by how much the resources produced
     *          increase by every level
     */
    public int getProductionIncrementPercentage() {
        return productionMultiplierPercentage;
    }
    /**
     * @return  A percentage that represents by how much the time that it takes
     *          to produce some resources decreases by every level
     */
    public int getProductionTimeReductionPercentage() {
        return productionTimeReductionPercentage;
    }

    private void fallbackConfiguration() {
        this.maxLevel = Building.MAXLEVEL;
        this.maxBuildings = Building.MAXBUILDINGS;
        this.refundTaxPercentage = Building.REFUND_TAX_PERCENTAGE;
        this.upgradeTaxPercentage = Building.UPGRADE_TAX_PERCENTAGE;
        this.productionMultiplierPercentage = Building.PRODUCTION_MULTIPLIER_PERCENTAGE;
        this.productionTimeReductionPercentage = Building.PRODUCTION_TIME_REDUCITON_PERCENTAGE;
    }
}
