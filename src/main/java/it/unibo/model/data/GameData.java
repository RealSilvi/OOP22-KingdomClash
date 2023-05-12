package it.unibo.model.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import it.unibo.model.base.basedata.Building;
/**
 * A simple data class to store all of the game's information
 */
public class GameData {
    private String playerName;
    private Set<Resource> resources;
    private Map<UUID, Building> buildings;

    public GameData() {
        this.resources = new HashSet<>();
        this.buildings = new HashMap<>();
    }
    //TODO: Add data for battle
    /**
     * Gets the player's name
     * @return a string representing the player's name
     */
    public String getPlayerName() {
        return playerName;
    }
    /**
     * Sets the player's name
     * @param playerName a string representing the player's name
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    /**
     * Gets the resources owned by the player
     * @return a set of resources owned by the player
     */
    public Set<Resource> getResources() {
        return resources;
    }
    /**
     * Sets the resources owned by the player
     * @param resources A set representing the resources owned
     */
    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }
    /**
     * Gets the currently built buildings owned by the player
     * @return a map containing all the buildings and their corresponding identifier
     */
    public Map<UUID, Building> getBuildings() {
        return buildings;
    }
    /**
     * Sets a map of buildings currently owned by the player
     * @param buildings a map containing all buildings owned by the player and
     * their corresponding identifier
     */
    public void setBuildings(Map<UUID, Building> buildings) {
        this.buildings = buildings;
    }
}