package it.unibo.model.base;

import java.awt.geom.Point2D;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Stream;

import org.jetbrains.annotations.NotNull;

import it.unibo.model.base.api.BuildingObserver;
import it.unibo.model.base.basedata.Building;
import it.unibo.model.base.exceptions.BuildingMaxedOutException;
import it.unibo.model.base.exceptions.InvalidBuildingPlacementException;
import it.unibo.model.base.exceptions.InvalidStructureReferenceException;
import it.unibo.model.base.exceptions.InvalidTroopLevelException;
import it.unibo.model.base.exceptions.MaxBuildingLimitReachedException;
import it.unibo.model.base.exceptions.NotEnoughResourceException;
import it.unibo.model.base.internal.BuildingBuilder;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.model.base.internal.BuildingBuilderImpl;
import it.unibo.model.data.FightData;
import it.unibo.model.data.GameData;
import it.unibo.model.data.Resource;
import it.unibo.model.data.Resource.ResourceType;
import it.unibo.view.battle.Troop;

public class BaseModelImpl implements BaseModel {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private GameData gameData;
    private ThreadManager threadManager;

    private List<BuildingObserver> buildingStateChangedObservers;
    private List<BuildingObserver> buildingProductionObservers;

    public BaseModelImpl(@NotNull GameData gameData) {
        Objects.requireNonNull(gameData);
        this.gameData = gameData;
        this.threadManager = new ThreadManagerImpl(this, gameData.getBuildings());
        this.buildingStateChangedObservers = new ArrayList<>();
        this.buildingProductionObservers = new ArrayList<>();
        initializeDataStructures();
        logger.info("Base model succesfully initialized");
    }

    @Override
    public UUID buildStructure(final Point2D position, final BuildingTypes type, final int startingLevel, final boolean cheatMode)
            throws NotEnoughResourceException, InvalidBuildingPlacementException, MaxBuildingLimitReachedException {
        if (gameData.getBuildings().size() >= Building.MAXBUILDINGS) {
            throw new MaxBuildingLimitReachedException();
        }
        BuildingBuilder buildingBuilder = new BuildingBuilderImpl();
        Building newStructure = buildingBuilder.makeStandardBuilding(type, position, startingLevel);
        if (!cheatMode) {
            gameData.setResources(subtractResources(gameData.getResources(),
            BuildingBuilder
            .applyIncrementToResourceSet(newStructure.getType().getCost(),
                startingLevel)));
        }
        UUID newStructureId = generateBuildingId();
        gameData.getBuildings().put(newStructureId, newStructure);
        return newStructureId;
    }

    @Override
    public UUID buildStructure(final Point2D position, final BuildingTypes type, final int startingLevel)
            throws NotEnoughResourceException, InvalidBuildingPlacementException, MaxBuildingLimitReachedException {
        return buildStructure(position, type, startingLevel, false);
    }

    @Override
    public UUID buildStructure(Point2D position, BuildingTypes type)
            throws NotEnoughResourceException, InvalidBuildingPlacementException, MaxBuildingLimitReachedException {
        return buildStructure(position, type, 0, false);
    }
    @Override
    public void upgradeStructure(UUID structureId, boolean cheatMode)
            throws NotEnoughResourceException, BuildingMaxedOutException, InvalidStructureReferenceException {
        checkAndGetBuilding(structureId);
        if (this.gameData.getBuildings().get(structureId).getLevel() >= Building.MAXLEVEL) {
            throw new BuildingMaxedOutException();
        }
        gameData.setResources(subtractResources(gameData.getResources(),
            this.gameData.getBuildings().get(structureId).getType().getCost(
                this.gameData.getBuildings().get(structureId).getLevel()+1)));
        this.gameData.getBuildings().get(structureId).setBeingBuilt(true);
        addBuildingStateChangedObserver(new BuildingObserver() {
            @Override
            public void update(UUID buildingId) {
                if (structureId.equals(buildingId) 
                    && (gameData.getBuildings().get(structureId).getBuildingProgress() == 0
                        && !gameData.getBuildings().get(structureId).isBeingBuilt())) {
                        threadManager.addBuilding(buildingId);
                }
            }
        });
        threadManager.addBuilding(structureId);
    }

    @Override
    public void upgradeStructure(UUID structureId)
            throws NotEnoughResourceException, BuildingMaxedOutException, InvalidStructureReferenceException {
        upgradeStructure(structureId, false);
    }
    @Override
    public Set<Resource> demolishStructure(UUID structureId) throws InvalidStructureReferenceException {
        checkAndGetBuilding(structureId);
        Set<Resource> refund = this.gameData.getBuildings().get(structureId).getType().getCost(
            this.gameData.getBuildings().get(structureId).getLevel()+1);
        for (Resource resource : refund) {
            resource.setAmount(resource.getAmount()%Building.REFUND_TAX_PERCENTAGE);
        }
        try {
            applyResources(refund);
        } catch (NotEnoughResourceException e) {
            logger.severe("Not enough resources Exception thrown when adding resources,"
                        +" this implies a broken state of the player resource set and should be fixed!"
                        +" dumping stacktrace:\n"+e.getStackTrace());
        }
        return refund;
    }

    @Override
    public void relocateStructure(Point2D position, UUID structureId)
            throws InvalidBuildingPlacementException, InvalidStructureReferenceException {
        checkAndGetBuilding(structureId);
        Set<UUID> keys = gameData.getBuildings().keySet();
        for (UUID key : keys) {
            if (gameData.getBuildings().get(key).getStructurePos().equals(position) && !structureId.equals(key)) {
                throw new InvalidBuildingPlacementException();
            }
        }
        this.gameData.getBuildings().get(structureId).setStructurePos(position);
    }

    @Override
    public Path getStructureTexture(UUID structureId) throws InvalidStructureReferenceException {
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
    public void upgradeTroop(Troop troopToUpgrade) throws InvalidTroopLevelException {
        upgradeTroop(troopToUpgrade, troopToUpgrade.getLevel()+1);
    }

    @Override
    public void upgradeTroop(Troop troopToUpgrade, int level) throws InvalidTroopLevelException {
        //TODO Remove placeholder when limit is implemented and finish this function
        //TODO Implement cost system when battle part is complete
        int placeholderLimit = 3;
        if (level>=placeholderLimit) {
            throw new InvalidTroopLevelException(troopToUpgrade, level);
        }
        if (gameData.getFightData().isEmpty()) {
            gameData.setFightData(Optional.of(new FightData()));
        }
    }

    @Override
    public Map<Troop, Integer> getTroopMap() {
        return Collections.unmodifiableMap(gameData.getPlayerArmyLevel());
    }

    @Override
    public void addBuildingStateChangedObserver(BuildingObserver observer) {
        this.buildingStateChangedObservers.add(observer);
    }

    @Override
    public void removeBuildingStateChangedObserver(BuildingObserver observer) {
        this.buildingProductionObservers.remove(observer);
    }

    @Override
    public void addBuildingProductionObserver(BuildingObserver observer) {
        this.buildingProductionObservers.add(observer);
    }

    @Override
    public void removeBuildingProductionObserver(BuildingObserver observer) {
        this.buildingProductionObservers.remove(observer);
    }

    @Override
    public void setClockTicking(boolean ticktime) {
        if (!ticktime) {
            this.threadManager.pauseThreads();
        } else {
            this.threadManager.startThreads();
        }
    }

    @Override
    public boolean isClockTicking() {
        return this.threadManager.areThreadsRunning();
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

    @Override
    public String getPlayerName() {
        return gameData.getPlayerName();
    }
    
    @Override
    public void setPlayerName(String playerName) {
        gameData.setPlayerName(playerName);
    }

    @Override
    public void notifyBuildingStateChangedObservers(UUID building) {
        this.buildingStateChangedObservers.forEach(buildingStateObserver->buildingStateObserver.update(building));
    }
    @Override
    public void notifyBuildingProductionObservers(UUID building) {
        this.buildingProductionObservers.forEach(productionObserver->productionObserver.update(building));
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
        while (storageIterator.hasNext()) {
            Resource currentStorageResource = storageIterator.next();
            Iterator<Resource> costIterator = resourceCost.iterator();
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
        Set<Resource> negatedResources = Resource.deepCopySet(resourceToNegate);
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
    /**
     * Check if data structures in GameData are initialized, if not
     * this method will correctly initialize the data structures
     */
    private void initializeDataStructures() {
        initializeResourceSet();
    }

    private void initializeResourceSet() {
        if (gameData.getResources() == null) {
            gameData.setResources(new HashSet<>());
        }
        if (gameData.getResources().size() != Resource.ResourceType.values().length) {
            Set.of(Resource.ResourceType.values()).forEach(
                resourceType->gameData.getResources().add(new Resource(resourceType)));
        }
    }
    @Override
    public void refreshBuildings() {
        this.gameData.getBuildings().forEach((structureId, structure)->
            this.threadManager.addBuilding(structureId));
    }
}