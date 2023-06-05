package it.unibo.controller;

import javax.swing.*;

/**
 * A very simple standardized controller interface
 */
 public interface Controller {
//    /**
//     * Tells the controller to activate/deactivate all time-related logic
//     *
//     * @param currentControllerActive true for activation, false for deactivation
//     */
//
//    void setActive(boolean currentControllerActive);
//
//    /**
//     * @return true if time related logic is active
//     */
//
//    boolean isActive();
//
//    /**
//     * Tells the controller to safely close and stop everything it's doing
//     */
//     void disable();
//

    JPanel getGuiPanel();
}