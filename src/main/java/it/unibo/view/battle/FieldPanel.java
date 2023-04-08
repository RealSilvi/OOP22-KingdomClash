package it.unibo.view.battle;

import javax.swing.*;
import java.awt.*;

public class FieldPanel extends JPanel {

    private final Image backgroundImage;

    public FieldPanel(Dimension screenSize) {
        this.backgroundImage = new ImageIcon(
                "src/main/resources/it/unibo/icons/battle/battleCenter.png"
        ).getImage();

        this.setOpaque(false);

        //this.setPreferredSize(new Dimension((int)(screenSize.getWidth() * 0.6), (int)(screenSize.getHeight() * 0.6)));
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
}
