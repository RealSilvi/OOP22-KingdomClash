package it.unibo.view.battle.panels.entities.impl;

import it.unibo.view.battle.Troop;
import it.unibo.view.battle.panels.entities.api.TroopButton;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;

import javax.swing.*;
import java.awt.*;

public class TroopButtonImpl extends JButton implements TroopButton {

    private final Troop troop;
    private final Dimension size;
    private boolean status;

    public TroopButtonImpl(final Troop troop, final boolean status, final Dimension size) {
        this.troop=troop;
        this.status= status;
        this.size=size;

        this.setPreferredSize(size);

        this.setIcon(ImageIconsSupplier.getImageIconFromTroop(this.troop,this.status,this.size));
        this.setBackground(Color.BLACK);
        this.setOpaque(true);
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY,4,true));


    }

    @Override
    public void changeStatusImage(){
        this.status=!this.status;
        this.setIcon(ImageIconsSupplier.getImageIconFromTroop(this.troop,this.status,this.size));
    }

    @Override
    public Troop getTroop() {
        return this.troop;
    }

    @Override
    public void setEnabled(boolean b) {
        super.setEnabled(b);
        if(b){
            this.setBorder(BorderFactory.createLineBorder(new Color(249,158,24),4,true));
        }else{
            this.setBorder(BorderFactory.createLineBorder(Color.GRAY,4,true));
        }
    }
}

