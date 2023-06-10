package it.unibo.view.battle.panels.entities.impl;

import it.unibo.model.data.TroopType;
import it.unibo.view.battle.panels.entities.api.TroopLabel;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;

import javax.swing.*;
import java.awt.*;

public class TroopLabelImpl extends JLabel implements TroopLabel {

    private final Dimension size;

    /**
     * @param troop The troop to display.
     * @param size  The size of the JLabel.
     */
    public TroopLabelImpl(final TroopType troop, final Dimension size) {
        super(ImageIconsSupplier.getImageIconFromTroop(troop, size));
        this.size = size;
        this.setPreferredSize(this.size);

    }

    /**
     * Create an empty label.
     *
     * @param size The dimension of the JLabel.
     */
    public TroopLabelImpl(final Dimension size) {
        super();
        this.size = size;
    }

    @Override
    public void setEmpty() {
        this.setIcon(null);
    }

//    @Override
//    public void setTroop(final TroopType troop) {
//        this.setIcon(ImageIconsSupplier.getImageIconFromTroop(troop, this.size));
//    }


    public void setTroop(final TroopType troop,int delay) {
        Timer timer = new Timer(delay, e -> this.setIcon(ImageIconsSupplier.getImageIconFromTroop(troop, this.size)));
        timer.setRepeats(false);
        timer.start();

    }
}
