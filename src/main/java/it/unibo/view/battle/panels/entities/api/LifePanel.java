package it.unibo.view.battle.panels.entities.api;

import javax.swing.*;

/**
 * Define the class which manage the player's life.
 */
public interface LifePanel {

    /**
     * Dislay that the player lose a health point
     */
    void decreaseLife();

    /**
     *
     * @return this panel
     */
    JPanel getPanel();
}

