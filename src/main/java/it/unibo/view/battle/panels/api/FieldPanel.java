package it.unibo.view.battle.panels.api;

import it.unibo.view.battle.Troop;

import java.util.List;
import java.util.Optional;

/**
 * Describe the behaviour of the fieldPanel. Where Troops are displayed to fight.
 */
public interface FieldPanel {

    /**
     *
     * @param field Ordered list of troops to display.
     *              The first field.size()/2 are bot's troops.
     *              The second half are player's troops.
     */
    void redraw(List<Optional<Troop>> field);
}
