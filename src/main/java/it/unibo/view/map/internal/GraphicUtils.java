package it.unibo.view.map.internal;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

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
    //The assignment is necessary
    @SuppressWarnings("java:S1488")
    public static Image resizeImage(Image image, int width, int height) {
        Image changedImage = width == 0 || height == 0 ? image :
            image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return changedImage;
    }
    /**
     * Resizes the image while mantaining the aspect ratio.
     * @param image     image to resize
     * @param width     new image width
     * @param height    new image height
     * @return          proportionally resized image
     */
    public static Image resizeImageWithProportion(final Image image,
        final int width, final int height) {
        int checkedWidth = width >= height ? width : -1; 
        int checkedHeight = height >= width ? height : -1;
        return image.getScaledInstance(checkedWidth, checkedHeight, Image.SCALE_SMOOTH);
    }

    /**
     * Overlays an image to the center of the original image.
     * @param backgroundImage   the original image or background
     * @param overlayImage      the image to overlay
     * @return                  the original image with an applied overlay
     */
    public static Image overlayImages(final Image backgroundImage,
        final Image overlayImage) {
        int x = (backgroundImage.getWidth(null)
            - overlayImage.getWidth(null)) / 2;
        int y = (backgroundImage.getHeight(null)
            - overlayImage.getHeight(null)) / 2;

        BufferedImage processedImages = new BufferedImage(
            backgroundImage.getWidth(null),
            backgroundImage.getHeight(null),
            BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = processedImages.createGraphics();
        g.drawImage(backgroundImage, 0, 0, null);
        g.drawImage(overlayImage, x, y, null);
        g.dispose();

        return processedImages;
    }
}
