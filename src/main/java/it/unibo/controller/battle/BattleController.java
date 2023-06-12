package it.unibo.controller.battle;

/**
 * The class deals to create a bridge
 * between model and gui, and helps
 * the communication.
 */
public interface BattleController {

    /**
     * Disable buttons and start the turn
     * of the bot.
     */
    void pass();

    /**
     * Spin player's troops.
     */
    void spin();

    /**
     * Start the battle between player and bot.
     */
    void battle();

    /**
     * It finishes the battle and return to the city.
     *
     * @param entity who win the battle.
     */
    void end(Integer entity);

    /**
     * Add or remove from the field the troop selected.
     *
     * @param key position of the troop clicked by the player.
     */

    void clickedButtonPlayer(Integer key);

    /**
     * Updates the field.
     *
     * @param skip is the number of position in the field to skip.
     *             With the skip, the update shows only the troops remained which has to fight.
     */

    void update(Integer skip);

    /**
     * Decrease player's life.
     */

    void playerLifeDecrease();

    /**
     * Decrease robot's life.
     */

    void botLifeDecrease();

}
