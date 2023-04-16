package it.unibo.view.battle.panels.utilities;

import it.unibo.view.battle.Troop;

import javax.swing.*;

public interface ImageIconEntitiesManager {

    String AXE_URL = "something/somthingElse.png";

     static ImageIcon getImageFromTroop(final Troop troop, final boolean alive){
        return null;
    }

     static ImageIcon getImageRandomTroop(){
         return null;
     }

     static ImageIcon getImageLive(final boolean alive){
        return null;
    }

    static Troop getTroopFromImage(Icon icon){ return null;}

}
