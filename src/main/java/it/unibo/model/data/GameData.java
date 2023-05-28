package it.unibo.model.data;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import it.unibo.model.base.basedata.Building;
import it.unibo.view.battle.Troop;
/**
 * A simple data class to store all the game's information
 */
public class GameData {
    private String playerName;
    private Set<Resource> resources;
    private ConcurrentMap<UUID, Building> buildings;
    private Map<Troop, Integer> playerArmyLevel;

    private Optional<FightData> fightData;

    public GameData() {
        this.resources = new HashSet<>();
        this.buildings = new ConcurrentHashMap<>();
        this.fightData = Optional.empty();
        this.playerArmyLevel = new EnumMap<>(Troop.class);
        Arrays.stream(Troop.values()).forEach(troopType->this.playerArmyLevel.put(troopType, 0));
    }

    public GameData(Set<Resource> resources, ConcurrentMap<UUID, Building> buildings, Optional<FightData> fightData){
        this.resources = resources;
        this.buildings = buildings;
        this.fightData = fightData;
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
    public ConcurrentMap<UUID, Building> getBuildings() {
        return buildings;
    }
    /**
     * Sets a map of buildings currently owned by the player
     * @param buildings a map containing all buildings owned by the player and
     * their corresponding identifier
     */
    public void setBuildings(ConcurrentMap<UUID, Building> buildings) {
        this.buildings = buildings;
    }

    public Optional<FightData> getFightData(){ return fightData;}

    public void setFightData(Optional<FightData> fightData){this.fightData = fightData;}

}