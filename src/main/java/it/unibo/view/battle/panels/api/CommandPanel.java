package it.unibo.view.battle.panels.api;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * This interface show how to use the westPanel of the BattlePanel.
 * <br>
 * Show how to use the spinButton, the passButton and the lives of the Players.
 */
public interface CommandPanel {

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
     * Display that the player has lost a health point.
     */
    void decreasePlayerLive();

    /**
     * Display that the bot has lost a health point.
     */
    void decreaseBotLive();

    /**
     *
     * @param actionListener the action listener to set at the Pass button.
     */
    void setActionListenerPass(ActionListener actionListener);

    /**
     *
     * @param actionListener the action listener to set at the Spin button.
     */
    void setActionListenerSpin(ActionListener actionListener);

    /**
     *
     * @param actionListener the action listener to set at the Info button.
     */
    void setActionListenerInfo(ActionListener actionListener);

    JPanel getPanel();
}
