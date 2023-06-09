package it.unibo.model.battle;

import it.unibo.model.data.TroopType;

import java.util.Map;

public interface BattleModel {

    Integer getCountedRound();

    /**
     * Takes care of passing the turn to the bot and managing its choices.
     */
    void battlePass(Integer finished);

    /**
     * It allows you to make a spin, it means
     * to make random troops appear between the choices of the player or bot.
     * The troops that change are only those that have not been selected.
     */
    Map<Integer, TroopType> battleSpin(Integer entity);

    /**
     * Takes care about the fighting player vs bot. It lets troops
     * fighting against each other, controlling the life of both entity.
     */

    Integer battleCombat(Integer position);

    void reset();

    void endFight(Boolean increment);

    Map<TroopType,Boolean> getInfoTable();

}
