package it.unibo.view.battle.panels.utilities;

import it.unibo.model.data.TroopType;

import javax.annotation.Nonnull;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Objects;

public interface ImageIconsSupplier {

    String IMAGE_EXTENSION = ".png";

    String TEXTURES_DIRECTORY = "/it/unibo/textures/battle/";

    String TROOPS_DIRECTORY = TEXTURES_DIRECTORY + "troops/";
    String LABEL_DIRECTORY = TEXTURES_DIRECTORY + "labels/";
    String BUTTONS_DIRECTORY = TEXTURES_DIRECTORY + "buttons/";

    Map<TroopType, String> TROOP_FILES_NAME = Map.of(
            TroopType.AXE, TROOPS_DIRECTORY + "Axe",
            TroopType.SWORD, TROOPS_DIRECTORY + "Sword",
            TroopType.HAMMER, TROOPS_DIRECTORY + "Hammer",
            TroopType.MACE, TROOPS_DIRECTORY + "Mace",
            TroopType.AXE_DEFENCE, TROOPS_DIRECTORY + "Shield01",
            TroopType.SWORD_DEFENCE, TROOPS_DIRECTORY + "Shield02",
            TroopType.HAMMER_DEFENCE, TROOPS_DIRECTORY + "Shield03",
            TroopType.MACE_DEFENCE, TROOPS_DIRECTORY + "Helmet");

    Image BACKGROUND_FILL_PATTERN = loadPngImage(TEXTURES_DIRECTORY + "Background");

    Image PASS = loadPngImage(BUTTONS_DIRECTORY + "Pass");
    Image SPIN = loadPngImage(BUTTONS_DIRECTORY + "Spin");
    Image INFO = loadPngImage(BUTTONS_DIRECTORY + "Info");
    Image EXIT = loadPngImage(BUTTONS_DIRECTORY + "Exit");

    Image CHECK = loadPngImage(LABEL_DIRECTORY + "Check");
    Image X = loadPngImage(LABEL_DIRECTORY + "X");
    Image INDICATOR = loadPngImage(LABEL_DIRECTORY + "Indicator");

    Image LIFE = loadPngImage(LABEL_DIRECTORY + "Life");
    Image DEATH = loadPngImage(LABEL_DIRECTORY + "Death");

    @Nonnull
    static Image loadPngImage(final String pathToFile) {
        try {
            return ImageIO.read(Objects.requireNonNull(ImageIconsSupplier.class.getResource(pathToFile + IMAGE_EXTENSION)));
        } catch (Exception e) {
            //TODO metti un imagine di default
            return new ImageIcon().getImage();
        }
    }

    static ImageIcon loadPngImageIcon(@Nonnull Image image, final Dimension size) {
        return new ImageIcon(image.getScaledInstance(size.width, size.height, Image.SCALE_DEFAULT));
    }

    static ImageIcon getImageIconFromTroop(final TroopType troop, final Dimension size) {
        return loadPngImageIcon(
                loadPngImage(TROOP_FILES_NAME.get(troop)),
                size);
    }

    static ImageIcon getImageIconLife(final boolean alive, final Dimension size) {
        return (alive) ?
                loadPngImageIcon(LIFE,size) :
                loadPngImageIcon(DEATH,size);
    }

    static ImageIcon getImageIconPass(Dimension size) {
        return loadPngImageIcon(PASS,size);
    }

    static ImageIcon getImageIconSpin(Dimension size) {
        return loadPngImageIcon(SPIN,size);
    }

    static ImageIcon getImageIconX(Dimension size) {
        return loadPngImageIcon(X,size);
    }

    static ImageIcon getImageIconInfo(Dimension size) {
        return loadPngImageIcon(INFO,size);
    }

    static ImageIcon getImageIconCheck(Dimension size) {
        return loadPngImageIcon(CHECK,size);
    }

    static ImageIcon getImageIconIndicator(Dimension size) {
        return loadPngImageIcon(INDICATOR,size);
    }

    static ImageIcon getImageIconExit(Dimension size) {
        return loadPngImageIcon(EXIT,size);
    }

    static ImageIcon getImageIconBackground(Dimension size) {
        return loadPngImageIcon(BACKGROUND_FILL_PATTERN,size);
    }
}
