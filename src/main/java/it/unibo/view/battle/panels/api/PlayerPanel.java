package it.unibo.view.battle.panels.api;

import it.unibo.model.data.TroopType;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * This interface show how to use southPanel and northPanel of the BattlePanel.
 * <br>
 * Show how to use the TroopButtons which the user can choose before passing the round.
 */
public interface PlayerPanel {

    /**
     * Update the displayed troops
     *
     * @param troops define in which position put the new troop
     */
    void update(Map<Integer, TroopType> troops);

    /**
     * Disable all the buttons.
     */
    void disableAllSlots();

    /**
     * Enable all the buttons.
     */
    void enableAllSlots();

    /**
     * @param actionListener gives instruction at all the TroopButtons.
     */
    void setActionListenersSlot(ActionListener actionListener);

    JPanel getPanel();
}
