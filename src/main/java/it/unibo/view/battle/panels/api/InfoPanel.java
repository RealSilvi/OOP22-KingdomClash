package it.unibo.view.battle.panels.api;

import it.unibo.view.battle.Troop;

import javax.swing.*;
import java.util.Map;

/**
 * Describe the behaviour of the InfoPanel.
 */
public interface InfoPanel {

    /**
     * Draws an info table which displayed if the player's troop
     * are strong enough to win on bot's troops.
     * @param powerTable Foreach troop there's a boolean value if the troopLV is high enough for this LV.
     */
    void drawTable(Map<Troop, Boolean> powerTable);

    JPanel getPanel();
}
