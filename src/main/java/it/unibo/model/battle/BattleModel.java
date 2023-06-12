package it.unibo.model.battle;

import it.unibo.model.data.TroopType;

import java.util.Map;

/**
 * Interface created to set functions used during the battle.
 */
public interface BattleModel {

    /**
     * Takes the number of the actual round.
     * @return The number of the round.
     */
    Integer getCountedRound();

    /**
     * Takes care of passing the turn to the bot and managing its choices.
     * @param finished It is used to understand if the player has already chosen
     * all the troops or not.
     */
    void battlePass(Integer finished);

    /**
     * It allows you to make a spin, it means
     * to make random troops appear between the choices of the player or bot.
     * The troops that change are only those that have not been selected.
     * @param entity It is used to represent if you want to spin player or bot.
     * @return A map which contains only the troops changed with the spin,
     * and their positions.
     */
    Map<Integer, TroopType> battleSpin(Integer entity);

    /**
     * Takes care about the fighting player vs bot. It lets troops
     * fighting against each other, controlling the life of both entity.
     * @param position The position needed to take the troop which has to fight.
     * @return A value which represent which one between bot or player lose one life.
     * If it is remained only one life, then the return will represent player's victory,
     * or robot's victory.
     */

    Integer battleCombat(Integer position);

    /**
     * This method reset the battle. Reset the counted round to zero.
     * Puts all the player's and robot's troops to not clicked, and then
     * do a spin to change it.
     */
    void reset();

    /**
     * This method it's similar to the reset, but it is used
     * when player or bot lose the total battle.
     * reset the player and bot life, and increment bots troops
     * to the next level.
     * @param increment If it is true, robot's troops get an increment
     * of 1 level on each troop.
     */
    void endFight(Boolean increment);

    /**
     * Used to get information to see if player troops can
     * beat the corresponding enemy troop.
     * @return A map with all the existent troops, and true if
     * the troop can beat its corresponding one, or false in the other case.
     */
    Map<TroopType, Boolean> getInfoTable();

}
