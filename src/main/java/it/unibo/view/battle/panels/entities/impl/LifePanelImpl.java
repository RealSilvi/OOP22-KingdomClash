package it.unibo.view.battle.panels.entities.impl;

import it.unibo.kingdomclash.config.PathIconsConfiguration;
import it.unibo.view.battle.panels.entities.DrawPanelImpl;
import it.unibo.view.battle.panels.entities.api.LifePanel;
import it.unibo.view.battle.panels.entities.api.LivesLabel;
import it.unibo.view.utilities.ImageIconsSupplier;
import it.unibo.view.battle.panels.PanelDimensions;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Dimension;
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
     * Constructs an instance of a LifePanel.
     *
     * @param nrOfLives              set the number of the health points to display.
     * @param pathIconsConfiguration where are defined the paths of the textures.
     */
    public LifePanelImpl(final int nrOfLives, final PathIconsConfiguration pathIconsConfiguration) {
        this.lives = new ArrayList<>();
        this.mainPanel = new DrawPanelImpl(
                ImageIconsSupplier.loadImageIcon(pathIconsConfiguration.getBackgroundFillPattern()),
                PanelDimensions.getSideLifePanel());

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

    @Override
    public void reset() {
        this.lives.forEach(LivesLabelImpl::reset);
    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

    /**
     * This class can't serialize.
     */
    @SuppressWarnings(value = "serial")
    private static class LivesLabelImpl extends JLabel implements LivesLabel {

        private final Dimension size;
        private boolean alive;
        private final PathIconsConfiguration pathIconsConfiguration;

        /**
         * Constructs an instance of a LivesLabel like a JLabel.
         *
         * @param size                   set the size of the JLabel.
         * @param pathIconsConfiguration where are defined the paths of the textures.
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

        @Override
        public void reset() {
            this.alive = true;
            this.setIcon(ImageIconsSupplier.getScaledImageIcon(pathIconsConfiguration.getLife(true), size));
        }

    }
}
