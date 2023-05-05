package it.unibo.view.battle.panels.entities.api;

import it.unibo.view.battle.Troop;

/**
 * Define the behavior of a JButton in the slots.
 */
public interface TroopButton {

    /**
     * Change the background image displaying the select status of the JButton.
     */
    void changeStatusImage();

    /**
     *
     * @return the Troop of the slot.
     */
    Troop getTroop();


}
