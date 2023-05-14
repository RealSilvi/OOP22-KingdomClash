package it.unibo.controller.battle;

import it.unibo.model.battle.BattleModel;
import it.unibo.model.battle.BattleModelImpl;
import it.unibo.model.data.FightData;
import it.unibo.model.data.GameData;

import java.util.*;

public class BattleControllerImpl implements  BattleController{

    private final Map<Event, List<SubEvent>> specific_event;
    private final BattleModel battleModel;
    private Optional<FightData> fightData;

    public BattleControllerImpl(BattleModel battleModel, GameData gameData){
        this.specific_event = new HashMap<>();
        for(Event event : Event.values()){
            this.specific_event.put(event,new ArrayList<>());
        }
        this.battleModel = battleModel;
        if(gameData.getFightData().isPresent()){
            this.fightData = gameData.getFightData();
        }
    }

    public BattleControllerImpl(GameData gameData){
        this.specific_event = new HashMap<>();
        for(Event event : Event.values()){
            this.specific_event.put(event,new ArrayList<>());
        }
        this.battleModel = new BattleModelImpl(gameData);
        if(gameData.getFightData().isPresent()){
            this.fightData = gameData.getFightData();
        }
    }

    @Override
    public void addSubEvent(Event event, SubEvent subevent) {
        specific_event.get(event).add(subevent);
    }

    @Override
    public void removeSubEvent(Event event, SubEvent subevent) {
        specific_event.get(event).remove(subevent);
    }

    @Override
    public void notify(Event event) {
        specific_event.get(event).forEach(x -> x.update(event));
    }

    public void Pass(){
        battleModel.BattlePass();
    }

    public void Spin(){
        battleModel.BattleSpin();
    }

    public void ClickedButtonPlayer(Integer key){
        fightData.get().getPlayerData().AddPlayerTroop(key);
    }

    public void UnClickedButtonPlayer(Integer key){
        fightData.get().getPlayerData().RemovePlayerTroop(key);
    }

    public void ClickedButtonBot(Integer key){
        fightData.get().getBotData().AddBotTroop(key);
    }

    public void UnClickedButtonBot(Integer key){
        fightData.get().getBotData().RemoveBotTroop(key);
    }



}
