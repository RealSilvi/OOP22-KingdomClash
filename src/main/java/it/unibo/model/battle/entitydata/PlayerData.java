package it.unibo.model.battle.entitydata;

import it.unibo.model.battle.CellsImpl;
import it.unibo.view.battle.Troop;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PlayerData {

    public void AddPlayerTroop(Integer key);

    public void RemovePlayerTroop(Integer key);

    public List<Troop> getSelected();

    public List<Troop> getChosen();

    public Map<Integer, CellsImpl> changeNotSelectedTroop();

    public void setClickedToChosen();

    public List<Optional<Troop>> getOrderedField(BotData botData);

    public void setAllChosen();

}
