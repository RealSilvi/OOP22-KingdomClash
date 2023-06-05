package it.unibo.view.battle.tutorial;

import javax.swing.*;
import java.awt.*;

/**
 * Defines JPanels with a default background a title and a text.
 * The default Panels are directly read from the json file
 */
public interface TextPanelFactory {

    /**
     * @return a north panel for the tutorial panel with default title and text
     */
    JPanel getTutorialNorthPanelDefault();

    /**
     * @return a west panel for the tutorial panel with default title and text
     */
    JPanel getTutorialWestPanelDefault();

    /**
     * @return a wast panel for the tutorial panel with default title and text
     */
    JPanel getTutorialEastPanelDefault();

    /**
     * @return a south panel for the tutorial panel with default title and text
     */
    JPanel getTutorialSouthPanelDefault();

    /**
     * @return a center panel for the tutorial panel with default title and text
     */
    JPanel getTutorialCenterPanelDefault();

    /**
     * @param title the title to set at the Panel
     * @param text  the text to set at the Panel
     * @param size  the size of the panel
     * @return the panel
     */
    JPanel getCustomizedTextPanel(final String title, final String text, final Dimension size);
}
