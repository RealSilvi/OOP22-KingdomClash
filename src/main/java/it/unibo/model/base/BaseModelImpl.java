package it.unibo.model.base;

import java.awt.geom.Point2D;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;
import java.util.stream.Stream;

import org.jetbrains.annotations.NotNull;

import it.unibo.model.base.api.BuildingObserver;
import it.unibo.model.base.basedata.Building;
import it.unibo.model.base.exceptions.BuildingMaxedOutException;
import it.unibo.model.base.exceptions.InvalidBuildingPlacementException;
import it.unibo.model.base.exceptions.InvalidStructureReferenceException;
import it.unibo.model.base.exceptions.NotEnoughResourceException;
import it.unibo.model.base.internal.BuildingBuilder;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.model.base.internal.BuildingBuilderImpl;
import it.unibo.model.data.GameData;
import it.unibo.model.data.Resource;
import it.unibo.model.data.Resource.ResourceType;

public class BaseModelImpl implements BaseModel {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private GameData gameData;
    private ThreadManager threadManager;

    public BaseModelImpl(@NotNull GameData gameData) {
        this();
        Objects.requireNonNull(gameData);
        this.gameData = gameData;
    }
    private BaseModelImpl(){
        //TODO: When implemented instantiate ThreadManager
        /*this.threadManager = new ThreadManagerImpl()*/;
    }
    //TODO: Make sure to make return values unmodifiable
    @Override
    public UUID buildStructure(final Point2D position, final BuildingTypes type, final int startingLevel, final boolean cheatMode)
            throws NotEnoughResourceException, InvalidBuildingPlacementException {
        BuildingBuilder buildingBuilder = new BuildingBuilderImpl();
        Building newStructure = buildingBuilder.makeStandardBuilding(type, position, startingLevel);
        gameData.setResources(subtractResources(gameData.getResources(),
            BaseModel
            .applyMultiplierToResources(newStructure.getType().getCost(),
                startingLevel)));
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
    //TODO: Remember to upgrade ALL properties of a building
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
        return selectedBuilding.getBuildingProgress();
    }

    @Override
    public Set<Resource> getBuildingProduction(UUID structureId) throws InvalidStructureReferenceException {
        Building selectedBuilding = checkAndGetBuilding(structureId);
        return Collections.unmodifiableSet(selectedBuilding.getProductionAmount());
    }

    @Override
    public boolean isBuildingBeingBuilt(UUID structureId) throws InvalidStructureReferenceException {
        Building selectedBuilding = checkAndGetBuilding(structureId);
        return selectedBuilding.isBeingBuilt();
    }

    @Override
    public Set<UUID> getBuildingIds() {
        return Collections.unmodifiableSet(gameData.getBuildings().keySet());
    }

    @Override
    public int getResourceCount(ResourceType type) {
        Optional<Resource> resourceCounter = gameData
            .getResources()
            .stream().filter(x->x.getResource().equals(type)).findFirst();
        if (resourceCounter.isEmpty()) {
            return Integer.valueOf(0);
        }
        return Integer.valueOf(resourceCounter.get().getAmount());
    }

    @Override
    public Set<Resource> getResourceCount() {
        return Collections.unmodifiableSet(gameData.getResources());
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

    @Override
    public synchronized void applyResources(Set<Resource> resource) throws NotEnoughResourceException {
        applyResources(resource, OperationType.ADDITION);
    }
    @Override
    public synchronized void applyResources(Set<Resource> resource, OperationType operation) throws NotEnoughResourceException {
        switch(operation) {
            case SUBTRACTION:
                this.gameData.setResources(subtractResources(this.gameData.getResources(), resource));
            break;
            case ADDITION:
                this.gameData.setResources(addResources(this.gameData.getResources(), resource));
            break;
        }
    }

    @Override
    public Map<UUID, Building> getBuildingMap() {
        Map<UUID, Building> unmodMap = gameData.getBuildings();
        return Collections.unmodifiableMap(unmodMap);
    }

    private synchronized void notifyBuildingStateChanged() {
    }
    private synchronized void notifyBuildingProduction() {
    }

    /**
     * Executes an addition between resources of the same type inside the set
     * this operation is unsafe because it doesn't check for negative results
     * @param resourceStorage the resource set that is going to be affected
     * @param resourceCost the second set that contains resources that will
     * be used or added
     * @return a set with the result of the operation
     */
    private Set<Resource> unsafeOperation(final Set<Resource> resourceStorage, Set<Resource> resourceCost) {
        Set<Resource> storageResult = new HashSet<>();
        Iterator<Resource> storageIterator = resourceStorage.iterator();
        Iterator<Resource> costIterator = resourceCost.iterator();
        while (storageIterator.hasNext()) {
            Resource currentStorageResource = storageIterator.next();
            while (costIterator.hasNext()) {
                Resource currentCostResource = costIterator.next();
                if (currentStorageResource.equals(currentCostResource)) {
                    storageResult.add(new Resource(currentStorageResource.getResource(),
                    currentStorageResource.getAmount()+currentCostResource.getAmount()));
                }
            }
        }
        return storageResult;
    }
    /**
     * Checks for negative values in the resources inside the resources in the set
     * @param resourcesToCheck the set that needs to be checked
     * @return the resources with negative values within the set
     */
    private Set<Resource> filterNegativeValues(final Set<Resource> resourcesToCheck) {
        Set<Resource> missingResources = new HashSet<>();
        resourcesToCheck.forEach(x->{
            if (x.getAmount()<0) {
                missingResources.add(x);
            }
        });
        return missingResources;
    }
    /**
     * Inverts the sign of values inside a set of resources
     * @param resourceToNegate the set of resources to negate
     * @return a negated set
     */
    private Set<Resource> negateResources(Set<Resource> resourceToNegate) {
        Set<Resource> negatedResources = new HashSet<>(resourceToNegate);
        negatedResources.stream().forEach(x->x.setAmount(-x.getAmount()));
        return negatedResources;
    }

    private Set<Resource> subtractResources(Set<Resource> resourceStorage, Set<Resource> resourceCost) throws NotEnoughResourceException {
        return addResources(resourceStorage, negateResources(resourceCost));
    }

    private Set<Resource> addResources(Set<Resource> resourceStorage, Set<Resource> resourcesAdded)  throws NotEnoughResourceException {
        Set<Resource> updatedList = unsafeOperation(resourceStorage, resourcesAdded);
        Set<Resource> missingResources = filterNegativeValues(updatedList);
        if (missingResources.isEmpty()) {
            return updatedList;
        }
        throw new NotEnoughResourceException(missingResources);
    }
    /**
     * Checks if the referenced UUID corresponds to a building
     * and returns a building
     * @param structureId the id to check for
     * @return the building with the corresponding UUID
     * @throws InvalidStructureReferenceException thrown when the given UUID
     * does not correspond to an existing building
     */
    private Building checkAndGetBuilding(UUID structureId) throws InvalidStructureReferenceException {
        Building selectedBuilding = gameData.getBuildings().get(structureId);
        if (selectedBuilding == null) {
            throw new InvalidStructureReferenceException(structureId);
        }
        return selectedBuilding;
    }
    /**
     * Makes sure that a non-conflicting UUID is generated for a building
     * @return a freshly generated UUID
     */
    private UUID generateBuildingId() {
        return Stream.generate(UUID::randomUUID)
        .filter(x->!gameData.getBuildings().containsKey(x)).findFirst()
        .orElseThrow();
    }
}