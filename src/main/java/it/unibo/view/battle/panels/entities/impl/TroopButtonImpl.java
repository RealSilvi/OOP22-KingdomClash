package it.unibo.view.battle.panels.entities.impl;

import it.unibo.view.battle.Troop;
import it.unibo.view.battle.panels.entities.api.TroopButton;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;

import javax.swing.*;
import java.awt.*;

public class TroopButtonImpl implements TroopButton {

    private Troop troop;
    private final Dimension size;
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
        this.size=size;

        this.button.setPreferredSize(size);

        this.button.setIcon(ImageIconsSupplier.getImageIconFromTroop(this.troop,this.size));
        this.button.setDisabledIcon(ImageIconsSupplier.getImageIconFromTroop(this.troop,this.size));
        this.button.setBackground(Color.BLACK);
        this.button.setOpaque(true);
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
    }

    public JButton getButton() {
        return this.button;
    }

    public class PositionJbutton extends JButton{

        private final int position;
        private boolean selectedBorder;

        public PositionJbutton(int position) {
            this.position = position;
            this.selectedBorder=false;
            this.setEnabled(true);
        }

        public int getPosition() {
            return position;
        }

        public void updateBorder(){
            this.selectedBorder=!this.selectedBorder;
            if(!selectedBorder){
                this.setBorder(BorderFactory.createLineBorder(ImageIconsSupplier.PRIMARY_COLOR,4,true));
            }else{
                this.setBorder(BorderFactory.createLineBorder(ImageIconsSupplier.SECONDARY_COLOR,4,true));
            }
        }

        @Override
        public void setEnabled(boolean b) {
            super.setEnabled(b);
            this.selectedBorder=false;
            if(b){
                this.setBorder(BorderFactory.createLineBorder(ImageIconsSupplier.PRIMARY_COLOR,4,true));
            }else{
                this.setBorder(BorderFactory.createLineBorder(ImageIconsSupplier.DEFAULT_COLOR,4,true));
            }
        }
    }
}

