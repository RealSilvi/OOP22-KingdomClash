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

    private final static double LABEL_SCALE = 0.2;
    private final static Dimension LABEL_DIMENSION = new Dimension(
            (int) (PanelDimensions.getSideLifePanel().getHeight() * LABEL_SCALE),
            (int) (PanelDimensions.getSideLifePanel().getHeight() * LABEL_SCALE));

    private final JPanel mainPanel;
    private final ArrayList<LivesLabelImpl> lives;

    /**
     * @param nrOfLives how many health points has the player.
     */
    public LifePanelImpl(final int nrOfLives) {
        this.lives = new ArrayList<>();
        this.mainPanel = new DrawPanel(ImageIconsSupplier.BACKGROUND_FILL_PATTERN, PanelDimensions.getSideLifePanel());


        IntStream.range(0, nrOfLives).forEach(i -> lives.add(new LivesLabelImpl(LABEL_DIMENSION)));
        this.lives.forEach(this.mainPanel::add);
    }

    @Override
    public void decreaseLife() {
        this.lives.stream()
                .filter(LivesLabelImpl::isAlive)
                .findFirst()
                .ifPresent(LivesLabel::changeStatus);
    }

    public void reset(){
        this.lives.forEach(LivesLabelImpl::reset);
    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

    private static class LivesLabelImpl extends JLabel implements LivesLabel {

        private final Dimension size;
        private boolean alive;

        /**
         * @param size the size of the label
         */
        public LivesLabelImpl(final Dimension size) {
            super(ImageIconsSupplier.getImageIconLife(true, size));

            this.size = size;
            this.alive = true;

            this.setPreferredSize(size);
        }


        @Override
        public void changeStatus() {
            this.alive = !this.alive;
            this.setIcon(ImageIconsSupplier.getImageIconLife(this.alive, this.size));
        }

        @Override
        public boolean isAlive() {
            return this.alive;
        }

        public void reset(){
            this.alive=true;
            this.setIcon(ImageIconsSupplier.getImageIconLife(this.alive, this.size));
        }
    }
}
