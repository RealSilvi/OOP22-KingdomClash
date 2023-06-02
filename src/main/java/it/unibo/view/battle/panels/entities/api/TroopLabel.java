package it.unibo.view.battle.panels.entities.api;

import it.unibo.view.battle.Troop;

/**
 * Define a JLabel for displaying a Troop.
 */
public interface TroopLabel {

    /**
     * Displays the absence of the troop.<br>
     * Sets a background default icon.
     */
    void setEmpty();

    /**
     * Displays a troop.
     *
     * @param troop the troop to display.
     */
    void setTroop(Troop troop);
}
