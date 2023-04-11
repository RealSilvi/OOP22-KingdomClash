package it.unibo.view.battle.impl;

import javax.swing.*;

public class TroopButton extends JButton {

    private Troop troop;

    public TroopButton() {
        super();
        this.troop = Troop.getRandomTroop();
        this.setOpaque(false);
    }

    public void changeTroop(){
        this.troop = Troop.getRandomTroop();
        this.setIcon(new ImageIcon(this.troop.getUrl()));
    }

    public Troop getTroop() {
        return this.troop;
    }

}
