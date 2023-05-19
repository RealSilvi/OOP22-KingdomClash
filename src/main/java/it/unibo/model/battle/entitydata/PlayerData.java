package it.unibo.model.battle.entitydata;

import it.unibo.model.battle.CellsImpl;
import it.unibo.view.battle.Troop;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PlayerData {

    /**
     * Adds the clicked troop into the field
     * @param key represents the position of the clicked troop
     */

    public void addPlayerTroop(Integer key);

    /**
     * Removes the clicked troop from the field
     * @param key represents the position of the clicked troop
     */

    public void removePlayerTroop(Integer key);

    /**
     * take the key and return its status
     * @param key the position which I need to get the status
     * @return CellsImpl, it means all the information about that position
     * (troop, clicked or not, chosen or not).
     */

    public CellsImpl getCells(Integer key);

    /**
     * Selects only the clicked troops.
     * @return the selected troops. It means all the troops in the field.
     */

    public List<Troop> getSelected();

    /**
     * Selects all the troops chosen.
     * @return all the chosen troops, that is,
     * only the troops that cannot be taken back in hand.
     */

    public List<Troop> getChosen();

    /**
     * Change all the troops in the hand, which are not clicked.
     * @return a Map of the troop with the current values (eventually modified) and the right position.
     */

    public Map<Integer, Troop> changeNotSelectedTroop();

    /**
     * Blocks the clicked troops, setting them to chosen.
     */

    public void setClickedToChosen();

    /**
     * Orders the field. Puts each troop against nothing or against the opposite,
     * creating a corrected field, with empty spaces and right troops.
     * @param botData the data of the bot,
     * and therefore the corresponding troops chosen,
     * which we want to compare with those of the player
     * @return the corrected player's field.
     */

    public List<Optional<Troop>> getOrderedField(BotData botData);

    /**
     * Blocks all the troops, clicked and not clicked, setting them to chosen.
     * Chosen means immutable, non-modifiable. You can take it back to your hand.
     */

    public void setAllChosen();

}
