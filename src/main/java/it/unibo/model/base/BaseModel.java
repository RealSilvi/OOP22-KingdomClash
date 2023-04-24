package it.unibo.model.base;

import it.unibo.model.base.basedata.BuildingFactory.BuildingTypes;
import it.unibo.model.base.exceptions.BuildingMaxedOutException;
import it.unibo.model.base.exceptions.InvalidBuildingPlacementException;
import it.unibo.model.base.exceptions.NotEnoughResourceException;
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
     */
    public int buildStructure(Point2D position, BuildingTypes type, int startingLevel, boolean cheatMode) throws NotEnoughResourceException, InvalidBuildingPlacementException;
    /**
     * Tries to build a structure in a given position at a given level
     * @param position placing position of the structure
     * @param type type of structure
     * @param startingLevel the starting level of the structure
     * @return integer that identifies the structure
     * @throws NotEnoughResourceException thrown when the player does not have enough resources to build this structure
     * @throws InvalidBuildingPlacementException thrown when the building position is obstructed
     */
    public int buildStructure(Point2D position, BuildingTypes type, int startingLevel) throws NotEnoughResourceException, InvalidBuildingPlacementException;
    /**
     * Tries to build a structure in a given position
     * @param position placing position of the structure
     * @param type type of structure
     * @return integer that identifies the structure
     * @throws NotEnoughResourceException thrown when the player does not have enough resources to build this structure
     * @throws InvalidBuildingPlacementException thrown when the building position is obstructed
     */
    public int buildStructure(Point2D position, BuildingTypes type) throws NotEnoughResourceException, InvalidBuildingPlacementException;
    /**
     * If the structure exists, starts the upgrading progress or builds it
     * instantly if instabuild is true
     * @param structureId an existing structure's identifier
     * @param cheatMode if true disables the resource cost and time wait
     * @throws NotEnoughResourceException thrown when the player does not have enough resources to upgrade this structure
     * @throws BuildingMaxedOutException thrown when the the provided building has already the maximum level allowed
     */
    public void upgradeStructure(int structureId, boolean cheatMode) throws NotEnoughResourceException, BuildingMaxedOutException;
    /**
     * If the structure exists, starts the upgrading progress
     * @param structureId an existing structure's identifier
     * @throws NotEnoughResourceException thrown when the player does not have enough resources to upgrade this structure
     * @throws BuildingMaxedOutException thrown when the the provided building has already the maximum level allowed
     */
    public void upgradeStructure(int structureId) throws NotEnoughResourceException, BuildingMaxedOutException;
    /**
     * Tries to destroy a building, giving back part of the building resources spent
     * @param structureId an existing structure's identifier
     * @return a list of recovered resources
     */
    public List<Resource> demolishStructure(int structureId);
    /**
     * Tries to relocate an already existing structure to another location if possible
     * @param position the new position of the structure
     * @param structureId an existing structure's identifier
     * @throws InvalidBuildingPlacementException thrown when the building position is obstructed
     */
    public void relocateStructure(Point2D position, int structureId) throws InvalidBuildingPlacementException;
    /**
     * Gets the path of the texture that represents the current structure's status
     * @param structureId an existing structure's identifier
     * @return the texture's path
     */
    public Path getStructureTexture(int structureId);
    /**
     * Given a structure's identifier, returns the progress in percentage of the current operation
     * @param structureId an existing structure's identifier
     * @return progress in percentage
     */
    public int getBuildingProgress(int structureId);
    /**
     * Produces a list of materials that the structures produces
     * @param structureId an existing structure's identifier
     * @return a list of materials that the structures produces
     */
    public List<Resource> getBuildingProduction(int structureId);
    
    /**
     * Starts and stops the clock that keeps track of time passed
     * @param ticktime true to make time pass, false to stop time from passing
     */
    public void setClockTicking(boolean ticktime);
    /**
     * Checks if the clock is ticking and time is passing
     * @return true if time is passing, false if stopped
     */
    public boolean isClockTicking();
}