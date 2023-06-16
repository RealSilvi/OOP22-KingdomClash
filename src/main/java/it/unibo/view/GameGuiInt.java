package it.unibo.view;

import it.unibo.controller.SoundManager;

import java.awt.event.ActionListener;

/**
 * This class shows public methods of the GameGui.
 */
public interface GameGuiInt {

    /**
     * Shows the menu Panel.
     */
    void showMenuPanel();

    /**
     * Shows the info Panel.
     */
    void showInfoPanel();

    /**
     * Shows the battle Panel.
     */
    void showBattle();

    /**
     * Shows the city Panel.
     */
    void showCity();

    /**
     * Shows the map Panel.
     */
    void showMap();

    void showNamePanel();

    Boolean showNewGameOptions();

    void showLoadOptions();

    void setActionListenerNewGame(ActionListener actionListener);

    void setActionListenerStart(ActionListener actionListener);

    void setActionListenerSave(ActionListener actionListener);

    void setActionListenerLoad(ActionListener actionListener);

    void setActionListenerCity(ActionListener actionListener);

    void setActionListenerMap(ActionListener actionListener);

    /**
     * Takes the action listener to show the map.
     * @return The action listener which shows the map.
     */
    ActionListener getActionListenerMap();

    /**
     * Used to activate the next battle
     * against the bot.
     * @param level represents the level of
     * the battle.
     */
    void setActivateBattle(Integer level);

    /**
     * Used to get the music of the game.
     * @return the class which manage the music
     * of the game.
     */
    SoundManager getSoundManager();

    /**
     * Set the level beaten.
     * @param levels represents the level beaten.
     */
    void setBeatenLevels(Integer levels);

}
