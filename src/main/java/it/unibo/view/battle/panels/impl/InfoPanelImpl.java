package it.unibo.view.battle.panels.impl;

import it.unibo.model.data.TroopType;
import it.unibo.view.battle.config.PathIconsConfiguration;
import it.unibo.view.battle.panels.api.InfoPanel;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.battle.panels.entities.impl.TroopLabelImpl;
import it.unibo.view.utilities.ImageIconsSupplier;
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
    private final PathIconsConfiguration pathIconsConfiguration;

    /**
     * @param nrOfTroops how many troops are in total in the game
     */
    public InfoPanelImpl(final int nrOfTroops, final PathIconsConfiguration pathIconsConfiguration) {
        this.pathIconsConfiguration=pathIconsConfiguration;
        this.mainPanel = new DrawPanel(ImageIconsSupplier.loadImage(pathIconsConfiguration.getBackgroundFillPattern()),
                PanelDimensions.getSidePanel());

        this.mainPanel.setLayout(new GridLayout(nrOfTroops, 3));

    }

    @Override
    public void drawTable(final Map<TroopType, Boolean> powerTable) {
        powerTable.forEach((troop, lv) -> {
            this.mainPanel.add(new TroopLabelImpl(troop, LABEL_DIMENSION,this.pathIconsConfiguration));
            this.mainPanel.add(new JLabel(ImageIconsSupplier.getScaledImageIcon(pathIconsConfiguration.getIndicator(),LABEL_DIMENSION)));

            if (lv) {
                this.mainPanel.add(new JLabel(ImageIconsSupplier.getScaledImageIcon(pathIconsConfiguration.getCheck(),LABEL_DIMENSION)));
            } else {
                this.mainPanel.add(new JLabel(ImageIconsSupplier.getScaledImageIcon(pathIconsConfiguration.getX(),LABEL_DIMENSION)));
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

}
