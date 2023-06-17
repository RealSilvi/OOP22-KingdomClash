package it.unibo.view.map.mapdata;

import java.util.EnumMap;
import java.util.Map;

import it.unibo.view.map.MapPanel.ButtonIdentification;

/**
 * A simple configuration class for the MapView.
 */
public final class MapConfiguration {

    private int initialPlayerTile;
    private int levels;
    private int rows;
    private int columns;
    private Map<ButtonIdentification, String> imagePathMap;

    /**
     * Constructs a MapConfiguration object with default parameters.
     */
    public MapConfiguration() {
        this.imagePathMap = new EnumMap<>(ButtonIdentification.class);
        this.imagePathMap.put(ButtonIdentification.TILE,
            "/it/unibo/textures/map/grass_01.png");
        this.imagePathMap.put(ButtonIdentification.PLAYER,
            "/it/unibo/textures/map/city.png");
        this.imagePathMap.put(ButtonIdentification.ENEMY,
            "/it/unibo/textures/map/enemy.png");
        this.imagePathMap.put(ButtonIdentification.DEATH,
            "/it/unibo/textures/map/ruins.png");
        this.initialPlayerTile = 11;
        this.levels = 3;
        this.rows = 10;
        this.columns = 10;
    }
    /**
     * @return a map of image paths
     */
    public Map<ButtonIdentification, String> getImageMap() {
        return new EnumMap<>(imagePathMap);
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
    /**
     * @return maximum number of levels
     */
    public int getLevels() {
        return levels;
    }
    /**
     * @return initial position of the player
     */
    public int getInitialPlayerTile() {
        return initialPlayerTile;
    }
}
