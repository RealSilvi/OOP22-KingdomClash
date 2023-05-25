package it.unibo.model.base;

import java.awt.geom.Point2D;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import it.unibo.model.base.exceptions.InvalidBuildingPlacementException;
import it.unibo.model.base.exceptions.NotEnoughResourceException;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.model.data.GameData;
import it.unibo.model.data.Resource;
import it.unibo.model.data.Resource.ResourceType;

public class BaseModelImplTest {
    @Test
    public void testBuildStructure() {
        boolean wasExceptionThrown = false;
        GameData gameData = new GameData();
        gameData.setResources(
            Set.of(new Resource(ResourceType.WHEAT, 0),
                new Resource(ResourceType.WOOD, 0)));
        BaseModel baseModel = new BaseModelImpl(gameData);
        try {
            baseModel.buildStructure(new Point2D.Float(0.0f, 0.0f), BuildingTypes.FARM);
        } catch (NotEnoughResourceException e) {
            wasExceptionThrown = true;
        } catch (InvalidBuildingPlacementException e) {}
        Assertions.assertTrue(wasExceptionThrown);
    }
}