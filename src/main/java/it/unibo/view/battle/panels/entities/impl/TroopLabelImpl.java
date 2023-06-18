package it.unibo.view.battle.panels.entities.impl;

import it.unibo.model.data.TroopType;
import it.unibo.kingdomclash.config.PathIconsConfiguration;
import it.unibo.view.battle.panels.entities.api.TroopLabel;
import it.unibo.view.utilities.ImageIconsSupplier;

import javax.swing.JLabel;
import java.awt.Dimension;

/**
 * This class is not designed to handle serialization.
 */

@SuppressWarnings(value = "serial")
public class TroopLabelImpl extends JLabel implements TroopLabel {

    private final Dimension size;
    private final PathIconsConfiguration pathIconsConfiguration;

    /**
     * Constructs an instance of a TroopLabel defining the troop.
     *
     * @param troop                  the troop to set.
     * @param size                   the size of the label.
     * @param pathIconsConfiguration where are defined the paths of the textures.
     */
    public TroopLabelImpl(final TroopType troop,
                          final Dimension size,
                          final PathIconsConfiguration pathIconsConfiguration) {
        super(ImageIconsSupplier.getScaledImageIcon(pathIconsConfiguration.getTroop(troop), size));
        this.pathIconsConfiguration = pathIconsConfiguration;
        this.size = size;
        this.setPreferredSize(this.size);

    }

    /**
     * Constructs an instance of an empty TroopLabel.
     *
     * @param size                   the size of the label.
     * @param pathIconsConfiguration where are defined the paths of the textures.
     */
    public TroopLabelImpl(final Dimension size, final PathIconsConfiguration pathIconsConfiguration) {
        super();
        this.size = size;
        this.pathIconsConfiguration = pathIconsConfiguration;
    }


    @Override
    /* Passed null according to inherit javadoc */
    public void setEmpty() {
        this.setIcon(null);
    }

    @Override
    public void setTroop(final TroopType troop) {
        this.setIcon(ImageIconsSupplier.getScaledImageIcon(this.pathIconsConfiguration.getTroop(troop), size));
    }
}
