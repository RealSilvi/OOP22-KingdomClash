package it.unibo.view.menu.extensiveclasses;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.data.GameConfiguration;
import it.unibo.model.data.TroopType;
import it.unibo.view.GameGui;
import it.unibo.view.battle.config.PathIconsConfiguration;
import it.unibo.view.utilities.ImageIconsSupplier;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class ImageTextArea extends JTextArea {
    private Image backgroundImage;
    private static final double INCREMENT_HEIGHT = 1.175;
    private static final double IMAGE_WIDTH_DIMENSION =  0.7;
    private static final double IMAGE_HEIGHT_DIMENSION =  1.2;
    private PathIconsConfiguration pathIconsConfiguration;

    public ImageTextArea(GameConfiguration gameConfiguration) {
        super();
        this.pathIconsConfiguration = gameConfiguration.getPathIconsConfiguration();
    }

    @SuppressFBWarnings(value = "EI2",
            justification = "I want to use the background in input to represent the image in the textArea")
    public void setImage(final Image backgroundImage){
        this.backgroundImage = backgroundImage;
        setOpaque(false);
        repaint();
    }

    public void setTextImage(final Dimension dimension){
        System.out.println(dimension.width + "   2   " + dimension.height);
        int lenght = TroopType.values().length;
        int i;
        setForeground(Color.WHITE);
        setText("Welcome in the tutorial of Kingdom Clash:\n" +
                "In the game are present 3 principal panels which you can switch:\n" +
                "-City\n" +
                "-Map\n" +
                "-Battle\n" +
                "You can do different things in each panel.\n" + "The City:\n" +
                "When you start a new game, you will be teleported to the city. The city is the place of your base, you can place\n " +
                "constructions to upgrade the speed of your resources, so earn it more. There are two resources:\n" +
                "-Wheat\n" +
                "-Wood\n" +
                "Wheat its used to upgrade your troops before the battle and during the game,\n " +
                "instead wood its used to upgrade your constructions.\n" +
                "Different constructions give different resources. In this strategic game, you can manage your city in any way you want.\n" +
                "The Map:\n" +
                "In the map you will find your city at the top left and at the right the enemy's bases.\n" +
                "Each base of the enemy its a different level where the bot will have different levels of troops.\n" +
                "You can do the next level only if you have beaten the level before. When you beat a level you see the base destroyed,\n " +
                "and the new base available (so the next level is available only if you have won the fight against the bot \n" +
                "in the level before). You have to fight against each base step by step. There is no time, you can fight\n " +
                "when you want and upgrade your city and troops between each level without time limit.\n" +
                "You complete and finish the game when you destroy all the enemy's bases.\n" +
                "The Battle:\n" +
                "When you click on an enemy's base, the battle starts. You will spin your troops and choose which troops you want\n " +
                "into the field. After 3 rounds the fight starts and the corresponding life decrease. You win the battle when\n " +
                "the bot lose all its life, otherwise you lose, and you have to repeat the level (you don't lose your upgrades).\n" +
                "You can find more information about the battle, inside of the battle clicking on the 'info' button.\n" +
                "In the game there are " + lenght + " different troops, and each troop can have only one correspondence.\n" +
                "There are " + lenght / 2 + " correspondences:\n     \n");
        double incrementHeight = INCREMENT_HEIGHT;
        for(i=0; i < lenght / 2; i++){
            int finalI = i;
            JLabel label = new JLabel(ImageIconsSupplier.getScaledImageIcon(pathIconsConfiguration.getTroop(Arrays.stream(TroopType.values()).
                            filter(x -> x.ordinal() == finalI).toList().get(0)),
                    new Dimension(((int) (dimension.getWidth() / 30)), ((int) (dimension.getHeight() / 30)))));

            label.setSize(new Dimension((int) (dimension.getWidth() / 20), ((int) (dimension.getHeight() * (incrementHeight)))));
            System.out.println(label.getSize().width + "   LABEL   " + label.getSize().height);
            label.setVerticalTextPosition(JLabel.TOP);
            add(label);
            append("                    <---->               ");

            JLabel label1 = new JLabel(ImageIconsSupplier.getScaledImageIcon(pathIconsConfiguration.getTroop(Arrays.stream(TroopType.values())
                            .filter(x -> x.ordinal() == (lenght / 2) + finalI).toList().get(0)),
                    new Dimension(((int) (dimension.getWidth() / 30)), ((int) (dimension.getHeight() / 30)))));

            label1.setSize(new Dimension((int) (dimension.getWidth() / 5), ((int) (dimension.getHeight() * incrementHeight))));

            add(label1);
            incrementHeight += 0.085;
            append("\n       \n");
        }
        append("If an attack troop doesn't find any troop of the enemy in front of it, then the enemy lose 1 life.\n" +
                "If an attack troop finds a defense troop against it, and they have the same level, then none take damage.\n" +
                "If an attack troop finds a defense troop against it, and the level of the attack troop is higher, then the\n" +
                "enemy lose one life. If a defense troop has its level higher or equal at the level of the attack troop, \n" +
                " doesn't change anything, none take damage.\n" +
                "All this rules are the same for both teams.\n" +
                "At the finish of a battle, if you win, at the next battle the enemy's troops levels will increase by one.\n" +
                "Now you are ready to play and complete the game. GOOD LUCK AND ENJOY THE GAME!!!");
    }

    @Override
    public void paint(final Graphics graphics) {
        graphics.drawImage(backgroundImage, 0, 0, null);
        super.paint(graphics);
    }

}
