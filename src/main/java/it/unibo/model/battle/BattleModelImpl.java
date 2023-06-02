package it.unibo.model.battle;

import it.unibo.model.data.FightData;
import it.unibo.model.data.GameData;
import it.unibo.view.battle.Troop;

import javax.swing.text.html.Option;
import java.util.*;

import static it.unibo.controller.battle.BattleControllerImpl.*;
import static it.unibo.model.data.FightData.PLAYER_TROOPS;

public class BattleModelImpl implements BattleModel {

    public static final int BOT = 0;
    public static final int WIN_BOT = 2;
    public static final int WIN_PLAYER = 3;
    public static final int MAX_ROUND = FightData.MAX_ROUND;
    private Optional<FightData> fightData;

    int counted_round = 0;
    int botLife = FightData.BOT_LIFE;
    int playerLife = FightData.PLAYER_LIFE;


    public BattleModelImpl(GameData gameData) {
        if (gameData.getFightData().isPresent()) {
            this.fightData = gameData.getFightData();
        }
    }

    public BattleModelImpl(Optional<FightData> fightData) {
        this.fightData = fightData;
    }

    public Integer getCountedRound() {
        return this.counted_round;
    }

    @Override
    public void battlePass(Integer finished) {

        fightData.get().getPlayerData().setClickedToChosen();

        if (fightData.get().getPlayerData().getSelected().size() > 0) {
            fightData.get().getPlayerData().getSelected().forEach(x -> {
                int key = 0;
                if (!fightData.get().getBotData().isMatch(x)) {
                    if (fightData.get().getBotData().getNotSelected().contains(Troop.getNullable(x))) {
                        key = fightData.get().getBotData().getKeyFromTroop(Troop.getNullable(x));
                        fightData.get().getBotData().addBotTroop(key);
                    } else {
                        if (finished == CONTINUE) {
                            if (fightData.get().getBotData().getSelected().size() < FightData.BOT_TROOPS) {
                                fightData.get().getBotData().addBotTroop(fightData.get().getBotData().selectRandomTroop());
                            }
                        }
                    }
                }
            });
        } else {
            if (fightData.get().getBotData().getSelected().size() < FightData.BOT_TROOPS) {
                int key = fightData.get().getBotData().selectRandomTroop();
                fightData.get().getBotData().addBotTroop(key);
            }
        }

        counted_round++;
        if (counted_round >= MAX_ROUND) {
            fightData.get().getBotData().setAllChosen();
            fightData.get().getPlayerData().setAllChosen();
        } else {
            fightData.get().getBotData().setClickedToChosen();
        }

    }

    @Override
    public Map<Integer, Troop> battleSpin(Integer entity) {

        if (entity == PLAYER) {
            return fightData.get().getPlayerData().changeNotSelectedTroop();
        } else {
            return fightData.get().getBotData().changeNotSelectedTroop();
        }

    }

    @Override
    public Integer battleCombat(Integer position) {

        Optional<Troop> playerField = fightData.get().getPlayerData().getOrderedField(fightData.get().getBotData()).get(position);
        Optional<Troop> botField = fightData.get().getBotData().getOrderedField(fightData.get().getPlayerData()).get(position);

        if (botField.isPresent() && playerField.isPresent()) {
            if (playerField.get().getLevel() > botField.get().getLevel()) {
                if (!playerField.get().isDefense()) {
                    if (botLife == 1) {
                        return WIN_PLAYER;
                    } else {
                        return BOT;
                    }
                }
            } else if (playerField.get().getLevel() < botField.get().getLevel()) {
                if (playerField.get().isDefense()) {
                    if (playerLife == 1) {
                        return WIN_BOT;
                    } else {
                        return PLAYER;
                    }
                }
            }
        } else if (botField.isEmpty() && playerField.isPresent() && (!playerField.get().isDefense())) {
            if (botLife == 1) {
                return WIN_PLAYER;
            } else {
                return BOT;
            }
        } else if (playerField.isEmpty() && botField.isPresent() && (!botField.get().isDefense())) {
            if (playerLife == 1) {
                return WIN_BOT;
            } else {
                return PLAYER;
            }
        }

        return -1;
    }

    @Override
    public void reset() {
        counted_round = 0;

        for (int i = 0; i < PLAYER_TROOPS; i++) {
            fightData.get().getPlayerData().removePlayerTroop(i);
            fightData.get().getBotData().removeBotTroop(i);
        }

    }


}