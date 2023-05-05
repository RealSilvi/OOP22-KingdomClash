package it.unibo.view.battle.panels.entities.impl;

import it.unibo.view.battle.Troop;
import it.unibo.view.battle.panels.entities.api.TroopButton;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;

import javax.swing.*;

public class TroopButtonImpl extends JButton implements TroopButton {

    private final Troop troop;
    private boolean status;

    public TroopButtonImpl(final Troop troop, final boolean status) {
        this.troop=troop;
        this.status= status;

        this.setIcon(ImageIconsSupplier.getImageIconFromTroop(troop,status));
        this.setOpaque(false);
    }

    @Override
    public void changeStatusImage(){
        this.status=!this.status;
        this.setIcon(ImageIconsSupplier.getImageIconFromTroop(troop,status));
    }

    @Override
    public Troop getTroop() {
        return this.troop;
    }
}
