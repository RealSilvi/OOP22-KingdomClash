package it.unibo.view.battle.panels.entities.impl;

import it.unibo.view.battle.panels.utilities.ImageIconEntitiesManager;

import javax.swing.*;

public class TroopLabelImpl extends JLabel implements it.unibo.view.battle.panels.entities.api.TroopLabel {

    public TroopLabelImpl(Icon image) {
        super(image);
    }

    public TroopLabelImpl() {
        super(ImageIconEntitiesManager.getImageDefaultBackground());
    }

    @Override
    public void setEmpty(){
        this.setIcon(ImageIconEntitiesManager.getImageDefaultBackground());
    }
}
