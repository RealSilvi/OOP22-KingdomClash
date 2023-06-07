package it.unibo.view.map;

import javax.swing.JPanel;

/**
 * A an interface for a panel composed of tiles that do something when clicked.
 */
public interface MapPanel {
    /**
     * Defines the type of tile that the button represents.
     */
    enum ButtonIdentification{
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

        private ButtonIdentification(String actionCommand) {
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
     * @param beatenLevels the number of beaten levels, must be less than
     * max levels
     */
    void setBeatenLevels(int beatenLevels);
}
