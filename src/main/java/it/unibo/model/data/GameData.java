package it.unibo.model.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import it.unibo.model.base.basedata.Building;

public class GameData {
    private String playerName;
    private Set<Resource> resources;
    private Map<UUID, Building> buildings;

    public GameData() {
        this.resources = new HashSet<>();
        this.buildings = new HashMap<>();
    }
    //TODO: Aggiungere dati per battle
    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public Set<Resource> getResources() {
        return resources;
    }
    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }
    public Map<UUID, Building> getBuildings() {
        return buildings;
    }
    public void setBuildings(Map<UUID, Building> buildings) {
        this.buildings = buildings;
    }
}