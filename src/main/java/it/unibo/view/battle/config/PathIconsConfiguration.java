package it.unibo.view.battle.config;

import it.unibo.model.base.internal.BuildingBuilder;
import it.unibo.model.data.TroopType;

import java.util.Map;

public class PathIconsConfiguration {

    private final String texturesDirectory;
    private final String cityDirectory;
    private final String buildingDirectory;
    private final String battleDirectory;
    private final String battleTroopsDirectory;
    private final String battleLabelDirectory;
    private final String battleButtonsDirectory;

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
        this.texturesDirectory = "/it/unibo/textures/";
        this.battleDirectory = texturesDirectory + "battle/";
        this.cityDirectory = texturesDirectory + "city/";
        this.battleTroopsDirectory = battleDirectory + "troops/";
        this.battleLabelDirectory = battleDirectory + "labels/";
        this.battleButtonsDirectory = battleDirectory + "buttons/";
        this.buildingDirectory = cityDirectory + "buildings/";

        this.backgroundFillPattern = battleDirectory + "Background.png";
        this.backgroundCity = cityDirectory + "grass.png";
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

    }

    public String getTexturesDirectory() {
        return this.texturesDirectory;
    }

    public String getBuildingDirectory() {
        return this.buildingDirectory;
    }

    public String getBattleDirectory() {
        return this.battleDirectory;
    }

    public String getBattleTroopsDirectory() {
        return this.battleTroopsDirectory;
    }

    public String getBattleLabelDirectory() {
        return this.battleLabelDirectory;
    }

    public String getBattleButtonsDirectory() {
        return this.battleButtonsDirectory;
    }

    public String getCityDirectory() {
        return this.cityDirectory;
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
        return (level > 3 || level < 1) ?
                this.buildings.get(type).get(1) :
                this.buildings.get(type).get(level);
    }
}
