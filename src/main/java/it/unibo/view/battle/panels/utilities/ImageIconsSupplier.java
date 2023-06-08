package it.unibo.view.battle.panels.utilities;

import it.unibo.model.data.TroopType;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Map;

public interface ImageIconsSupplier {

    String iconsDirectory = System.getProperty("user.dir")
            + File.separator + "src"
            + File.separator + "main"
            + File.separator + "resources"
            + File.separator + "it"
            + File.separator + "unibo"
            + File.separator + "textures"
            + File.separator + "battle"
            + File.separator;

    String troopsDirectory = iconsDirectory + "troops" + File.separator;

    String labelDirectory = iconsDirectory + "labels" + File.separator;
    String buttonsDirectory = iconsDirectory + "buttons" + File.separator;

    ImageIcon BACKGROUND_FILL_PATTERN = new ImageIcon(iconsDirectory + "Background.png");

    Map<TroopType, String> troopUrl = Map.of(
            TroopType.AXE, troopsDirectory+"Axe.png",
            TroopType.SWORD,   troopsDirectory+"Sword.png",
            TroopType.HAMMER,  troopsDirectory +"Hammer.png",
            TroopType.MACE,   troopsDirectory+"Mace.png",
            TroopType.AXE_DEFENCE,  troopsDirectory +"Shield01.png",
            TroopType.SWORD_DEFENCE,  troopsDirectory +"Shield02.png",
            TroopType.HAMMER_DEFENCE, troopsDirectory  +"Shield03.png",
            TroopType.MACE_DEFENCE,   troopsDirectory+"Helmet.png");

    ImageIcon PASS = new ImageIcon( buttonsDirectory +"Pass.png");
    ImageIcon SPIN = new ImageIcon( buttonsDirectory +"Spin.png");
    ImageIcon INFO = new ImageIcon( buttonsDirectory +"Info.png");
    ImageIcon EXIT = new ImageIcon( buttonsDirectory +"Exit.png");

    ImageIcon CHECK = new ImageIcon( labelDirectory +"Check.png");
    ImageIcon X = new ImageIcon( labelDirectory +"X.png");
    ImageIcon INDICATOR = new ImageIcon( labelDirectory +"Indicator.png");

    ImageIcon LIFE = new ImageIcon( labelDirectory +"Life.png");
    ImageIcon DEATH = new ImageIcon( labelDirectory +"Death.png");


    static ImageIcon getImageIconFromTroop(final TroopType troop, Dimension size) {
        return new ImageIcon(getImageFromTroop(troop, size));
    }

    static ImageIcon getImageIconFromTroop(final TroopType troop) {
        return new ImageIcon(troopUrl.get(troop));
    }

    static Image getImageFromTroop(final TroopType troop, Dimension size) {
        return getImageIconFromTroop(troop).getImage().getScaledInstance(size.width, size.height, Image.SCALE_DEFAULT);

    }

    static ImageIcon getImageIconLife(final boolean alive, final Dimension size) {
        return (alive) ?
                new ImageIcon(LIFE.getImage().getScaledInstance(size.width, size.height, Image.SCALE_DEFAULT)) :
                new ImageIcon(DEATH.getImage().getScaledInstance(size.width, size.height, Image.SCALE_DEFAULT));
    }

    static ImageIcon getImageIconPass(Dimension size) {
        return new ImageIcon(PASS.getImage().getScaledInstance(size.width, size.height, Image.SCALE_DEFAULT));
    }

    static ImageIcon getImageIconSpin(Dimension size) {
        return new ImageIcon(SPIN.getImage().getScaledInstance(size.width, size.height, Image.SCALE_DEFAULT));
    }

    static ImageIcon getImageIconX(Dimension size) {
        return new ImageIcon(X.getImage().getScaledInstance(size.width, size.height, Image.SCALE_DEFAULT));
    }

    static ImageIcon getImageIconInfo(Dimension size) {
        return new ImageIcon(INFO.getImage().getScaledInstance(size.width, size.height, Image.SCALE_DEFAULT));
    }

    static ImageIcon getImageIconCheck(Dimension size) {
        return new ImageIcon(CHECK.getImage().getScaledInstance(size.width, size.height, Image.SCALE_DEFAULT));
    }

    static ImageIcon getImageIconIndicator(Dimension size) {
        return new ImageIcon(INDICATOR.getImage().getScaledInstance(size.width, size.height, Image.SCALE_DEFAULT));
    }

    static ImageIcon getImageExitIndicator(Dimension size) {
        return new ImageIcon(EXIT.getImage().getScaledInstance(size.width, size.height, Image.SCALE_DEFAULT));
    }

}
