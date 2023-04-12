package it.unibo.view.battle.impl;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class FieldPanelImpl extends JPanel {

    private final Dimension preferredSize;
    private final Image backgroundImage;

    public FieldPanelImpl(Dimension preferredSize) {
        this.preferredSize=preferredSize;
        this.backgroundImage = new ImageIcon(
                "src/main/resources/it/unibo/icons/battle/battleCenter.png"
        ).getImage();

        this.setOpaque(false);

        this.setPreferredSize(preferredSize);
    }

    /**
     * @param g the <code>Graphics</code> object to protect
     *          The method is overwritten to start the panel with a background image
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image.
        g.drawImage(backgroundImage, 0, 0, this);
    }


    public void restart() {

    }


    public void update(Observable o, Object arg) {

    }
}
