package it.unibo.controller.base;

import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import it.unibo.model.base.basedata.Building;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.model.data.GameData;
import it.unibo.model.data.Resource;

public final class BaseControllerTest {
    private GameData gameDataRef;
    private BaseController baseController;

    @Before
    public void initBaseMVC() {
        GameData generatedGameData = gameDataRef = new GameData();
        this.gameDataRef = generatedGameData;
        Set<Resource> resources = new HashSet<>();
        resources.add(new Resource(Resource.ResourceType.WHEAT, 999));
        resources.add(new Resource(Resource.ResourceType.WOOD, 999));
        this.gameDataRef.setResources(resources);
        this.baseController = new BaseControllerImpl(generatedGameData);
    }
    @Test
    public void testDataIncapsulation() {
        Set<Resource> requestSet = this.baseController.requestResourceCount();
        Map<UUID, Building> buildingMap = this.baseController.requestBuildingMap();

        Optional<UUID> createdBuildingId = baseController.handleBuildingPlaced(new Point2D.Float(11f, 24f), BuildingTypes.FARM);
        Assertions.assertTrue(createdBuildingId.isPresent());
        
        Assertions.assertThrowsExactly(UnsupportedOperationException.class,
            () -> requestSet.add(new Resource(Resource.ResourceType.WHEAT, 104)));
        Assertions.assertThrowsExactly(NullPointerException.class,
            () -> buildingMap.put(UUID.randomUUID(), new Building(null, 0, 0,
             0, false, 0, 0, null, requestSet, requestSet)));
    }
}
