package it.unibo.view.battle.panels.entities.api;

import javax.swing.*;
import java.awt.event.ActionListener;

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
     * Enable the spin button.
     */
    void disableSpinButton();


    /**
     * Enable the spin button.
     */
    void enableSpinButton();

    /**
     * Sets the action listener at the pass button
     *
     * @param actionListener
     */
    void setActionListenerPass(ActionListener actionListener);

    /**
     * Sets the action listener at the spin button
     *
     * @param actionListener
     */
    void setActionListenerSpin(ActionListener actionListener);

    /**
     * Sets the action listener at the info button
     *
     * @param actionListener
     */
    void setActionListenerInfo(ActionListener actionListener);


    JPanel getPanel();
}
