package it.unibo.view.battle.panels.entities.impl;

import it.unibo.view.battle.Troop;
import it.unibo.view.battle.panels.entities.api.TroopButton;
import it.unibo.view.battle.panels.utilities.ImageIconEntitiesManager;

import javax.swing.*;
import java.awt.*;

public class TroopButtonImpl extends JButton implements TroopButton {

    private Boolean selectable;

    public TroopButtonImpl(final Troop troop) {
        this.setIcon(ImageIconEntitiesManager.getImageFromTroop(troop,true));
        this.selectable =true;
        this.setOpaque(false);
    }

    @Override
    public void changeTroop(){
        this.setIcon(ImageIconEntitiesManager.getImageRandomTroop());
    }

    @Override
    public void changeSelectable(){
        this.selectable =!this.selectable;
        this.changeBorder();
    }

    @Override
    public Troop getTroop() {
        return ImageIconEntitiesManager.getTroopFromImage(this.getIcon());
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
