package it.unibo.view.battle.panels.impl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InfoPanelImpl extends JPanel {

    private final int NUMBER_OF_TROOPS = 8;

    private final Dimension preferredSize;
    private final Image backgroundImage;

    private final List<JLabel> table;

    public InfoPanelImpl(Dimension preferredSize) {
        this.table=new ArrayList<>();
        this.setLayout(new GridLayout(NUMBER_OF_TROOPS,3));


        this.preferredSize=preferredSize;

        this.backgroundImage = new ImageIcon(
                "src/main/resources/it/unibo/icons/battle/battleSide.png"
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


    public void drowTable() {

    }
}
