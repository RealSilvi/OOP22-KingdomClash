package it.unibo.model.base;

import java.awt.geom.Point2D;
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

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.base.api.BuildingObserver;
import it.unibo.model.base.basedata.BaseConfiguration;
import it.unibo.model.base.basedata.Building;
import it.unibo.model.base.basedata.BuildingConfiguration;
import it.unibo.model.base.exceptions.BuildingMaxedOutException;
import it.unibo.model.base.exceptions.InvalidBuildingPlacementException;
import it.unibo.model.base.exceptions.InvalidStructureReferenceException;
import it.unibo.model.base.exceptions.InvalidTroopLevelException;
import it.unibo.model.base.exceptions.MaxBuildingLimitReachedException;
import it.unibo.model.base.exceptions.NotEnoughResourceException;
import it.unibo.model.base.internal.BuildingBuilder;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.model.base.internal.BuildingBuilderImpl;
import it.unibo.kingdomclash.config.GameConfiguration;
import it.unibo.model.data.GameData;
import it.unibo.model.data.Resource;
import it.unibo.model.data.TroopType;
import it.unibo.model.data.Resource.ResourceType;

/**
 * Easy to use implementation of BaseModel, that uses threads for resources and
 * construction timing, can also be paused.
 */
public final class BaseModelImpl implements BaseModel {

    private static final String BEINGBUILT_ERROR_STRING = 
        "Selected building is currently being upgraded!";

    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private GameData gameData;
    private GameConfiguration configuration;
    private BuildingConfiguration buildingConfiguration;
    private BaseConfiguration baseModelConfiguration;
    private ThreadManager threadManager;

    private List<BuildingObserver> buildingStateChangedObservers;
    private List<BuildingObserver> buildingProductionObservers;

    /**
     * Constructs a new instance of BaseModelImpl with the specified
     * data class as storage.
     * 
     * @param gameData      data of the game, must not be null
     * @param configuration the configuration of the game
     */
    @SuppressFBWarnings(value = "EI2",
    justification = "No encapsulation needed as BaseModel handles everything")
    public BaseModelImpl(final @NotNull GameData gameData, final GameConfiguration configuration) {
        logger.finest("Initializing BaseModel...");
        Objects.requireNonNull(gameData);
        this.gameData = gameData;
        this.configuration = configuration;
        this.buildingConfiguration = this.configuration.getBuildingConfig();
        this.baseModelConfiguration = this.configuration.getBaseConfiguration();
        this.threadManager = new ThreadManagerImpl(this, gameData.getBuildings());
        this.buildingStateChangedObservers = new ArrayList<>();
        this.buildingProductionObservers = new ArrayList<>();
        initializeDataStructures();
        logger.finest("Base model succesfully initialized!");
    }
    /**
     * Constructs a new instance of BaseModelImpl with the specified
     * data class as storage and standard configuration.
     * 
     * @param gameData      data of the game, must not be null
     */
    public BaseModelImpl(final @NotNull GameData gameData) {
        this(gameData, new GameConfiguration());
    }

    @Override
    public UUID buildStructure(final Point2D position, final BuildingTypes type,
    final int startingLevel, final boolean cheatMode)
        throws NotEnoughResourceException,
        InvalidBuildingPlacementException, MaxBuildingLimitReachedException {
        if (gameData.getBuildings().size() >= buildingConfiguration.getMaxBuildings()) {
            throw new MaxBuildingLimitReachedException();
        }
        final BuildingBuilder buildingBuilder = new BuildingBuilderImpl();
        final Building newStructure = buildingBuilder
        .makeStandardBuilding(type, position, startingLevel);
            if (!cheatMode) {
                newStructure.setBeingBuilt(true);
                gameData.setResources(subtractResources(gameData.getResources(),
                    BuildingBuilder
                        .applyIncrementToResourceSet(newStructure
                        .getType().getCost(), startingLevel)));
            }
        final UUID newStructureId = generateBuildingId();
        gameData.getBuildings().put(newStructureId, newStructure);
        threadManager.addBuilding(newStructureId);
        notifyBuildingStateChangedObservers(newStructureId);
        return newStructureId;
    }

    @Override
    public UUID buildStructure(final Point2D position,
        final BuildingTypes type, final int startingLevel)
        throws NotEnoughResourceException,
        InvalidBuildingPlacementException, MaxBuildingLimitReachedException {
        return buildStructure(position, type, startingLevel, false);
    }

    @Override
    public UUID buildStructure(final Point2D position, final BuildingTypes type)
        throws NotEnoughResourceException,
        InvalidBuildingPlacementException,
        MaxBuildingLimitReachedException {
        return buildStructure(position, type, 0, false);
    }

    @Override
    public void upgradeStructure(final UUID structureId, final boolean cheatMode)
            throws NotEnoughResourceException,
            BuildingMaxedOutException, InvalidStructureReferenceException {
        checkAndGetBuilding(structureId);
        if (this.gameData.getBuildings().get(structureId).getLevel() >= buildingConfiguration.getMaxLevel()) {
            throw new BuildingMaxedOutException();
        }
        if (this.gameData.getBuildings().get(structureId).isBeingBuilt()) {
            throw new InvalidStructureReferenceException(BEINGBUILT_ERROR_STRING);
        }
        gameData.setResources(subtractResources(gameData.getResources(),
                this.gameData.getBuildings().get(structureId).getType().getCost(
                        this.gameData.getBuildings().get(structureId).getLevel() + 1)));
        threadManager.removeBuilding(structureId);
        this.gameData.getBuildings().get(structureId).setBeingBuilt(true);
        addBuildingStateChangedObserver(new BuildingObserver() {
            @Override
            public void update(final UUID buildingId) {
                if (gameData.getBuildings().containsKey(structureId)
                    && structureId.equals(buildingId)
                    && gameData.getBuildings().get(structureId).getBuildingProgress() == 0
                    && !gameData.getBuildings().get(structureId).isBeingBuilt()) {
                    threadManager.addBuilding(buildingId);
                }
            }
        });
        threadManager.addBuilding(structureId);
    }

    @Override
    public void upgradeStructure(final UUID structureId)
        throws NotEnoughResourceException,
        BuildingMaxedOutException, InvalidStructureReferenceException {
        upgradeStructure(structureId, false);
    }

    @Override
    public Set<Resource> demolishStructure(final UUID structureId)
    throws InvalidStructureReferenceException {
        checkAndGetBuilding(structureId);
        if (this.gameData.getBuildings().get(structureId).isBeingBuilt()) {
            throw new InvalidStructureReferenceException(BEINGBUILT_ERROR_STRING);
        }
        final Set<Resource> refund = this.gameData.getBuildings().get(structureId).getType().getCost(
                this.gameData.getBuildings().get(structureId).getLevel() + 1);
        for (final Resource resource : refund) {
            resource.setAmount(resource.getAmount() % buildingConfiguration.getRefundTaxPercentage());
        }
        try {
            applyResources(refund);
        } catch (NotEnoughResourceException e) {
            logger.severe("Not enough resources Exception thrown when adding resources,"
                    + " this implies a broken state of the player resource set and should be fixed!"
                    + " dumping stacktrace:\n" + e.getStackTrace());
        }
        this.threadManager.removeBuilding(structureId);
        this.gameData.getBuildings().remove(structureId);
        this.notifyBuildingStateChangedObservers(structureId);
        return refund;
    }

    @Override
    public void relocateStructure(final Point2D position, final UUID structureId)
        throws InvalidBuildingPlacementException, InvalidStructureReferenceException {
        checkAndGetBuilding(structureId);
        if (this.gameData.getBuildings().get(structureId).isBeingBuilt()) {
            throw new InvalidStructureReferenceException(BEINGBUILT_ERROR_STRING);
        }
        final Set<UUID> keys = gameData.getBuildings().keySet();
        for (final UUID key : keys) {
            if (gameData.getBuildings().get(key)
                .getStructurePos().equals(position) && !structureId.equals(key)) {
                throw new InvalidBuildingPlacementException();
            }
        }
        this.gameData.getBuildings().get(structureId).setStructurePos(position);
    }

    @Override
    public int getBuildingProgress(final UUID structureId)
        throws InvalidStructureReferenceException {
        final Building selectedBuilding = checkAndGetBuilding(structureId);
        return selectedBuilding.getBuildingProgress();
    }

    @Override
    public Set<Resource> getBuildingProduction(final UUID structureId)
        throws InvalidStructureReferenceException {
        final Building selectedBuilding = checkAndGetBuilding(structureId);
        return Collections.unmodifiableSet(selectedBuilding.getProductionAmount());
    }

    @Override
    public boolean isBuildingBeingBuilt(final UUID structureId)
        throws InvalidStructureReferenceException {
        final Building selectedBuilding = checkAndGetBuilding(structureId);
        return selectedBuilding.isBeingBuilt();
    }

    @Override
    public Set<UUID> getBuildingIds() {
        return Collections.unmodifiableSet(gameData.getBuildings().keySet());
    }

    @Override
    public int getResourceCount(final ResourceType type) {
        final Optional<Resource> resourceCounter = gameData
                .getResources()
                .stream().filter(x -> x.getResource().equals(type)).findFirst();
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
    public void upgradeTroop(final TroopType troopToUpgrade)
        throws InvalidTroopLevelException, NotEnoughResourceException {
        upgradeTroop(troopToUpgrade, gameData.getPlayerArmyLevel().get(troopToUpgrade) + 1);
    }

    @Override
    public void upgradeTroop(final TroopType troopToUpgrade, final int level)
        throws InvalidTroopLevelException, NotEnoughResourceException {
        if (level > baseModelConfiguration.getMaximumTroopLevel()) {
            throw new InvalidTroopLevelException(troopToUpgrade, level);
        }
        gameData.setResources(subtractResources(gameData.getResources(),
            this.configuration.getBaseConfiguration().getCostPerTroop()
                .get(troopToUpgrade).get(level - 1)));
        gameData.getPlayerArmyLevel().put(troopToUpgrade, level);
    }

    @Override
    public Map<TroopType, Integer> getTroopMap() {
        return Collections.unmodifiableMap(gameData.getPlayerArmyLevel());
    }

    @Override
    public void addBuildingStateChangedObserver(final BuildingObserver observer) {
        this.buildingStateChangedObservers.add(observer);
    }

    @Override
    public void removeBuildingStateChangedObserver(final BuildingObserver observer) {
        this.buildingProductionObservers.remove(observer);
    }

    @Override
    public void addBuildingProductionObserver(final BuildingObserver observer) {
        this.buildingProductionObservers.add(observer);
    }

    @Override
    public void removeBuildingProductionObserver(final BuildingObserver observer) {
        this.buildingProductionObservers.remove(observer);
    }

    @Override
    public void setClockTicking(final boolean ticktime) {
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
    public synchronized void applyResources(final Set<Resource> resource) throws NotEnoughResourceException {
        applyResources(resource, OperationType.ADDITION);
    }

    @Override
    public synchronized void applyResources(final Set<Resource> resource,
        final OperationType operation) throws NotEnoughResourceException {
        switch (operation) {
            case SUBTRACTION:
                this.gameData.setResources(subtractResources(this.gameData.getResources(), resource));
                break;
            case ADDITION:
                this.gameData.setResources(addResources(this.gameData.getResources(), resource));
                break;
            default:
        }
    }

    @Override
    public Map<UUID, Building> getBuildingMap() {
        final Map<UUID, Building> unmodMap = gameData.getBuildings();
        return Collections.unmodifiableMap(unmodMap);
    }

    @Override
    public String getPlayerName() {
        return gameData.getPlayerName();
    }

    @Override
    public void setPlayerName(final String playerName) {
        gameData.setPlayerName(playerName);
    }

    @Override
    public void notifyBuildingStateChangedObservers(final UUID building) {
        this.buildingStateChangedObservers
            .forEach(buildingStateObserver -> buildingStateObserver.update(building));
    }

    @Override
    public void notifyBuildingProductionObservers(final UUID building) {
        this.buildingProductionObservers
            .forEach(productionObserver -> productionObserver.update(building));
    }

    @Override
    public void refreshBuildings() {
        this.gameData.getBuildings().forEach((structureId, structure) ->
                this.threadManager.addBuilding(structureId));
    }

    @Override
    public void deactivateModel() {
        threadManager.clearBuildings();
    }

    /**
     * Executes an addition between resources of the same type inside the set
     * this operation is unsafe because it doesn't check for negative results.
     *
     * @param resourceStorage the resource set that is going to be affected
     * @param resourceCost    the second set that contains resources that will
     *                        be used or added
     * @return a set with the result of the operation
     */
    private Set<Resource> unsafeOperation(final Set<Resource> resourceStorage,
        final Set<Resource> resourceCost) {
        final Set<Resource> storageResult = new HashSet<>();
        final Iterator<Resource> storageIterator = 
            Resource
            .checkAndAddMissingResources(
                Resource.deepCopySet(resourceStorage)).iterator();
        while (storageIterator.hasNext()) {
            final Resource currentStorageResource = storageIterator.next();
            final Iterator<Resource> costIterator = Resource
                .checkAndAddMissingResources(Resource.deepCopySet(resourceCost)).iterator();
            while (costIterator.hasNext()) {
                final Resource currentCostResource = costIterator.next();
                if (currentStorageResource.equals(currentCostResource)) {
                    storageResult.add(new Resource(currentStorageResource.getResource(),
                            currentStorageResource.getAmount()
                            + currentCostResource.getAmount()));
                }
            }
        }
        return storageResult;
    }

    /**
     * Checks for negative values in the resources inside the resources in the set.
     *
     * @param resourcesToCheck the set that needs to be checked
     * @return the resources with negative values within the set
     */
    private Set<Resource> filterNegativeValues(final Set<Resource> resourcesToCheck) {
        final Set<Resource> missingResources = new HashSet<>();
        resourcesToCheck.forEach(x -> {
            if (x.getAmount() < 0) {
                missingResources.add(x);
            }
        });
        return missingResources;
    }

    /**
     * Inverts the sign of values inside a set of resources.
     *
     * @param resourceToNegate the set of resources to negate
     * @return a negated set
     */
    private Set<Resource> negateResources(final Set<Resource> resourceToNegate) {
        final Set<Resource> negatedResources = Resource.deepCopySet(resourceToNegate);
        negatedResources.stream().forEach(x -> x.setAmount(-x.getAmount()));
        return negatedResources;
    }

    private Set<Resource> subtractResources(final Set<Resource> resourceStorage,
        final Set<Resource> resourceCost) throws NotEnoughResourceException {
        return addResources(resourceStorage, negateResources(resourceCost));
    }

    private Set<Resource> addResources(final Set<Resource> resourceStorage,
        final Set<Resource> resourcesAdded) throws NotEnoughResourceException {
        final Set<Resource> updatedList = unsafeOperation(resourceStorage, resourcesAdded);
        final Set<Resource> missingResources = filterNegativeValues(updatedList);
        if (missingResources.isEmpty()) {
            return updatedList;
        }
        throw new NotEnoughResourceException(missingResources);
    }

    /**
     * Checks if the referenced UUID corresponds to a building
     * and returns a building.
     *
     * @param structureId the id to check for
     * @return the building with the corresponding UUID
     * @throws InvalidStructureReferenceException thrown when the given UUID
     *                                            does not correspond to an existing building
     */
    private Building checkAndGetBuilding(final UUID structureId)
        throws InvalidStructureReferenceException {
        final Building selectedBuilding = gameData.getBuildings().get(structureId);
        if (selectedBuilding == null) {
            throw new InvalidStructureReferenceException(structureId);
        }
        return selectedBuilding;
    }

    /**
     * Makes sure that a non-conflicting UUID is generated for a building.
     *
     * @return a freshly generated UUID
     */
    private UUID generateBuildingId() {
        return Stream.generate(UUID::randomUUID)
                .filter(x -> !gameData.getBuildings().containsKey(x)).findFirst()
                .orElseThrow();
    }

    /**
     * Check if data structures in GameData are initialized, if not
     * this method will correctly initialize the data structures.
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
                    resourceType -> gameData
                        .getResources().add(new Resource(resourceType)));
        }
    }
}
