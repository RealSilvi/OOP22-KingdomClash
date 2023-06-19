package it.unibo.model.base;

import java.awt.geom.Point2D;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.base.api.BuildingObserver;
import it.unibo.model.base.exceptions.BuildingMaxedOutException;
import it.unibo.model.base.exceptions.InvalidBuildingPlacementException;
import it.unibo.model.base.exceptions.InvalidStructureReferenceException;
import it.unibo.model.base.exceptions.MaxBuildingLimitReachedException;
import it.unibo.model.base.exceptions.NotEnoughResourceException;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.model.data.GameData;
import it.unibo.model.data.Resource;
import it.unibo.model.data.Resource.ResourceType;
/**
 * Tests the base model's thread manager.
 */
public final class ThreadManagerImplTest {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    private GameData gameData;
    private BaseModel baseModel;

    /**
     * Initializes the game model.
     */
    @BeforeEach
    public void initModel() {
        this.gameData = new GameData();
        this.baseModel = new BaseModelImpl(this.gameData);
    }
    /**
     * Checks the full building and upgrade cycle.
     * @throws NotEnoughResourceException           thrown when an action that
     *                                              requires resources is being
     *                                              done when not present
     * @throws InvalidBuildingPlacementException    
     * @throws BuildingMaxedOutException            thrown when the level limit
     *                                              is exceeded
     * @throws InvalidStructureReferenceException   thrown if a non existing building is
     *                                              being referenced
     * @throws MaxBuildingLimitReachedException     thrown when the maximum number
     *                                              of building is reached
     */
    @Test
    public void testBuildingAndProductionCycle() throws NotEnoughResourceException, InvalidBuildingPlacementException, BuildingMaxedOutException, InvalidStructureReferenceException, MaxBuildingLimitReachedException {
        Object lock = new Object();
        if (gameData.getResources().add(new Resource(ResourceType.WHEAT, 30)) == false) {
            gameData.getResources().remove(new Resource(ResourceType.WHEAT, 30));
            gameData.getResources().add(new Resource(ResourceType.WHEAT, 30));
        }
        if (gameData.getResources().add(new Resource(ResourceType.WOOD, 50)) == false) {
            gameData.getResources().remove(new Resource(ResourceType.WOOD, 50));
            gameData.getResources().add(new Resource(ResourceType.WOOD, 50));
        }
        UUID builtStructureId = baseModel.buildStructure(new Point2D.Float(0.0f, 0.0f), BuildingTypes.FARM, 0, true);
        long buildingTime = baseModel.getBuildingMap().get(builtStructureId).getBuildingTime();
        baseModel.addBuildingStateChangedObserver(new BuildingObserver() {
            @Override
            public void update(UUID buildingId) {
                if (gameData.getBuildings().get(buildingId).getLevel() != 1) {
                    logger.log(Level.INFO, "Checking building progress {0}%", gameData.getBuildings().get(buildingId).getBuildingProgress());
                    return;
                }
                synchronized (lock) {
                    lock.notifyAll();
                }
            }
        });
        if (gameData.getResources().add(new Resource(ResourceType.WHEAT, 34)) == false) {
            gameData.getResources().remove(new Resource(ResourceType.WHEAT, 34));
            gameData.getResources().add(new Resource(ResourceType.WHEAT, 34));
        }
        if (gameData.getResources().add(new Resource(ResourceType.WOOD, 57)) == false) {
            gameData.getResources().remove(new Resource(ResourceType.WOOD, 57));
            gameData.getResources().add(new Resource(ResourceType.WOOD, 57));
        }
        long startTime = System.currentTimeMillis();
        baseModel.upgradeStructure(builtStructureId);
        synchronized (lock) {
            try {
                lock.wait();
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;
                logger.info("Time passed: " + elapsedTime);
                //Tolerance of 500ms
                boolean timeElapsedCorrect = (elapsedTime < (buildingTime + 500)) && (elapsedTime > (buildingTime - 500));
                Assertions.assertEquals(1, baseModel.getBuildingMap().get(builtStructureId).getLevel());
                Assertions.assertTrue(timeElapsedCorrect);
            } catch (InterruptedException e) {
            }
        }
        baseModel.addBuildingProductionObserver(new BuildingObserver() {
            @Override
            public void update(UUID buildingId) {
                if (buildingId.equals(builtStructureId) && gameData.getBuildings().get(builtStructureId).getProductionProgress() == 99) {
                    synchronized (lock) {
                        lock.notifyAll();
                    }
                }
            }
        });
        synchronized (lock) {
            try {
                lock.wait();
                Thread.sleep(1000L);
                Assertions.assertEquals(Resource.checkAndAddMissingResources(
                    Resource.deepCopySet(gameData.getBuildings().get(builtStructureId)
                            .getProductionAmount())), gameData.getResources());
                Assertions.assertEquals(1, baseModel.getBuildingMap().get(builtStructureId).getLevel());
            } catch (InterruptedException e) {
            }
        }
    }
}
