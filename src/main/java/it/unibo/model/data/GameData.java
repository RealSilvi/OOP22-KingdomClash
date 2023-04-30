package it.unibo.model.data;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import it.unibo.model.base.basedata.Building;

public class GameData {
    private String playerName;
    private List<Resource> resources;
    private Map<UUID, Building> buildings;

    //TODO: Aggiungere dati per battle
    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public List<Resource> getResources() {
        return resources;
    }
    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
    public Map<UUID, Building> getBuildings() {
        return buildings;
    }
    public void setBuildings(Map<UUID, Building> buildings) {
        this.buildings = buildings;
    }
}