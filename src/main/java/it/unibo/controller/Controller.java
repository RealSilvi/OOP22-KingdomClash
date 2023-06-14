package it.unibo.controller;

import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * A very simple standardized controller interface
 */
 public interface Controller {

    JPanel getGuiPanel();

    void setReturnActionListener(ActionListener returnActionToAdd);
    void removeReturnActionListener(ActionListener returnActionToRemove);
}