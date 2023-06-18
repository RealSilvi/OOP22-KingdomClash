package it.unibo.kingdomclash.config;

import it.unibo.model.base.internal.BuildingBuilder;
import it.unibo.model.data.TroopType;
import it.unibo.view.map.MapPanel;

import java.util.EnumMap;
import java.util.Map;

/**
 * Configuration of the icons paths.
 */
public class PathIconsConfiguration {

    /**
     * The path of the root directory of the icons.
     */
    public static final String TEXTURES_DIRECTORY = "/it/unibo/textures/";

    private final Map<TroopType, String> troops;
    private final Map<BuildingBuilder.BuildingTypes, Map<Integer, String>> buildings;
    private final Map<MapPanel.ButtonIdentification, String> imagePathMap;
    private final String backgroundFillPattern;
    private final String pass;
    private final String spin;
    private final String info;
    private final String exit;
    private final String check;
    private final String x;
    private final String indicator;
    private final String life;
    private final String death;

    public PathIconsConfiguration() {
        final String battleDirectory = TEXTURES_DIRECTORY + "battle/";
        final String mapDirectory = TEXTURES_DIRECTORY + "map/";
        final String cityDirectory = TEXTURES_DIRECTORY + "city/";
        final String battleTroopsDirectory = battleDirectory + "troops/";
        final String battleLabelDirectory = battleDirectory + "labels/";
        final String battleButtonsDirectory = battleDirectory + "buttons/";
        final String buildingDirectory = cityDirectory + "buildings/";

        this.backgroundFillPattern = battleDirectory + "Background.png";
        this.pass = battleButtonsDirectory + "Pass.png";
        this.spin = battleButtonsDirectory + "Spin.png";
        this.info = battleButtonsDirectory + "Info.png";
        this.exit = battleButtonsDirectory + "Exit.png";
        this.check = battleLabelDirectory + "Check.png";
        this.x = battleLabelDirectory + "X.png";
        this.indicator = battleLabelDirectory + "Indicator.png";
        this.life = battleLabelDirectory + "Life.png";
        this.death = battleLabelDirectory + "Death.png";
        this.troops = Map.of(
                TroopType.AXE, battleTroopsDirectory + "Axe.png",
                TroopType.SWORD, battleTroopsDirectory + "Sword.png",
                TroopType.HAMMER, battleTroopsDirectory + "Hammer.png",
                TroopType.MACE, battleTroopsDirectory + "Mace.png",
                TroopType.AXE_DEFENCE, battleTroopsDirectory + "Shield01.png",
                TroopType.SWORD_DEFENCE, battleTroopsDirectory + "Shield02.png",
                TroopType.HAMMER_DEFENCE, battleTroopsDirectory + "Shield03.png",
                TroopType.MACE_DEFENCE, battleTroopsDirectory + "Helmet.png");
        this.buildings = Map.of(
                BuildingBuilder.BuildingTypes.FARM, Map.of(
                        1, buildingDirectory + "farm1.png",
                        2, buildingDirectory + "farm2.png",
                        3, buildingDirectory + "farm3.png"),
                BuildingBuilder.BuildingTypes.HALL, Map.of(
                        1, buildingDirectory + "hall_1.png",
                        2, buildingDirectory + "hall_2.png",
                        3, buildingDirectory + "hall_3.png"
                ),
                BuildingBuilder.BuildingTypes.LUMBERJACK, Map.of(
                        1, buildingDirectory + "lumberjack1.png",
                        2, buildingDirectory + "lumberjack2.png",
                        3, buildingDirectory + "lumberjack3.png"
                )
        );
        this.imagePathMap = new EnumMap<>(MapPanel.ButtonIdentification.class);
        this.imagePathMap.put(MapPanel.ButtonIdentification.TILE,
                mapDirectory + "grass_01.png");
        this.imagePathMap.put(MapPanel.ButtonIdentification.PLAYER,
                mapDirectory + "city.png");
        this.imagePathMap.put(MapPanel.ButtonIdentification.ENEMY,
                mapDirectory + "enemy.png");
        this.imagePathMap.put(MapPanel.ButtonIdentification.DEATH,
                mapDirectory + "ruins.png");

    }

    /**
     * @return the path to the background fill pattern's texture of battle.
     */
    public String getBackgroundFillPattern() {
        return this.backgroundFillPattern;
    }

    /**
     * @return the path to the pass button's texture of battle.
     */
    public String getPass() {
        return this.pass;
    }

    /**
     * @return the path to the spin button's texture of battle.
     */
    public String getSpin() {
        return this.spin;
    }

    /**
     * @return the path to the info button's texture of battle.
     */
    public String getInfo() {
        return this.info;
    }
    /**
     * @return the path to the exit button's texture of battle.
     */
    public String getExit() {
        return this.exit;
    }
    /**
     * @return the path to the check button's texture of battle.
     */
    public String getCheck() {
        return this.check;
    }
    /**
     * @return the path to the X button's texture of battle.
     */
    public String getX() {
        return this.x;
    }
    /**
     * @return the path to the indicator button's texture of battle.
     */
    public String getIndicator() {
        return this.indicator;
    }

    /**
     * @param alive true for the life button's texture of the battle or
     *              false for the death button's texture of the battle.
     * @return the life or the death texture of the battle's button.
     */
    public String getLife(final boolean alive) {
        return alive ? this.life : this.death;
    }

    /**
     * @param troop indicates the required troop's texture.
     * @return the texture of the troop required.
     */
    public String getTroop(final TroopType troop) {
        return this.troops.get(troop);
    }

    /**
     * @return a map of image paths.
     */
    public Map<MapPanel.ButtonIdentification, String> getImageMap() {
        return new EnumMap<>(imagePathMap);
    }

    /**
     * @param type indicates the required building type's texture.
     * @param level indicates the level of the building.
     * @return the texture of the building required.
     */
    public String getBuilding(final BuildingBuilder.BuildingTypes type, final Integer level) {
        return level > 3 || level < 1 ?
                this.buildings.get(type).get(1) :
                this.buildings.get(type).get(level);
    }
}
