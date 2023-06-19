package it.unibo.view.city.panels.api;

import javax.swing.JPanel;

/**
 * Defines a generic graphical element.
 */
public abstract class InternalElement extends JPanel {
    /**
     * Updates the content inside of the panel.
     */
    public abstract void refreshContent();
    /**
     * Adds a listener for when an interactive element inside this element is
     * being clicked.
     */
    public abstract void addSelectionObserver();
    /**
     * Removes a listener for when an interactive element inside this element is
     * being clicked.
     */
    public abstract void removeSelectionObserver();
}
