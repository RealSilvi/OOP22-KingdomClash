package it.unibo.view.battle.panels.entities.api;


import javax.swing.JPanel;
import java.awt.event.ActionListener;

/**
 * Describe which actions are allowed in the ButtonPanel.<br>
 * Which contains Pass button, Spin button and the lives of the players.
 */
public interface ButtonsPanel {

    /**
     * Disable the pass button.
     */
    void disablePassButton();

    /**
     * Enable the pass button.
     */
    void enablePassButton();

    /**
     * Disable the spin button.
     */
    void disableSpinButton();

    /**
     * Enable the spin button.
     */
    void enableSpinButton();

    /**
     * Sets the action listener at the pass button.
     *
     * @param actionListener an action listener to set at the Pass Button.
     */
    void setActionListenerPass(ActionListener actionListener);

    /**
     * Sets the action listener at the spin button.
     *
     * @param actionListener an action listener to set at the Pass Button.
     */
    void setActionListenerSpin(ActionListener actionListener);

    /**
     * Sets the action listener at the info button.
     *
     * @param actionListener an action listener to set at the Info Button.
     */
    void setActionListenerInfo(ActionListener actionListener);

    /**
     * Returns itself in a JPanel.
     *
     * @return this instance like a JPanel.
     */
    JPanel getPanel();
}
