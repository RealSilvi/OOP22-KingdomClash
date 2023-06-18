
package it.unibo.view.city.panels.impl;

/**
 * This class give the configuration for the main class of the cityPanel.
 */
public class CityConfiguration {
    private final int width;
    private final int height;
    /**
     * This costructor contains the values for build the field where you can place the buildings.
     */
    public CityConfiguration() {
        this.width = 10;
        this.height = 10;
    }
    /**
     * This method return the widht of the grid for the main field.
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
