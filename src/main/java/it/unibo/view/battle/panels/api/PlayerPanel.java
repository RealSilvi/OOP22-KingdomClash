package it.unibo.view.battle.panels.api;

import it.unibo.model.data.TroopType;

import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * Describe the behaviour of southPanel and northPanel in the BattlePanel.<br>
 * Which contains the TroopButtons which that the user can choose before passing the round.
 */
public interface PlayerPanel {

    /**
     * Update the displayed troops.
     *
     * @param troops define in which position put the new troop.
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
     * Adds the action listener to all the TroopButtons.
     *
     * @param actionListener gives instruction at all the TroopButtons.
     */
    void setActionListenersSlot(ActionListener actionListener);

    /**
     * Returns itself in a JPanel.
     *
     * @return this instance like a JPanel.
     */
    JPanel getPanel();
}
