package it.unibo.view.battle.panels.entities.api;

/**
 * Define a JLabel for representing a single healthPoint.
 */
public interface LivesLabel {

    /**
     * Switch the status of the label displaying a lost health point
     * or an active health point.
     */
    void changeStatus();

    /**
     * @return the status of a health point.
     */
    boolean isAlive();

    /**
     * Reset the health point.
     */
    void reset();
}
