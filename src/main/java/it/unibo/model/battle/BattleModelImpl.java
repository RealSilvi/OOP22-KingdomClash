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

    private final FightData fightData;
    private final GameData gameData;
    private final Map<TroopType, Integer> troopLevel;
    private final Map<TroopType, Integer> troopBotLevel;

    /**MAX_ROUND represents the max rounds available during one match of the battle.*/
    private final int maxRound;
    private final int botTroops;
    private final int playerTroops;
    private int countedRound = 0;
    private int botLife;
    private int playerLife;


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
            this.fightData = new FightData(gameData.getGameConfiguration().getBattleConfiguration());
            gameData.setFightData(this.fightData);
        }
        this.botLife = gameData.getGameConfiguration().getBattleConfiguration().getNrOfLives();
        this.playerLife = gameData.getGameConfiguration().getBattleConfiguration().getNrOfLives();
        this.botTroops = gameData.getGameConfiguration().getBattleConfiguration().getNrOfSlots();
        this.playerTroops = gameData.getGameConfiguration().getBattleConfiguration().getNrOfSlots();
        this.maxRound = gameData.getGameConfiguration().getBattleConfiguration().getMaxRound();
        this.troopLevel = gameData.getPlayerArmyLevel();
        this.troopBotLevel = new EnumMap<>(TroopType.class);
        Arrays.stream(TroopType.values()).forEach(troopType -> this.troopBotLevel.put(troopType, 1));
        this.gameData = gameData;
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
                                if (fightData.getBotData().getSelected().size() < this.botTroops) {
                                    fightData.getBotData().addEntityTroop(fightData.getBotData().selectRandomTroop());
                                }
                            }
                        }
                    }
                }
            });
        } else {
            if (fightData.getBotData().getSelected().size() < this.botTroops) {
                int key = fightData.getBotData().selectRandomTroop();
                fightData.getBotData().addEntityTroop(key);
            }
        }

        countedRound++;
        if (countedRound >= maxRound) {
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

        for (int i = 0; i < this.playerTroops; i++) {
            fightData.getPlayerData().removeEntityTroop(i);
            fightData.getBotData().removeEntityTroop(i);
        }

    }

    @Override
    public void endFight(final Boolean increment) {
        botLife = this.gameData.getGameConfiguration().getBattleConfiguration().getNrOfLives();
        playerLife = this.gameData.getGameConfiguration().getBattleConfiguration().getNrOfLives();
        if (increment) {
            int level = 0;
            if (this.gameData.getPlayerArmyLevel().values().stream().findFirst().isPresent()) {
                level = this.gameData.getPlayerArmyLevel().values().stream().findFirst().get();
            }
            for (TroopType troopType : TroopType.values()) {
                this.troopBotLevel.put(troopType, level + 1);
            }
            this.gameData.setPlayerArmyLevel(this.troopBotLevel);
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
