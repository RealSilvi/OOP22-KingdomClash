package it.unibo.model.base;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.base.api.BuildingObserver;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.model.data.GameData;
import it.unibo.model.data.Resource;
import it.unibo.model.data.Resource.ResourceType;
/**
 * Tests the base model's thread manager.
 */
final class ThreadManagerImplTest {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private static final int COMPLETED_PERC = 100;
    private static final long DEFAULT_WAIT_TIME = 1000L;

    private GameData gameData;
    private BaseModel baseModel;

    /**
     * Initializes the game model.
     */
    @BeforeEach
    void initModel() {
        this.gameData = new GameData();
        this.baseModel = new BaseModelImpl(this.gameData);
    }
    /**
     * Checks the full building and upgrade cycle.
     */
    @SuppressFBWarnings(value = {"SWL", "UW", "WA"},
        justification = "Controlled and expected behaviour")
    @Test
    void testBuildingAndProductionCycle() {
        final Object lock = new Object();
        final UUID builtStructureId = Assertions.assertDoesNotThrow(() -> 
            baseModel.buildStructure(
                new Point2D.Float(0.0f, 0.0f), BuildingTypes.FARM, 0, true));
        final long buildingTime = baseModel.getBuildingMap().get(builtStructureId).getBuildingTime();
        baseModel.addBuildingStateChangedObserver(new BuildingObserver() {
            @Override
            public void update(final UUID buildingId) {
                if (gameData.getBuildings().get(buildingId).getLevel() != 1) {
                    logger.log(Level.FINEST,
                        "Checking building progress {0}%",
                        gameData.getBuildings()
                            .get(buildingId).getBuildingProgress());
                    return;
                }
                synchronized (lock) {
                    lock.notifyAll();
                }
            }
        });
        synchronized (gameData.getResources()) {
            Arrays.stream(ResourceType.values())
                .forEach(singleType -> {
                    gameData.getResources().remove(new Resource(singleType, Integer.MAX_VALUE / 2));
                    gameData.getResources().add(new Resource(singleType, Integer.MAX_VALUE / 2));
                });
        }
        final long startTime = System.currentTimeMillis();
        Assertions.assertDoesNotThrow(() -> 
            baseModel.upgradeStructure(builtStructureId));
        synchronized (lock) {
            try {
                lock.wait();
                final long endTime = System.currentTimeMillis();
                final long elapsedTime = endTime - startTime;
                logger.log(Level.FINEST, "Time passed: {0}", elapsedTime);
                final boolean timeElapsedCorrect = BaseTestUtils
                    .checkElapsedTime(elapsedTime,
                        buildingTime,
                        BaseTestUtils.STANDARD_TIME_TOLERANCE);
                Assertions.assertEquals(1, baseModel.getBuildingMap().get(builtStructureId).getLevel());
                Assertions.assertTrue(timeElapsedCorrect);
            } catch (InterruptedException e) {
            }
        }
        baseModel.addBuildingProductionObserver(new BuildingObserver() {
            @Override
            public void update(final UUID buildingId) {
                if (buildingId.equals(builtStructureId)
                    && gameData.getBuildings().get(builtStructureId).getProductionProgress()
                    == (COMPLETED_PERC - 1)) {
                    synchronized (lock) {
                        lock.notifyAll();
                    }
                }
            }
        });
        synchronized (lock) {
            try {
                lock.wait();
                Thread.sleep(DEFAULT_WAIT_TIME);
                Assertions.assertEquals(Resource.checkAndAddMissingResources(
                    Resource.deepCopySet(gameData.getBuildings().get(builtStructureId)
                            .getProductionAmount())), gameData.getResources());
                Assertions.assertEquals(1, baseModel.getBuildingMap().get(builtStructureId).getLevel());
            } catch (InterruptedException e) {
            }
        }
    }
}
