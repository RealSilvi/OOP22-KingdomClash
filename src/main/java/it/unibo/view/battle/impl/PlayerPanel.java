package it.unibo.view.battle.impl;

import it.unibo.view.battle.api.BattleGuiPanels;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class PlayerPanel extends JPanel implements BattleGuiPanels {

    private final Image backgroundImage;

    public PlayerPanel(Dimension screenSize) {
        this.backgroundImage = new ImageIcon(
                "src/main/resources/it/unibo/icons/battle/battleUpDown.png"
        ).getImage();

        this.setOpaque(false);

        this.setPreferredSize(new Dimension((int)(screenSize.getWidth()), (int)(screenSize.getHeight() * 0.2)));
    }


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
