package it.unibo.controller;

/**
 * A very simple standardized controller interface
 */
public interface Controller {
    /**
     * Tells the controller to activate/deactivate all time-related logic
     *
     * @param currentControllerActive true for activation, false for deactivation
     */
    public void setActive(boolean currentControllerActive);

    /**
     * @return true if time related logic is active
     */
    public boolean isActive();

    /**
     * Tells the controller to safely close and stop everything it's doing
     */
    public void disable();
}