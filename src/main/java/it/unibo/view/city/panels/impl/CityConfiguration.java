package it.unibo.view.city.panels.impl;

/**
 * This class give the configuration for the main class of the cityPanel.
 */
public class CityConfiguration {
    private static final int DEFAULT_COLUMNS = 5;
    private static final int DEFAULT_ROWS = 5;

    private final int width;
    private final int height;
    /**
     * This costructor contains the values for build the field where you can place the buildings.
     */
    public CityConfiguration() {
        this.width = DEFAULT_COLUMNS;
        this.height = DEFAULT_ROWS;
    }
    /**
     * This method return the width of the grid for the main field.
     * @return the width of the grid for the main field
     */
    public int getWidth() {
        return width;
    }
    /**
     * This method return the height of the grid for the main field.
     * @return the height of the grid for the main field
     */
    public int getHeight() {
        return height;
    }
}
