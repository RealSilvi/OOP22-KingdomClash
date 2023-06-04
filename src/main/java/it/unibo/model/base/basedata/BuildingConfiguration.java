package it.unibo.model.base.basedata;

import java.util.logging.Logger;

import it.unibo.view.battle.config.JSonToData;

public class BuildingConfiguration {
//    private Logger logger = Logger.getLogger(this.getClass().getName());

    public static final String BUILDING_CONF_PATH = "BaseModelConfig.BuildingConfiguration";

    public BuildingConfiguration(JSonToData dataJson) {
        try {
            this.maxLevel = Integer.parseInt(
                    dataJson.getProperty(BUILDING_CONF_PATH + ".MaxLevel"));
            this.maxBuildings = Integer.parseInt(
                    dataJson.getProperty(BUILDING_CONF_PATH + ".MaxBuildings"));
            this.refundTaxPercentage = Integer.parseInt(
                    dataJson.getProperty(BUILDING_CONF_PATH + ".RefundTaxPercentage"));
            this.upgradeTaxPercentage = Integer.parseInt(
                    dataJson.getProperty(BUILDING_CONF_PATH + ".UpgradeTaxPercentage"));
            this.productionMultiplierPercentage = Integer.parseInt(
                    dataJson.getProperty(BUILDING_CONF_PATH + ".ProductionIncrementPercentage"));
            this.productionTimeReductionPercentage = Integer.parseInt(
                    dataJson.getProperty(BUILDING_CONF_PATH + ".ProductionTimeReductionPercentage"));
        } catch (Exception e) {
//            logger.warning("Exception thrown when reading configuration!"
//                    + " Falling back to default configurations, printing stacktrace:\n" + e.toString());
            fallbackConfiguration();
        }
    }

    public BuildingConfiguration() {
        fallbackConfiguration();
    }

    private int maxLevel;
    private int maxBuildings;
    private int refundTaxPercentage;
    private int upgradeTaxPercentage;
    private int productionMultiplierPercentage;
    private int productionTimeReductionPercentage;

//    public Logger getLogger() {
//        return logger;
//    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getMaxBuildings() {
        return maxBuildings;
    }

    public int getRefundTaxPercentage() {
        return refundTaxPercentage;
    }

    public int getUpgradeTaxPercentage() {
        return upgradeTaxPercentage;
    }

    public int getProductionIncrementPercentage() {
        return productionMultiplierPercentage;
    }

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