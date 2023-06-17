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
     * //TODO finish javadoc
     *
     * @param winner
     */
    void showEndPanel(@Nonnull Boolean winner);

    void showTutorialPanel();

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
     * Update the player's slot
     *
     * @param troops Indicate which troop to put in which position.
     */
    void spinPlayerFreeSlot(Map<Integer, TroopType> troops);

    /**
     * Update the bots slot
     *
     * @param troops Indicate which troop to put in which position.
     */
    void spinBotFreeSlot(Map<Integer, TroopType> troops);

    /**
     * Display a power info of the player's troops.
     *
     * @param troopLv Foreach troop indicates if it's strong enough to defeat corresponding bots defense.
     */
    void drawInfoTable(Map<TroopType, Boolean> troopLv);

    /**
     * Update the Field with all the selected troops on it keeping in mind that can
     * exist empty slots.
     */
    void updateField(List<Optional<TroopType>> field);

    /**
     * Disable all the bots slots
     */
    void disableBotSlots();

    /**
     * Enable all the bots slots
     */
    void enableBotSlots();

    /**
     * Disable all the player's slots
     */
    void disablePlayerSlots();

    /**
     * Enable all the player's slots
     */
    void enablePlayerSlots();

    /**
     * Disable the spin button
     */
    void disableSpinButton();

    /**
     * Enable the spin button
     */
    void enableSpinButton();

    /**
     * Disable the Pass button
     */
    void disablePassButton();

    /**
     * Enable the pass button
     */
    void enablePassButton();


    /**
     * @return The BattlePanel to add at the frame
     */
    JPanel getPanel();

    void setActionListenersPlayerSlot(ActionListener actionListener);

    void setActionListenerSpinButton(ActionListener actionListener);

    void setActionListenerPass(ActionListener actionListener);

    void setBackActionListener(ActionListener actionListener);
}
