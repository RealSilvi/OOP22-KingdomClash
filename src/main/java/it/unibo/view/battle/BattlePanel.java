package it.unibo.view.battle;

import it.unibo.model.data.TroopType;

import javax.annotation.Nonnull;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Defines all the methods that the controller can use to control a BattlePanel.
 */
public interface BattlePanel {

    /**
     * Shows the EndPanel.
     *
     * @param winner true if the player win the battle.
     */
    void showEndPanel(@Nonnull Boolean winner);

    /**
     * Shows the TutorialPanel.
     */
    void showTutorialPanel();

    /**
     * Shows the Game Panel.
     */
    void showGamePanel();

    /**
     * Display that the player loses a health point.
     */
    void hitPlayer();

    /**
     * Display that the bot loses a health point.
     */
    void hitBot();

    /**
     * Update the player's slot.
     *
     * @param troops indicate which troop to put in which position.
     */
    void spinPlayerFreeSlot(Map<Integer, TroopType> troops);

    /**
     * Update the bots slot.
     *
     * @param troops indicate which troop to put in which position.
     */
    void spinBotFreeSlot(Map<Integer, TroopType> troops);

    /**
     * Display a power info of the player's troops.
     *
     * @param troopLv foreach troop indicates if it's strong enough to defeat corresponding bots troop.
     */
    void drawInfoTable(Map<TroopType, Boolean> troopLv);

    /**
     * Update the Field with all the selected troops on it keeping in mind that can
     * exist empty slots.
     */
    void updateField(List<Optional<TroopType>> field);

    /**
     * Disable all the bots slots.
     */
    void disableBotSlots();

    /**
     * Enable all the bots slots.
     */
    void enableBotSlots();

    /**
     * Disable all the player's slots.
     */
    void disablePlayerSlots();

    /**
     * Enable all the player's slots.
     */
    void enablePlayerSlots();

    /**
     * Disable the spin button.
     */
    void disableSpinButton();

    /**
     * Enable the spin button.
     */
    void enableSpinButton();

    /**
     * Disable the Pass button.
     */
    void disablePassButton();

    /**
     * Enable the pass button.
     */
    void enablePassButton();

    /**
     * Set the action listener in the player's slots and in the bots slots.
     *
     * @param actionListener to add in the players slots.
     */
    void setActionListenersPlayerSlot(ActionListener actionListener);

    /**
     * Set the action listener in the spin button.
     *
     * @param actionListener to add in the spin button.
     */
    void setActionListenerSpinButton(ActionListener actionListener);

    /**
     * Set the action listener in the pass button.
     *
     * @param actionListener to add in the pass button.
     */
    void setActionListenerPass(ActionListener actionListener);

    /**
     * Set the action listener to the exit button.
     *
     * @param actionListener to add in the exit button.
     */
    void setBackActionListener(ActionListener actionListener);

    /**
     * Returns itself in a JPanel.
     *
     * @return this instance like a JPanel.
     */
    JPanel getPanel();
}
