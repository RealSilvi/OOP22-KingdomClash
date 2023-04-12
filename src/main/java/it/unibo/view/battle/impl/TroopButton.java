package it.unibo.view.battle.impl;

import javax.swing.*;
import java.awt.*;

public class TroopButton extends JButton {

    private Troop troop;
    private Boolean selectable;

    public TroopButton() {
        super();
        this.troop = Troop.getRandomTroop();
        this.selectable =true;
        this.setOpaque(false);
    }

    public void changeTroop(){
        this.troop = Troop.getRandomTroop();
        this.setIcon(new ImageIcon(this.troop.getUrl()));
    }

    public void changeSelectable(){
        this.selectable =!this.selectable;
        this.changeBorder();
    }

    public Troop getTroop() {
        return this.troop;
    }

    public Boolean getSelectable() {
        return this.selectable;
    }

    private void changeBorder(){
        if(this.selectable){
            this.setBorder(BorderFactory.createEmptyBorder());
        }else{
            this.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        }
    }
}
