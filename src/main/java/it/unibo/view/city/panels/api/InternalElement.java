package it.unibo.view.city.panels.api;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

/**
 * Defines a generic graphical element.
 */
public abstract class InternalElement extends JPanel {
    private static final long serialVersionUID = 123456789L;
    /**
     * Updates the content inside of the panel.
     */
    public void refreshContent() { }
    /**
     * Adds a listener for when an interactive element inside this element is
     * being clicked.
     * @param selectionObservertoAdd the selection observer to be added
     */
    public void addSelectionObserver(final ActionListener selectionObservertoAdd) { }
    /**
     * Removes a listener for when an interactive element inside this element is
     * being clicked.
     * @param selectionObservertoRemove the selection observer to be removed
     */
    public void removeSelectionObserver(final ActionListener selectionObservertoRemove) { }
}
