package it.unibo.controller.battle;

import it.unibo.model.battle.BattleModel;
import it.unibo.model.battle.BattleModelImpl;
import it.unibo.model.data.FightData;
import it.unibo.model.data.GameData;
import it.unibo.view.battle.BattlePanel;
import it.unibo.view.battle.BattlePanelImpl;

import java.util.*;

import static it.unibo.model.battle.BattleModelImpl.BOT;
import static it.unibo.model.battle.BattleModelImpl.MAX_ROUND;
import static it.unibo.model.data.FightData.TOTAL_TROOPS;

public class BattleControllerImpl implements  BattleController{

    public static final int PLAYER = 1;
    public static final int NOSKIP = 0;
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

    public BattleControllerImpl(Optional<FightData> fightData){
        this.battleModel = new BattleModelImpl(fightData);
        this.battlePanel = new BattlePanelImpl();
        this.fightData = fightData;
    }


    public void pass(){
        this.battlePanel.enableBotSlots();
        this.battlePanel.disablePassButton();
        this.battlePanel.disablePlayerSlots();
        this.battlePanel.disableSpinButton();
        battlePanel.spinBotFreeSlot(this.battleModel.battleSpin(BOT));
        this.battleModel.battlePass();
        update(NOSKIP);
        if(this.battleModel.getCountedRound() == MAX_ROUND){
            battle();
        }
        this.battlePanel.enablePassButton();
        this.battlePanel.enablePlayerSlots();
        this.battlePanel.enableSpinButton();
        this.battlePanel.disableBotSlots();
    }

    public void spin(){
        battlePanel.disableSpinButton();
        battlePanel.spinPlayerFreeSlot(this.battleModel.battleSpin(PLAYER));
    }

    public void battle(){
        for(int i= 0; i < TOTAL_TROOPS; i++){
            if(this.battleModel.battleCombat(i) == BOT){
                botLifeDecrease();
            }else if(this.battleModel.battleCombat(i) == PLAYER){
                playerLifeDecrease();
            }else{
                //TODO nobody get damage
            }
            update(i+1);
        }
    }

    public void clickedButtonPlayer(Integer key){
        if(fightData.get().getPlayerData().getCells(key).getClicked()){
            fightData.get().getPlayerData().removePlayerTroop(key);
            update(NOSKIP);
        }else{
            fightData.get().getPlayerData().addPlayerTroop(key);
            update(NOSKIP);
        }
    }

    public void update(Integer skip){
        battlePanel.updateField(fightData.get().getPlayerData().getOrderedField(fightData.get().getBotData()).stream().skip(skip).toList(),
                fightData.get().getBotData().getOrderedField(fightData.get().getPlayerData()).stream().skip(skip).toList());
    }

    public void playerLifeDecrease(){
        battlePanel.hitPlayer();
    }

    public void botLifeDecrease(){
        battlePanel.hitBot();
    }

}
