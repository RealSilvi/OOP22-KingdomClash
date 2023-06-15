package it.unibo.controller;

import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * A very simple standardized controller interface
 */
 public interface Controller {

    JPanel getGuiPanel();
    /**
     * Sets an action listener that tells the view to return to the main menu.
     * @param returnActionToAdd the ActionListener to add.
     */
    void setReturnActionListener(ActionListener returnActionToAdd);
}