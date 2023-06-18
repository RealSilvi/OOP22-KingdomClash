package it.unibo.view.battle.panels.api;

import javax.swing.JPanel;
import java.awt.event.ActionListener;

/**
 * Describe the behaviour of the westPanel in the BattlePanel.<br>
 * Which contains the Spin button, the Pass button and the lives of the Players.
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
     * @param actionListener the action listener to set at the Pass button.
     */
    void setActionListenerPass(ActionListener actionListener);

    /**
     * @param actionListener the action listener to set at the Spin button.
     */
    void setActionListenerSpin(ActionListener actionListener);

    /**
     * @param actionListener the action listener to set at the Info button.
     */
    void setActionListenerInfo(ActionListener actionListener);

    /**
     * Reset the life of each player.
     */
    void reset();

    /**
     * Returns itself in a JPanel.
     *
     * @return this instance like a JPanel.
     */
    JPanel getPanel();
}
