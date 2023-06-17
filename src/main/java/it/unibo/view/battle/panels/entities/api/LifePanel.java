package it.unibo.view.battle.panels.entities.api;

import javax.swing.JPanel;

/**
 * Describe which actions are allowed in the LifePanel.
 */
public interface LifePanel {

    /**
     * Display that the player loses a health point.
     */
    void decreaseLife();

    /**
     * Reset all the health points.
     */
    void reset();

    /**
     * Returns itself in a JPanel.
     *
     * @return this instance like a JPanel.
     */
    JPanel getPanel();
}

