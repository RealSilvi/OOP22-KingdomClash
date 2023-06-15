package it.unibo.view.utilities;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * A small interface with some utilities to simplify operations with the GUI.
 */
public interface GraphicUtils {
    /**
     * Resizes image to a given size.
     * @param image     image to resize
     * @param width     width of the image
     * @param height    height of the image
     * @return          resized image
     */
    //The assignment is necessary to avoid an exception
    @SuppressWarnings("java:S1488")
    public static Image resizeImage(final Image image, final int width, final int height) {
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
        Image ovelrayImageTemp = overlayImage;

        int backgroundWidth = backgroundImage.getWidth(null);
        int backgroundHeight = backgroundImage.getHeight(null);


        ovelrayImageTemp = resizeImageWithProportion(ovelrayImageTemp,
            backgroundWidth, backgroundHeight);

        int overlayWidth = ovelrayImageTemp.getWidth(null);
        int overlayHeight = ovelrayImageTemp.getHeight(null);

        int xPosition = (backgroundWidth - overlayWidth) / 2;
        int yPosition = (backgroundHeight - overlayHeight) / 2;

        BufferedImage overlaidImages = new BufferedImage(
            backgroundWidth, backgroundHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics = overlaidImages.createGraphics();
        graphics.drawImage(backgroundImage, 0, 0, null);
        graphics.drawImage(ovelrayImageTemp, xPosition, yPosition, null);
        graphics.dispose();

        return overlaidImages;
    }

    /**
     * Applies a color filter to a given image.
     * @param img           the image to apply the color filter
     * @param colorFilter   the color filter to apply
     * @return              an image with the applied color filter
     */
    public static Image applyColorFilterToImage(Image img, Color colorFilter) {
        int width = img.getWidth(null);
        int height = img.getHeight(null);

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        g2d.drawImage(img, 0, 0, null);
        g2d.setComposite(AlphaComposite.SrcAtop);
        g2d.setColor(colorFilter);
        g2d.fillRect(0, 0, width, height);
        g2d.dispose();

        return bufferedImage;
    }
}