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

    private final static double LABEL_SCALE=0.2;
    private final static Dimension LABEL_DIMENSION= new Dimension(
            (int)(PanelDimensions.getSideLifePanel().getHeight() * LABEL_SCALE),
            (int)(PanelDimensions.getSideLifePanel().getHeight() * LABEL_SCALE));

    private final JPanel mainPanel;
    private final ArrayList<LivesLabelImpl> lives;

    public LifePanelImpl(final int nrOfLives) {
        this.lives=new ArrayList<>();
        this.mainPanel=new DrawPanel(ImageIconsSupplier.BACKGROUND_FILL_PATTERN,PanelDimensions.getSideLifePanel());


        IntStream.range(0,nrOfLives).forEach(i -> lives.add(new LivesLabelImpl(LABEL_DIMENSION)));
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
