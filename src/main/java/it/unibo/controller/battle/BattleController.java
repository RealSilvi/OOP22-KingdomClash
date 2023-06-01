package it.unibo.controller.battle;

public interface BattleController {

    public void pass();

    public void spin();
    public void battle();

    public void clickedButtonPlayer(Integer key);

    public void update(Integer skip);

    public void playerLifeDecrease();

    public void botLifeDecrease();

}
