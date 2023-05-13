package it.unibo.model.battle;

public interface BattleModel {

    /**
     * Takes care of passing the turn to the bot and managing its choices.
     */
    void BattlePass();

    /**
     * It allows you to make a spin, it means
     * to make random troops appear between the choices of the player or bot.
     * The troops that change are only those that have not been selected.
     */
    void BattleSpin();

    /**
     * Takes care about the fighting player vs bot. It lets troops
     * fighting against each other, controlling the life of both entity.
     */

    void BattleCombat();

}
