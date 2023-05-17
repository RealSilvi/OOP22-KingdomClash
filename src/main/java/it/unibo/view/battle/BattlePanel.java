package it.unibo.view.battle;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Defines all the methods that the controller can use to control the Gui.
 */
public interface BattlePanel {

    /**
     * Display that the player got a point life less.
     */
    void hitPlayer();

    /**
     * Display that the bot got a point life less.
     */
    void hitBot();

    /**
     * Spin the player's slots.
     */
    void spinPlayerFreeSlot(Map<Integer,Troop> troops);

    /**
     * Spin the bot's slots.
     */
    void spinBotFreeSlot(Map<Integer,Troop> troops);

    /**
     * Display a power info table of the player.
     * @param troopLv Foreach troop indicates if it's strong enough.
     */
    void drawInfoTable(final Map<Troop,Boolean> troopLv);

    /**
     * Display the new field with the troops on it.
     * @param playerTroops Indicates if the spots on the field are free or there's a troop in it. <br>
     *              Note: The first half indicates the bot's field and the second half indicates the
     *              player's field.
     */
    void updateField(final List<Optional<Troop>> playerTroops,final List<Optional<Troop>> botPlayer);

    /**
     * Disable all the bot's slots
     */
    void disableBotSlots();

    /**
     * Enable all the not selected bot's slots
     */
    void enableBotSlots();

    /**
     * Disable all the player's slots
     */
    void disablePlayerSlots();

    /**
     * Enable all the not selected player's slots
     */
    void enablePlayerSlots();

    /**
     * Disable the spin
     */
    void disableSpinButton();

    /**
     * Enable the spin
     */
    void enableSpinButton();

    /**
     * Disable the Pass
     */
    void disablePassButton();

    /**
     * Enable the pass
     */
    void enablePassButton();

    JPanel getPanel();
}
