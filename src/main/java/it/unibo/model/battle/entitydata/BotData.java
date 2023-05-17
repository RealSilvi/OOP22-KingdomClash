package it.unibo.model.battle.entitydata;

import it.unibo.model.battle.CellsImpl;
import it.unibo.view.battle.Troop;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BotData {

    /**
     * Adds the clicked troop into the field
     * @param key represents the position of the clicked troop
     */

    public void AddBotTroop(Integer key);

    /**
     * Removes the clicked troop from the field
     * @param key represents the position of the clicked troop
     */

    public void RemoveBotTroop(Integer key);

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
     * Selects all the troops which are not clicked yet.
     * @return the not selected troops. It means all the troops not clicked.
     */

    public List<Troop> getNotSelected();

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
     * Selects a random troop between the not clicked troops.
     * @return the key, it means the position of the troop randomly selected.
     */

    public Integer selectRandomTroop();

    /**
     * Sees if a specific troop exist.
     * @param troop the troop to find.
     * @return true, if the troop exist between the selected ones.
     * Or false, if not.
     */

    public Boolean isMatch(Troop troop);

    /**
     * Finds the right key of the troop given in input,
     * searching this troop between the not clicked.
     * @param troop the not clicked troop that we want to find the key to.
     * @return the key, or position, of the troop.
     */

    public Integer getKeyFromTroop(Troop troop);

    /**
     * Orders the field. Puts each troop against nothing or against the opposite,
     * creating a corrected field, with empty spaces and right troops.
     * @param playerData the data of the player,
     * and therefore the corresponding troops chosen,
     * which we want to compare with those of the bot
     * @return the corrected bot's field.
     */

    public List<Optional<Troop>> getOrderedField(PlayerData playerData);

    /**
     * Blocks all the troops, clicked and not clicked, setting them to chosen.
     * Chosen means immutable, non-modifiable. You can take it back to your hand.
     */

    public void setAllChosen();

}
