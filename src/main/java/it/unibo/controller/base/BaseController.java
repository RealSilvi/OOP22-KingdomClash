package it.unibo.controller.base;

import java.awt.geom.Point2D;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import it.unibo.model.base.basedata.Building;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.model.data.Resource;

public interface BaseController {
    /**
     * Attemtpts the construction of a building
     * @param position placing position of the structure
     * @param type type of structure
     * @param startingLevel the starting level of the structure
     * @param cheatMode if true disables the resource cost and time wait
     * @return If the placement was possible, an unmodifiable
     * optional containing the identifier assigned to the building
     */
    public Optional<UUID> handleBuildingPlaced(final Point2D position, final BuildingTypes type, final int startingLevel, final boolean cheatMode);
    /**
     * Attemtpts the construction of a building
     * @param position placing position of the structure
     * @param type type of structure
     * @param startingLevel the starting level of the structure
     * @return If the placement was possible, an unmodifiable 
     * optional containing the identifier assigned to the building
     */
    public Optional<UUID> handleBuildingPlaced(final Point2D position, final BuildingTypes type, final int startingLevel);
    /**
     * Attemtpts the construction of a building
     * @param position placing position of the structure
     * @param type type of structure
     * @return If the placement was possible, an unmodifiable
     * optional containing the identifier assigned to the building
     */
    public Optional<UUID> handleBuildingPlaced(final Point2D position, final BuildingTypes type);
    /**
     * If the structure exists, starts the upgrading progress or builds it
     * instantly if instabuild is true
     * @param structureId an existing structure's identifier
     * @param cheatMode if true disables the resource cost and time wait
     * @return true if the structure could be upgraded
     */
    public boolean handleStructureUpgrade(final UUID structureId, final boolean cheatMode);
    /**
     * If the structure exists, starts the upgrading progress
     * @param structureId an existing structure's identifier
     * @return true if the structure could be upgraded
     */
    public boolean handleStructureUpgrade(final UUID structureId);
    /**
     * Tries to destroy a building, giving back part of the building resources spent
     * @param structureId an existing structure's identifier
     * @return true if the structure was demolished
     */
    public boolean handleStructureDestruction(final UUID structureId);
    /**
     * Tries to relocate an already existing structure to another location if possible
     * @param position the new position of the structure
     * @param structureId an existing structure's identifier
     * @return true if the structure has been succesfully relocated
     */
    public boolean handleStructureRelocation(final Point2D position, final UUID structureId);

    /**
     * Returns the amount of the provided type of resources that the player has
     * @param type the type of resource to query for
     * @return the amount of the type of resource
     */
    public int requestResourceCount(final Resource.ResourceType type);
    /**
     * For every existing resource type, returns the amount that the player has in an unmodifiable set
     * @return an unmodifiable set of resources
     */
    public Set<Resource> requestResourceCount();

    /**
     * Returns an unmodifiable map of Buildings
     * @return an unmodifiable map of buildings
     */
    public Map<UUID, Building> requestBuildingMap();

    /**
     * Starts and stops the clock that keeps track of time passed
     * @param ticktime true to make time pass, false to stop time from passing
     */
    public void setTimeRunning(final boolean ticktime);
    /**
     * Checks if the clock is ticking and time is passing
     * @return true if time is passing, false if stopped
     */
    public boolean isTimeRunning();
}