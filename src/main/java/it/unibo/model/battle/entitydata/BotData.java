package it.unibo.model.battle.entitydata;

import it.unibo.model.battle.CellsImpl;
import it.unibo.view.battle.Troop;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BotData {

    public void AddBotTroop(Integer key);

    public void RemoveBotTroop(Integer key);

    public List<Troop> getSelected();

    public List<Troop> getNotSelected();

    public List<Troop> getChosen();

    public Map<Integer, CellsImpl> changeNotSelectedTroop();

    public Integer selectRandomTroop();

    public Boolean isMatch(Troop troop);

    public Integer getKeyFromTroop(Troop troop);

    public List<Optional<Troop>> getOrderedField(PlayerData playerData);

}
