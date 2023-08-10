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

/**
 * This class tests the EntityData class.
 * It doesn't test all the methods, but only
 * the riskiest (The biggest methods are tested
 * in the model test).
 */
final class EntityDataTest {

    private EntityData entityData;

    /**
     * Initialize the class, to use it.
     */
    @BeforeEach
    void init() {
        final GameData gameData = new GameData();
        this.entityData = new EntityDataImpl(gameData.getGameConfiguration().getBattleConfiguration());
    }

    /**
     * Tests the getSelected method.
     * It should return a list with only the selected
     * troops.
     */
    @Test
    void selected() {
        this.entityData.addEntityTroop(1);
        this.entityData.addEntityTroop(3);
        final List<TroopType> expected = new ArrayList<>();
        expected.add(this.entityData.getEntityTroop().get(1).getTroop());
        expected.add(this.entityData.getEntityTroop().get(3).getTroop());
        Assertions.assertEquals(expected, this.entityData.getSelected());
    }

    /**
     * Tests the changeNotSelectedTroop method.
     * It should return a list where the not selected troop
     * are changed.
     */
    @Test
    void changeNotSelectedTroop() {
        this.entityData.addEntityTroop(1);
        this.entityData.addEntityTroop(3);
        this.entityData.addEntityTroop(2);
        final List<TroopType> expected = this.entityData.getNotSelected();
        Assertions.assertNotSame(expected, this.entityData.changeNotSelectedTroop());
    }

    /**
     * Tests isMatch method, which controls if
     * the opposite of the required troop exists
     * between the troops already selected.
     */
    @Test
    void isMatch() {
        this.entityData.addEntityTroop(3);
        if (TroopType.getNullable(this.entityData.getCells(3).getTroop()).isPresent()) {
            final TroopType expected = TroopType.getNullable(this.entityData.getCells(3).getTroop()).get();
            Assertions.assertEquals(true, this.entityData.isMatch(expected));
        }
    }

}
