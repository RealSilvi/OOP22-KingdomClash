package it.unibo.model.battle;

import it.unibo.controller.battle.BattleControllerImpl;
import it.unibo.model.battle.entitydata.EntityDataImpl;
import it.unibo.model.data.FightData;
import it.unibo.model.data.GameData;
import it.unibo.model.data.TroopType;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;

import static it.unibo.controller.battle.BattleControllerImpl.CONTINUE;
import static it.unibo.controller.battle.BattleControllerImpl.PLAYER;
import static it.unibo.model.data.FightData.BOT_LIFE;
import static it.unibo.model.data.FightData.PLAYER_LIFE;
import static it.unibo.model.data.FightData.PLAYER_TROOPS;

/**
 * Class used to implements methods in Battle Model
 * to manage the battle.
 */
public final class BattleModelImpl implements BattleModel {

    /**This variable it is used to represent the bot.*/
    public static final int BOT = BattleControllerImpl.BOT;
    /**This variable it is used to represent when the bot wins.*/
    public static final int WIN_BOT = 2;
    /**This variable it is used to represent when the player wins.*/
    public static final int WIN_PLAYER = 3;
    /**MAX_ROUND represents the max rounds available during one match of the battle.*/
    public static final int MAX_ROUND = FightData.MAX_ROUND;
    private final FightData fightData;
    private Map<TroopType, Integer> troopLevel;
    private Map<TroopType, Integer> troopBotLevel;

    private int countedRound = 0;
    private int botLife = BOT_LIFE;
    private int playerLife = PLAYER_LIFE;


    /**
     * Constructor used when It's usefully use GameData,
     * to get the actual troops levels in both sides, or
     * data of the fight.
     * @param gameData Contains data of the game.
     */
    public BattleModelImpl(final GameData gameData) {
        if (gameData.getFightData() != null) {
            this.fightData = gameData.getFightData();
        } else {
            this.fightData = new FightData();
            gameData.setFightData(this.fightData);
        }
        this.troopLevel = gameData.getPlayerArmyLevel();
        this.troopBotLevel = new EnumMap<>(TroopType.class);
        Arrays.stream(TroopType.values()).forEach(troopType -> this.troopBotLevel.put(troopType, 1));
    }

    /**
     * Use to initialize only fight data.
     * @param fightData The data of the fight.
     */
    public BattleModelImpl(final FightData fightData) {
        this.fightData = fightData;
    }

    @Override
    public Integer getCountedRound() {
        return this.countedRound;
    }

    @Override
    public void battlePass(final Integer finished) {

        fightData.getPlayerData().setClickedToChosen();

        if (fightData.getPlayerData().getSelected().size() > 0) {
            fightData.getPlayerData().getSelected().forEach(x -> {
                int key;
                if (!fightData.getBotData().isMatch(x)) {
                    if (TroopType.getNullable(x).isPresent()) {
                        if (fightData.getBotData().getNotSelected().contains(TroopType.getNullable(x).get())) {
                            key = fightData.getBotData().getKeyFromTroop(TroopType.getNullable(x).get());
                            fightData.getBotData().addEntityTroop(key);
                        } else {
                            if (finished == CONTINUE) {
                                if (fightData.getBotData().getSelected().size() < FightData.BOT_TROOPS) {
                                    fightData.getBotData().addEntityTroop(fightData.getBotData().selectRandomTroop());
                                }
                            }
                        }
                    }
                }
            });
        } else {
            if (fightData.getBotData().getSelected().size() < FightData.BOT_TROOPS) {
                int key = fightData.getBotData().selectRandomTroop();
                fightData.getBotData().addEntityTroop(key);
            }
        }

        countedRound++;
        if (countedRound >= MAX_ROUND) {
            fightData.getBotData().setAllChosen();
            fightData.getPlayerData().setAllChosen();
        } else {
            fightData.getBotData().setClickedToChosen();
        }

    }

    @Override
    public Map<Integer, TroopType> battleSpin(final Integer entity) {

        if (entity == PLAYER) {
            return fightData.getPlayerData().changeNotSelectedTroop();
        } else {
            return fightData.getBotData().changeNotSelectedTroop();
        }

    }

    @Override
    public Integer battleCombat(final Integer position) {

        List<Optional<TroopType>> bothOrdered = EntityDataImpl.getOrderedField(fightData.getPlayerData(), fightData.getBotData());
        Optional<TroopType> playerField = bothOrdered.subList(0, (bothOrdered.size() / 2)).get(position);
        Optional<TroopType> botField = bothOrdered.subList(bothOrdered.size() / 2, bothOrdered.size()).get(position);

        if (botField.isPresent() && playerField.isPresent()) {
            if (troopLevel.get(playerField.get()) > troopBotLevel.get(botField.get())) {
                if (!TroopType.isDefense(playerField.get())) {
                    if (botLife == 1) {
                        botLife--;
                        return WIN_PLAYER;
                    } else {
                        botLife--;
                        return BOT;
                    }
                }
            } else if (troopLevel.get(playerField.get()) < troopBotLevel.get(botField.get())) {
                if (TroopType.isDefense(playerField.get())) {
                    if (playerLife == 1) {
                        playerLife--;
                        return WIN_BOT;
                    } else {
                        playerLife--;
                        return PLAYER;
                    }
                }
            }
        } else if (botField.isEmpty() && playerField.isPresent() && (!TroopType.isDefense(playerField.get()))) {
            if (botLife == 1) {
                botLife--;
                return WIN_PLAYER;
            } else {
                botLife--;
                return BOT;
            }
        } else if (playerField.isEmpty() && botField.isPresent() && (!TroopType.isDefense(botField.get()))) {
            if (playerLife == 1) {
                playerLife--;
                return WIN_BOT;
            } else {
                playerLife--;
                return PLAYER;
            }
        }
        return -1;
    }

    @Override
    public void reset() {
        countedRound = 0;

        for (int i = 0; i < PLAYER_TROOPS; i++) {
            fightData.getPlayerData().removeEntityTroop(i);
            fightData.getBotData().removeEntityTroop(i);
        }

    }

    @Override
    public void endFight(final Boolean increment) {
        botLife = BOT_LIFE;
        playerLife = PLAYER_LIFE;
        if (increment) {
            this.troopBotLevel.values().forEach(x -> ++x);
        }
    }

    @Override
    public Map<TroopType, Boolean> getInfoTable() {
        Map<TroopType, Boolean> infoTable = new EnumMap<>(TroopType.class);
        Arrays.stream(TroopType.values()).forEach(troopType ->
                infoTable.put(troopType, (this.troopLevel.get(troopType) > this.troopBotLevel.get(troopType)
                        && !TroopType.isDefense(troopType))
                || (this.troopLevel.get(troopType) >= this.troopBotLevel.get(troopType)
                        && TroopType.isDefense(troopType))));
        return infoTable;
    }

}
