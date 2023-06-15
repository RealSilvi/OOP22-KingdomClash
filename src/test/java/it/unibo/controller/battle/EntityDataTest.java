package it.unibo.controller.battle;

import it.unibo.model.battle.entitydata.EntityData;
import it.unibo.model.battle.entitydata.EntityDataImpl;
import it.unibo.model.data.GameData;
import it.unibo.model.data.TroopType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EntityDataTest {

    private EntityData entityData;

    @BeforeEach
    public void init() {
        GameData gameData = new GameData();
        this.entityData = new EntityDataImpl(gameData.getGameConfiguration().getBattleConfiguration());
    }

    @Test
    public void getSelected() {
        this.entityData.addEntityTroop(1);
        this.entityData.addEntityTroop(3);
        List<TroopType> expected = new ArrayList<>();
        expected.add(this.entityData.getEntityTroop().get(1).getTroop());
        expected.add(this.entityData.getEntityTroop().get(3).getTroop());
        Assertions.assertEquals(expected, this.entityData.getSelected());
    }

    @Test
    public void changeNotSelectedTroop() {
        this.entityData.addEntityTroop(1);
        this.entityData.addEntityTroop(3);
        this.entityData.addEntityTroop(2);
        List<TroopType> expected = this.entityData.getNotSelected();
        Assertions.assertNotSame(expected, this.entityData.changeNotSelectedTroop());
    }

    @Test
    public void isMatch() {
        this.entityData.addEntityTroop(3);
        TroopType expected = TroopType.getNullable(this.entityData.getCells(3).getTroop()).get();
        Assertions.assertEquals(true,this.entityData.isMatch(expected));
    }

}
