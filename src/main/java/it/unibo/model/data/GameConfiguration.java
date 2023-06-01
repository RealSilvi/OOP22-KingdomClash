package it.unibo.model.data;

import it.unibo.model.base.basedata.BuildingConfiguration;

public class GameConfiguration {
    private BuildingConfiguration buildingConfig;

    public GameConfiguration(BuildingConfiguration buildingConfig) {
        this.buildingConfig = buildingConfig;
    }

    public BuildingConfiguration getBuildingConfig() {
        return buildingConfig;
    }
}