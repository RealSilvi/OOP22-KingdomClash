package it.unibo.view.battle.config;

import it.unibo.model.base.internal.BuildingBuilder;
import it.unibo.model.data.TroopType;

import java.util.Map;

public class PathIconsConfiguration {

    private final String texturesDirectory;
    private final String troopsDirectory;
    private final String labelDirectory;
    private final String buttonsDirectory;
    private final String cityDirectory;
    private final String buildingDirectory;

    private final Map<TroopType, String> troops;
    private final Map<BuildingBuilder.BuildingTypes, Map<Integer, String>> buildings;
    private final String backgroundFillPattern;
    private final String backgroundCity;
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
        this.texturesDirectory = "/it/unibo/textures/battle/";
        this.troopsDirectory = texturesDirectory + "troops/";
        this.labelDirectory = texturesDirectory + "labels/";
        this.buttonsDirectory = texturesDirectory + "buttons/";
        this.cityDirectory = texturesDirectory + "city/"; 
        this.buildingDirectory = cityDirectory + "buildings/";
        this.backgroundFillPattern = texturesDirectory + "Background.png";
        this.backgroundCity = cityDirectory + "grass.png";
        this.pass = buttonsDirectory + "Pass.png";
        this.spin = buttonsDirectory + "Spin.png";
        this.info = buttonsDirectory + "Info.png";
        this.exit = buttonsDirectory + "Exit.png";
        this.check = labelDirectory + "Check.png";
        this.x = labelDirectory + "X.png";
        this.indicator = labelDirectory + "Indicator.png";
        this.life = labelDirectory + "Life.png";
        this.death = labelDirectory + "Death.png";
        this.troops = Map.of(
                TroopType.AXE, troopsDirectory + "Axe.png",
                TroopType.SWORD, troopsDirectory + "Sword.png",
                TroopType.HAMMER, troopsDirectory + "Hammer.png",
                TroopType.MACE, troopsDirectory + "Mace.png",
                TroopType.AXE_DEFENCE, troopsDirectory + "Shield01.png",
                TroopType.SWORD_DEFENCE, troopsDirectory + "Shield02.png",
                TroopType.HAMMER_DEFENCE, troopsDirectory + "Shield03.png",
                TroopType.MACE_DEFENCE, troopsDirectory + "Helmet.png");
        this.buildings = Map.of(
            BuildingBuilder.BuildingTypes.FARM, Map.of(
            1, buildingDirectory+ "farm1.png",
            2, buildingDirectory+ "farm2.png",
            3, buildingDirectory+ "farm3.png"),
            BuildingBuilder.BuildingTypes.HALL, Map.of(
            1, buildingDirectory+ "hall_1.png",
            2, buildingDirectory+ "hall_2.png",
            3, buildingDirectory+ "hall_3.png"
            ),
            BuildingBuilder.BuildingTypes.LUMBERJACK, Map.of(
            1, buildingDirectory+ "lumberjack1.png",
            2, buildingDirectory+ "lumberjack2.png",
            3, buildingDirectory+ "lumberjack3.png"
            )
        );
                
    }

    public String getTexturesDirectory() {
        return this.texturesDirectory;
    }

    public String getTroopsDirectory() {
        return this.troopsDirectory;
    }

    public String getLabelDirectory() {
        return this.labelDirectory;
    }

    public String getButtonsDirectory() {
        return this.buttonsDirectory;
    }

    public String getBackgroundFillPattern() {
        return this.backgroundFillPattern;
    }

    public String getBackgroundCity() {
        return this.backgroundCity;
    }

    public String getPass() {
        return this.pass;
    }

    public String getSpin() {
        return this.spin;
    }

    public String getInfo() {
        return this.info;
    }

    public String getExit() {
        return this.exit;
    }

    public String getCheck() {
        return this.check;
    }

    public String getX() {
        return this.x;
    }

    public String getIndicator() {
        return this.indicator;
    }

    public String getLife(boolean alive) {
        return (alive) ? this.life : this.death;
    }

    public String getTroop(TroopType troop) {
        return this.troops.get(troop);
    }

    public String getBuilding(BuildingBuilder.BuildingTypes type, Integer level) {
        return (level>3 || level<1)? 
        this.buildings.get(type).get(1) :
        this.buildings.get(type).get(level) ;
    }
}
