package it.unibo.view.city;

import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import javax.swing.JComponent;
import javax.swing.JPanel;

import it.unibo.view.city.panels.api.TileClickObserver;

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
     /**
      * Registers an observers that gets notified whenever a tile gets clicked.
      * @param tileClickObservertoRegister the observer to register
      */
     void registerTileClickObserver(TileClickObserver tileClickObservertoRegister);
     /**
      * Unregisters an observers that gets notified whenever a tile gets clicked.
      * @param tileClickObservertoRegister the observer to unregister
      */
     void unregisterTileClickObserver(TileClickObserver tileClickObservertoUnregister);
     /**
      * Notifies all the registered tileClickObservers.
      * @param tile      the responsible tile as a JComponent
      * @param position  the responsible position in the tilemap
      */
     void notifyTileClick(JComponent tile, Point2D.Float position);
}

