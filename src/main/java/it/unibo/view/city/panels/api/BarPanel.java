package it.unibo.view.city.panels.api;

import javax.swing.JPanel;


/*/**
 * This interface is used for the implementation of the top panel the city panel.
 */
public interface BarPanel {

    /**
     * 
     */
    void setOptionsLocked();
    /**
     * This method give the main panel
     * @return the main panel
     */
    JPanel getPanel();
    /**
     * method that display the popups
     */
    void disposeAllPopups();



}
