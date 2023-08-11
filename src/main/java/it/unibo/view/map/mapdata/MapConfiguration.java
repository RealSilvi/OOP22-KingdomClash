package it.unibo.view.map.mapdata;

/**
 * A simple configuration class for the MapView.
 */
public final class MapConfiguration {

    private static final int DEFAULT_INITIAL_PLAYER_TILE = 11;

    private final int initialPlayerTile;
    private final int levels;
    private final int rows;
    private final int columns;

    /**
     * Constructs a MapConfiguration object with default parameters.
     */
    //Values defined in this class are all default values.
    @SuppressWarnings({"magic", "magicnumber"})
    public MapConfiguration() {
        this.initialPlayerTile = DEFAULT_INITIAL_PLAYER_TILE;
        this.levels = 3;
        this.rows = 10;
        this.columns = 10;
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
