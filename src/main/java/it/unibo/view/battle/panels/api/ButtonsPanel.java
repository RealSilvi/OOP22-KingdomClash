package it.unibo.view.battle.panels.api;

import java.awt.event.ActionListener;

/**
 * This interface show how to use the westPanel of the Gui.
 * <br>
 * Show how to use the spinButton, the passButton and the lives of the Players.
 */
public interface ButtonsPanel {

    /**
     * Restart the panel.
     */
    void restart();

    /**
     * Disable the passButton.
     */
    void disablePassButton();

    /**
     * Enable the passButton.
     */
    void enablePassButton();

    /**
     * Disable the SpinButton.
     */
    void disableSpinButton();

    /**
     * Enable the SpinButton.
     */
    void enableSpinButton();

    /**
     * Display that the player has lost a live.
     */
    void decreasePlayerLive();

    /**
     * Display that the bot has lost a live.
     */
    void decreaseBotLive();

    /**
     *
     * @param actionListener gives instruction at the PassButton.
     */
    void setActionListenerPass(ActionListener actionListener);

    /**
     *
     * @param actionListener gives instruction at the SpinButton.
     */
    void setActionListenerSpin(ActionListener actionListener);
}
