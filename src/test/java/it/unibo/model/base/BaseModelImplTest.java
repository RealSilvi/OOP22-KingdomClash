package it.unibo.model.base;

import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import it.unibo.model.base.api.BuildingObserver;
import it.unibo.model.base.exceptions.BuildingMaxedOutException;
import it.unibo.model.base.exceptions.InvalidBuildingPlacementException;
import it.unibo.model.base.exceptions.InvalidStructureReferenceException;
import it.unibo.model.base.exceptions.NotEnoughResourceException;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.model.data.GameData;

public class BaseModelImplTest {
    private GameData gameData;
    private BaseModel baseModel;
    private int counter;

    @org.junit.jupiter.api.BeforeEach
    public void buildAllStructures() {
        initModel();
        float xvariation = 0.0f;
        for (BuildingTypes currentType : BuildingTypes.values()) {
            xvariation++;
            Point2D pos = new Point2D.Float(xvariation, 0);
            Assertions.assertDoesNotThrow(() -> baseModel.buildStructure(pos, currentType, 0, true));
        }
    }

    @Test
    public void testBuildMultipleStructures() {
        buildAllStructures();
    }

    @Test
    public void testStructureUpgrade() {
        testBuildMultipleStructures();
        Object synchronizationObject = new Object();
        Set<UUID> buildingKeys = baseModel.getBuildingIds();
        buildingKeys.forEach((buildingIdentifier) -> {
            baseModel.addBuildingStateChangedObserver(new BuildingObserver() {
                @Override
                public void update(UUID buildingId) {
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

    @Test
    public void testStructureRelocation() throws InvalidBuildingPlacementException, InvalidStructureReferenceException {
        testBuildMultipleStructures();
        float positionIncrement = 5.5f;
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

    @Test
    public void testStructureDemolition() {
        testBuildMultipleStructures();
        Set<UUID> buildingKeys = baseModel.getBuildingIds();
        buildingKeys.forEach((buildingIdentifier) -> Assertions.assertDoesNotThrow(() -> baseModel.demolishStructure(buildingIdentifier)));
    }

    @Test
    public void testGamePause() throws NotEnoughResourceException, BuildingMaxedOutException, InvalidStructureReferenceException, InterruptedException {
        Object synchronizationObject = new Object();
        long initialWaitingTime = 5000L;
        counter = 0;
        testBuildMultipleStructures();
        Iterator<UUID> buildingIdentifiers = baseModel.getBuildingIds().iterator();
        UUID singleBuildingUUID = buildingIdentifiers.next();
        long buildingTime = baseModel.getBuildingMap().get(singleBuildingUUID).getBuildingTime();
        baseModel.addBuildingStateChangedObserver(new BuildingObserver() {
            @Override
            public void update(UUID buildingId) {
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
            synchronizationObject.wait(buildingTime + 10000);
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

    public synchronized void increaseCounter() {
        counter++;
    }

    public int getCounter() {
        return this.counter;
    }

    private void initModel() {
        this.gameData = new GameData();
        this.baseModel = new BaseModelImpl(this.gameData);
    }
}