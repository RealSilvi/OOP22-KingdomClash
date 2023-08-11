package it.unibo.view.utilities;


import javax.annotation.Nonnull;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.util.Objects;

/**
 * This interface is used to load images in static way correctly.
 */
public interface ImageIconsSupplier {
    //TODO put a default image

    /**
     * Load the image.
     * @param pathToFile the path to the image.
     * @return the image.
     */
    @Nonnull
    static ImageIcon loadImageIcon(final String pathToFile) {
        try {
            return new ImageIcon(ImageIO.read(Objects.requireNonNull(ImageIconsSupplier.class.getResource(pathToFile))));
        } catch (NullPointerException | IOException exception) {
            return new ImageIcon();
        }
    }

    /**
     * Load the image.
     * @param pathToFile the path to the image.
     * @return the image.
     */
    @Nonnull
    static Image loadImage(final String pathToFile) {
        try {
            return ImageIO.read(Objects.requireNonNull(ImageIconsSupplier.class.getResource(pathToFile)));
        } catch (NullPointerException | IOException exception) {
            return new ImageIcon().getImage();
        }
    }


    /**
     * Load the image and resize it.
     * @param pathToFile he path to the image.
     * @param size the size required.
     * @return the image.
     */
    static ImageIcon getScaledImageIcon(String pathToFile, final Dimension size) {
        try {
            return new ImageIcon(ImageIO.read(Objects.requireNonNull(ImageIconsSupplier.class.getResource(pathToFile))).
                getScaledInstance(size.width, size.height, Image.SCALE_DEFAULT));
        } catch (NullPointerException | IOException exception) {
            return new ImageIcon();
        }
    }

}
