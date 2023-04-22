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
     * Change the status of the pressed button.
     * @param key: the key represents the button pressed.
     */

    void ChoosenButton(Integer key);



}
