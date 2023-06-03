package it.unibo.model.base;

import it.unibo.model.base.api.BuildingObserver;
import it.unibo.model.base.basedata.Building;
import it.unibo.model.base.exceptions.BuildingMaxedOutException;
import it.unibo.model.base.exceptions.InvalidBuildingPlacementException;
import it.unibo.model.base.exceptions.InvalidStructureReferenceException;
import it.unibo.model.base.exceptions.InvalidTroopLevelException;
import it.unibo.model.base.exceptions.MaxBuildingLimitReachedException;
import it.unibo.model.base.exceptions.NotEnoughResourceException;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.model.data.GameData;
import it.unibo.model.data.Resource;
import it.unibo.model.data.TroopType;

import java.awt.geom.Point2D;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Interface for the base model, it has simple functions that allows other classes to interact
 * and inspect the game data
 */
public interface BaseModel {
    public enum OperationType {
        SUBTRACTION,
        ADDITION
    }

    /**
     * Tries to build a structure in a given position at a given level
     *
     * @param position      placing position of the structure
     * @param type          type of structure
     * @param startingLevel the starting level of the structure
     * @param cheatMode     if true disables the resource cost and time wait
     * @return UUID that identifies the structure
     * @throws NotEnoughResourceException         thrown when the player does not have enough resources to build this structure
     * @throws InvalidBuildingPlacementException  thrown when the building position is obstructed
     * @throws InvalidStructureReferenceException thrown when the provided identifier does not represent a building
     */
    public UUID buildStructure(final Point2D position, final BuildingTypes type, final int startingLevel, final boolean cheatMode) throws NotEnoughResourceException, InvalidBuildingPlacementException, MaxBuildingLimitReachedException;

    /**
     * Tries to build a structure in a given position at a given level
     *
     * @param position      placing position of the structure
     * @param type          type of structure
     * @param startingLevel the starting level of the structure
     * @return UUID that identifies the structure
     * @throws NotEnoughResourceException         thrown when the player does not have enough resources to build this structure
     * @throws InvalidBuildingPlacementException  thrown when the building position is obstructed
     * @throws InvalidStructureReferenceException thrown when the provided identifier does not represent a building
     */
    public UUID buildStructure(final Point2D position, final BuildingTypes type, final int startingLevel) throws NotEnoughResourceException, InvalidBuildingPlacementException, MaxBuildingLimitReachedException;

    /**
     * Tries to build a structure in a given position
     *
     * @param position placing position of the structure
     * @param type     type of structure
     * @return UUID that identifies the structure
     * @throws NotEnoughResourceException        thrown when the player does not have enough resources to build this structure
     * @throws InvalidBuildingPlacementException thrown when the building position is obstructed
     */
    public UUID buildStructure(final Point2D position, final BuildingTypes type) throws NotEnoughResourceException, InvalidBuildingPlacementException, MaxBuildingLimitReachedException;

    /**
     * If the structure exists, starts the upgrading progress or builds it
     * instantly if instabuild is true
     *
     * @param structureId an existing structure's identifier
     * @param cheatMode   if true disables the resource cost and time wait
     * @throws NotEnoughResourceException         thrown when the player does not have enough resources to upgrade this structure
     * @throws BuildingMaxedOutException          thrown when the the provided building has already the maximum level allowed
     * @throws InvalidStructureReferenceException thrown when the provided identifier does not represent a building
     */
    public void upgradeStructure(final UUID structureId, final boolean cheatMode) throws NotEnoughResourceException, BuildingMaxedOutException, InvalidStructureReferenceException;

    /**
     * If the structure exists, starts the upgrading progress
     *
     * @param structureId an existing structure's identifier
     * @throws NotEnoughResourceException         thrown when the player does not have enough resources to upgrade this structure
     * @throws BuildingMaxedOutException          thrown when the the provided building has already the maximum level allowed
     * @throws InvalidStructureReferenceException thrown when the provided identifier does not represent a building
     */
    public void upgradeStructure(final UUID structureId) throws NotEnoughResourceException, BuildingMaxedOutException, InvalidStructureReferenceException;

    /**
     * Tries to destroy a building, giving back part of the building resources spent
     *
     * @param structureId an existing structure's identifier
     * @return a set of recovered resources
     * @throws InvalidStructureReferenceException thrown when the provided identifier does not represent a building
     */
    public Set<Resource> demolishStructure(final UUID structureId) throws InvalidStructureReferenceException;

    /**
     * Tries to relocate an already existing structure to another location if possible
     *
     * @param position    the new position of the structure
     * @param structureId an existing structure's identifier
     * @throws InvalidBuildingPlacementException  thrown when the building position is obstructed
     * @throws InvalidStructureReferenceException thrown when the provided identifier does not represent a building
     */
    public void relocateStructure(final Point2D position, final UUID structureId) throws InvalidBuildingPlacementException, InvalidStructureReferenceException;

    /**
     * Gets the path of the texture that represents the current structure's status
     *
     * @param structureId an existing structure's identifier
     * @return the texture's path
     * @throws InvalidStructureReferenceException thrown when the provided identifier does not represent a building
     * @deprecated This method will be removed in the future because texture handling is not a
     * responsability for the model anymore
     */
    @Deprecated
    public Path getStructureTexture(final UUID structureId) throws InvalidStructureReferenceException;

    /**
     * Given a structure's identifier, returns the progress in percentage of the current operation
     *
     * @param structureId an existing structure's identifier
     * @return progress in percentage
     * @throws InvalidStructureReferenceException thrown when the provided identifier does not represent a building
     */
    public int getBuildingProgress(final UUID structureId) throws InvalidStructureReferenceException;

    /**
     * Produces a set of materials that the structures produces
     *
     * @param structureId an existing structure's identifier
     * @return a set of materials that the structures produces
     * @throws InvalidStructureReferenceException thrown when the provided identifier does not represent a building
     */
    public Set<Resource> getBuildingProduction(final UUID structureId) throws InvalidStructureReferenceException;

    /**
     * Checks if the current structure is being built and returns a boolean
     *
     * @param structureId an existing structure's identifier
     * @return true if the structure is currently being built
     * @throws InvalidStructureReferenceException thrown when the provided identifier does not represent a building
     */
    public boolean isBuildingBeingBuilt(UUID structureId) throws InvalidStructureReferenceException;

    /**
     * @return an identifier for every existing building
     */
    public Set<UUID> getBuildingIds();

    /**
     * Returns the amount of the provided type of resources that the player has
     *
     * @param type the type of resource to query for
     * @return the amount of the type of resource
     */
    public int getResourceCount(final Resource.ResourceType type);

    /**
     * For every existing resource type, returns the amount that the player has in an unmodifiable set
     *
     * @return an unmodifiable set of resources
     */
    public Set<Resource> getResourceCount();

    /**
     * @param troopToUpgrade
     */
    public void upgradeTroop(TroopType troopToUpgrade) throws InvalidTroopLevelException;

    /**
     * Upgrades a troop to a given level
     *
     * @param troopToUpgrade the type of troop to upgrade
     * @param level          the level wich the troop has to be upgraded to
     */
    public void upgradeTroop(TroopType troopToUpgrade, int level) throws InvalidTroopLevelException;

    /**
     * @return a map containing the player's troops with their corresponding stats
     */
    public Map<TroopType, Integer> getTroopMap();

    /**
     * Registers an observer object that gets notified whenever a building state changes
     *
     * @param observer the object that needs to be registered
     */
    public void addBuildingStateChangedObserver(final BuildingObserver observer);

    /**
     * Unregisters an observer that gets notified whenever a building state changes
     *
     * @param observer the object that needs to be unregistered
     * @see {@link #addBuildingStateChangedObserver()}
     */
    public void removeBuildingStateChangedObserver(final BuildingObserver observer);

    /**
     * Registers an observer object that gets notified whenever a building generates resources
     *
     * @param observer the object that needs to be registered
     */
    public void addBuildingProductionObserver(final BuildingObserver observer);

    /**
     * Unregisters an observer that gets notified whenever a building generates resources
     *
     * @param observer the object that needs to be unregistered
     * @see {@link #addBuildingProductionObserver()}
     */
    public void removeBuildingProductionObserver(final BuildingObserver observer);

    /**
     * Notifies all object registered to BuildingStateChangedObservers
     *
     * @param building the identifier of the building responsible for the event
     * @see {@link #addBuildingStateChangedObserver()}
     */
    public void notifyBuildingStateChangedObservers(final UUID building);

    /**
     * Notifies all object registered to BuildingProductionObservers
     *
     * @param building the identifier of the building responsible for the event
     * @see {@link #addBuildingProductionObserver()}
     */
    public void notifyBuildingProductionObservers(final UUID building);

    /**
     * Starts and stops the clock that keeps track of time passed
     *
     * @param ticktime true to make time pass, false to stop time from passing
     */
    public void setClockTicking(final boolean ticktime);

    /**
     * Checks if the clock is ticking and time is passing
     *
     * @return true if time is passing, false if stopped
     */
    public boolean isClockTicking();

    /**
     * Returns the instance of GameData.
     * Be careful! directly manipulating data inside of the returned object
     * might result in uncontrolled behaviour, use it carefully!
     *
     * @return game data object that contains every information of the current game
     * @deprecated This method will soon be removed because it is unsafe and it'not the
     * BaseModel's responsibility to directly return GameData
     */
    @Deprecated
    public GameData obtainGameData();

    /**
     * Returns an unmodifiable map of Buildings
     *
     * @return an unmodifiable map of buildings
     */
    public Map<UUID, Building> getBuildingMap();

    /**
     * @return the player's name
     */
    public String getPlayerName();

    /**
     * Sets the player's ingame name
     *
     * @param playerName the player's nickname
     */
    public void setPlayerName(String playerName);

    /**
     * Safely adds or removes the given resources to the player's deposit
     *
     * @param resource A set of resources, if a resource's amount is negative, it will be subtracted from player's deposit
     */
    public void applyResources(Set<Resource> resource) throws NotEnoughResourceException;

    /**
     * Safely adds or removes the given resources to the player's deposit
     *
     * @param resource  A set of resources, if a resource's amount is negative, it will be subtracted from player's deposit
     * @param operation The type of operation that has to be applied to the resources
     */
    public void applyResources(Set<Resource> resource, OperationType operation) throws NotEnoughResourceException;

    /**
     * Refreshes threadmanager's building list
     */
    public void refreshBuildings();

    /**
     * Applies the level multiplier given a set of resources and a level, returning a set with updated resources
     *
     * @param resource the set of resources that need to be worked on
     * @param level    an integer representing the level
     * @return a set with updated resources given a level multiplier
     * @deprecated This metod is deprecated and will not be used anymore
     * because it has been implemented internally in {@link it.unibo.model.base.internal.BuildingBuilderImpl}
     */
    @Deprecated
    public static Set<Resource> applyMultiplierToResources(Set<Resource> resource, int level) {
        Set<Resource> alteredResource = new HashSet<>();
        resource.forEach(singleCost -> alteredResource.add(new Resource(singleCost.getResource(), singleCost.getAmount() * (level == 0 ? 1 : level))));
        return alteredResource;
    }
}