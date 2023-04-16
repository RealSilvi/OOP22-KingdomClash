package it.unibo.view.battle.panels.entities.impl;

import it.unibo.view.battle.Troop;
import it.unibo.view.battle.panels.entities.api.TroopButton;

import javax.swing.*;
import java.awt.*;

public class TroopButtonImpl extends JButton implements TroopButton {

    private Troop troop;
    private Boolean selectable;

    public TroopButtonImpl() {
        super();
        this.troop = Troop.getRandomTroop();
        this.selectable =true;
        this.setOpaque(false);
    }

    @Override
    public void changeTroop(){
        this.troop = Troop.getRandomTroop();
        this.setIcon(new ImageIcon(this.troop.getUrl()));
    }

    @Override
    public void changeSelectable(){
        this.selectable =!this.selectable;
        this.changeBorder();
    }

    @Override
    public Troop getTroop() {
        return this.troop;
    }

    @Override
    public Boolean getSelectable() {
        return this.selectable;
    }

    private void changeBorder(){
        if ((this.selectable)) {
            this.setBorder(BorderFactory.createEmptyBorder());
        } else {
            this.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        }
    }
}
