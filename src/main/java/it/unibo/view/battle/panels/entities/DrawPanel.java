package it.unibo.view.battle.panels.entities;

import javax.swing.*;
import java.awt.*;

/**
 *  A JPanel with a backgroundImage.
 */
public class DrawPanel extends JPanel {

    private static final int WIDHT_IMAGE_FILL_PATTERN=200;
    private static final int HEIGHT_IMAGE_FILL_PATTERN=200;
    private static final int HGAP=20;
    private static final int VGAP=10;

    private final Image backgroundImage;
    private final Dimension size;

    /**
     * @param backgroundImage The image to set as background.
     */
    public DrawPanel(final Image backgroundImage, final Dimension size) {
        this.backgroundImage= backgroundImage.getScaledInstance(WIDHT_IMAGE_FILL_PATTERN,HEIGHT_IMAGE_FILL_PATTERN,Image.SCALE_DEFAULT);
        this.size= size;

        this.setLayout(new FlowLayout(FlowLayout.CENTER,HGAP,VGAP));
        this.setPreferredSize(size);
    }

    /**
     *
     * @param backgroundImageIcon The ImageIcon to set as background.
     */
    public DrawPanel(final ImageIcon backgroundImageIcon,final Dimension size){
        this(backgroundImageIcon.getImage(),size);
    }

    /**
     *
     * @param backgroundUrl The url of the image to set as background.
     */
    public DrawPanel(final String backgroundUrl, final Dimension size){
        this(new ImageIcon(backgroundUrl),size);
    }

    public DrawPanel(final Color color,final Dimension size){
        this.backgroundImage=null;
        this.size=size;

        this.setPreferredSize(size);
        this.setBackground(color);
    }

    public DrawPanel(final Dimension size){
        this(Color.white,size);
    }



    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        for (int y = 0; y < this.size.height; y += HEIGHT_IMAGE_FILL_PATTERN) {
            for (int x = 0; x < this.size.width; x += WIDHT_IMAGE_FILL_PATTERN) {
                g2d.drawImage(backgroundImage, x, y, this);
            }
        }
        g2d.dispose();
    }
}
