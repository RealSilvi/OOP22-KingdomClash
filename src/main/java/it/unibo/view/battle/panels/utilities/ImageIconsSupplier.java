package it.unibo.view.battle.panels.utilities;

import it.unibo.model.data.TroopType;

import javax.annotation.Nonnull;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Objects;

public interface ImageIconsSupplier {

    String IMAGE_EXTENSION_PNG = ".png";
    String IMAGE_EXTENSION_JPG = ".jpg";

    String TEXTURES_DIRECTORY = "/it/unibo/textures/battle/";

    String TROOPS_DIRECTORY = TEXTURES_DIRECTORY + "troops/";
    String LABEL_DIRECTORY = TEXTURES_DIRECTORY + "labels/";
    String BUTTONS_DIRECTORY = TEXTURES_DIRECTORY + "buttons/";

    Map<TroopType, String> TROOP_FILES_NAME = Map.of(
            TroopType.AXE, "Axe",
            TroopType.SWORD, "Sword",
            TroopType.HAMMER, "Hammer",
            TroopType.MACE, "Mace",
            TroopType.AXE_DEFENCE, "Shield01",
            TroopType.SWORD_DEFENCE, "Shield02",
            TroopType.HAMMER_DEFENCE, "Shield03",
            TroopType.MACE_DEFENCE, "Helmet");

    Image BACKGROUND_FILL_PATTERN = loadPngImage(TEXTURES_DIRECTORY , "Background");

    Image PASS = loadPngImage(BUTTONS_DIRECTORY , "Pass");
    Image SPIN = loadPngImage(BUTTONS_DIRECTORY , "Spin");
    Image INFO = loadPngImage(BUTTONS_DIRECTORY , "Info");
    Image EXIT = loadPngImage(BUTTONS_DIRECTORY , "Exit");

    Image CHECK = loadPngImage(LABEL_DIRECTORY , "Check");
    Image X = loadPngImage(LABEL_DIRECTORY , "X");
    Image INDICATOR = loadPngImage(LABEL_DIRECTORY , "Indicator");

    Image LIFE = loadPngImage(LABEL_DIRECTORY , "Life");
    Image DEATH = loadPngImage(LABEL_DIRECTORY , "Death");

    @Nonnull
    static Image loadPngImage(final String pathToFileDirectory, String filename) {
        try {
            return ImageIO.read(Objects.requireNonNull(ImageIconsSupplier.class.getResource(pathToFileDirectory + filename + IMAGE_EXTENSION_PNG)));
        } catch (Exception e) {
            return new ImageIcon().getImage();
        }
    }

    @Nonnull
    static Image loadJpgImage(final String pathToFileDirectory, String filename) {
        try {
            return ImageIO.read(Objects.requireNonNull(ImageIconsSupplier.class.getResource(pathToFileDirectory + filename + IMAGE_EXTENSION_JPG)));
        } catch (Exception e) {
            return new ImageIcon().getImage();
        }
    }


    static ImageIcon getScaledImageIcon(@Nonnull Image image, final Dimension size) {
        return new ImageIcon(image.getScaledInstance(size.width, size.height, Image.SCALE_DEFAULT));
    }

    static ImageIcon getImageIconFromTroop(final TroopType troop, final Dimension size) {
        return getScaledImageIcon(
                loadPngImage(TROOPS_DIRECTORY,TROOP_FILES_NAME.get(troop)),
                size);
    }

    static ImageIcon getImageIconLife(final boolean alive, final Dimension size) {
        return (alive) ?
                getScaledImageIcon(LIFE,size) :
                getScaledImageIcon(DEATH,size);
    }

    static ImageIcon getImageIconPass(Dimension size) {
        return getScaledImageIcon(PASS,size);
    }

    static ImageIcon getImageIconSpin(Dimension size) {
        return getScaledImageIcon(SPIN,size);
    }

    static ImageIcon getImageIconX(Dimension size) {
        return getScaledImageIcon(X,size);
    }

    static ImageIcon getImageIconInfo(Dimension size) {
        return getScaledImageIcon(INFO,size);
    }

    static ImageIcon getImageIconCheck(Dimension size) {
        return getScaledImageIcon(CHECK,size);
    }

    static ImageIcon getImageIconIndicator(Dimension size) {
        return getScaledImageIcon(INDICATOR,size);
    }

    static ImageIcon getImageIconExit(Dimension size) {
        return getScaledImageIcon(EXIT,size);
    }

}
