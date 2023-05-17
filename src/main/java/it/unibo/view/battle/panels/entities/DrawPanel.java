package it.unibo.view.battle.panels.entities;

import javax.swing.*;
import java.awt.*;

/**
 *  A JPanel with a backgroundImage.
 */
public class DrawPanel extends JPanel {

    private static final int HGAP=20;
    private static final int VGAP=10;

    private final Image backgroundImage;

    /**
     * @param backgroundImage The image to set as background.
     */
    public DrawPanel(final Image backgroundImage) {

        this.setLayout(new FlowLayout(FlowLayout.CENTER,HGAP,VGAP));
        this.backgroundImage= backgroundImage;
    }

    /**
     *
     * @param backgroundImageIcon The ImageIcon to set as background.
     */
    public DrawPanel(final ImageIcon backgroundImageIcon){
        this(backgroundImageIcon.getImage());
    }

    /**
     *
     * @param backgroundUrl The url of the image to set as background.
     */
    public DrawPanel(final String backgroundUrl){
        this(new ImageIcon(backgroundUrl));
    }

    public DrawPanel(final Color color){
        this.backgroundImage=null;
        this.setBackground(color);
    }

    public DrawPanel(){
        this(Color.white);
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.backgroundImage, 0, 0, this);
    }
}
