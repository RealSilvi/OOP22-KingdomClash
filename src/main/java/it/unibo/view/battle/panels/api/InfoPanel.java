package it.unibo.view.battle.panels.api;

import it.unibo.model.data.TroopType;

import javax.swing.*;
import java.util.Map;

/**
 * Describe the behaviour of the InfoPanel.
 */
public interface InfoPanel {

    /**
     * Draws an info table which displayed if the player's troops
     * are strong enough to win on bots troops.
     *
     * @param powerTable Foreach troop there's a boolean value if the troopLV is high enough.
     */
    void drawTable(Map<TroopType, Boolean> powerTable);

    JPanel getPanel();
}
