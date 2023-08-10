package it.unibo.model.battle;

import it.unibo.controller.battle.BattleControllerImpl;
import it.unibo.model.battle.entitydata.EntityData;
import it.unibo.model.battle.entitydata.EntityDataImpl;
import it.unibo.model.data.FightData;
import it.unibo.model.data.GameData;
import it.unibo.model.data.TroopType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

/**
 * This class tests Battle Model methods.
 * It tests also a method of the EntityData class.
 */
public final class BattleModelTest {

    /** The address at the 9 position.*/
    private static final int FIELD_ADDRESS_9 = 9;
    /** The address at the 11 position.*/
    private static final int FIELD_ADDRESS_11 = 11;
    /** The address at the 19 position.*/
    private static final int FIELD_ADDRESS_19 = 19;

    private BattleModel battleModel;
    private FightData fightData;
    private EntityData botData;
    private EntityData playerData;
    private GameData gameData;
    private int botLife;
    private int playerLife;
    private int field;

    /**
     * Initializes all the usefully objects which
     * helps to get the configurations needed, and also
     * two different entityData (bot and player).
     * Creates two hands and set that into the entity classes.
     */
    @BeforeEach
    public void init() {

        System.out.println("entered init");
        this.gameData = new GameData();
        this.fightData = new FightData(gameData.getGameConfiguration().getBattleConfiguration());
        this.botLife = gameData.getGameConfiguration().getBattleConfiguration().getNrOfLives();
        this.playerLife = gameData.getGameConfiguration().getBattleConfiguration().getNrOfLives();
        this.field = gameData.getGameConfiguration().getBattleConfiguration().getNrOfFieldSpots();
        gameData.setFightData(this.fightData);
        this.botData = this.fightData.getBotData();

        this.playerData = this.fightData.getPlayerData();
        gameData.setFightData(this.fightData);
        this.battleModel = new BattleModelImpl(gameData);


        Map<Integer, CellsImpl> botTroop = new HashMap<>();
        Map<Integer, CellsImpl> playerTroop = new HashMap<>();

        botTroop.put(0, new CellsImpl(TroopType.HAMMER, false));
        botTroop.put(1, new CellsImpl(TroopType.SWORD, false));
        botTroop.put(2, new CellsImpl(TroopType.AXE, false));
        botTroop.put(3, new CellsImpl(TroopType.SWORD_DEFENCE, false));
        botTroop.put(4, new CellsImpl(TroopType.MACE_DEFENCE, false));

        playerTroop.put(0, new CellsImpl(TroopType.AXE_DEFENCE, false));
        playerTroop.put(1, new CellsImpl(TroopType.AXE_DEFENCE, false));
        playerTroop.put(2, new CellsImpl(TroopType.AXE, false));
        playerTroop.put(3, new CellsImpl(TroopType.MACE, false));
        playerTroop.put(4, new CellsImpl(TroopType.SWORD, false));

        this.botData.setEntityTroop(botTroop);
        this.playerData.setEntityTroop(playerTroop);

        System.out.println("player troops:"
                + this.playerData.getEntityTroop().values().stream().map(CellsImpl::getTroop).toList());
        System.out.println("bot troops:"
                + this.botData.getEntityTroop().values().stream().map(CellsImpl::getTroop).toList());

    }

    /**
     * Tests the ordered class of the entity data class.
     * Creates the correct battle's field, with the required positions.
     * All the attack troops of the player have to be on the left side,
     * and the defense troops on the right. For the bot is the opposite.
     * The troops are inserted and showed in order to the id of the troop in the enum.
     */
    @Test
    public void exOrdered() {

        this.playerData.getCells(0).setClicked(true);
        this.playerData.getCells(1).setClicked(false);
        this.playerData.getCells(2).setClicked(true);
        this.playerData.getCells(3).setClicked(false);
        this.playerData.getCells(4).setClicked(false);

        this.botData.getCells(0).setClicked(false);
        this.botData.getCells(1).setClicked(false);
        this.botData.getCells(2).setClicked(true);
        this.botData.getCells(3).setClicked(true);
        this.botData.getCells(4).setClicked(false);

        System.out.println("entered getOrdered");
        List<Optional<TroopType>> bothOrdered = EntityDataImpl.exOrdered(this.botData, this.playerData);
        List<Optional<TroopType>> pc = bothOrdered.subList(0, (bothOrdered.size() / 2));
        List<Optional<TroopType>> bc = bothOrdered.subList(bothOrdered.size() / 2, bothOrdered.size());
        List<Optional<TroopType>> expected = new ArrayList<>();
        for (int i = 0; i < bothOrdered.size(); i++) {
            expected.add(Optional.empty());
        }
        expected.set(0, Optional.of(TroopType.AXE));
        expected.set(FIELD_ADDRESS_9, Optional.of(TroopType.AXE_DEFENCE));
        expected.set(FIELD_ADDRESS_11, Optional.of(TroopType.SWORD_DEFENCE));
        expected.set(FIELD_ADDRESS_19, Optional.of(TroopType.AXE));
        Assertions.assertEquals(expected.subList(0, expected.size() / 2), pc);
        Assertions.assertEquals(expected.subList(expected.size() / 2, expected.size()), bc);

    }

    /**
     * This method tests the pass function.
     * It's used to see the correct operation of the bot
     * during its turn. The bot has to see the clicked troops
     * of the player and choose in the right way, which troop put.
     * It gives precedence to the opposite troops of the player.
     * Otherwise, if it doesn't have any opposite troops, it puts
     * a random troop.
     */
    @Test
    public void getPass() {

        this.playerData.getCells(0).setClicked(true);
        this.playerData.getCells(1).setClicked(false);
        this.playerData.getCells(2).setClicked(false);
        this.playerData.getCells(3).setClicked(false);
        this.playerData.getCells(4).setClicked(false);
        this.botData.getCells(0).setClicked(false);
        this.botData.getCells(1).setClicked(false);
        this.botData.getCells(2).setClicked(false);
        this.botData.getCells(3).setClicked(false);
        this.botData.getCells(4).setClicked(false);

        this.battleModel.battlePass(BattleControllerImpl.CONTINUE);
        List<TroopType> bot = this.fightData.getBotData().getSelected();
        List<TroopType> expected = new ArrayList<>();
        expected.add(this.botData.getCells(2).getTroop());

        Assertions.assertEquals(expected, bot);

        this.playerData.getCells(0).setClicked(true);
        this.playerData.getCells(1).setClicked(true);
        this.playerData.getCells(2).setClicked(true);
        this.playerData.getCells(3).setClicked(true);
        this.playerData.getCells(4).setClicked(true);
        this.botData.getCells(0).setClicked(false);
        this.botData.getCells(1).setClicked(false);
        this.botData.getCells(2).setClicked(false);
        this.botData.getCells(3).setClicked(false);
        this.botData.getCells(4).setClicked(false);

        this.battleModel.battlePass(BattleControllerImpl.PLAYER_FINISH);
        bot = this.fightData.getBotData().getSelected();
        expected = new ArrayList<>();
        expected.add(this.botData.getCells(2).getTroop());
        expected.add(this.botData.getCells(3).getTroop());
        expected.add(this.botData.getCells(4).getTroop());

        Assertions.assertEquals(expected, bot);

    }

    /**
     * Tests the battle, to see if the lives
     * go down when they have to.
     */
    @Test
    public void getBattle() {

        int maxLives = this.playerLife;

        this.playerData.getCells(0).setClicked(true);
        this.playerData.getCells(1).setClicked(false);
        this.playerData.getCells(2).setClicked(false);
        this.playerData.getCells(3).setClicked(true);
        this.playerData.getCells(4).setClicked(true);
        this.botData.getCells(0).setClicked(false);
        this.botData.getCells(1).setClicked(true);
        this.botData.getCells(2).setClicked(true);
        this.botData.getCells(3).setClicked(true);
        this.botData.getCells(4).setClicked(true);
        int contB = 0;
        int contP = 0;
        for (int i = 0; i < this.field; i++) {
            if (this.battleModel.battleCombat(i) == BattleControllerImpl.BOT) {
                this.botLife--;
                contB++;
            } else if (this.battleModel.battleCombat(i) == BattleControllerImpl.PLAYER) {
                this.playerLife--;
                contP++;
            }
        }
        Assertions.assertEquals(this.playerLife, maxLives - 1);
        Assertions.assertEquals(this.botLife, maxLives);

        this.playerData.getCells(0).setClicked(true);
        this.playerData.getCells(1).setClicked(true);
        this.playerData.getCells(2).setClicked(true);
        this.playerData.getCells(3).setClicked(true);
        this.playerData.getCells(4).setClicked(true);
        this.botData.getCells(0).setClicked(true);
        this.botData.getCells(1).setClicked(true);
        this.botData.getCells(2).setClicked(true);
        this.botData.getCells(3).setClicked(true);
        this.botData.getCells(4).setClicked(true);
        this.botLife += contB;
        this.playerLife += contP;
        contP = 0;
        contB = 0;

        for (int i = 0; i < this.field; i++) {
            if (this.battleModel.battleCombat(i) == BattleControllerImpl.BOT) {
                this.botLife--;
                contB++;
            } else if (this.battleModel.battleCombat(i) == BattleControllerImpl.PLAYER) {
                this.playerLife--;
                contP++;
            }
        }

        Assertions.assertEquals(this.playerLife, maxLives - 2);
        Assertions.assertEquals(this.botLife, maxLives - 1);

    }

    /**
     * Tests the end of the fight.
     * It is used to see if in case
     * of victory, robot's troops increase
     * their levels.
     */
    @Test
    public void endFight() {
        Map<TroopType, Integer> troops = new HashMap<>();
        for (TroopType troopType : TroopType.values()) {
            troops.put(troopType, 1);
        }

        this.gameData.setPlayerArmyLevel(troops);
        this.battleModel.endFight(true);
        for (TroopType troopType : TroopType.values()) {
            troops.put(troopType, 2);
        }
        Assertions.assertEquals(troops, this.gameData.getPlayerArmyLevel());

    }

    /**
     * Tests if the current round
     * it's correct.
     */
    @Test
    public void countedRound() {

        int countedRound = 1;
        this.playerData.getCells(0).setClicked(true);
        this.playerData.getCells(1).setClicked(false);
        this.playerData.getCells(2).setClicked(false);
        this.playerData.getCells(3).setClicked(false);
        this.playerData.getCells(4).setClicked(false);
        this.botData.getCells(0).setClicked(false);
        this.botData.getCells(1).setClicked(false);
        this.botData.getCells(2).setClicked(false);
        this.botData.getCells(3).setClicked(false);
        this.botData.getCells(4).setClicked(false);

        for (int i = 1; i < gameData.getGameConfiguration().getBattleConfiguration().getMaxRound(); i++) {
            this.battleModel.battlePass(BattleControllerImpl.CONTINUE);
            Assertions.assertEquals(countedRound, this.battleModel.getCountedRound());
            countedRound++;
        }

        this.battleModel.battlePass(BattleControllerImpl.CONTINUE);
        Assertions.assertEquals(countedRound, this.battleModel.getCountedRound());
        Assertions.assertEquals(this.battleModel.getCountedRound(), gameData.getGameConfiguration().getBattleConfiguration().getMaxRound());

    }

}
