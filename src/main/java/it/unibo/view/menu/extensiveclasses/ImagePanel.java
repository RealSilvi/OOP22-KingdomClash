package it.unibo.view.menu.extensiveclasses;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Graphics;

/**
 * This class is used to extend the JPanel class.
 * It helps in creating in a faster way a panel with specific image.
 */
@SuppressWarnings("serial")
public final class ImagePanel extends JPanel {
    /** The dimension of the screen.*/
    public static final Dimension DIMENSION_SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    private final Image backgroundImage;

    /**
     * The constructor sets the image of the panel.
     * @param backgroundImage The image to set.
     */
    public ImagePanel(final Image backgroundImage) {
        this.backgroundImage = backgroundImage.getScaledInstance((int) DIMENSION_SCREEN.getWidth(),
                (int) DIMENSION_SCREEN.getHeight(), Image.SCALE_DEFAULT);
    }

    @Override
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(backgroundImage, 0, 0, this);
    }

}
