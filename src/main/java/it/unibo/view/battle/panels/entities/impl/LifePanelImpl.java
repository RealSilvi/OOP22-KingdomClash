package it.unibo.view.battle.panels.entities.impl;

import it.unibo.view.battle.panels.entities.api.LivesLabel;
import it.unibo.view.battle.panels.utilities.ImageIconEntitiesManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class LifePanelImpl extends JPanel implements it.unibo.view.battle.panels.entities.api.LifePanel {

    private ArrayList<LivesLabelImpl> lives;

    private final Image backgroundImage;
    private final int nrOfLifes;

    public LifePanelImpl(int nrOfLifes) {
        this.nrOfLifes=nrOfLifes;
        this.backgroundImage =new ImageIcon(
                ImageIconEntitiesManager.BACKGROUND_LIFE_URL).getImage();
        this.setOpaque(false);

        this.restart();

    }

    @Override
    public void restart(){
        this.lives=new ArrayList<>();

        IntStream.range(0,this.nrOfLifes).forEach(
                i -> lives.add(new LivesLabelImpl(true))
        );

        this.lives.forEach(this::add);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
    }


    @Override
    public void decreaseLife(){
        this.lives.stream()
                .filter(LivesLabelImpl::isStillAlive)
                .findFirst()
                .ifPresent(LivesLabel ::changeStatus);
    }

}
