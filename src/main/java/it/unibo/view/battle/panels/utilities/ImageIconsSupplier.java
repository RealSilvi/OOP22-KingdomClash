package it.unibo.view.battle.panels.utilities;

import it.unibo.view.battle.Troop;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public interface ImageIconsSupplier {

    ImageIcon BACKGROUND_FIELD = new ImageIcon("src/main/resources/it/unibo/icons/battle/FieldBackground.png");
    ImageIcon BACKGROUND_FREE_SPOT = new ImageIcon("src/main/resources/it/unibo/icons/battle/FreeSpotBackground.png");
    ImageIcon BACKGROUND_PLAYERS = new ImageIcon("src/main/resources/it/unibo/icons/battle/PlayerBackground.png");
    ImageIcon BACKGROUND_SIDE = new ImageIcon("src/main/resources/it/unibo/icons/battle/SideBackground.png");
    ImageIcon BACKGROUND_LIFE = new ImageIcon("src/main/resources/it/unibo/icons/battle/LifeBackground.png");
    ImageIcon BACKGROUND_BUTTONS = new ImageIcon("src/main/resources/it/unibo/icons/battle/ButtonsBackground.png");

    Map<Troop, List<String>> troopUrl = Map.of(
            Troop.AXE,
            List.of("src/main/resources/it/unibo/icons/battle/Axe.png",
                    "src/main/resources/it/unibo/icons/battle/AxeSelected.png"),
            Troop.SWORD,
            List.of("src/main/resources/it/unibo/icons/battle/Sword.png",
                    "src/main/resources/it/unibo/icons/battle/SwordSelected.png"),
            Troop.CATAPULT,
            List.of("src/main/resources/it/unibo/icons/battle/Hammer.png",
                    "src/main/resources/it/unibo/icons/battle/HammerSelected.png"),
            Troop.ARROW,
            List.of("src/main/resources/it/unibo/icons/battle/Mace.png",
                    "src/main/resources/it/unibo/icons/battle/MaceSelected.png"),
            Troop.SHIELD,
            List.of("src/main/resources/it/unibo/icons/battle/Shield01.png",
                    "src/main/resources/it/unibo/icons/battle/Shield01Selected.png"),
            Troop.HELMET,
            List.of("src/main/resources/it/unibo/icons/battle/Shield02.png",
                    "src/main/resources/it/unibo/icons/battle/Shield02Selected.png"),
            Troop.TOWER,
            List.of("src/main/resources/it/unibo/icons/battle/Shield03.png",
                    "src/main/resources/it/unibo/icons/battle/Shield03Selected.png"),
            Troop.DODGE,
            List.of("src/main/resources/it/unibo/icons/battle/Helmet.png",
                    "src/main/resources/it/unibo/icons/battle/HelmetSelected.png")

    );

    ImageIcon LIFE = new ImageIcon("src/main/resources/it/unibo/icons/battle/Life.png");
    ImageIcon DEATH = new ImageIcon("src/main/resources/it/unibo/icons/battle/Death.png");

    ImageIcon PASS = new ImageIcon("src/main/resources/it/unibo/icons/battle/Pass.png");
    ImageIcon SPIN = new ImageIcon("src/main/resources/it/unibo/icons/battle/Spin.png");

    static ImageIcon getImageIconFromTroop(final Troop troop, final boolean selected,Dimension size){
        return new ImageIcon(getImageFromTroop(troop,selected,size));

    }

    static ImageIcon getImageIconFromTroop(final Troop troop, final boolean selected){
        return (selected) ?
                new ImageIcon(troopUrl.get(troop).iterator().next()) :
                new ImageIcon(troopUrl.get(troop).get(troopUrl.get(troop).size()));
    }

    static Image getImageFromTroop(final Troop troop, final boolean selected,Dimension size){
        return getImageIconFromTroop(troop,selected).getImage().getScaledInstance(size.width,size.height,Image.SCALE_DEFAULT);

    }

    static ImageIcon getImageIconLive(final boolean alive,final Dimension size){
        return new ImageIcon(getImageLive(alive,size));
    }

    static ImageIcon getImageIconLive(final boolean alive){
        return (alive) ?
                LIFE :
                DEATH ;
    }

    static Image getImageLive(final boolean alive,final Dimension size){
        return  getImageIconLive(alive).getImage().getScaledInstance(size.width,size.height,Image.SCALE_DEFAULT);
    }

    static ImageIcon getImageIconPass(Dimension size){
        return  new ImageIcon(PASS.getImage().getScaledInstance(size.width,size.height,Image.SCALE_DEFAULT));
    }

    static ImageIcon getImageIconSpin(Dimension size){
        return  new ImageIcon(SPIN.getImage().getScaledInstance(size.width,size.height,Image.SCALE_DEFAULT));
    }

}
