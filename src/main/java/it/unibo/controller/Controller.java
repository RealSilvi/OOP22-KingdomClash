package it.unibo.controller;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

/**
 * A very simple standardized controller interface.
 */
public interface Controller {

    /**
     * @return the main panel of the controller which will be registered to the gui.
     */
    JPanel getGuiPanel();

    /**
     * Sets an action listener that switches the panel in the mainPanel of the gui.
     *
     * @param returnActionToAdd the ActionListener to add.
     */
    void setReturnActionListener(ActionListener returnActionToAdd);
    /**
     * Tells the controller to stop everything it's doing and prepare for game
     * exit.
     */
    void closureOperation();
}
