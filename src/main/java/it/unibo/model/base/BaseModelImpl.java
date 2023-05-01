package it.unibo.model.base;

import java.awt.geom.Point2D;

import it.unibo.model.base.api.BuildingObserver;
import it.unibo.model.base.basedata.Building;
import it.unibo.model.base.exceptions.BuildingMaxedOutException;
import it.unibo.model.base.exceptions.InvalidBuildingPlacementException;
import it.unibo.model.base.exceptions.InvalidStructureReferenceException;
import it.unibo.model.base.exceptions.NotEnoughResourceException;
import it.unibo.model.base.internal.BuildingFactory.BuildingTypes;
import it.unibo.model.data.GameData;
import it.unibo.model.data.Resource;

import java.awt.geom.Point2D;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class BaseModelImpl implements BaseModel {

    Logger logger = Logger.getLogger(this.getClass().getName());
    private GameData gameData;

    public BaseModelImpl(GameData gameData) {
        this.gameData = gameData;
    }

    public GameData obtainGameData() {
        return this.gameData;
    }

    private UUID generateBuildingId() {
        return Stream.generate(UUID::randomUUID)
        .filter(x->!gameData.getBuildings().containsKey(x)).findFirst()
        .orElseThrow();
    }
}