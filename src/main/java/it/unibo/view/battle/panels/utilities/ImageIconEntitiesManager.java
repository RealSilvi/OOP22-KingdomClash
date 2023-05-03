package it.unibo.view.battle.panels.utilities;

import it.unibo.view.battle.Troop;

import javax.swing.*;
import java.util.Map;

public interface ImageIconEntitiesManager {

    Map<Troop,String> troopUrl = Map.of(
            Troop.AXE,"src/main/resources/it/unibo/icons/battle/Axe.png",
            Troop.SWORD,"src/main/resources/it/unibo/icons/battle/Sword.png",
            Troop.CATAPULT,"src/main/resources/it/unibo/icons/battle/Hammer.png",
            Troop.ARROW,"src/main/resources/it/unibo/icons/battle/Mace.png",
            Troop.SHIELD,"src/main/resources/it/unibo/icons/battle/Shield01.png",
            Troop.HELMET,"src/main/resources/it/unibo/icons/battle/Shield02.png",
            Troop.TOWER,"src/main/resources/it/unibo/icons/battle/Shield03.png",
            Troop.DODGE,"src/main/resources/it/unibo/icons/battle/Helmet.png"
    );

    String LIFE_URL = "src/main/resources/it/unibo/icons/battle/Life.png";
    String DEATH_URL = "src/main/resources/it/unibo/icons/battle/Death.png";
    String BACKGROUND_FIELD_URL = "src/main/resources/it/unibo/icons/battle/FieldBackground.png";
    String BACKGROUND_FREE_SPOT_URL = "src/main/resources/it/unibo/icons/battle/FreeSpotBackground.png";
    String BACKGROUND_PLAYERS_URL = "src/main/resources/it/unibo/icons/battle/PlayerBackground.png";
    String BACKGROUND_SIDE_URL = "src/main/resources/it/unibo/icons/battle/SideBackground.png";
    String BACKGROUND_LIFE_URL = "src/main/resources/it/unibo/icons/battle/LifeBackground.png";
    String BACKGROUND_BUTTONS_URL = "src/main/resources/it/unibo/icons/battle/ButtonsBackground.png";


     static ImageIcon getImageFromTroop(final Troop troop, final boolean alive){
        return null;
    }

     static ImageIcon getImageRandomTroop(){
         return null;
     }

     static ImageIcon getImageLive(final boolean alive){
        return (alive) ?  new ImageIcon(LIFE_URL) :  new ImageIcon(DEATH_URL) ;
    }

    static ImageIcon getImageDefaultBackground(){
         return null;
    }

    static Troop getTroopFromImage(Icon icon){ return null;}

}
