package it.unibo.view;

import it.unibo.controller.SoundManager;
import it.unibo.view.menu.SouthPanel;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * This class shows public methods of the GameGui.
 */
public interface GameGuiInt {

    void addPanels(JPanel panel, String name);

    void showPanels(String name);

    /**
     * Shows the menu Panel.
     */
    void showMenuPanel();

    /**
     * Shows the info Panel.
     */
    void showInfoPanel();

    void showNamePanel();

    Boolean showNewGameOptions();

    void showLoadOptions();

    Integer showMenuSouthOptions();

    void setActionListenerButtons(ActionListener actionListener, SouthPanel.BUTTONS_NAME name);

    void setButtonsVisibility(SouthPanel.BUTTONS_NAME name, Boolean visibility);

    void setActionListenerNewGame(ActionListener actionListener);

    void setActionListenerStart(ActionListener actionListener);

    void setActionListenerLoad(ActionListener actionListener);

    void setMapBaseActionListener(ActionListener actionListener);

    void setMapBattleActionListener(ActionListener actionListener);

    void setActionListenerQuit(ActionListener actionListener);

    /**
     * Used to activate the next battle
     * against the bot.
     * @param level represents the level of
     * the battle.
     */
    void setActivateBattle(Integer level);


    /**
     * Set the level beaten.
     * @param levels represents the level beaten.
     */
    void setBeatenLevels(Integer levels);

    /**
     * Used to get the music of the game.
     * @return the class which manage the music
     * of the game.
     */
    SoundManager getSoundManager();

}
