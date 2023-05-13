package it.unibo.view.battle.panels.entities.impl;

import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.battle.panels.entities.api.LifePanel;
import it.unibo.view.battle.panels.entities.api.LivesLabel;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;
import it.unibo.view.battle.panels.utilities.PanelDimensions;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class LifePanelImpl implements LifePanel {

    private final static int rows=2;

    private final JPanel mainPanel;
    private ArrayList<LivesLabelImpl> lives;

    public LifePanelImpl(final int nrOfLives) {
        this.mainPanel=new DrawPanel(ImageIconsSupplier.BACKGROUND_LIFE);

        this.mainPanel.setMinimumSize(PanelDimensions.getSideLifePanel());
        this.mainPanel.setMaximumSize(PanelDimensions.getSideLifePanel());

        if(nrOfLives%rows == 0){
            this.mainPanel.setLayout(new GridLayout(rows,nrOfLives /rows));
        }else{
            this.mainPanel.setLayout(new GridLayout(rows,(nrOfLives + nrOfLives%rows)/rows));
        }

        this.restart(nrOfLives);
    }

    private void restart(final int nrOfLives){
        this.lives=new ArrayList<>();

        IntStream.range(0,nrOfLives).forEach(i -> lives.add(new LivesLabelImpl()));

        this.lives.forEach(this.mainPanel::add);

    }

    @Override
    public void decreaseLife(){
        this.lives.stream()
                .filter(LivesLabelImpl::isAlive)
                .findFirst()
                .ifPresent(LivesLabel ::changeStatus);
    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

}
