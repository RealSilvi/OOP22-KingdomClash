package it.unibo.model.base;

import it.unibo.model.base.api.BuildingObserver;
import it.unibo.model.base.basedata.BuildingFactory.BuildingTypes;
import it.unibo.model.base.exceptions.BuildingMaxedOutException;
import it.unibo.model.base.exceptions.InvalidBuildingPlacementException;
import it.unibo.model.base.exceptions.InvalidStructureReferenceException;
import it.unibo.model.base.exceptions.NotEnoughResourceException;
import it.unibo.model.data.GameData;
import it.unibo.model.data.Resource;

import java.awt.geom.Point2D;
import java.nio.file.Path;
import java.util.List;
/**
 * Interface for the base model, it has simple functions that allows other classes to interact
 * and inspect the game data
 */
public interface BaseModel {
    /**
     * Tries to build a structure in a given position at a given level
     * @param position placing position of the structure
     * @param type type of structure
     * @param startingLevel the starting level of the structure
     * @param cheatMode if true disables the resource cost and time wait
     * @return integer that identifies the structure
     * @throws NotEnoughResourceException thrown when the player does not have enough resources to build this structure
     * @throws InvalidBuildingPlacementException thrown when the building position is obstructed
     * @throws InvalidStructureReferenceException thrown when the provided identifier does not represent a building
     */
    public int buildStructure(final Point2D position, final BuildingTypes type, final int startingLevel, final boolean cheatMode) throws NotEnoughResourceException, InvalidBuildingPlacementException, InvalidStructureReferenceException;
    /**
     * Tries to build a structure in a given position at a given level
     * @param position placing position of the structure
     * @param type type of structure
     * @param startingLevel the starting level of the structure
     * @return integer that identifies the structure
     * @throws NotEnoughResourceException thrown when the player does not have enough resources to build this structure
     * @throws InvalidBuildingPlacementException thrown when the building position is obstructed
     * @throws InvalidStructureReferenceException thrown when the provided identifier does not represent a building
     */
    public int buildStructure(final Point2D position, final BuildingTypes type, final int startingLevel) throws NotEnoughResourceException, InvalidBuildingPlacementException, InvalidStructureReferenceException;
    /**
     * Tries to build a structure in a given position
     * @param position placing position of the structure
     * @param type type of structure
     * @return integer that identifies the structure
     * @throws NotEnoughResourceException thrown when the player does not have enough resources to build this structure
     * @throws InvalidBuildingPlacementException thrown when the building position is obstructed
     */
    public int buildStructure(final Point2D position, final BuildingTypes type) throws NotEnoughResourceException, InvalidBuildingPlacementException;
    /**
     * If the structure exists, starts the upgrading progress or builds it
     * instantly if instabuild is true
     * @param structureId an existing structure's identifier
     * @param cheatMode if true disables the resource cost and time wait
     * @throws NotEnoughResourceException thrown when the player does not have enough resources to upgrade this structure
     * @throws BuildingMaxedOutException thrown when the the provided building has already the maximum level allowed
     * @throws InvalidStructureReferenceException thrown when the provided identifier does not represent a building
     */
    public void upgradeStructure(final int structureId, final boolean cheatMode) throws NotEnoughResourceException, BuildingMaxedOutException, InvalidStructureReferenceException;
    /**
     * If the structure exists, starts the upgrading progress
     * @param structureId an existing structure's identifier
     * @throws NotEnoughResourceException thrown when the player does not have enough resources to upgrade this structure
     * @throws BuildingMaxedOutException thrown when the the provided building has already the maximum level allowed
     * @throws InvalidStructureReferenceException thrown when the provided identifier does not represent a building
     */
    public void upgradeStructure(final int structureId) throws NotEnoughResourceException, BuildingMaxedOutException, InvalidStructureReferenceException;
    /**
     * Tries to destroy a building, giving back part of the building resources spent
     * @param structureId an existing structure's identifier
     * @return a list of recovered resources
     * @throws InvalidStructureReferenceException thrown when the provided identifier does not represent a building
     */
    public List<Resource> demolishStructure(final int structureId) throws InvalidStructureReferenceException;
    /**
     * Tries to relocate an already existing structure to another location if possible
     * @param position the new position of the structure
     * @param structureId an existing structure's identifier
     * @throws InvalidBuildingPlacementException thrown when the building position is obstructed
     * @throws InvalidStructureReferenceException thrown when the provided identifier does not represent a building
     */
    public void relocateStructure(final Point2D position, final int structureId) throws InvalidBuildingPlacementException, InvalidStructureReferenceException;
    /**
     * Gets the path of the texture that represents the current structure's status
     * @param structureId an existing structure's identifier
     * @return the texture's path
     * @throws InvalidStructureReferenceException thrown when the provided identifier does not represent a building
     */
    public Path getStructureTexture(final int structureId) throws InvalidStructureReferenceException;
    /**
     * Given a structure's identifier, returns the progress in percentage of the current operation
     * @param structureId an existing structure's identifier
     * @return progress in percentage
     * @throws InvalidStructureReferenceException thrown when the provided identifier does not represent a building
     */
    public int getBuildingProgress(final int structureId) throws InvalidStructureReferenceException;
    /**
     * Produces a list of materials that the structures produces
     * @param structureId an existing structure's identifier
     * @return a list of materials that the structures produces
     * @throws InvalidStructureReferenceException thrown when the provided identifier does not represent a building
     */
    public List<Resource> getBuildingProduction(final int structureId) throws InvalidStructureReferenceException;
    /**
     * @return an identifier for every existing building
     */
    public List<Integer> getBuildingIds();

    /**
     * Returns the amount of the provided type of resources that the player has
     * @param type the type of resource to query for
     * @return the amount of the type of resource
     */
    public int getResourceCount(final Resource.ResourceType type);
    /**
     * For every existing resource type, returns the amount that the player has in an unmodifiable list
     * @return an unmodifiable list of resources
     */
    public List<Resource> getResourceCount();
    
    /**
     * Registers an observer object that gets notified whenever a building state changes
     * @param observer the object that needs to be registered
     */
    public void addBuildingStateChangedObserver(final BuildingObserver observer);
    /**
     * Unregisters an observer that gets notified whenever a building state changes
     * @param observer the object that needs to be unregistered
     * @see {@link #addBuildingStateChangedObserver()}
     */
    public void removeBuildingStateChangedObserver(final BuildingObserver observer);

    /**
     * Starts and stops the clock that keeps track of time passed
     * @param ticktime true to make time pass, false to stop time from passing
     */
    public void setClockTicking(final boolean ticktime);
    /**
     * Checks if the clock is ticking and time is passing
     * @return true if time is passing, false if stopped
     */
    public boolean isClockTicking();

    /**
     * Returns the instance of GameData.
     * Be careful! directly manipulating data inside of the returned object
     * might result in uncontrolled behaviour, use it carefully!
     * @return game data object that contains every information of the current game
     */
    public GameData obtainGameData();
}