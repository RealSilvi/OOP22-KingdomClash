package it.unibo.view.battle.panels.entities.api;

/**
 * Represents a JLabel for a single healthPoint displaying an image.
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
}
