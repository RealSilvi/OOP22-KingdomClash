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
    private Map<Integer, CellsImpl> botTroop;
    private Map<Integer, CellsImpl> playerTroop;

    @BeforeEach
    public void init() {

        System.out.println("entered init");
        this.gameData = new GameData();
        this.fightData = new FightData();
        this.botData = this.fightData.getBotData();

        this.playerData = this.fightData.getPlayerData();
        this.gameData.setFightData(Optional.of(this.fightData));
        this.battleModel = new BattleModelImpl(Optional.of(this.fightData));


        this.botTroop = new HashMap<>();
        this.playerTroop = new HashMap<>();

        this.botTroop.put(0, new CellsImpl(TroopType.HAMMER, false, false));
        this.botTroop.put(1, new CellsImpl(TroopType.SWORD, false, false));
        this.botTroop.put(2, new CellsImpl(TroopType.AXE, false, false));
        this.botTroop.put(3, new CellsImpl(TroopType.SWORD_DEFENCE, false, false));
        this.botTroop.put(4, new CellsImpl(TroopType.SWORD, false, false));

        this.playerTroop.put(0, new CellsImpl(TroopType.AXE_DEFENCE, false, false));
        this.playerTroop.put(1, new CellsImpl(TroopType.AXE_DEFENCE, false, false));
        this.playerTroop.put(2, new CellsImpl(TroopType.AXE, false, false));
        this.playerTroop.put(3, new CellsImpl(TroopType.MACE, false, false));
        this.playerTroop.put(4, new CellsImpl(TroopType.SWORD, false, false));

        //this.botData.setBotTroop(this.botTroop);
        //this.playerData.setPlayerTroop(this.playerTroop);

        System.out.println("player troops:" + this.playerData.getEntityTroop().values().stream().map(CellsImpl::getTroop).toList());
        System.out.println("bot troops:" + this.botData.getEntityTroop().values().stream().map(CellsImpl::getTroop).toList());

    }

    @Test
    public void getSelected() {

        System.out.println("entered getSelected");
        this.playerData.getCells(1).setClicked(true);
        this.playerData.getCells(3).setClicked(true);
        this.playerData.getCells(4).setClicked(true);
        List<TroopType> bc = this.fightData.getPlayerData().getSelected();
        List<TroopType> expected = new ArrayList<>();
        expected.add(this.playerData.getCells(1).getTroop());
        expected.add(this.playerData.getCells(3).getTroop());
        expected.add(this.playerData.getCells(4).getTroop());

        Assertions.assertEquals(expected, bc);

    }

    @Test
    public void getOrderedField() {

        /*this.playerData.getCells(1).setClicked(false);
        this.playerData.getCells(3).setClicked(false);
        this.playerData.getCells(4).setClicked(false);
        this.botData.getCells(1).setClicked(false);
        this.botData.getCells(2).setClicked(true);
        this.botData.getCells(4).setClicked(false);
        this.botData.getCells(0).setClicked(true);
        */
        System.out.println("entered getOrdered");
        List<Optional<TroopType>> bothOrdered = EntityDataImpl.getOrderedField(this.playerData,this.botData);
        List<Optional<TroopType>> pc = bothOrdered.subList(0,(bothOrdered.size()/2)-1);
        List<Optional<TroopType>> bc = bothOrdered.subList(bothOrdered.size()/2,bothOrdered.size()-1);
        List<Optional<TroopType>> expected = new ArrayList<>();
        expected.add(Optional.of(TroopType.SWORD));
        /*expected.add(Optional.of(Troop.SWORD));
        expected.add(Optional.of(Troop.ARROW));
        expected.add(Optional.of(Troop.HELMET));
        expected.add(Optional.empty());
        expected.add(Optional.empty());
        expected.add(Optional.empty());
        */

        //System.out.println("expected:" + expected.stream().toList());
        System.out.println("bc ordered:" + bc.stream().toList());
        System.out.println("pc ordered:" + pc.stream().toList());

        //Assertions.assertEquals(expected, bc);

    }

    @Test
    public void getPass() {

        for (int i = 0; i < 3; i++) {

            /*this.playerData.getCells(1).setClicked(false);
            this.playerData.getCells(3).setClicked(false);
            this.playerData.getCells(4).setClicked(false);
            this.botData.getCells(0).setClicked(false);
            this.botData.getCells(1).setClicked(false);
            this.botData.getCells(2).setClicked(false);
            this.botData.getCells(3).setClicked(false);
            this.botData.getCells(4).setClicked(false);
             */
            System.out.println("entered getPass");
            this.battleModel.battlePass(CONTINUE);
            List<TroopType> bcp = this.fightData.getPlayerData().getSelected();
            List<TroopType> bcb = this.fightData.getBotData().getSelected();
            List<TroopType> expected1 = new ArrayList<>();
            expected1.add(TroopType.HAMMER);
            expected1.add(TroopType.AXE);
            expected1.add(TroopType.SWORD_DEFENCE);
            expected1.add(TroopType.SWORD);

            //Assertions.assertEquals(expected1, bcb);
            //System.out.println("expected:" + expected1.stream().map(Troop::getId).toList());
            //System.out.println("bcb:" + bcb.stream().map(Troop::getName).toList());
            getOrderedField();
        }

    }

}
