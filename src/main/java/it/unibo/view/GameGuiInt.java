package it.unibo.view;

import it.unibo.controller.sound.SoundManagerImpl;
import it.unibo.view.menu.GameMenuImpl;
import it.unibo.view.menu.SouthPanel;

import javax.swing.JPanel;
import java.awt.event.ActionListener;

/**
 * This class shows public methods of the GameGui.
 */
public interface GameGuiInt {

    /**
     * Used to add new panels in the game.
     * @param panel panel to add.
     * @param name name to represent the panel.
     */
    void addPanels(JPanel panel, String name);

    /**
     * It takes care to show the correct panels.
     * @param name The name of the panel to show.
     */
    void showPanels(String name);

    /**
     * Shows the menu Panel.
     */
    void showMenuPanel();

    /**
     * Shows the info Panel.
     */
    void showInfoPanel();

    /**
     * Show the name panel, so the panel which contains
     * the possibility to write the player's name.
     */
    void showNamePanel();

    /**
     * It returns the result of the dialog panel, when
     * you click on "new game" and you have a previous save.
     * @return The result of the dialog panel.
     */
    Boolean showNewGameOptions();

    /**
     * Shows the dialog panel when you click on the load
     * button, and you don't have any past saves.
     */
    void showLoadOptions();

    /**
     * It takes care of changing the visibility of the menu's buttons.
     * @param name the name of the button.
     * @param visibility the visibility required to be set.
     */
    void setButtonsVisibilityMenu(GameMenuImpl.BUTTONS_MENU name, Boolean visibility);

    /**
     * Sets the action listener to the required buttons.
     * @param actionListener The action listener to set.
     * @param name The name of the button.
     */
    void setActionListenerButtons(ActionListener actionListener, SouthPanel.BUTTONS_SOUTH name);

    /**
     * It takes care of changing the visibility of the south panel's buttons.
     * @param name the name of the button.
     * @param visibility the visibility required to be set.
     */
    void setButtonsVisibility(SouthPanel.BUTTONS_SOUTH name, Boolean visibility);

    /**
     * Sets the action listener to the new game button.
     * @param actionListener the action listener to set.
     */
    void setActionListenerNewGame(ActionListener actionListener);

    /**
     * Sets the action listener to the continue button.
     * @param actionListener the action listener to set.
     */
    void setActionListenerContinue(ActionListener actionListener);

    /**
     * Sets the action listener to the start button.
     * @param actionListener the action listener to set.
     */
    void setActionListenerStart(ActionListener actionListener);

    /**
     * Sets the action listener to the load button.
     * @param actionListener the action listener to set.
     */
    void setActionListenerLoad(ActionListener actionListener);

    /**
     * Sets the action listener to the exit button.
     * @param actionListener the action listener to set.
     */
    void setActionListenerExit(ActionListener actionListener);

    /**
     * Sets the action listener to the buttons in the map used to come back
     * to the city.
     * @param actionListener the action listener to set.
     */
    void setMapBaseActionListener(ActionListener actionListener);

    /**
     * Sets the action listener to the buttons in the map used
     * to activate the battle.
     * @param actionListener the action listener to set.
     */
    void setMapBattleActionListener(ActionListener actionListener);

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
    SoundManagerImpl getSoundManager();

    /**
     * Gets the player's name.
     * @return The player's name.
     */
    String getPlayerName();

    /**
     * Closes the main frame.
     */
    void closeGui();

}
