package it.unibo.controller.base;

import java.awt.geom.Point2D;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import it.unibo.model.base.api.BuildingObserver;
import it.unibo.model.base.basedata.Building;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.model.data.Resource;
import it.unibo.model.data.TroopType;

/**
 * Standard and easy to use interface for making a controller
 * related to the "Base Building" part of the game.
 */
public interface BaseController {
    /**
     * Attemtpts the construction of a building.
     *
     * @param position      placing position of the structure
     * @param type          type of structure
     * @param startingLevel the starting level of the structure
     * @param cheatMode     if true disables the resource cost and time wait
     * @return If the placement was possible, an unmodifiable
     * optional containing the identifier assigned to the building
     */
    Optional<UUID> handleBuildingPlaced(Point2D position, BuildingTypes type, int startingLevel, boolean cheatMode);

    /**
     * Attemtpts the construction of a building.
     *
     * @param position      placing position of the structure
     * @param type          type of structure
     * @param startingLevel the starting level of the structure
     * @return If the placement was possible, an unmodifiable
     * optional containing the identifier assigned to the building
     */
    Optional<UUID> handleBuildingPlaced(Point2D position, BuildingTypes type, int startingLevel);

    /**
     * Attemtpts the construction of a building.
     *
     * @param position placing position of the structure
     * @param type     type of structure
     * @return If the placement was possible, an unmodifiable
     * optional containing the identifier assigned to the building
     */
    Optional<UUID> handleBuildingPlaced(Point2D position, BuildingTypes type);

    /**
     * If the structure exists, starts the upgrading progress or builds it
     * instantly if instabuild is true.
     *
     * @param structureId an existing structure's identifier
     * @param cheatMode   if true disables the resource cost and time wait
     * @return true if the structure could be upgraded
     */
    boolean handleStructureUpgrade(UUID structureId, boolean cheatMode);

    /**
     * If the structure exists, starts the upgrading progress.
     *
     * @param structureId an existing structure's identifier
     * @return true if the structure could be upgraded
     */
    boolean handleStructureUpgrade(UUID structureId);

    /**
     * Tries to destroy a building, giving back part of the building resources spent.
     *
     * @param structureId an existing structure's identifier
     * @return true if the structure was demolished
     */
    boolean handleStructureDestruction(UUID structureId);

    /**
     * Tries to relocate an already existing structure to another location if possible.
     *
     * @param position    the new position of the structure
     * @param structureId an existing structure's identifier
     * @return true if the structure has been succesfully relocated
     */
    boolean handleStructureRelocation(Point2D position, UUID structureId);

    /**
     * Returns the amount of the provided type of resources that the player has.
     *
     * @param type the type of resource to query for
     * @return the amount of the type of resource
     */
    int requestResourceCount(Resource.ResourceType type);

    /**
     * For every existing resource type,
     * returns the amount that the player has in an unmodifiable set.
     *
     * @return an unmodifiable set of resources
     */
    Set<Resource> requestResourceCount();

    /**
     * @return an immutable map containing the player's troop types ad a key.
     * and it's corresponding level as an integer
     */
    Map<TroopType, Integer> requestTroopLevels();

    /**
     * Tries to upgrade a player's troop type to a given level.
     *
     * @param troopToUpgrade   the troop that needs to be upgraded
     * @param levelToUpgradeTo the level where the troop neesd to be upgraded to
     * @return true if the troop has been upgraded
     */
    boolean upgradeTroop(TroopType troopToUpgrade, int levelToUpgradeTo);

    /**
     * Tries to upgrade a player's troop type.
     *
     * @param troopToUpgrade the troop that needs to be upgraded
     * @return true if the troop has been upgraded
     */
    boolean upgradeTroop(TroopType troopToUpgrade);

    /**
     * Returns an unmodifiable map of Buildings.
     *
     * @return an unmodifiable map of buildings
     */
    Map<UUID, Building> requestBuildingMap();

    /**
     * @return the player's name
     */
    String requestPlayerName();

    /**
     * Sets the player's ingame name.
     *
     * @param playerName the player's nickname
     */
    void setPlayerName(String playerName);

    /**
     * Starts and stops the clock that keeps track of time passed.
     *
     * @param ticktime true to make time pass, false to stop time from passing
     */
    void setTimeRunning(boolean ticktime);

    /**
     * Checks if the clock is ticking and time is passing.
     *
     * @return true if time is passing, false if stopped
     */
    boolean isTimeRunning();

    /**
     * Registers an observer object that gets notified whenever a building state changes.
     *
     * @param observer the object that needs to be registered
     */
    void addBuildingStateChangedObserver(BuildingObserver observer);

    /**
     * Unregisters an observer that gets notified whenever a building state changes.
     *
     * @param observer the object that needs to be unregistered
     * @see {@link #addBuildingStateChangedObserver()}
     */
    void removeBuildingStateChangedObserver(BuildingObserver observer);

    /**
     * Registers an observer object that gets notified whenever a building generates resources.
     *
     * @param observer the object that needs to be registered
     */
    void addBuildingProductionObserver(BuildingObserver observer);

    /**
     * Unregisters an observer that gets notified whenever a building generates resources.
     *
     * @param observer the object that needs to be unregistered
     * @see {@link #addBuildingProductionObserver()}
     */
    void removeBuildingProductionObserver(BuildingObserver observer);
}
