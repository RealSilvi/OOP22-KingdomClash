package it.unibo.view.battle.panels.entities.api;

import it.unibo.view.battle.Troop;

/**
 * Define the behavior of a JButton in the slots.
 */
public interface TroopButton {

    /**
     * Sets a Random Troop.
     */
    void changeTroop();

    /**
     * Change the status of the slot. It NOT disables it.
     */
    void changeSelectable();

    /**
     *
     * @return the Troop of the slot.
     */
    Troop getTroop();

    /**
     *
     * @return the status of the slot.
     */
    Boolean getSelectable();
}
