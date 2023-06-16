package it.unibo.view.battle.panels.entities;

import javax.swing.*;
import java.awt.*;

/**
 * Extends a JPanel defining the size and a background.<br>
 * The background could be a color or an image.
 * The background's image is created replicating the input
 * image for all over the size of the Panel.
 */
public class DrawPanel extends JPanel {

    //TODO think which serial to set
    static final long serialVersionUID = 42L;

    private static final int WIDTH_IMAGE_FILL_PATTERN = 200;
    private static final int HEIGHT_IMAGE_FILL_PATTERN = 200;
    private static final int H_GAP = 20;
    private static final int V_GAP = 10;

    private final Image backgroundImage;
    private final Dimension size;

    /**
     * @param backgroundImage The image to replicate as background.
     * @param size            The dimension of the Panel.
     */
    public DrawPanel(final Image backgroundImage, final Dimension size) {
        this.backgroundImage = backgroundImage.getScaledInstance(WIDTH_IMAGE_FILL_PATTERN, HEIGHT_IMAGE_FILL_PATTERN, Image.SCALE_DEFAULT);
        this.size = size;

        this.setLayout(new FlowLayout(FlowLayout.CENTER, H_GAP, V_GAP));
        this.setPreferredSize(size);
    }

    /**
     * @param backgroundImageIcon The ImageIcon to replicate as background.
     * @param size                The dimension of the Panel.
     */
    public DrawPanel(final ImageIcon backgroundImageIcon, final Dimension size) {
        this(backgroundImageIcon.getImage(), size);
    }

    /**
     * @param color The color to set as background.
     * @param size  The dimension of the Panel.
     */
    public DrawPanel(final Color color, final Dimension size) {
        this.backgroundImage = null;
        this.size = size;

        this.setPreferredSize(size);
        this.setBackground(color);
    }


    /**
     * Overwritten the paintComponent method to display a background
     * image, fruit of replicating an imagePattern passed at the new instance of
     * the class.
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g.create();
        for (int y = 0; y < this.size.height; y += HEIGHT_IMAGE_FILL_PATTERN) {
            for (int x = 0; x < this.size.width; x += WIDTH_IMAGE_FILL_PATTERN) {
                g2d.drawImage(backgroundImage, x, y, this);
            }
        }
        g2d.dispose();
    }
}
