package it.unibo.view.battle.panels.api;

import it.unibo.view.battle.Troop;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

/**
 * This interface show how to use the centerPanel of the BattlePanel.
 * Where Troops are displayed to fight.
 */
public interface FieldPanel {

    /**
     * Removes all the troops from the panel.
     */
    void restart();

    /**
     * Update the panel setting the troops on it.<br>
     * Note: there's could be empties slots.
     *
     * @param playerTroops Ordered list of troops to display.
     * @param botTroops    Ordered list of troops to display
     */
    void redraw(final List<Optional<Troop>> playerTroops, final List<Optional<Troop>> botTroops);

    JPanel getPanel();
}
