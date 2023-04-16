package it.unibo.view.battle.panels.entities.impl;

import javax.swing.*;

public class LivesLabelImpl extends JLabel implements it.unibo.view.battle.panels.entities.api.LivesLabel {

    private boolean stillAlive;

    public LivesLabelImpl() {
        this.stillAlive=true;
        this.setText("vita");//ci srara l immagine della vita
    }

    public LivesLabelImpl(Boolean status){
        this();
        if(!status) this.changeStatus();
    }

    @Override
    public void changeStatus(){
        this.stillAlive=false;
        this.setText("morte");//ci sara l immagine della vita morta
    }

    @Override
    public boolean isStillAlive() {
        return this.stillAlive;
    }
}
