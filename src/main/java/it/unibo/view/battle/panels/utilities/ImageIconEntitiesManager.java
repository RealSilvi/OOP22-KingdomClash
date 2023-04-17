package it.unibo.view.battle.panels.utilities;

import it.unibo.view.battle.Troop;

import javax.swing.*;
import java.util.Map;

public interface ImageIconEntitiesManager {

    Map<Troop,String> troopUrl = Map.of(
            Troop.AXE,"something/somethingElse.png",
            Troop.SWORD,"something/somethingElse.png",
            Troop.CATAPULT,"something/somethingElse.png",
            Troop.ARROW,"something/somethingElse.png",
            Troop.SHIELD,"something/somethingElse.png",
            Troop.HELMET,"something/somethingElse.png",
            Troop.TOWER,"something/somethingElse.png",
            Troop.DODGE,"something/somethingElse.png"
    );

    String LIFE_URL = "something/somethingElse.png";
    String DEATH_URL = "something/somethingElse.png";
    String BACKGROUND_FIELD_URL = "src/main/resources/it/unibo/icons/battle/FieldBackground.png";
    String BACKGROUND_FREE_SPOT_URL = "src/main/resources/it/unibo/icons/battle/FreeSpotBackground.png";
    String BACKGROUND_PLAYERS_URL = "src/main/resources/it/unibo/icons/battle/PlayerBackground.png";
    String BACKGROUND_SIDE_URL = "src/main/resources/it/unibo/icons/battle/SideBackground.png";


     static ImageIcon getImageFromTroop(final Troop troop, final boolean alive){
        return null;
    }

     static ImageIcon getImageRandomTroop(){
         return null;
     }

     static ImageIcon getImageLive(final boolean alive){
        return null;
    }

    static ImageIcon getImageDefaultBackground(){
         return null;
    };

    static Troop getTroopFromImage(Icon icon){ return null;}

}
