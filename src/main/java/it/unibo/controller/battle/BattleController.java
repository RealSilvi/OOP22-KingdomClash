package it.unibo.controller.battle;

public interface BattleController {

    /**
     * Disable buttons and start the turn
     * of the bot
     */
    public void pass();

    /**
     * Spin player's troops
     */
    public void spin();

    /**
     * Start the battle between player and bot
     */
    public void battle();

    /**
     * It finishes the battle and return to the city
     * @param entity who win the battle
     */
    public void end(Integer entity);

    /**
     * Add or remove from the field the troop selected
     * @param key position of the troop clicked by the player
     */

    public void clickedButtonPlayer(Integer key);

    /**
     * Updates the field
     * @param skip is the number of position in the field to skip.
     * With the skip, the update shows only the troops remained which has to fight
     */

    public void update(Integer skip);

    /**
     * Decrease player's life
     */

    public void playerLifeDecrease();

    /**
     * Decrease bot's life
     */

    public void botLifeDecrease();

}
