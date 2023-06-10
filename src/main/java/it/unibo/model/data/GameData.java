package it.unibo.model.data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.base.basedata.Building;

/**
 * A simple data class to store all the game's information.
 */
public final class GameData implements Serializable {
    private String playerName;
    private Set<Resource> resources;
    private ConcurrentMap<UUID, Building> buildings;
    private Map<TroopType, Integer> playerArmyLevel;

    private final transient GameConfiguration configuration;

    private FightData fightData;

    public GameData() {
        this.resources = new HashSet<>();
        this.buildings = new ConcurrentHashMap<>();
        this.fightData = new FightData();
        this.playerArmyLevel = new EnumMap<>(TroopType.class);
        this.configuration = new GameConfiguration();
        Arrays.stream(TroopType.values()).forEach(troopType -> this.playerArmyLevel.put(troopType, 1));
    }
    public GameData(GameConfiguration gameConfiguration) {
        this.resources = new HashSet<>();
        this.buildings = new ConcurrentHashMap<>();
        this.fightData = new FightData();
        this.playerArmyLevel = new EnumMap<>(TroopType.class);
        this.configuration = gameConfiguration;
        Arrays.stream(TroopType.values()).forEach(troopType -> this.playerArmyLevel.put(troopType, 1));
    }
    /* No defensive copy needed because the base model already
     * handles data integrity
     */
    @SuppressFBWarnings(value = "EI2",
    justification = "No encapsulation needed as BaseModel handles everything")
    public GameData(Set<Resource> resources, ConcurrentMap<UUID, Building> buildings,
        FightData fightData, GameConfiguration configuration) {
        this.resources = resources;
        this.buildings = buildings;
        this.fightData = fightData;
        this.configuration = configuration;
    }

    /**
     * Gets the player's name
     *
     * @return a string representing the player's name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Sets the player's name
     *
     * @param playerName a string representing the player's name
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Gets the resources owned by the player
     *
     * @return a set of resources owned by the player
     */
    /* No defensive copy needed because the base model already
     * handles data integrity
     */
    @SuppressFBWarnings(value = "EI2",
    justification = "No encapsulation needed as BaseModel handles everything")
    public Set<Resource> getResources() {
        return resources;
    }

    /**
     * Sets the resources owned by the player
     *
     * @param resources A set representing the resources owned
     */
    /* No defensive copy needed because the base model already
     * handles data integrity
     */
    @SuppressFBWarnings(value = "EI2",
    justification = "No encapsulation needed as BaseModel handles everything")
    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    /**
     * Gets the currently built buildings owned by the player
     *
     * @return a map containing all the buildings and their corresponding identifier
     */
    /* No defensive copy needed because the base model already
     * handles data integrity
     */
    @SuppressFBWarnings(value = "EI2",
    justification = "No encapsulation needed as BaseModel handles everything")
    public ConcurrentMap<UUID, Building> getBuildings() {
        return buildings;
    }

    /**
     * Sets a map of buildings currently owned by the player
     *
     * @param buildings a map containing all buildings owned by the player and
     *                  their corresponding identifier
     */
    /* No defensive copy needed because the base model already
     * handles data integrity
     */
    @SuppressFBWarnings(value = "EI2",
    justification = "No encapsulation needed as BaseModel handles everything")
    public void setBuildings(ConcurrentMap<UUID, Building> buildings) {
        this.buildings = buildings;
    }

    /**
     * Gets a map with a troop type and it's corresponding level for the player
     *
     * @return a map with the troop and the level of the troop as an integer
     */
    /* No defensive copy needed because the base model already
     * handles data integrity
     */
    @SuppressFBWarnings(value = "EI2",
    justification = "No encapsulation needed as BaseModel handles everything")
    public Map<TroopType, Integer> getPlayerArmyLevel() {
        return this.playerArmyLevel;
    }

    /**
     * Sets a map with a troop type and it's corresponding level for the player
     *
     * @param playerArmyLevel a map with the troop and the level of the troop as an integer
     */
    /* No defensive copy needed because the base model already
     * handles data integrity
     */
    @SuppressFBWarnings(value = "EI2",
    justification = "No encapsulation needed as BaseModel handles everything")
    public void setPlayerArmyLevel(Map<TroopType, Integer> playerArmyLevel) {
        this.playerArmyLevel = playerArmyLevel;
    }

    public FightData getFightData() {
        return fightData;
    }

    public void setFightData(FightData fightData) {
        this.fightData = fightData;
    }

    /**
     * @return The game's configuration
     */
    public GameConfiguration getGameConfiguration() {
        return this.configuration;
    }
}
