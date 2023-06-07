package it.unibo.view.map.internal;

import java.awt.Image;

/**
 * A small interface with some utilities to simplify operations with the GUI
 */
public interface GraphicUtils {
    /**
     * Resizes image to a given size.
     * @param image     image to resize
     * @param width     width of the image
     * @param height    height of the image
     * @return          resized image
     */
    public static Image resizeImage(Image image, int width, int height) {
        return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }
    /**
     * Resizes the image while mantaining the aspect ratio.
     * @param image     image to resize
     * @param width     new image width
     * @param height    new image height
     * @return          proportionally resized image
     */
    public static Image resizeImageWithProportion(Image image, int width, int height) {
        int checkedWidth = width >= height ? width : -1; 
        int checkedHeight = height >= width ? height : -1;
        return image.getScaledInstance(checkedWidth, checkedHeight, Image.SCALE_SMOOTH);
    }
}
