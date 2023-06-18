package it.unibo.view.battle.panels.api;

import it.unibo.model.data.TroopType;

import javax.swing.JPanel;
import java.util.Map;

/**
 * Describe the behaviour of the InfoPanel in the EastPanel in BattlePanel.<br>
 * Which displays information of the troops in the current battle.
 */
public interface InfoPanel {

    /**
     * Draws an info table which displayed if the player's troops
     * are strong enough to win on bots troops.
     *
     * @param powerTable foreach troop there's a boolean value if the troop level
     *                   is high enough to attack/defend unconditionally.
     */
    void drawTable(Map<TroopType, Boolean> powerTable);

    /**
     * Returns itself in a JPanel.
     *
     * @return this instance like a JPanel.
     */
    JPanel getPanel();
}
