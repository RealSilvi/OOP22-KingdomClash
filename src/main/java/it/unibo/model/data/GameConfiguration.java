package it.unibo.model.data;

import it.unibo.model.base.basedata.BaseConfiguration;
import it.unibo.model.base.basedata.BuildingConfiguration;
import it.unibo.view.battle.config.BattlePanelConfiguration;
import it.unibo.view.city.panels.impl.CityConfiguration;
import it.unibo.view.map.mapdata.MapConfiguration;

/**
 * Configuration of the game.
 */
public class GameConfiguration {
    private final BuildingConfiguration buildingConfiguration;
    private final BaseConfiguration baseConfiguration;
    private final CityConfiguration cityConfiguration;

    private final BattlePanelConfiguration battleControllerConfiguration;

    private final MapConfiguration mapConfiguration;

    /**
     * Construct a configuration with basic values.
     */
    public GameConfiguration() {
        this.buildingConfiguration = new BuildingConfiguration();
        this.baseConfiguration = new BaseConfiguration();
        this.cityConfiguration = new CityConfiguration();

        this.battleControllerConfiguration = new BattlePanelConfiguration();

        this.mapConfiguration = new MapConfiguration();
    }

    /**
     * @return configuration for the buildings of the game.
     */
    public BuildingConfiguration getBuildingConfig() {
        return buildingConfiguration;
    }
    /**
     * @return configuration for the battle controller part of the game.
     */
    public BattlePanelConfiguration getBattleControllerConfiguration() {
        return battleControllerConfiguration;
    }
    /**
     * @return configuration for the map view of the game.
     */
    public MapConfiguration getMapConfiguration() {
        return mapConfiguration;
    }
    /**
     * @return generic configuration for the base part of the game
     */
    public BaseConfiguration getBaseConfiguration() {
        return baseConfiguration;
    }
    /**
     * 
     * @return configuration for the city view of the game
     */
    public CityConfiguration getCityConfiguration() {
        return cityConfiguration;
    }
}
