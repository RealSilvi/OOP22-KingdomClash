package it.unibo.view.menu;

import javax.swing.JPanel;
import java.awt.event.ActionListener;

/**
 * The interface contains methods to set action listeners, and get the panel.
 * This class it's the panel of the menu of the game.
 */
public interface GameMenu {

    /**
     * Gets the current panel.
     * @return The panel used to incorporate the buttons.
     */
    JPanel getPanel();

    /**
     * Sets the action listener to the continue button.
     * @param actionListener the action listener to set.
     */
    void setActionListenerContinue(ActionListener actionListener);

    /**
     * It takes care of changing the visibility of the menu's buttons.
     * @param name the name of the button.
     * @param visibility the visibility required to be set.
     */
    void setButtonsVisibilityMenu(GameMenuImpl.BUTTONSMENU name, Boolean visibility);

    /**
     * Sets the action listener to the info button.
     * @param actionListener the action listener to set.
     */
    void setActionListenerInfo(ActionListener actionListener);

    /**
     * Sets the action listener to the new game button.
     * @param actionListener the action listener to set.
     */
    void setActionListenerNewGame(ActionListener actionListener);

    /**
     * Sets the action listener to the load button.
     * @param actionListener the action listener to set.
     */
    void setActionListenerLoad(ActionListener actionListener);

    /**
     * Sets the action listener to the music button.
     * @param actionListener the action listener to set.
     */
    void setActionListenerMusic(ActionListener actionListener);

    /**
     * Sets the action listener to the exit button.
     * @param actionListener the action listener to set.
     */
    void setActionListenerExit(ActionListener actionListener);

}
