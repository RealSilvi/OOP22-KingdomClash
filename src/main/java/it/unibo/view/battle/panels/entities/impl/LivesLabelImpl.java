package it.unibo.view.battle.panels.entities.impl;

import it.unibo.view.battle.panels.entities.api.LivesLabel;
import it.unibo.view.battle.panels.utilities.ImageIconEntitiesManager;

import javax.swing.*;
import java.awt.*;

public class LivesLabelImpl extends JLabel implements LivesLabel {

    private boolean stillAlive;

    public LivesLabelImpl() {
        super(ImageIconEntitiesManager.getImageLive(true));
        this.stillAlive=true;
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setText("vita");//ci sara l immagine della vita morte

    }

    public LivesLabelImpl(final Boolean status){
        this();
        if(!status) this.changeStatus();
    }

    @Override
    public void changeStatus(){
        this.stillAlive=!this.stillAlive;
        this.setIcon(ImageIconEntitiesManager.getImageLive(this.stillAlive));
        this.setText("morte");//ci sara l immagine della vita morta
    }

    @Override
    public boolean isStillAlive() {
        return this.stillAlive;
    }
}
