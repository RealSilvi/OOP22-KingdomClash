package it.unibo.controller.battle;

public interface BattleController {

    public void addSubEvent(Event event, SubEvent subevent);

    public void removeSubEvent(Event event, SubEvent subevent);

    public void notify(Event event);

    public void Pass();

    public void Spin();

    public void ClickedButtonPlayer(Integer key);

    public void UnClickedButtonPlayer(Integer key);

    public void ClickedButtonBot(Integer key);

    public void UnClickedButtonBot(Integer key);

}
