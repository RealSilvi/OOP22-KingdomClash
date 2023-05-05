package it.unibo.view.battle.panels.entities.impl;

import it.unibo.view.battle.panels.entities.api.LivesLabel;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;

import javax.swing.*;

public class LivesLabelImpl extends JLabel implements LivesLabel {

    private boolean alive;

    public LivesLabelImpl() {
        super(ImageIconsSupplier.LIFE);
        this.alive=true;
        this.setOpaque(true);
    }


    @Override
    public void changeStatus(){
        this.alive=!this.alive;
        this.setIcon(ImageIconsSupplier.getImageLive(this.alive));
    }

    @Override
    public boolean isAlive() {
        return this.alive;
    }
}
