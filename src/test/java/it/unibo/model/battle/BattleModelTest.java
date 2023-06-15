package it.unibo.model.battle;

import it.unibo.model.battle.entitydata.EntityData;
import it.unibo.model.battle.entitydata.EntityDataImpl;
import it.unibo.model.data.FightData;
import it.unibo.model.data.GameData;
import it.unibo.model.data.TroopType;
import org.junit.jupiter.api.*;

import static it.unibo.controller.battle.BattleControllerImpl.*;

import java.util.*;

public class BattleModelTest {

    private BattleModel battleModel;
    private FightData fightData;
    private EntityData botData;
    private EntityData playerData;
    private GameData gameData;
    private int botLife;
    private int playerLife;
    private int field;

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

        System.out.println("player troops:" + this.playerData.getEntityTroop().values().stream().map(CellsImpl::getTroop).toList());
        System.out.println("bot troops:" + this.botData.getEntityTroop().values().stream().map(CellsImpl::getTroop).toList());

    }

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
        for(int i=0; i < bothOrdered.size(); i++){
            expected.add(Optional.empty());
        }
        expected.set(0,Optional.of(TroopType.AXE));
        expected.set(9,Optional.of(TroopType.AXE_DEFENCE));
        expected.set(11,Optional.of(TroopType.SWORD_DEFENCE));
        expected.set(19,Optional.of(TroopType.AXE));
        Assertions.assertEquals(expected.subList(0,expected.size() / 2), pc);
        Assertions.assertEquals(expected.subList(expected.size() / 2, expected.size()), bc);

    }

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

        this.battleModel.battlePass(CONTINUE);
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

        this.battleModel.battlePass(PLAYER_FINISH);
        bot = this.fightData.getBotData().getSelected();
        expected = new ArrayList<>();
        expected.add(this.botData.getCells(2).getTroop());
        expected.add(this.botData.getCells(3).getTroop());
        expected.add(this.botData.getCells(4).getTroop());

        Assertions.assertEquals(expected, bot);

    }

    @Test
    public void getBattle() {

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
        for(int i=0; i < this.field; i++){
            if(this.battleModel.battleCombat(i) == BOT) {
                this.botLife--;
                contB++;
            } else if(this.battleModel.battleCombat(i) == PLAYER) {
                this.playerLife--;
                contP++;
            }
        }
        Assertions.assertEquals(this.playerLife, 7);
        Assertions.assertEquals(this.botLife, 8);

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

        for(int i=0; i < this.field; i++){
            if(this.battleModel.battleCombat(i) == BOT) {
                this.botLife--;
                contB++;
            } else if(this.battleModel.battleCombat(i) == PLAYER) {
                this.playerLife--;
                contP++;
            }
        }

        Assertions.assertEquals(this.playerLife, 6);
        Assertions.assertEquals(this.botLife, 7);

    }

    @Test
    public void endFight(){
        Map<TroopType,Integer> troops = new HashMap<>();
        for(TroopType troopType : TroopType.values()){
            troops.put(troopType,1);
        }

        this.gameData.setPlayerArmyLevel(troops);
        this.battleModel.endFight(true);
        for(TroopType troopType : TroopType.values()){
            troops.put(troopType,2);
        }
        Assertions.assertEquals(troops,this.gameData.getPlayerArmyLevel());

    }

}
