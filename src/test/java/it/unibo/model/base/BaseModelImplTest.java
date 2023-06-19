package it.unibo.model.base;

import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.base.api.BuildingObserver;
import it.unibo.model.base.exceptions.BuildingMaxedOutException;
import it.unibo.model.base.exceptions.InvalidBuildingPlacementException;
import it.unibo.model.base.exceptions.InvalidStructureReferenceException;
import it.unibo.model.base.exceptions.NotEnoughResourceException;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.model.data.GameData;

/**
 * Tests for the model for the base part of the game.
 */
public final class BaseModelImplTest {
    private static final long GAME_PAUSE_MS = 5000L;
    private static final float POSITION_INCREMENT = 5.5f;
    private static final int BUILDING_TIME_TOLERANCE = 10000;
    private GameData gameData;
    private BaseModel baseModel;
    private int counter;

    /**
     * Build all type of buildings.
     */
    @BeforeEach
    public void buildAllStructures() {
        initModel();
        float xvariation = 0.0f;
        for (BuildingTypes currentType : BuildingTypes.values()) {
            xvariation++;
            Point2D pos = new Point2D.Float(xvariation, 0);
            Assertions.assertDoesNotThrow(() -> baseModel.buildStructure(pos, currentType, 0, true));
        }
    }

    /**
     * Tests by building all type of structures.
     */
    @Test
    public void testBuildMultipleStructures() {
        buildAllStructures();
    }
    /**
     * Tests the upgrade of a structure.
     */
    @Test
    public void testStructureUpgrade() {
        Object synchronizationObject = new Object();
        Set<UUID> buildingKeys = baseModel.getBuildingIds();
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
    public void testStructureRelocation() throws InvalidBuildingPlacementException, InvalidStructureReferenceException {
        testBuildMultipleStructures();
        float positionIncrement = POSITION_INCREMENT;
        Set<UUID> identifiers = baseModel.getBuildingIds();
        for (UUID identifier : identifiers) {
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
    public void testStructureDemolition() {
        Set<UUID> buildingKeys = baseModel.getBuildingIds();
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
    public void testGamePause() throws NotEnoughResourceException,
        BuildingMaxedOutException, InvalidStructureReferenceException,
        InterruptedException {
        Object synchronizationObject = new Object();
        long initialWaitingTime = GAME_PAUSE_MS;
        counter = 0;
        testBuildMultipleStructures();
        Iterator<UUID> buildingIdentifiers = baseModel.getBuildingIds().iterator();
        UUID singleBuildingUUID = buildingIdentifiers.next();
        long buildingTime = baseModel.getBuildingMap().get(singleBuildingUUID).getBuildingTime();
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
        long startTime = System.currentTimeMillis();
        baseModel.setClockTicking(true);
        synchronized (synchronizationObject) {
            synchronizationObject.wait();
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            Assertions.assertEquals(1,
                    baseModel.getBuildingMap().get(singleBuildingUUID).getLevel());
            Assertions.assertTrue(BaseTestUtils.checkElapsedTime(elapsedTime,
                    buildingTime - initialWaitingTime,
                    BaseTestUtils.STANDARD_TIME_TOLERANCE));
        }
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
