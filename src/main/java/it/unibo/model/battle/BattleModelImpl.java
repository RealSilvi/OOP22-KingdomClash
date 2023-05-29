package it.unibo.model.battle;

import it.unibo.controller.battle.BattleController;
import it.unibo.controller.battle.BattleControllerImpl;
import it.unibo.model.data.FightData;
import it.unibo.model.data.GameData;
import it.unibo.view.battle.Troop;

import javax.swing.text.html.Option;
import java.util.*;

public class BattleModelImpl implements BattleModel{

    public static final int FIRST_TROOP = 0;
    public static final int BOT = 0;
    public static final int MAX_ROUND = FightData.MAX_ROUND;
    private Optional<FightData> fightData;
    private BattleController battleController;

    int counted_round = 0;
    int botLife = FightData.BOT_LIFE;
    int playerLife = FightData.PLAYER_LIFE;


    public BattleModelImpl(GameData gameData){
        if(gameData.getFightData().isPresent()){
            this.fightData = gameData.getFightData();
        }
        this.battleController = new BattleControllerImpl(gameData);
    }

    public BattleModelImpl(){

    }

    public BattleModelImpl(Optional<FightData> fightData){
        this.fightData = fightData;
        this.battleController = new BattleControllerImpl(this.fightData);
    }

    @Override
    public void battlePass() {

        fightData.get().getPlayerData().setClickedToChosen();
        battleSpin(BOT);

        if(fightData.get().getPlayerData().getSelected().size() > 0) {
            fightData.get().getPlayerData().getSelected().forEach(x -> {
                int key=0;
                if (!fightData.get().getBotData().isMatch(x)) {
                    if (fightData.get().getBotData().getNotSelected().contains(Troop.getNullable(x))) {
                        key = fightData.get().getBotData().getKeyFromTroop(Troop.getNullable(x));
                        fightData.get().getBotData().addBotTroop(key);
                    } else {
                        if (fightData.get().getBotData().getSelected().size() < FightData.BOT_TROOPS) {
                            fightData.get().getBotData().addBotTroop(fightData.get().getBotData().selectRandomTroop());
                        }
                    }
                }
            });
        }else{
            if (fightData.get().getBotData().getSelected().size() < FightData.BOT_TROOPS) {
                fightData.get().getBotData().addBotTroop(fightData.get().getBotData().selectRandomTroop());
            }
        }

        counted_round++;
        if(counted_round >= MAX_ROUND){
            fightData.get().getBotData().setAllChosen();
            fightData.get().getPlayerData().setAllChosen();
            this.battleController.update();
            counted_round = 0;
            battleCombat();
        }else{
            fightData.get().getBotData().setClickedToChosen();
            this.battleController.update();
        }

    }

    @Override
    public void battleSpin(Integer entity) {
        this.battleController.spin(entity);
    }

    @Override
    public void battleCombat(){

        List<Optional<Troop>> playerField = fightData.get().getPlayerData().getOrderedField(fightData.get().getBotData());
        List<Optional<Troop>> botField = fightData.get().getBotData().getOrderedField(fightData.get().getPlayerData());

        playerField.forEach(x -> {

                if(botField.get(FIRST_TROOP).isPresent() && x.isPresent()){
                    if(x.get().getLevel() > botField.get(FIRST_TROOP).get().getLevel()){
                        if(!x.get().isDefense()){
                            if(botLife == 1){
                                botLife--;
                                this.battleController.botLifeDecrease();
                                //TODO player win
                            }else{
                                botLife--;
                                this.battleController.botLifeDecrease();
                            }
                        }
                    }else if(x.get().getLevel() < botField.get(FIRST_TROOP).get().getLevel()){
                        if(x.get().isDefense()){
                            if(playerLife == 1){
                                playerLife--;
                                this.battleController.playerLifeDecrease();
                                //TODO bot win
                            }else{
                                playerLife--;
                                this.battleController.playerLifeDecrease();
                            }
                        }
                    }
                }else if(botField.get(FIRST_TROOP).isEmpty() && x.isPresent() && (!x.get().isDefense())){
                    if(botLife == 1){
                        botLife--;
                        this.battleController.botLifeDecrease();
                        //TODO player win
                    }else{
                        botLife--;
                        this.battleController.botLifeDecrease();
                    }
                }else if(x.isEmpty() && botField.get(FIRST_TROOP).isPresent() && (!botField.get(FIRST_TROOP).get().isDefense())){
                    if(playerLife == 1){
                        playerLife--;
                        this.battleController.playerLifeDecrease();
                        //TODO bot win
                    }else{
                        playerLife--;
                        this.battleController.playerLifeDecrease();
                    }
                }

                botField.remove(FIRST_TROOP);

        });
        playerField = null;

    }



}