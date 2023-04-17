package it.unibo.view.battle.panels.api;

import java.awt.event.ActionListener;

/**
 * This interface show how to use southPanel and northPanel of the Gui.
 * <br>
 * Show how to use the TroopButtons which the user can choose before passing the round.
 */
public interface PlayerPanel {

    /**
     * Restart the panel.
     */
    void restart();

    /**
     * Display new Random TroopButtons and block those which are already chosen.
     */
    void update();

    /**
     * Disable all the buttons.
     */
    void disableAllSlots();

    /**
     * Enable all the buttons.
     */
    void enableAllSlots();

    /**
     *
     * @param actionListener gives instruction at all the TroopButtons.
     */
    void setActionListenersSlot(ActionListener actionListener);
}
