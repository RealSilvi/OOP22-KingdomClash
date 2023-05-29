package it.unibo.view.battle.panels.entities.api;

import it.unibo.view.battle.Troop;

/**
 * Define a JButton which displays a slot for choosing troops.
 */
public interface TroopButton {

    /**
     * Display the select status of the JButton.
     */
    void changeStatusImage();

    /**
     * @return the Troop of the slot.
     */
    Troop getTroop();

    /**
     * Sets a new troop for the choice.
     * @param troop the troop to set on this button
     */
    void setTroop(final Troop troop);


}
