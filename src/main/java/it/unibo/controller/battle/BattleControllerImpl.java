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


    public void pass(){
        this.battlePanel.enableBotSlots();
        this.battlePanel.disablePassButton();
        this.battlePanel.disablePlayerSlots();
        this.battlePanel.disableSpinButton();
        this.battleModel.battlePass();
        this.battlePanel.enablePassButton();
        this.battlePanel.enablePlayerSlots();
        this.battlePanel.enableSpinButton();
        this.battlePanel.disableBotSlots();
    }

    public void spin(Integer entity){
        if(entity == PLAYER){
            battlePanel.disableSpinButton();
            battlePanel.spinPlayerFreeSlot(fightData.get().getPlayerData().changeNotSelectedTroop());
        }else{
            battlePanel.spinBotFreeSlot(fightData.get().getBotData().changeNotSelectedTroop());
        }

    }

    public void clickedButtonPlayer(Integer key){
        if(fightData.get().getPlayerData().getCells(key).getClicked()){
            fightData.get().getPlayerData().removePlayerTroop(key);
            update();
        }else{
            fightData.get().getPlayerData().addPlayerTroop(key);
            update();
        }
    }

    public void update(){
        battlePanel.updateField(fightData.get().getPlayerData().getOrderedField(fightData.get().getBotData()),
                fightData.get().getBotData().getOrderedField(fightData.get().getPlayerData()));
    }

    public void playerLifeDecrease(){
        battlePanel.hitPlayer();
    }

    public void botLifeDecrease(){
        battlePanel.hitBot();
    }

}
