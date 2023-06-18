package it.unibo.view.battle.panels.entities.api;

import it.unibo.model.data.TroopType;

import javax.swing.JButton;

/**
 * Define a JButton which displays a slot for choosing troops.
 */
public interface TroopButton {

    /**
     * @return the troop of the slot.
     */
    TroopType getTroop();

    /**
     * Sets a new troop after a certain time.
     *
     * @param troop the troop to set.
     * @param delay the milliseconds to wait before displaying it.
     */
    void setTroop(TroopType troop, int delay);

    /**
     * Set the enabled status of the button,
     * displaying it prettier.
     *
     * @param enabled the status to set the button.
     */
    void setEnabled(boolean enabled);

    /**
     * Return itself like a JButton.
     *
     * @return itself like a JButton.
     */
    JButton getButton();
}
