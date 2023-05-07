package it.unibo.model.base;

import java.awt.geom.Point2D;

import it.unibo.model.base.api.BuildingObserver;
import it.unibo.model.base.basedata.Building;
import it.unibo.model.base.exceptions.BuildingMaxedOutException;
import it.unibo.model.base.exceptions.InvalidBuildingPlacementException;
import it.unibo.model.base.exceptions.InvalidStructureReferenceException;
import it.unibo.model.base.exceptions.NotEnoughResourceException;
import it.unibo.model.base.internal.BuildingBuilder;
import it.unibo.model.base.internal.BuildingBuilderImpl;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.model.data.GameData;
import it.unibo.model.data.Resource;
import it.unibo.model.data.Resource.ResourceType;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class BaseModelImpl implements BaseModel {

    Logger logger = Logger.getLogger(this.getClass().getName());
    private GameData gameData;

    public BaseModelImpl(GameData gameData) {
        this.gameData = gameData;
    }

    @Override
    public UUID buildStructure(final Point2D position, final BuildingTypes type, final int startingLevel, final boolean cheatMode)
            throws NotEnoughResourceException, InvalidBuildingPlacementException {
        BuildingBuilder buildingBuilder = new BuildingBuilderImpl();
        Building newStructure = buildingBuilder.makeStandardBuilding(type, position, startingLevel);
        gameData.setResources(subtractResources(gameData.getResources(), BaseModel.applyMultiplierToResources(newStructure.getType().getCost(), startingLevel)));
        UUID newStructureId = generateBuildingId();
        gameData.getBuildings().put(newStructureId, newStructure);
        return newStructureId;
    }

    @Override
    public UUID buildStructure(final Point2D position, final BuildingTypes type, final int startingLevel)
            throws NotEnoughResourceException, InvalidBuildingPlacementException {
        return buildStructure(position, type, startingLevel, false);
    }

    @Override
    public UUID buildStructure(Point2D position, BuildingTypes type)
            throws NotEnoughResourceException, InvalidBuildingPlacementException {
        return buildStructure(position, type, 0, false);
    }

    @Override
    public void upgradeStructure(UUID structureId, boolean cheatMode)
            throws NotEnoughResourceException, BuildingMaxedOutException, InvalidStructureReferenceException {
        Building selectedBuilding = checkAndGetBuilding(structureId);
        if (selectedBuilding.getLevel() == Building.MAXLEVEL) {
            throw new BuildingMaxedOutException();
        }
        float buildingTime = selectedBuilding.getBuildingTime();
        if (!cheatMode) {
            buildingTime = 0.0f;
        }
        gameData.setResources(subtractResources(gameData.getResources(), BaseModel.applyMultiplierToResources(selectedBuilding.getType().getCost(), selectedBuilding.getLevel()+1)));
        //TODO: Start timer that builds structure
    }

    @Override
    public void upgradeStructure(UUID structureId)
            throws NotEnoughResourceException, BuildingMaxedOutException, InvalidStructureReferenceException {
        upgradeStructure(structureId, false);
    }

    @Override
    public Set<Resource> demolishStructure(UUID structureId) throws InvalidStructureReferenceException {
        Building selectedBuilding = checkAndGetBuilding(structureId);
        Set<Resource> refund = BaseModel.applyMultiplierToResources(selectedBuilding.getType().getCost(), selectedBuilding.getLevel());
        for (Resource resource : refund) {
            resource.setAmount(resource.getAmount()%Building.REFUND_TAX_PERCENTAGE);
        }
        return refund;
    }

    @Override
    public void relocateStructure(Point2D position, UUID structureId)
            throws InvalidBuildingPlacementException, InvalidStructureReferenceException {
        Building selectedBuilding = checkAndGetBuilding(structureId);
        Set<UUID> keys = gameData.getBuildings().keySet();
        for (UUID key : keys) {
            if (gameData.getBuildings().get(key).getStructurePos().equals(position) && !structureId.equals(key)) {
                throw new InvalidBuildingPlacementException();
            }
        }
        selectedBuilding.setStructurePos(position);
    }

    @Override
    public Path getStructureTexture(UUID structureId) throws InvalidStructureReferenceException {
        Building selectedBuilding = checkAndGetBuilding(structureId);
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStructureTexture'");
    }

    @Override
    public int getBuildingProgress(UUID structureId) throws InvalidStructureReferenceException {
        Building selectedBuilding = checkAndGetBuilding(structureId);
        return selectedBuilding.getBuildingProgess();
    }

    @Override
    public Set<Resource> getBuildingProduction(UUID structureId) throws InvalidStructureReferenceException {
        Building selectedBuilding = checkAndGetBuilding(structureId);
        return selectedBuilding.getProductionAmount();
    }

    @Override
    public boolean isBuildingBeingBuilt(UUID structureId) throws InvalidStructureReferenceException {
        Building selectedBuilding = checkAndGetBuilding(structureId);
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isBuildingBeingBuilt'");
    }

    @Override
    public List<UUID> getBuildingIds() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBuildingIds'");
    }

    @Override
    public int getResourceCount(ResourceType type) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getResourceCount'");
    }

    @Override
    public List<Resource> getResourceCount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getResourceCount'");
    }

    @Override
    public void addBuildingStateChangedObserver(BuildingObserver observer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addBuildingStateChangedObserver'");
    }

    @Override
    public void removeBuildingStateChangedObserver(BuildingObserver observer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeBuildingStateChangedObserver'");
    }

    @Override
    public void addBuildingProductionObserver(BuildingObserver observer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addBuildingProductionObserver'");
    }

    @Override
    public void removeBuildingProductionObserver(BuildingObserver observer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeBuildingProductionObserver'");
    }

    @Override
    public void setClockTicking(boolean ticktime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setClockTicking'");
    }

    @Override
    public boolean isClockTicking() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isClockTicking'");
    }

    @Override
    public GameData obtainGameData() {
        return this.gameData;
    }

    private UUID generateBuildingId() {
        return Stream.generate(UUID::randomUUID)
        .filter(x->!gameData.getBuildings().containsKey(x)).findFirst()
        .orElseThrow();
    }

    private Set<Resource> unsafeSubtraction(Set<Resource> resourceStorage, Set<Resource> resourceCost) {
        Set<Resource> storageResult = new HashSet<>();
        Iterator<Resource> storageIterator = resourceStorage.iterator();
        Iterator<Resource> costIterator = resourceCost.iterator();
        while (storageIterator.hasNext()) {
            Resource currentStorageResource = storageIterator.next();
            while (costIterator.hasNext()) {
                Resource currentCostResource = costIterator.next();
                if (currentStorageResource.equals(currentCostResource)) {
                    storageResult.add(new Resource(currentStorageResource.getResource(),
                    currentStorageResource.getAmount()-currentCostResource.getAmount()));
                }
            }
        }
        return storageResult;
    }
    //TODO: If possible, check if one ore more resources are missing and change the error message
    private Set<Resource> subtractResources(Set<Resource> resourceStorage, Set<Resource> resourceCost) throws NotEnoughResourceException{
        Set<Resource> updatedList = unsafeSubtraction(resourceStorage, resourceCost);
        Set<Resource> missingResources = new HashSet<>();
        updatedList.forEach(x->{
            if (x.getAmount()<0) {
                missingResources.add(x);
            }
        });
        if (missingResources.isEmpty()) {
            return updatedList;
        }
        throw new NotEnoughResourceException(missingResources);
    }
    private Building checkAndGetBuilding(UUID structureId) throws InvalidStructureReferenceException {
        Building selectedBuilding = gameData.getBuildings().get(structureId);
        if (selectedBuilding == null) {
            throw new InvalidStructureReferenceException(structureId);
        }
        return selectedBuilding;
    }
}