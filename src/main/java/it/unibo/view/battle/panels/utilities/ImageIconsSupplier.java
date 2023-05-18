package it.unibo.view.battle.panels.utilities;

import it.unibo.view.battle.Troop;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public interface ImageIconsSupplier {

    ImageIcon BACKGROUND_FILL_PATTERN = new ImageIcon("src/main/resources/it/unibo/icons/battle/BackgroundFumoNero.png");

    Map<Troop, List<String>> troopUrl = Map.of(
            Troop.AXE,
            List.of("src/main/resources/it/unibo/icons/battle/AxeBlack.png",
                    "src/main/resources/it/unibo/icons/battle/AxeSelected.png"),
            Troop.SWORD,
            List.of("src/main/resources/it/unibo/icons/battle/SwordBlack.png",
                    "src/main/resources/it/unibo/icons/battle/SwordSelected.png"),
            Troop.CATAPULT,
            List.of("src/main/resources/it/unibo/icons/battle/HammerBlack.png",
                    "src/main/resources/it/unibo/icons/battle/HammerSelected.png"),
            Troop.ARROW,
            List.of("src/main/resources/it/unibo/icons/battle/MaceBlack.png",
                    "src/main/resources/it/unibo/icons/battle/MaceSelected.png"),
            Troop.SHIELD,
            List.of("src/main/resources/it/unibo/icons/battle/Shield01Black.png",
                    "src/main/resources/it/unibo/icons/battle/Shield01Selected.png"),
            Troop.HELMET,
            List.of("src/main/resources/it/unibo/icons/battle/Shield02Black.png",
                    "src/main/resources/it/unibo/icons/battle/Shield02Selected.png"),
            Troop.TOWER,
            List.of("src/main/resources/it/unibo/icons/battle/Shield03Black.png",
                    "src/main/resources/it/unibo/icons/battle/Shield03Selected.png"),
            Troop.DODGE,
            List.of("src/main/resources/it/unibo/icons/battle/HelmetBlack.png",
                    "src/main/resources/it/unibo/icons/battle/HelmetSelected.png")

    );

    ImageIcon LIFE = new ImageIcon("src/main/resources/it/unibo/icons/battle/LifeBlack.png");
    ImageIcon DEATH = new ImageIcon("src/main/resources/it/unibo/icons/battle/DeathBlack.png");

    ImageIcon PASS = new ImageIcon("src/main/resources/it/unibo/icons/battle/PassBlack.png");
    ImageIcon SPIN = new ImageIcon("src/main/resources/it/unibo/icons/battle/SpinBlack.png");

    ImageIcon CHECK = new ImageIcon("src/main/resources/it/unibo/icons/battle/CheckBlack.png");
    ImageIcon X = new ImageIcon("src/main/resources/it/unibo/icons/battle/XBlack.png");

    ImageIcon INDICATOR = new ImageIcon("src/main/resources/it/unibo/icons/battle/IndicatorBlack.png");
    ImageIcon VS = new ImageIcon("src/main/resources/it/unibo/icons/battle/VsBlack.png");

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

    static ImageIcon getImageIconX(Dimension size){
        return  new ImageIcon(X.getImage().getScaledInstance(size.width,size.height,Image.SCALE_DEFAULT));
    }

    static ImageIcon getImageIconCheck(Dimension size){
        return  new ImageIcon(CHECK.getImage().getScaledInstance(size.width,size.height,Image.SCALE_DEFAULT));
    }

    static ImageIcon getImageIconIndicator(Dimension size){
        return  new ImageIcon(INDICATOR.getImage().getScaledInstance(size.width,size.height,Image.SCALE_DEFAULT));
    }

    static ImageIcon getImageIconVs(Dimension size){
        return  new ImageIcon(VS.getImage().getScaledInstance(size.width,size.height,Image.SCALE_DEFAULT));
    }

}
