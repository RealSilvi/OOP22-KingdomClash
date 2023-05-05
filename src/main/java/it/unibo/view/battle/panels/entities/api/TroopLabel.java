package it.unibo.view.battle.panels.entities.api;

import it.unibo.view.battle.Troop;

/**
 * Define the behaviour of a TroopLabel.
 */
public interface TroopLabel {

    /**
     * Displays an empty field's slot.<br>
     * Sets a background default icon.
     */
    void setEmpty();

    /**
     * Displays a troop on the field's slot.
     * @param troop the troop to display.
     */
    void setTroop(Troop troop);
}
