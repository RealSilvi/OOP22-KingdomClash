package it.unibo.view;

import it.unibo.controller.SoundManager;

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
