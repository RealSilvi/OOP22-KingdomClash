package it.unibo.controller.battle;

public interface BattleController {

    public void pass();

    public void spin(Integer entity);

    public void clickedButtonPlayer(Integer key);

    public void update();

    public void playerLifeDecrease();

    public void botLifeDecrease();

}
