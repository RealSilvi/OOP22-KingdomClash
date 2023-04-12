package it.unibo.view.battle.api;

import java.awt.*;

/**
 * An interface that show how to get the preferred size of all the BorderLayout's JPanels.
 * <br>
 * Note: The NorthPanel has a BorderLayout also.
 */
public interface PanelDimensions {

    Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

    double FIELD_WIDTH_SCALE = 0.6;
    double FIELD_HEIGHT_SCALE = 0.6;

    double SIDE_WIDTH_SCALE = 0.2;
    double SIDE_HEIGHT_SCALE = 0.6;

    double PLAYERS_WIDTH_SCALE = 1;
    double PLAYERS_HEIGHT_SCALE = 0.2;

    double MENU_WIDTH_SCALE = 1;
    double MENU_HEIGHT_SCALE = 0.1;

    /**
     * @return The preferred dimension of the CenterPanel.
     */
    static Dimension getFieldPanel() {
        return new Dimension(
                (int) (SCREEN_SIZE.getWidth() * FIELD_WIDTH_SCALE) ,
                (int)(SCREEN_SIZE.getHeight() * FIELD_HEIGHT_SCALE));
    }

    /**
     * @return The preferred dimension of the EastPanel and WestPanel.
     */
    static Dimension getSidePanel() {
        return new Dimension(
                (int) (SCREEN_SIZE.getWidth() * SIDE_WIDTH_SCALE) ,
                (int)(SCREEN_SIZE.getHeight() * SIDE_HEIGHT_SCALE));
    }

    /**
     * @return The preferred dimension of the NorthNorthPanel.
     */
    static Dimension getMenuPanel() {
        return new Dimension(
                (int) (SCREEN_SIZE.getWidth() * MENU_WIDTH_SCALE) ,
                (int)(SCREEN_SIZE.getHeight() * MENU_HEIGHT_SCALE));
    }

    /**
     * @return The preferred dimension of the NorthSouthPanel and SouthPanel.
     */
    static Dimension getPlayersPanel() {
        return new Dimension(
                (int) (SCREEN_SIZE.getWidth() * PLAYERS_WIDTH_SCALE) ,
                (int)(SCREEN_SIZE.getHeight() * PLAYERS_HEIGHT_SCALE));
    }

}