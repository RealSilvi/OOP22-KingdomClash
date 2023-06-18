package it.unibo.view.battle.panels.api;

import it.unibo.model.data.TroopType;

import javax.swing.JPanel;
import java.util.List;
import java.util.Optional;

/**
 * Describe the behaviour of the centerPanel in the BattlePanel.<br>
 * Which contains Troop labels to represent the army's.
 */
public interface FieldPanel {

    /**
     * Removes all the troops from the panel.
     */
    void restart();

    /**
     * Update the panel setting the troops on it.
     *
     * @param field in the list set <code> Optional.of(TroopType) </code>
     *              to set the troop or <code>Optional.empty</code> to remove the troop.<br>
     *              Note: if the size of the List doesn't match the size of the spots the call
     *              will be ignored.
     */
    void redraw(List<Optional<TroopType>> field);

    /**
     * Returns itself in a JPanel.
     *
     * @return this instance like a JPanel.
     */
    JPanel getPanel();
}
