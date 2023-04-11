package it.unibo.view.battle.impl;

import it.unibo.view.battle.api.BattleGuiPanels;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class InfoPanel extends JPanel implements BattleGuiPanels {
    private final Image backgroundImage;

    public InfoPanel(Dimension screenSize) {
        this.backgroundImage = new ImageIcon(
                "src/main/resources/it/unibo/icons/battle/battleSide.png"
        ).getImage();

        this.setOpaque(false);

        this.setPreferredSize(new Dimension((int)(screenSize.getWidth() * 0.2), (int)(screenSize.getHeight())));
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

    @Override
    public void restart() {

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
