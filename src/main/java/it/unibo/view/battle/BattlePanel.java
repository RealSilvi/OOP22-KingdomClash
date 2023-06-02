package it.unibo.view.battle;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Defines all the methods that the controller can use to control a BattlePanel.
 */
public interface BattlePanel {

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
    void spinPlayerFreeSlot(final Map<Integer, Troop> troops);

    /**
     * Update the bot's slot
     *
     * @param troops Indicate which troop to put in which position.
     */
    void spinBotFreeSlot(final Map<Integer, Troop> troops);

    /**
     * Display a power info of the player's troops.
     *
     * @param troopLv Foreach troop indicates if it's strong enough to defeat corresponding bot's defense.
     */
    void drawInfoTable(final Map<Troop, Boolean> troopLv);

    /**
     * Update the Field with all the selected troops on it keeping in mind that can
     * exist empty slots.
     *
     * @param playerTroops Sorted List of player's troops.
     * @param botTroops    Sorted List od bot's tropps
     */
    void updateField(final List<Optional<Troop>> playerTroops, final List<Optional<Troop>> botTroops);

    /**
     * Disable all the bot's slots
     */
    void disableBotSlots();

    /**
     * Enable all the bot's slots
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
}
