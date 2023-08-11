package it.unibo.model.battle.entitydata;

import it.unibo.model.battle.CellsImpl;
import it.unibo.model.data.TroopType;

import java.util.List;
import java.util.Map;

/**
 * Class used to contain and manipulate
 * player data and bot data, during the battle.
 */
public interface EntityData {

    /**
     * Used to set the troops in the hand.
     * @param entityTroop new entity's troops.
     */
    void setEntityTroop(Map<Integer, CellsImpl> entityTroop);

    /**
     * Used to ask the troops in the hand.
     * @return entity's troops
     */

    Map<Integer, CellsImpl> getEntityTroop();

    /**
     * Adds the clicked troop into the field.
     * @param key represents the position of the clicked troop.
     */

    void addEntityTroop(Integer key);

    /**
     * Removes the clicked troop from the field.
     * @param key represents the position of the clicked troop.
     */

    void removeEntityTroop(Integer key);

    /**
     * take the key and return its status.
     * @param key the position which I need to get the status.
     * @return CellsImpl, it means all the information about that position,
     * (troop, clicked or not, chosen or not).
     */

    CellsImpl getCells(Integer key);

    /**
     * Selects only the clicked troops.
     * @return the selected troops. It means all the troops in the field.
     */

    List<TroopType> getSelected();

    /**
     * Selects all the troops which are not clicked yet.
     * @return the not selected troops. It means all the troops not clicked.
     */

    List<TroopType> getNotSelected();

    /**
     * Change all the troops in the hand, which are not clicked.
     * @return a Map of the troop with the current values (eventually modified) and the right position.
     */

    Map<Integer, TroopType> changeNotSelectedTroop();

    /**
     * Blocks the clicked troops, setting them to chosen.
     */

    void setClickedToChosen();

    /**
     * Selects a random troop between the not clicked troops.
     * @return the key, it means the position of the troop randomly selected.
     */

    Integer selectRandomTroop();

    /**
     * Sees if a specific troop exist.
     * @param troop the troop to find.
     * @return true, if the troop exist between the selected ones,
     * or false if not.
     */

    Boolean isMatch(TroopType troop);

    /**
     * Finds the right key of the troop given in input,
     * searching this troop between the not clicked.
     * @param troop the not clicked troop that we want to find the key to.
     * @return the key, or position, of the troop.
     */

    Integer getKeyFromTroop(TroopType troop);

    /**
     * Blocks all the troops, clicked and not clicked, setting them to chosen.
     * Chosen means immutable, non-modifiable. You can't take it back to your hand.
     */

    void setAllChosen();

    /**
     * Return the number of the total troops possible in the field.
     * @return The amount of the possible troops.
     */
    Integer getTotalTroops();

}
