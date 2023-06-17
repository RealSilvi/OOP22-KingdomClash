package it.unibo.kingdomclash.config;

import it.unibo.model.base.basedata.BaseConfiguration;
import it.unibo.model.base.basedata.BuildingConfiguration;
import it.unibo.kingdomclash.config.BattleConfiguration;
import it.unibo.kingdomclash.config.PathIconsConfiguration;
import it.unibo.view.city.panels.impl.CityConfiguration;
import it.unibo.view.map.mapdata.MapConfiguration;

/**
 * Configuration of the game.
 */
public class GameConfiguration {
    private final BuildingConfiguration buildingConfiguration;
    private final BaseConfiguration baseConfiguration;
    private final CityConfiguration cityConfiguration;

    private final BattleConfiguration battleConfiguration;
    private final PathIconsConfiguration pathIconsConfiguration;

    private final MapConfiguration mapConfiguration;

    /**
     * Construct a configuration with basic values.
     */
    public GameConfiguration() {
        this.buildingConfiguration = new BuildingConfiguration();
        this.baseConfiguration = new BaseConfiguration();
        this.cityConfiguration = new CityConfiguration();

        this.battleConfiguration = new BattleConfiguration();
        this.pathIconsConfiguration = new PathIconsConfiguration();

        this.mapConfiguration = new MapConfiguration();
    }

    /**
     * @return configuration for the paths of the icons to load.
     */
    public PathIconsConfiguration getPathIconsConfiguration(){
        return this.pathIconsConfiguration;
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
    public BattleConfiguration getBattleConfiguration() {
        return battleConfiguration;
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
     * @return configuration for the city view of the game
     */
    public CityConfiguration getCityConfiguration() {
        return cityConfiguration;
    }
}
