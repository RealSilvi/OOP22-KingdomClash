package it.unibo.view.battle.panels.utilities;

import it.unibo.view.GameGui;

import java.awt.*;

/**
 * An interface that show how to get the preferred size of all the BorderLayout's JPanels.
 * <br>
 * Note: The NorthPanel has a BorderLayout also.
 */
public interface PanelDimensions {

    Dimension MAIN_PANEL_SIZE = GameGui.getAllPanel();

    double FIELD_WIDTH_SCALE = 0.6;
    double FIELD_HEIGHT_SCALE = 0.6;

    double SIDE_WIDTH_SCALE = 0.2;
    double SIDE_HEIGHT_SCALE = 0.6;

    double PLAYERS_WIDTH_SCALE = 1;
    double PLAYERS_HEIGHT_SCALE = 0.2;

    double SIDE_LIFE_WIDTH_SCALE = 1;
    double SIDE_LIFE_HEIGHT_SCALE = 0.3;

    double SIDE_BUTTONS_WIDTH_SCALE = 1;
    double SIDE_BUTTONS_HEIGHT_SCALE = 0.4;

    /**
     * @return The preferred dimension of the CenterPanel.
     */
    static Dimension getFieldPanel() {
        return new Dimension(
                (int) (MAIN_PANEL_SIZE.getWidth() * FIELD_WIDTH_SCALE),
                (int) (MAIN_PANEL_SIZE.getHeight() * FIELD_HEIGHT_SCALE));
    }

    /**
     * @return The preferred dimension of the NorthSouthPanel and SouthPanel.
     */
    static Dimension getPlayersPanel() {
        return new Dimension(
                (int) (MAIN_PANEL_SIZE.getWidth() * PLAYERS_WIDTH_SCALE),
                (int) (MAIN_PANEL_SIZE.getHeight() * PLAYERS_HEIGHT_SCALE));
    }

    /**
     * @return The preferred dimension of the EastPanel and WestPanel.
     */
    static Dimension getSidePanel() {
        return new Dimension(
                (int) (MAIN_PANEL_SIZE.getWidth() * SIDE_WIDTH_SCALE),
                (int) (MAIN_PANEL_SIZE.getHeight() * SIDE_HEIGHT_SCALE));
    }

    /**
     * @return The preferred dimension of the MiddlePanel of SidePanel.
     */
    static Dimension getSideButtonsPanel() {
        return new Dimension(
                (int) (getSideLifePanel().getWidth() * SIDE_BUTTONS_WIDTH_SCALE),
                (int) (getSidePanel().getHeight() * SIDE_BUTTONS_HEIGHT_SCALE));
    }

    /**
     * @return The preferred dimension of the TopPanel and BottomPanel of SidePanel.
     */
    static Dimension getSideLifePanel() {
        return new Dimension(
                (int) (getSidePanel().width * SIDE_LIFE_WIDTH_SCALE),
                (int) (getSidePanel().height * SIDE_LIFE_HEIGHT_SCALE));
    }
}
