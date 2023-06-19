package it.unibo.view.battle.panels;

import it.unibo.view.GameGui;

import java.awt.Dimension;

/**
 * Utility interface that calculates all the dimensions of the panels in the BattlePanel.
 */
public interface PanelDimensions {

    /**
     * Indicate the dimension required by the frame.
     */
    Dimension MAIN_PANEL_SIZE = GameGui.getAllPanel();

    /**
     * Field panel width scale.
     */
    double FIELD_WIDTH_SCALE = 0.6;

    /**
     * Field panel height scale.
     */
    double FIELD_HEIGHT_SCALE = 0.6;

    /**
     * Sides panels width scale.
     */
    double SIDE_WIDTH_SCALE = 0.2;

    /**
     * Sides panels height scale.
     */
    double SIDE_HEIGHT_SCALE = 0.6;

    /**
     * Players panels width scale.
     */
    double PLAYERS_WIDTH_SCALE = 1;

    /**
     * Players panel height scale.
     */
    double PLAYERS_HEIGHT_SCALE = 0.2;

    /**
     * North and South panel width scale of the buttons panel.
     */
    double SIDE_LIFE_WIDTH_SCALE = 1;

    /**
     * North and South panel height scale of the buttons panel.
     */
    double SIDE_LIFE_HEIGHT_SCALE = 0.3;

    /**
     * Center panel width scale of the buttons panel.
     */
    double SIDE_BUTTONS_WIDTH_SCALE = 1;

    /**
     * Center panel height scale of the buttons panel.
     */
    double SIDE_BUTTONS_HEIGHT_SCALE = 0.4;

    /**
     * @return the preferred dimension of the CenterPanel.
     */
    static Dimension getFieldPanel() {
        return new Dimension(
                (int) (MAIN_PANEL_SIZE.getWidth() * FIELD_WIDTH_SCALE),
                (int) (MAIN_PANEL_SIZE.getHeight() * FIELD_HEIGHT_SCALE));
    }

    /**
     * @return the preferred dimension of the NorthSouthPanel and SouthPanel.
     */
    static Dimension getPlayersPanel() {
        return new Dimension(
                (int) (MAIN_PANEL_SIZE.getWidth() * PLAYERS_WIDTH_SCALE),
                (int) (MAIN_PANEL_SIZE.getHeight() * PLAYERS_HEIGHT_SCALE));
    }

    /**
     * @return the preferred dimension of the EastPanel and WestPanel.
     */
    static Dimension getSidePanel() {
        return new Dimension(
                (int) (MAIN_PANEL_SIZE.getWidth() * SIDE_WIDTH_SCALE),
                (int) (MAIN_PANEL_SIZE.getHeight() * SIDE_HEIGHT_SCALE));
    }

    /**
     * @return the preferred dimension of the MiddlePanel of SidePanel.
     */
    static Dimension getSideButtonsPanel() {
        return new Dimension(
                (int) (getSideLifePanel().getWidth() * SIDE_BUTTONS_WIDTH_SCALE),
                (int) (getSidePanel().getHeight() * SIDE_BUTTONS_HEIGHT_SCALE));
    }

    /**
     * @return the preferred dimension of the TopPanel and BottomPanel of SidePanel.
     */
    static Dimension getSideLifePanel() {
        return new Dimension(
                (int) (getSidePanel().width * SIDE_LIFE_WIDTH_SCALE),
                (int) (getSidePanel().height * SIDE_LIFE_HEIGHT_SCALE));
    }
}
