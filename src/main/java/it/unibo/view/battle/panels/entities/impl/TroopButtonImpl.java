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

    /**
     *
     * @param troop the troop to set on this button
     * @param status the enable status of this button
     * @param size  the dimension of this button
     */
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

    private void setBorderStatus(){
        if(this.isEnabled()){
            if(this.status){
                this.setBorder(BorderFactory.createLineBorder(ImageIconsSupplier.PRIMARY_COLOR,4,true));
            }else{
                this.setBorder(BorderFactory.createLineBorder(ImageIconsSupplier.SECONDARY_COLOR,4,true));
            }
        }else{
            this.setBorder(BorderFactory.createLineBorder(ImageIconsSupplier.DEFAULT_COLOR,4,true));
        }
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
    public void setTroop(final Troop troop){
        this.troop=troop;
        this.setIcon(ImageIconsSupplier.getImageIconFromTroop(this.troop,this.size));
        this.setDisabledIcon(ImageIconsSupplier.getImageIconFromTroop(this.troop,this.size));
    }

    /**
     * Overwritten the method to change the border of the button
     * based on isEnable()
     * @param b  true to enable the button, otherwise false
     */
    @Override
    public void setEnabled(final boolean b) {
        super.setEnabled(b);
        this.setBorderStatus();
    }
}

