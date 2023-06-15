package it.unibo.view.city;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

public interface CityPanel {
     void resources();

     void setBuildings();

     void setfield();

     JPanel getPanel();
     /**
      * Sets the actionlistener to trigger the return to the main menu.
      * @param returnActionListener action listener to assign to this view
      */
     void setReturnActionListener(ActionListener returnActionListener);
}

