package it.unibo.view.map.mapdata;

import java.net.URL;
import java.util.EnumMap;
import java.util.Map;

import it.unibo.view.map.MapPanel.ButtonIdentification;

public final class MapConfiguration {

    private Map<ButtonIdentification, String> imageMap;

    public MapConfiguration() {
        this.imageMap = new EnumMap<>(ButtonIdentification.class);
        this.imageMap.put(ButtonIdentification.TILE,
            "/it/unibo/textures/map/tile_grass_2.png");
        this.imageMap.put(ButtonIdentification.PLAYER,
            "/it/unibo/textures/map/base_player.png");
        this.imageMap.put(ButtonIdentification.ENEMY,
            "/it/unibo/textures/map/base_enemy.png");
        this.imageMap.put(ButtonIdentification.DEATH,
            "/it/unibo/textures/map/base_enemy_defeated.png");
        
    }

    public Map<ButtonIdentification, String> getImageMap() {
        return imageMap;
    }
}
