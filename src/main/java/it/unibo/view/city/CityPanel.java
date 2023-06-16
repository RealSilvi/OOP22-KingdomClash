
package it.unibo.view.city;

import java.awt.event.ActionListener;
import javax.swing.JPanel;
/**
 * This interface is used for the implementation of the main city panel.
 */
public interface CityPanel {

     /**
      * @return the main City panel.
      */
     JPanel getPanel();

     /**
      * Sets the actionlistener to trigger the return to the main menu.
      * @param returnActionListener action listener to assign to this view.
      */
     void setReturnActionListener(ActionListener returnActionListener);
     /**
      * This method enable the visibility of the popups.
      */
     void disposeAll();
}
