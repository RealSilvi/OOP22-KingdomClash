package it.unibo.view.battle.panels.entities.impl;

import it.unibo.view.battle.panels.entities.api.LivesLabel;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;

import javax.swing.*;

public class LivesLabelImpl extends JLabel implements LivesLabel {


    public LivesLabelImpl() {
        super(ImageIconsSupplier.LIFE);
        this.setOpaque(true);
    }


    @Override
    public void changeStatus(){
        this.setIcon(ImageIconsSupplier.DEATH);
    }

}
