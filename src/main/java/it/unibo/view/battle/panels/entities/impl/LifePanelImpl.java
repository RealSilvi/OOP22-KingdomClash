package it.unibo.view.battle.panels.entities.impl;

import it.unibo.kingdomclash.config.PathIconsConfiguration;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.battle.panels.entities.api.LifePanel;
import it.unibo.view.battle.panels.entities.api.LivesLabel;
import it.unibo.view.utilities.ImageIconsSupplier;
import it.unibo.view.battle.utilities.PanelDimensions;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class LifePanelImpl implements LifePanel {

    private static final double LABEL_SCALE = 0.2;
    private static final Dimension LABEL_DIMENSION = new Dimension(
            (int) (PanelDimensions.getSideLifePanel().getHeight() * LABEL_SCALE),
            (int) (PanelDimensions.getSideLifePanel().getHeight() * LABEL_SCALE));

    private final JPanel mainPanel;
    private final List<LivesLabelImpl> lives;

    /**
     * @param nrOfLives how many health points has the player.
     */
    public LifePanelImpl(final int nrOfLives, final PathIconsConfiguration pathIconsConfiguration) {
        this.lives = new ArrayList<>();
        this.mainPanel = new DrawPanel(ImageIconsSupplier.loadImageIcon(pathIconsConfiguration.getBackgroundFillPattern()), PanelDimensions.getSideLifePanel());


        IntStream.range(0, nrOfLives).forEach(i -> lives.add(new LivesLabelImpl(LABEL_DIMENSION, pathIconsConfiguration)));
        this.lives.forEach(this.mainPanel::add);
    }

    @Override
    public void decreaseLife() {
        this.lives.stream()
                .filter(LivesLabelImpl::isAlive)
                .findFirst()
                .ifPresent(LivesLabel::changeStatus);
    }

    public void reset() {
        this.lives.forEach(LivesLabelImpl::reset);
    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

    private static class LivesLabelImpl extends JLabel implements LivesLabel {

        //        TODO set the serial UID
        static final long serialVersionUID = 42L;

        private final Dimension size;
        private boolean alive;
        private final PathIconsConfiguration pathIconsConfiguration;

        /**
         * @param size the size of the label
         */
        public LivesLabelImpl(final Dimension size, PathIconsConfiguration pathIconsConfiguration) {
            super(ImageIconsSupplier.getScaledImageIcon(pathIconsConfiguration.getLife(true), size));

            this.size = size;
            this.alive = true;
            this.pathIconsConfiguration = pathIconsConfiguration;

            this.setPreferredSize(size);
        }


        @Override
        public void changeStatus() {
            this.alive = !this.alive;
            this.setIcon(ImageIconsSupplier.getScaledImageIcon(pathIconsConfiguration.getLife(this.alive), size));
        }

        @Override
        public boolean isAlive() {
            return this.alive;
        }

        public void reset() {
            this.alive = true;
            this.setIcon(ImageIconsSupplier.getScaledImageIcon(pathIconsConfiguration.getLife(true), size));
        }
    }
}
