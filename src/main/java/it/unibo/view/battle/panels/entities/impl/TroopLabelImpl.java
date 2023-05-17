package it.unibo.view.battle.panels.entities.impl;

import it.unibo.view.battle.Troop;
import it.unibo.view.battle.panels.entities.api.TroopLabel;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;

import javax.swing.*;
import java.awt.*;

public class TroopLabelImpl extends JLabel implements TroopLabel {

    private final Dimension size;

    public TroopLabelImpl(final Troop troop, final Dimension size) {
        super(ImageIconsSupplier.getImageIconFromTroop(troop,true,size));
        this.size=size;
        this.setPreferredSize(this.size);

    }

    public TroopLabelImpl(final Dimension size) {
        super(ImageIconsSupplier.BACKGROUND_FREE_SPOT);
        this.size = size;
    }

    @Override
    public void setEmpty(){
        this.setIcon(ImageIconsSupplier.BACKGROUND_FREE_SPOT);
        this.setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    public void setTroop(final Troop troop){
        this.setIcon(ImageIconsSupplier.getImageIconFromTroop(troop,true,this.size));
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY,2,true));
    }
}
