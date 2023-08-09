package it.unibo.controller.base;

import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import it.unibo.model.base.basedata.Building;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.model.data.GameData;
import it.unibo.model.data.Resource;
import it.unibo.model.data.TroopType;
/**
 * Tests for the BaseController logic.
 */
final class BaseControllerTest {
    private GameData gameDataRef;
    private BaseController baseController;
    private final Random randomGen = new Random();

    /**
     * Initializes the Base MVC.
     */
    @BeforeEach
    //In this case, null checking is not necessary.
    @SuppressWarnings("null")
    void initBaseMVC() {
        gameDataRef = new GameData();
        final Set<Resource> resources = new HashSet<>();
        resources.add(new Resource(Resource.ResourceType.WHEAT, Integer.MAX_VALUE));
        resources.add(new Resource(Resource.ResourceType.WOOD, Integer.MAX_VALUE));
        this.gameDataRef.setResources(resources);
        this.baseController = new BaseControllerImpl(gameDataRef);
    }
    /**
     * Tests if all of the data structures are safely returned.
     */
    @Test
    void testDataIncapsulation() {
        final Set<Resource> resourceSet = this.baseController.requestResourceCount();
        final Map<TroopType, Integer> troopMap = this.baseController.requestTroopLevels();
        final Map<UUID, Building> buildingMap = this.baseController.requestBuildingMap();

        final Optional<UUID> createdBuildingId = baseController.handleBuildingPlaced(
            new Point2D.Float(randomGen.nextFloat(), randomGen.nextFloat()), BuildingTypes.FARM);
        Assertions.assertTrue(createdBuildingId.isPresent());

        Assertions.assertThrowsExactly(UnsupportedOperationException.class,
            () -> resourceSet.add(new Resource(Resource.ResourceType.WHEAT, randomGen.nextInt())));
        Assertions.assertThrowsExactly(UnsupportedOperationException.class,
            () -> troopMap.put(TroopType.AXE, gameDataRef.getGameConfiguration()
                .getBaseConfiguration().getMaximumTroopLevel()));
        Assertions.assertThrowsExactly(UnsupportedOperationException.class,
            () -> buildingMap.put(UUID.randomUUID(), new Building(null, 0, 0,
                0, false, 0, 0,
                new Point2D.Float(0.0f, 0.0f), resourceSet, resourceSet)));
    }
}
