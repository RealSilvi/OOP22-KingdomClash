package it.unibo.view.battle.panels.entities.impl;

import it.unibo.view.battle.panels.entities.api.LifePanel;
import it.unibo.view.battle.panels.entities.api.LivesLabel;
import it.unibo.view.battle.panels.utilities.ImageIconEntitiesManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class LifePanelImpl implements LifePanel {

    private final JPanel mainPanel;
    private ArrayList<LivesLabelImpl> lives;

    private final int nrOfLives;

    public LifePanelImpl(int nrOfLives) {
        this.mainPanel=new DrawPanel(ImageIconEntitiesManager.BACKGROUND_LIFE_URL);
        this.nrOfLives =nrOfLives;

        this.restart();

    }

    @Override
    public void restart(){
        this.lives=new ArrayList<>();

        IntStream.range(0,this.nrOfLives).forEach(
                i -> lives.add(new LivesLabelImpl(true))
        );

        this.lives.forEach(this.mainPanel::add);

    }

    @Override
    public void decreaseLife(){
        this.lives.stream()
                .filter(LivesLabelImpl::isStillAlive)
                .findFirst()
                .ifPresent(LivesLabel ::changeStatus);
    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

}
