package it.unibo.view.battle.panels.entities.impl;

import it.unibo.model.data.TroopType;
import it.unibo.kingdomclash.config.PathIconsConfiguration;
import it.unibo.view.battle.panels.entities.api.TroopLabel;
import it.unibo.view.utilities.ImageIconsSupplier;

import javax.swing.*;
import java.awt.*;

public class TroopLabelImpl extends JLabel implements TroopLabel {

    private final Dimension size;
    private final PathIconsConfiguration pathIconsConfiguration;

    /**
     * @param troop The troop to display.
     * @param size  The size of the JLabel.
     */
    public TroopLabelImpl(final TroopType troop, final Dimension size,final PathIconsConfiguration pathIconsConfiguration) {
        super(ImageIconsSupplier.getScaledImageIcon(pathIconsConfiguration.getTroop(troop),size));
        this.pathIconsConfiguration=pathIconsConfiguration;
        this.size = size;
        this.setPreferredSize(this.size);

    }

    /**
     * Create an empty label.
     *
     * @param size The dimension of the JLabel.
     */
    public TroopLabelImpl(final Dimension size,final PathIconsConfiguration pathIconsConfiguration) {
        super();
        this.size = size;
        this.pathIconsConfiguration=pathIconsConfiguration;
    }

    @Override
    //passed null according to the javadoc inherit
    public void setEmpty() {
        this.setIcon(null);
    }

    public void setTroop(final TroopType troop) {
        this.setIcon(ImageIconsSupplier.getScaledImageIcon(this.pathIconsConfiguration.getTroop(troop),size));
    }
}
