package it.unibo.view.menu.extensiveclasses;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.data.TroopType;

import javax.swing.JTextArea;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serial;

/**
 * This class is used to extend the JTextArea class.
 * It helps in creating in a faster way a textArea with specific values.
 */
public final class ImageTextArea extends JTextArea {

    @Serial
    private static final long serialVersionUID = 123456789L;

    transient private Image backgroundImage;

    /**
     * The constructor sets the text of the textArea.
     */
    public ImageTextArea() {
        super();
        final int length = TroopType.values().length;
        setForeground(Color.WHITE);
        setText("Welcome in the tutorial of Kingdom Clash:\n"
                + "In the game are present 3 principal panels which you can switch:\n"
                + "-City\n"
                + "-Map\n"
                + "-Battle\n"
                + "You can do different things in each panel.\n"
                + "The City:\n"
                + "When you start a new game, you will be teleported to the city. "
                + "The city is the place of your base, you can place "
                + "constructions to upgrade the speed of your resources, so earn it more. There are two resources:\n"
                + "-Wheat\n"
                + "-Wood\n"
                + "Wheat its used to upgrade your troops before the battle and during the game, "
                + "instead wood its used to upgrade your constructions.\n"
                + "Different constructions give different resources. In this strategic game, "
                + "you can manage your city in any way you want.\n"
                + "The Map:\n"
                + "In the map you will find your city at the top left and at the right the enemy's bases. "
                + "Each base of the enemy its a different level where the bot will have different levels of troops. \n"
                + "You can do the next level only if you have beaten the level before. "
                + "When you beat a level you see the base destroyed,\n "
                + "and the new base available (so the next level is available only "
                + "if you have won the fight against the bot "
                + "in the level before). You have to fight against each base step by step. "
                + "There is no time, you can fight\n "
                + "when you want and upgrade your city and troops between each level without time limit. "
                + "You complete and finish the game when you destroy all the enemy's bases.\n"
                + "The Battle:\n"
                + "When you click on an enemy's base, the battle starts. You will spin "
                + "your troops and choose which troops you want "
                + "into the field. After 3 rounds the fight starts and the corresponding life decrease. "
                + "You win the battle when\n "
                + "the bot lose all its life, otherwise you lose, and you have to repeat "
                + "the level (you don't lose your upgrades).\n"
                + "You can find more information about the battle, inside of the battle clicking on the 'info' button. "
                + "In the game there are " + length + " different troops, and each troop can have only one correspondence. "
                + "There are " + length / 2 + " correspondences (you can see it on the left).\n "
                + "If an attack troop doesn't find any troop of the enemy in front of it, then the enemy lose 1 life. "
                + "If an attack troop finds a defense troop against it, and they have the same level, then none take damage.\n"
                + "If an attack troop finds a defense troop against it, and the level of the attack troop is higher, then the "
                + "enemy lose one life. If a defense troop has its level higher or equal at the level of the attack troop, \n"
                + " doesn't change anything, none take damage.\n"
                + "All this rules are the same for both teams. "
                + "At the finish of a battle, if you win, at the next battle the enemy's troops levels will increase by one.\n"
                + "Now you are ready to play and complete the game. GOOD LUCK AND ENJOY THE GAME!!!");
    }

    /**
     * Sets the image.
     * @param backgroundImage The image to set.
     */
    @SuppressFBWarnings(value = "EI2",
            justification = "I want to use the background in input to represent the image in the textArea")
    public void setImage(final Image backgroundImage) {
        this.backgroundImage = backgroundImage;
        setOpaque(false);
        repaint();
    }

    @Override
    public void paint(final Graphics graphics) {
        if (this.backgroundImage != null) {

            for (int x = 0; x < getWidth(); x += backgroundImage.getWidth(null)) {
                for (int y = 0; y < getHeight(); y += backgroundImage.getHeight(null)) {
                    graphics.drawImage(backgroundImage, x, y, null);
                }
            }
        }
        super.paint(graphics);
    }

}
