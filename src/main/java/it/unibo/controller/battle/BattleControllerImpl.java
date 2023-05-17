package it.unibo.controller.battle;

import it.unibo.model.battle.BattleModel;
import it.unibo.model.battle.BattleModelImpl;
import it.unibo.model.data.FightData;
import it.unibo.model.data.GameData;
import it.unibo.view.battle.BattlePanel;
import it.unibo.view.battle.BattlePanelImpl;

import java.util.*;

public class BattleControllerImpl implements  BattleController{

    public static final int PLAYER = 1;
    private final BattleModel battleModel;
    private Optional<FightData> fightData;
    private final BattlePanel battlePanel;

    public BattleControllerImpl(BattleModel battleModel, GameData gameData){
        this.battleModel = battleModel;
        this.battlePanel = new BattlePanelImpl();
        if(gameData.getFightData().isPresent()){
            this.fightData = gameData.getFightData();
        }
    }

    public BattleControllerImpl(GameData gameData){
        this.battleModel = new BattleModelImpl(gameData);
        this.battlePanel = new BattlePanelImpl();
        if(gameData.getFightData().isPresent()){
            this.fightData = gameData.getFightData();
        }
    }


    public void Pass(){
        this.battlePanel.enableBotSlots();
        this.battlePanel.disablePassButton();
        this.battlePanel.disablePlayerSlots();
        this.battlePanel.disableSpinButton();
        this.battleModel.BattlePass();
        this.battlePanel.enablePassButton();
        this.battlePanel.enablePlayerSlots();
        this.battlePanel.enableSpinButton();
        this.battlePanel.disableBotSlots();
    }

    public void Spin(){
        this.battlePanel.disableSpinButton();
        this.battlePanel.spinPlayerFreeSlot(battleModel.BattleSpin(PLAYER));
    }

    public void ClickedButtonPlayer(Integer key){
        if(fightData.get().getPlayerData().getCells(key).getClicked()){
            fightData.get().getPlayerData().RemovePlayerTroop(key);
        }else{
            fightData.get().getPlayerData().AddPlayerTroop(key);
        }
    }

    public void ClickedButtonBot(Integer key){
        if(fightData.get().getBotData().getCells(key).getClicked()){
            fightData.get().getBotData().RemoveBotTroop(key);
        }else{
            fightData.get().getBotData().AddBotTroop(key);
        }
    }




}
