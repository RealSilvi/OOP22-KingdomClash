package it.unibo.controller;

import javax.swing.*;

/**
 * A very simple standardized controller interface
 */
 public interface Controller {

    JPanel getGuiPanel();

    void setReturnActionListener();
    void removeReturnActionListener();
}