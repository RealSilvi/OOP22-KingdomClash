package it.unibo.view.battle.panels.entities.impl;

import it.unibo.view.battle.Troop;
import it.unibo.view.battle.panels.entities.api.TroopButton;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;

import javax.swing.*;
import java.awt.*;

public class TroopButtonImpl implements TroopButton {

    private Troop troop;
    private final Dimension size;
    private boolean status;
    private final PositionJbutton button;

    /**
     *
     * @param troop the troop to set on this button
     * @param status the enable status of this button
     * @param size  the dimension of this button
     */
    public TroopButtonImpl(final Troop troop, final boolean status, final Dimension size, final int position) {
        this.button=new PositionJbutton(position);
        this.troop=troop;
        this.status= status;
        this.size=size;

        this.button.setPreferredSize(size);

        this.button.setIcon(ImageIconsSupplier.getImageIconFromTroop(this.troop,this.size));
        this.button.setDisabledIcon(ImageIconsSupplier.getImageIconFromTroop(this.troop,this.size));
        this.button.setBackground(Color.BLACK);
        this.button.setOpaque(true);
        this.setBorderStatus();
    }

    private void setBorderStatus(){
        if(this.button.isEnabled()){
            if(this.status){
                this.button.setBorder(BorderFactory.createLineBorder(ImageIconsSupplier.PRIMARY_COLOR,4,true));
            }else{
                this.button.setBorder(BorderFactory.createLineBorder(ImageIconsSupplier.SECONDARY_COLOR,4,true));
            }
        }else{
            this.button.setBorder(BorderFactory.createLineBorder(ImageIconsSupplier.DEFAULT_COLOR,4,true));
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
        this.button.setIcon(ImageIconsSupplier.getImageIconFromTroop(this.troop,this.size));
        this.button.setDisabledIcon(ImageIconsSupplier.getImageIconFromTroop(this.troop,this.size));
    }

    /**
     * Overwritten the method to change the border of the button
     * based on isEnable()
     * @param b  true to enable the button, otherwise false
     */
    @Override
    public void setEnabled(final boolean b) {
        this.button.setEnabled(b);
        this.setBorderStatus();
    }

    public JButton getButton() {
        return this.button;
    }

    public class PositionJbutton extends JButton{

        private final int position;

        public PositionJbutton(int position) {
            this.position = position;
        }

        public int getPosition() {
            return position;
        }

        public void updateBorder(){
            changeStatusImage();
        }
    }
}

