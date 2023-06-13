package it.unibo.view.utilities;


import javax.annotation.Nonnull;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public interface ImageIconsSupplier {

    @Nonnull
    static ImageIcon loadImageIcon(final String pathToFile) {
        try {
            return new ImageIcon(ImageIO.read(Objects.requireNonNull(ImageIconsSupplier.class.getResource(pathToFile))));
        } catch (Exception e) {
            return new ImageIcon();
        }
    }

    @Nonnull
    static Image loadImage(final String pathToFile) {
        try {
            return ImageIO.read(Objects.requireNonNull(ImageIconsSupplier.class.getResource(pathToFile)));
        } catch (Exception e) {
            return new ImageIcon().getImage();
        }
    }


    static ImageIcon getScaledImageIcon(String pathToFile, final Dimension size) {
        try {
            return new ImageIcon(ImageIO.read(Objects.requireNonNull(ImageIconsSupplier.class.getResource(pathToFile))).getScaledInstance(size.width, size.height, Image.SCALE_DEFAULT));
        } catch (Exception e) {
            return new ImageIcon();
        }
    }

}
