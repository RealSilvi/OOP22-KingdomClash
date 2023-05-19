package it.unibo.view.battle.panels.entities.impl;

import it.unibo.view.battle.panels.entities.api.LivesLabel;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;

import javax.swing.*;
import java.awt.*;

public class LivesLabelImpl extends JLabel implements LivesLabel {

    private final Dimension size;
    private boolean alive;

    public LivesLabelImpl(final Dimension size) {
        super(ImageIconsSupplier.getImageIconLife(true,size));

        this.size=size;
        this.alive=true;

        this.setPreferredSize(size);
    }


    @Override
    public void changeStatus(){
        this.alive=!this.alive;
        this.setIcon(ImageIconsSupplier.getImageIconLife(this.alive,this.size));
    }

    @Override
    public boolean isAlive() {
        return this.alive;
    }
}
