package it.unibo.view.battle.panels.impl;

import it.unibo.model.data.TroopType;
import it.unibo.view.battle.panels.api.InfoPanel;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.battle.panels.entities.impl.TroopLabelImpl;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;
import it.unibo.view.battle.panels.utilities.PanelDimensions;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class InfoPanelImpl implements InfoPanel {

    private final static double LABEL_SCALE = 0.3;
    private final static Dimension LABEL_DIMENSION = new Dimension(
            (int) (PanelDimensions.getSideLifePanel().getHeight() * LABEL_SCALE),
            (int) (PanelDimensions.getSideLifePanel().getHeight() * LABEL_SCALE));

    private final JPanel mainPanel;

    /**
     * @param nrOfTroops how many troops are in total in the game
     */
    public InfoPanelImpl(final int nrOfTroops) {
        this.mainPanel = new DrawPanel(ImageIconsSupplier.BACKGROUND_FILL_PATTERN, PanelDimensions.getSidePanel());

        this.mainPanel.setLayout(new GridLayout(nrOfTroops, 3));

    }

    @Override
    public void drawTable(final Map<TroopType, Boolean> powerTable) {
        powerTable.forEach((troop, lv) -> {
            this.mainPanel.add(new TroopLabelImpl(troop, LABEL_DIMENSION));
            this.mainPanel.add(new JLabel(ImageIconsSupplier.getImageIconIndicator(LABEL_DIMENSION)));

            if (lv) {
                this.mainPanel.add(new JLabel(ImageIconsSupplier.getImageIconCheck(LABEL_DIMENSION)));
            } else {
                this.mainPanel.add(new JLabel(ImageIconsSupplier.getImageIconX(LABEL_DIMENSION)));
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

}
