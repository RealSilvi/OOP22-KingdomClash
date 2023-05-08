package it.unibo.view.battle.panels.entities.impl;

import it.unibo.view.battle.Troop;
import it.unibo.view.battle.panels.entities.api.TroopLabel;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;

import javax.swing.*;

public class TroopLabelImpl extends JLabel implements TroopLabel {

    public TroopLabelImpl(Troop troop) {
        super(ImageIconsSupplier.getImageIconFromTroop(troop,true));
    }

    public TroopLabelImpl() {
        super(ImageIconsSupplier.BACKGROUND_FREE_SPOT);
    }

    @Override
    public void setEmpty(){
        this.setIcon(ImageIconsSupplier.BACKGROUND_FREE_SPOT);
    }

    @Override
    public void setTroop(Troop troop){
        this.setIcon(ImageIconsSupplier.getImageIconFromTroop(troop,true));
    }
}
