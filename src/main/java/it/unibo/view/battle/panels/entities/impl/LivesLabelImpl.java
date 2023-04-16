package it.unibo.view.battle.panels.entities.impl;

import it.unibo.view.battle.panels.entities.api.LivesLabel;
import it.unibo.view.battle.panels.utilities.ImageIconEntitiesManager;

import javax.swing.*;

public class LivesLabelImpl extends JLabel implements LivesLabel {

    private boolean stillAlive;

    public LivesLabelImpl() {
        this.stillAlive=true;
        this.setText("vita");//ci sara l immagine della vita morta
        this.setIcon(ImageIconEntitiesManager.getImageLive(true));
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
