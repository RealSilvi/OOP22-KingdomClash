package it.unibo.view.battle.panels.impl;

import it.unibo.model.data.TroopType;
import it.unibo.kingdomclash.config.PathIconsConfiguration;
import it.unibo.view.battle.panels.api.InfoPanel;
import it.unibo.view.battle.panels.entities.DrawPanelImpl;
import it.unibo.view.battle.panels.entities.impl.TroopLabelImpl;
import it.unibo.view.utilities.ImageIconsSupplier;
import it.unibo.view.battle.panels.PanelDimensions;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Map;

public final class InfoPanelImpl implements InfoPanel {

    private static final int GRID_LAYOUT_COLS = 3;
    private static final double LABEL_SCALE = 0.3;
    private static final Dimension LABEL_DIMENSION = new Dimension(
            (int) (PanelDimensions.getSideLifePanel().getHeight() * LABEL_SCALE),
            (int) (PanelDimensions.getSideLifePanel().getHeight() * LABEL_SCALE));

    private final JPanel mainPanel;
    private final PathIconsConfiguration pathIconsConfiguration;

    /**
     * Constructs an instance of InfoPanel.
     *
     * @param nrOfTroops             set the number of troops to display.
     * @param pathIconsConfiguration where are defined the paths of the textures.
     */
    public InfoPanelImpl(final int nrOfTroops, final PathIconsConfiguration pathIconsConfiguration) {
        this.pathIconsConfiguration = pathIconsConfiguration;
        this.mainPanel = new DrawPanelImpl(ImageIconsSupplier.loadImage(pathIconsConfiguration.getBackgroundFillPattern()),
                PanelDimensions.getSidePanel());

        this.mainPanel.setLayout(new GridLayout(nrOfTroops, GRID_LAYOUT_COLS));
    }

    @Override
    public void drawTable(final Map<TroopType, Boolean> powerTable) {
        this.mainPanel.removeAll();
        powerTable.forEach((troop, lv) -> {
            this.mainPanel.add(new TroopLabelImpl(troop, LABEL_DIMENSION, this.pathIconsConfiguration));
            this.mainPanel.add(new JLabel(ImageIconsSupplier.getScaledImageIcon(pathIconsConfiguration.getIndicator(),
                    LABEL_DIMENSION)));

            if (Boolean.TRUE.equals(lv)) {
                this.mainPanel.add(new JLabel(ImageIconsSupplier.getScaledImageIcon(pathIconsConfiguration.getCheck(),
                        LABEL_DIMENSION)));
            } else {
                this.mainPanel.add(new JLabel(ImageIconsSupplier.getScaledImageIcon(pathIconsConfiguration.getX(),
                        LABEL_DIMENSION)));
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

}
