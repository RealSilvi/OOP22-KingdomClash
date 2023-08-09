package it.unibo.model.base;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.base.api.BuildingObserver;
import it.unibo.model.base.exceptions.BuildingMaxedOutException;
import it.unibo.model.base.exceptions.InvalidBuildingPlacementException;
import it.unibo.model.base.exceptions.InvalidStructureReferenceException;
import it.unibo.model.base.exceptions.InvalidTroopLevelException;
import it.unibo.model.base.exceptions.NotEnoughResourceException;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.model.data.GameData;
import it.unibo.model.data.TroopType;

/**
 * Tests for the model for the base part of the game.
 */
final class BaseModelImplTest {
    private static final long GAME_PAUSE_MS = 5000L;
    private static final float POSITION_INCREMENT = 5.5f;
    private static final int BUILDING_TIME_TOLERANCE = 10_000;
    private GameData gameData;
    private BaseModel baseModel;
    private int counter;

    /**
     * Build all type of buildings.
     */
    @BeforeEach
    void buildAllStructures() {
        initModel();
        float xvariation = 0.0f;
        for (final BuildingTypes currentType : BuildingTypes.values()) {
            xvariation++;
            final Point2D pos = new Point2D.Float(xvariation, 0);
            Assertions.assertDoesNotThrow(() -> baseModel.buildStructure(pos, currentType, 0, true));
        }
    }

    /**
     * Tests by building all type of structures.
     */
    @Test
    void testBuildMultipleStructures() {
        buildAllStructures();
    }
    /**
     * Tests the upgrade of a structure.
     */
    @Test
    void testStructureUpgrade() {
        final Object synchronizationObject = new Object();
        final Set<UUID> buildingKeys = baseModel.getBuildingIds();
        buildingKeys.forEach((buildingIdentifier) -> {
            baseModel.addBuildingStateChangedObserver(new BuildingObserver() {
                @Override
                public void update(final UUID buildingId) {
                    if (buildingId.equals(buildingIdentifier)
                            && !baseModel.getBuildingMap().get(buildingIdentifier).isBeingBuilt()) {
                        Assertions.assertEquals(1, baseModel.getBuildingMap().get(buildingId).getLevel());
                        synchronized (synchronizationObject) {
                            synchronizationObject.notifyAll();
                            increaseCounter();
                        }
                    }
                }
            });
            BaseTestUtils.applyBuildingResources(
                    gameData, baseModel.getBuildingMap().get(buildingIdentifier).getType(),
                    baseModel.getBuildingMap().get(buildingIdentifier).getLevel() + 1);
            Assertions.assertDoesNotThrow(() -> baseModel.upgradeStructure(buildingIdentifier));
        });
        synchronized (synchronizationObject) {
            try {
                do {
                    synchronizationObject.wait();
                } while (getCounter() < buildingKeys.size());
                baseModel.getBuildingMap().forEach((buildingKey, buildingObject) -> {
                    Assertions.assertEquals(1, buildingObject.getLevel());
                });
            } catch (InterruptedException e) {
            }
        }
    }
    /**
     * Tests if the relocation of a structure is done correctly and does not
     * allow ovelapping of buildings.
     * @throws InvalidBuildingPlacementException    thrown if the building
     *                                              is being placed in an invalid position
     * @throws InvalidStructureReferenceException   thrown if a non existing building is
     *                                              being referenced
     */
    @Test
    void testStructureRelocation() throws InvalidBuildingPlacementException, InvalidStructureReferenceException {
        testBuildMultipleStructures();
        float positionIncrement = POSITION_INCREMENT;
        final Set<UUID> identifiers = baseModel.getBuildingIds();
        for (final UUID identifier : identifiers) {
            positionIncrement++;
            baseModel.relocateStructure(
                    new Point2D.Float(positionIncrement, 0.0f), identifier);
            Assertions.assertEquals(baseModel.getBuildingMap()
                    .get(identifier).getStructurePos().getX(), positionIncrement);
            Assertions.assertEquals(0.0f, baseModel.getBuildingMap()
                    .get(identifier).getStructurePos().getY());
        }
    }
    /**
     * Tests the demolition of a building.
     */
    @Test
    void testStructureDemolition() {
        final Set<UUID> buildingKeys = baseModel.getBuildingIds();
        buildingKeys.forEach((buildingIdentifier) -> 
            Assertions.assertDoesNotThrow(() -> 
                baseModel.demolishStructure(buildingIdentifier)));
    }
    /**
     * Tests if the base model part pauses correctly.
     * @throws NotEnoughResourceException           thrown when an action that
     *                                              requires resources is being
     *                                              done when not present
     * @throws BuildingMaxedOutException            thrown when the level limit
     *                                              is exceeded
     * @throws InvalidStructureReferenceException   thrown if a non existing building is
     *                                              being referenced
     * @throws InterruptedException                 thrown when thread is interrupted
     */
    @Test
    void testGamePause() throws NotEnoughResourceException,
        BuildingMaxedOutException, InvalidStructureReferenceException,
        InterruptedException {
        final Object synchronizationObject = new Object();
        final long initialWaitingTime = GAME_PAUSE_MS;
        counter = 0;
        testBuildMultipleStructures();
        final Iterator<UUID> buildingIdentifiers = baseModel.getBuildingIds().iterator();
        final UUID singleBuildingUUID = buildingIdentifiers.next();
        final long buildingTime = baseModel.getBuildingMap().get(singleBuildingUUID).getBuildingTime();
        baseModel.addBuildingStateChangedObserver(new BuildingObserver() {
            @Override
            public void update(final UUID buildingId) {
                Assertions.assertTrue(baseModel.isClockTicking());
                if (buildingId.equals(singleBuildingUUID)
                        && !baseModel.getBuildingMap().get(singleBuildingUUID).isBeingBuilt()) {
                    Assertions.assertEquals(1, baseModel.getBuildingMap().get(buildingId).getLevel());
                    synchronized (synchronizationObject) {
                        synchronizationObject.notifyAll();
                    }
                }
            }
        });
        BaseTestUtils.applyBuildingResources(gameData,
                baseModel.getBuildingMap().get(singleBuildingUUID).getType(),
                baseModel.getBuildingMap().get(singleBuildingUUID).getLevel() + 1);
        baseModel.upgradeStructure(singleBuildingUUID);
        synchronized (synchronizationObject) {
            synchronizationObject.wait(initialWaitingTime);
        }
        baseModel.setClockTicking(false);
        synchronized (synchronizationObject) {
            synchronizationObject.wait(buildingTime + BUILDING_TIME_TOLERANCE);
        }
        final long startTime = System.currentTimeMillis();
        baseModel.setClockTicking(true);
        synchronized (synchronizationObject) {
            synchronizationObject.wait();
            final long endTime = System.currentTimeMillis();
            final long elapsedTime = endTime - startTime;
            Assertions.assertEquals(1,
                    baseModel.getBuildingMap().get(singleBuildingUUID).getLevel());
            Assertions.assertTrue(BaseTestUtils.checkElapsedTime(elapsedTime,
                    buildingTime - initialWaitingTime,
                    BaseTestUtils.STANDARD_TIME_TOLERANCE));
        }
    }
    /**
     * Tests the troop upgrade logic.
     */
    @Test
    void testTroopUpgrade() {
        Assertions.assertDoesNotThrow(() -> 
            this.baseModel.upgradeTroop(TroopType.AXE, 2));
        final Map<TroopType, Integer> expectedTroops = new EnumMap<>(TroopType.class);
        Arrays.stream(TroopType.values()).forEach(type -> expectedTroops.put(type, 1));
        expectedTroops.put(TroopType.AXE, 2);
        Assertions.assertEquals(expectedTroops, this.baseModel.getTroopMap());
        Assertions.assertThrowsExactly(InvalidTroopLevelException.class, () -> 
            baseModel.upgradeTroop(TroopType.AXE, gameData.getGameConfiguration()
                .getBaseConfiguration().getMaximumTroopLevel() + 1));
    }

    private synchronized void increaseCounter() {
        counter++;
    }

    private int getCounter() {
        return this.counter;
    }

    private void initModel() {
        this.gameData = new GameData();
        this.baseModel = new BaseModelImpl(this.gameData);
    }
}
