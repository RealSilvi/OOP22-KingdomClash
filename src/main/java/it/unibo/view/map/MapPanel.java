package it.unibo.view.map;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

/**
 * A an interface for a panel composed of tiles that do something when clicked.
 */
public interface MapPanel {
    /**
     * Defines the type of tile that the button represents.
     */
    enum ButtonIdentification {
        /**
         * A normal tile.
         */
        TILE("tile"),
        /**
         * The player's base.
         */
        PLAYER("player"),
        /**
         * An enemy.
         */
        ENEMY("enemy"),
        /**
         * A defeated enemy.
         */
        DEATH("death");

        private String actionCommand;

        ButtonIdentification(final String actionCommand) {
            this.actionCommand = actionCommand;
        }

        /**
         * @return the command that is assigned to the button representing
         * the tile
         */
        public String getActionCommand() {
            return this.actionCommand;
        }
    }
    /**
     * @return returns the map as a JPanel in order to use it as a GUI
     */
    JPanel getAsJPanel();

    /**
     * Sets the number of beaten levels in order to display it to the player.
     * @param beatenLevels  the number of beaten levels, must be less than
     *                      max levels
     */
    void setBeatenLevels(int beatenLevels);
    /**
     * Sets what enemy battle button is currently active.
     * @param battleIndex   the index of the button, values greater than 0
     *                      represent an enemy, less or equal than 0 represents
     *                      all enemies
     */
    void setActiveBattle(int battleIndex);
    /**
     * Sets an action listener linked to the enemy base's buttons.
     * @param battleActionListener      the listener to add
     */
    void setBattleActionListener(ActionListener battleActionListener);
    /**
     * Removes the action listener linked to the enemy base's buttons.
     * @param battleActionListenerToRemove      the listener to remove
     */
    void clearBattleActionListener(ActionListener battleActionListenerToRemove);
    /**
     * Sets an action listener linked to the player's base buttons.
     * @param baseActionListener      the listener to add
     */
    void setBaseActionListener(ActionListener baseActionListener);
    /**
     * Removes the action listener linked to the player's base buttons.
     * @param baseActionListenerToRemove      the listener to remove
     */
    void clearBaseActionListener(ActionListener baseActionListenerToRemove);
}
