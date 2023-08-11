package it.unibo.view.city.panels.api;

import javax.swing.JPanel;

/**
 * This interface is used for the implementation of the field for the city panel.
 */
public interface FieldCityPanel {

    /**
     * 
     * @return the main panel implented in the class.
     */
    JPanel getPanel();
    /**
     * This method refresh the building image when the building is upgrading.
     */
    void refreshContent();

}
