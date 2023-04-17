package it.unibo.view.battle.panels.impl;

import it.unibo.view.battle.Troop;
import it.unibo.view.battle.panels.utilities.ImageIconEntitiesManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InfoPanelImpl extends JPanel implements it.unibo.view.battle.panels.api.InfoPanel {

    private final int NUMBER_OF_TROOPS = 8;

    private final Dimension preferredSize;
    private final Image backgroundImage;

    private final List<JLabel> table;

    public InfoPanelImpl(Dimension preferredSize) {
        this.table=new ArrayList<>();
        this.setLayout(new GridLayout(NUMBER_OF_TROOPS,3));

        this.preferredSize=preferredSize;

        this.backgroundImage = new ImageIcon(
                ImageIconEntitiesManager.BACKGROUND_SIDE_URL
        ).getImage();

        this.setOpaque(false);

        this.setPreferredSize(preferredSize);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image.
        g.drawImage(backgroundImage, 0, 0, this);
    }


    @Override
    public void drawTable(Map<Troop, Boolean> powerTable) {

    }
}
