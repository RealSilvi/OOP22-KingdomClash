package it.unibo.view.battle.config;

import it.unibo.model.data.TroopType;

import java.util.Map;

public class PathIconsConfiguration {

    private final String texturesDirectory;
    private final String troopsDirectory;
    private final String labelDirectory;
    private final String buttonsDirectory;

    private final Map<TroopType, String> troops;

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
        this.texturesDirectory = "/it/unibo/textures/battle/";
        this.troopsDirectory = texturesDirectory + "troops/";
        this.labelDirectory = texturesDirectory + "labels/";
        this.buttonsDirectory = texturesDirectory + "buttons/";
        this.backgroundFillPattern = texturesDirectory + "Background.png";
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
}
