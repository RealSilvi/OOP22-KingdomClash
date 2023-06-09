package it.unibo.view.map.mapdata;

import java.util.EnumMap;
import java.util.Map;

import it.unibo.view.map.MapPanel.ButtonIdentification;

public final class MapConfiguration {

    private int rows;
    private int columns;
    private Map<ButtonIdentification, String> imageMap;

    @SuppressWarnings("java:S1075")
    //This is a default configuration file constructor
    public MapConfiguration() {
        this.imageMap = new EnumMap<>(ButtonIdentification.class);
        this.imageMap.put(ButtonIdentification.TILE,
            "/it/unibo/textures/map/grass_01.png");
        this.imageMap.put(ButtonIdentification.PLAYER,
            "/it/unibo/textures/map/city.png");
        this.imageMap.put(ButtonIdentification.ENEMY,
            "/it/unibo/textures/map/enemy.png");
        this.imageMap.put(ButtonIdentification.DEATH,
            "/it/unibo/textures/map/ruins.png");
        this.rows = 10;
        this.columns = 10;
    }

    public Map<ButtonIdentification, String> getImageMap() {
        return imageMap;
    }
    /**
     * @return  map tile rows
     */
    public int getRows() {
        return rows;
    }
    /**
     * @return  map tile columns
     */
    public int getColumns() {
        return columns;
    }
}
