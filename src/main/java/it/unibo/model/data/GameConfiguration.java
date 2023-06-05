package it.unibo.model.data;

import it.unibo.model.base.basedata.BuildingConfiguration;
import it.unibo.view.battle.config.BattlePanelConfiguration;

public class GameConfiguration {
    private final BuildingConfiguration buildingConfig;

    private final BattlePanelConfiguration battleControllerConfiguration;

    public GameConfiguration() {
        this.buildingConfig = new BuildingConfiguration();
        this.battleControllerConfiguration= new BattlePanelConfiguration();
    }



    public BuildingConfiguration getBuildingConfig() {
        return buildingConfig;
    }

    public BattlePanelConfiguration getBattleControllerConfiguration() {
        return battleControllerConfiguration;
    }
}