package it.unibo.view.battle.panels.entities.impl;

import javax.swing.*;
import java.awt.*;

/**
 *  A JPanel with a backgroundImage.
 */
public class DrawPanel extends JPanel {

    private final Image backgroundImage;

    /**
     * @param backgroundImage The image to set as background.
     */
    public DrawPanel(Image backgroundImage) {
        this.backgroundImage= backgroundImage;
    }

    /**
     *
     * @param backgroundImageIcon The ImageIcon to set as background.
     */
    public DrawPanel(ImageIcon backgroundImageIcon){
        this(backgroundImageIcon.getImage());
    }

    /**
     *
     * @param backgroundUrl The url of the image to set as background.
     */
    public DrawPanel(String backgroundUrl){
        this(new ImageIcon(backgroundUrl));
    }

    public DrawPanel(Color color){
        this.backgroundImage=null;
        this.setBackground(color);
    }

    public DrawPanel(){
        this(Color.white);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.backgroundImage, 0, 0, this);
    }
}
