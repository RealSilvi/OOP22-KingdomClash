package it.unibo.view.battle.panels.utilities;

import it.unibo.view.battle.Troop;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;
public interface ImageIconsSupplier {

    Color PRIMARY_COLOR=new Color(168,19,48);
    Color SECONDARY_COLOR=new Color(250,160,21);

    ImageIcon BACKGROUND_FILL_PATTERN = new ImageIcon("src/main/resources/it/unibo/icons/battle/Background.png");

    Map<Troop, String> troopUrl = Map.of(
            Troop.AXE, "src/main/resources/it/unibo/icons/battle/Axe.png",
            Troop.SWORD, "src/main/resources/it/unibo/icons/battle/Sword.png",
            Troop.CATAPULT, "src/main/resources/it/unibo/icons/battle/Hammer.png",
            Troop.ARROW, "src/main/resources/it/unibo/icons/battle/Mace.png",
            Troop.SHIELD, "src/main/resources/it/unibo/icons/battle/Shield01.png",
            Troop.HELMET, "src/main/resources/it/unibo/icons/battle/Shield02.png",
            Troop.TOWER, "src/main/resources/it/unibo/icons/battle/Shield03.png",
            Troop.DODGE, "src/main/resources/it/unibo/icons/battle/Helmet.png");

    ImageIcon LIFE = new ImageIcon("src/main/resources/it/unibo/icons/battle/Life.png");
    ImageIcon DEATH = new ImageIcon("src/main/resources/it/unibo/icons/battle/Death.png");

    ImageIcon PASS = new ImageIcon("src/main/resources/it/unibo/icons/battle/Pass.png");
    ImageIcon SPIN = new ImageIcon("src/main/resources/it/unibo/icons/battle/Spin.png");
    ImageIcon INFO = new ImageIcon("src/main/resources/it/unibo/icons/battle/Info.png");

    ImageIcon CHECK = new ImageIcon("src/main/resources/it/unibo/icons/battle/Check.png");
    ImageIcon X = new ImageIcon("src/main/resources/it/unibo/icons/battle/X.png");

    ImageIcon INDICATOR = new ImageIcon("src/main/resources/it/unibo/icons/battle/Indicator.png");
    ImageIcon EXIT = new ImageIcon("src/main/resources/it/unibo/icons/battle/Exit.png");

    static ImageIcon getImageIconFromTroop(final Troop troop,Dimension size){
        return new ImageIcon(getImageFromTroop(troop,size));
    }

    static ImageIcon getImageIconFromTroop(final Troop troop){
        return new ImageIcon(troopUrl.get(troop));
    }

    static Image getImageFromTroop(final Troop troop,Dimension size){
        return getImageIconFromTroop(troop).getImage().getScaledInstance(size.width,size.height,Image.SCALE_DEFAULT);

    }

    static ImageIcon getImageIconLife(final boolean alive,final Dimension size){
        return (alive) ?
                new ImageIcon(LIFE.getImage().getScaledInstance(size.width,size.height,Image.SCALE_DEFAULT)) :
                new ImageIcon(DEATH.getImage().getScaledInstance(size.width,size.height,Image.SCALE_DEFAULT)) ;
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
    static ImageIcon getImageIconInfo(Dimension size){
        return  new ImageIcon(INFO.getImage().getScaledInstance(size.width,size.height,Image.SCALE_DEFAULT));
    }

    static ImageIcon getImageIconCheck(Dimension size){
        return  new ImageIcon(CHECK.getImage().getScaledInstance(size.width,size.height,Image.SCALE_DEFAULT));
    }

    static ImageIcon getImageIconIndicator(Dimension size){
        return  new ImageIcon(INDICATOR.getImage().getScaledInstance(size.width,size.height,Image.SCALE_DEFAULT));
    }

    static ImageIcon getImageExitIndicator(Dimension size){
        return  new ImageIcon(EXIT.getImage().getScaledInstance(size.width,size.height,Image.SCALE_DEFAULT));
    }

    static Font getPrimaryFont(){
        Font font;
        try {
            font= Font.createFont(Font.TRUETYPE_FONT,new File("src/main/resources/it/unibo/icons/battle/armalite.ttf"));
            GraphicsEnvironment g= GraphicsEnvironment.getLocalGraphicsEnvironment();
            g.registerFont(Font.createFont(Font.TRUETYPE_FONT,new File("src/main/resources/it/unibo/icons/battle/armalite.ttf")));
            return font.deriveFont(40f);
        }catch(IOException | FontFormatException e){
            font=Font.getFont("arial");
            return font.deriveFont(40f);
        }
    }

}
