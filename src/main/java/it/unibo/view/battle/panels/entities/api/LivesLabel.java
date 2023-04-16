package it.unibo.view.battle.panels.entities.api;

/**
 * Represents a JLabel for a single part of players live.
 */
public interface LivesLabel {

    /**
     * Display that the player had lose ore gain a point life;
     */
    void changeStatus();

    /**
     * @return the status of this point live.
     */
    boolean isStillAlive();
}
