package it.unibo.view.battle.panels.entities.impl;

import it.unibo.view.battle.Troop;
import it.unibo.view.battle.panels.entities.api.TroopButton;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;

import javax.swing.*;
import java.awt.*;

public class TroopButtonImpl extends JButton implements TroopButton {

    private Troop troop;
    private final Dimension size;
    private boolean status;

    public TroopButtonImpl(final Troop troop, final boolean status, final Dimension size) {
        this.troop=troop;
        this.status= status;
        this.size=size;

        this.setPreferredSize(size);

        this.setIcon(ImageIconsSupplier.getImageIconFromTroop(this.troop,this.size));
        this.setDisabledIcon(ImageIconsSupplier.getImageIconFromTroop(this.troop,this.size));
        this.setBackground(Color.BLACK);
        this.setOpaque(true);
        this.setBorderStatus();
    }

    @Override
    public void changeStatusImage(){
        this.status=!this.status;
        this.setBorderStatus();
    }

    @Override
    public Troop getTroop() {
        return this.troop;
    }

    @Override
    public void setEnabled(boolean b) {
        super.setEnabled(b);
        this.setBorderStatus();
    }


    private void setBorderStatus(){
        if(this.isEnabled()){
            if(this.status){
                this.setBorder(BorderFactory.createLineBorder(ImageIconsSupplier.PRIMARY_COLOR,4,true));
            }else{
                this.setBorder(BorderFactory.createLineBorder(ImageIconsSupplier.SECONDARY_COLOR,4,true));
            }
        }else{
            this.setBorder(BorderFactory.createLineBorder(Color.GRAY,4,true));
        }
    }

    public void setTroop(Troop troop){
        this.troop=troop;
        this.setIcon(ImageIconsSupplier.getImageIconFromTroop(this.troop,this.size));
        this.setDisabledIcon(ImageIconsSupplier.getImageIconFromTroop(this.troop,this.size));
    }
}

