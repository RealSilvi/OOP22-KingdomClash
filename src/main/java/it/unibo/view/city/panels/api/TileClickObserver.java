package it.unibo.view.city.panels.api;

import java.awt.geom.Point2D;

import javax.swing.JComponent;

/**
 * A simple tile click observer interface that is used when a tile is clicked.
 */
public interface TileClickObserver {
    /**
     * Function that gets called whenever a tile gets clicked.
     * @param tile      the responsible tile
     * @param position  the position of the tile
     */
    void tileClicked(JComponent tile, Point2D.Float position);
}
