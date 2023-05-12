package it.unibo.model.battle;

import it.unibo.model.battle.entitydata.BotData;
import it.unibo.model.battle.entitydata.BotDataImpl;
import it.unibo.model.battle.entitydata.PlayerData;
import it.unibo.model.battle.entitydata.PlayerDataImpl;
import it.unibo.view.battle.Troop;

import java.util.*;

public class BattleModelImpl implements BattleModel{

    public static final int FIRST_TROOP = 0;
    private BotData botData = new BotDataImpl();
    private PlayerData playerData = new PlayerDataImpl();

    int counted_round = 0;
    int botLife = 10;
    int playerLife = 10;


    private void Initialize(){

    }

    @Override
    public void BattlePass() {

        playerData.setClickedToChosen();

        //disablePassButton()
        //disablePlayerSlots()
        //spinBotFreeSlot()

        if(playerData.getSelected().size() > 0) {
            playerData.getSelected().forEach(x -> {

                if (!botData.isMatch(x)) {
                    if (botData.getNotSelected().contains(Troop.getNullable(x))) {
                        botData.AddBotTroop(botData.getKeyFromTroop(Troop.getNullable(x)));
                    } else {
                        if (botData.getSelected().size() < BotDataImpl.BOT_TROOPS) {
                            botData.AddBotTroop(botData.selectRandomTroop());
                        }
                    }
                }

            });
        }else{
            if (botData.getSelected().size() < BotDataImpl.BOT_TROOPS) {
                botData.AddBotTroop(botData.selectRandomTroop());
            }
        }

        counted_round++;
        if(counted_round >= 3){
            counted_round = 0;
            BattleCombat();
        }

    }

    @Override
    public void BattleSpin() {

        playerData.changeNotSelectedTroop();

        //disableSpinButton()
        //spinPlayerFreeSlot()

    }

    private void BattleCombat(){

        List<Optional<Troop>> playerField = playerData.getOrderedField(botData);
        List<Optional<Troop>> botField = botData.getOrderedField(playerData);

        playerField.forEach(x -> {

                if(botField.get(FIRST_TROOP).isPresent() && x.isPresent()){
                    if(x.get().getLevel() > botField.get(FIRST_TROOP).get().getLevel()){
                        if(!x.get().isDefense()){
                            if(botLife == 1){
                                botLife--;
                                //TODO player win
                            }else{
                                botLife--;
                            }
                        }
                    }else if(x.get().getLevel() < botField.get(FIRST_TROOP).get().getLevel()){
                        if(x.get().isDefense()){
                            if(playerLife == 1){
                                playerLife--;
                                //TODO bot win
                            }else{
                                playerLife--;
                            }
                        }
                    }
                }else if(botField.get(FIRST_TROOP).isEmpty() && x.isPresent() && (!x.get().isDefense())){
                    if(botLife == 1){
                        botLife--;
                        //TODO player win
                    }else{
                        botLife--;
                    }
                }else if(x.isEmpty() && botField.get(FIRST_TROOP).isPresent() && (!botField.get(FIRST_TROOP).get().isDefense())){
                    if(playerLife == 1){
                        playerLife--;
                        //TODO bot win
                    }else{
                        playerLife--;
                    }
                }

                botField.remove(FIRST_TROOP);

        });

    }

    private void notifyController(){

    }



}