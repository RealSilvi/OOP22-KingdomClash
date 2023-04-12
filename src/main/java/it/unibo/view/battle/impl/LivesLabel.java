package it.unibo.view.battle.impl;

import javax.swing.*;

public class LivesLabel extends JLabel {

    private boolean stillAlive;

    public LivesLabel() {
        this.stillAlive=true;
        this.setText("vita");//ci srara l immagine della vita
    }

    public void changeStatus(){
        this.stillAlive=false;
        this.setText("morte");//ci sara l immagine della vita morta
    }

    public boolean isStillAlive() {
        return this.stillAlive;
    }
}
