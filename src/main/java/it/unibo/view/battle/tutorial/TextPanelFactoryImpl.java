package it.unibo.view.battle.tutorial;

import it.unibo.view.battle.config.JSonToData;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;
import it.unibo.view.battle.panels.utilities.PanelDimensions;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TextPanelFactoryImpl implements TextPanelFactory {
    private static final String TITLE_JSON_KEY = "title";
    private static final String CONTENT_JSON_KEY = "text";

    private static final String NORTH_PANEL_JSON_KEY = "NorthPanel";
    private static final String SOUTH_PANEL_JSON_KEY = "SouthPanel";
    private static final String WEST_PANEL_JSON_KEY = "WestPanel";
    private static final String EAST_PANEL_JSON_KEY = "EastPanel";
    private static final String CENTER_PANEL_JSON_KEY = "CenterPanel";

    private final JSonToData dataManager = new JSonToData();


    @Override
    public JPanel getTutorialNorthPanelDefault() {
        return new TextPanel(
                dataManager.getTutorialPanelsFields(NORTH_PANEL_JSON_KEY, TITLE_JSON_KEY),
                dataManager.getTutorialPanelsFields(NORTH_PANEL_JSON_KEY, CONTENT_JSON_KEY),
                PanelDimensions.getPlayersPanel());
    }

    @Override
    public JPanel getTutorialWestPanelDefault() {
        return new TextPanel(
                dataManager.getTutorialPanelsFields(WEST_PANEL_JSON_KEY, TITLE_JSON_KEY),
                dataManager.getTutorialPanelsFields(WEST_PANEL_JSON_KEY, CONTENT_JSON_KEY),
                PanelDimensions.getSidePanel());
    }

    @Override
    public JPanel getTutorialEastPanelDefault() {
        return new TextPanel(
                dataManager.getTutorialPanelsFields(EAST_PANEL_JSON_KEY, TITLE_JSON_KEY),
                dataManager.getTutorialPanelsFields(EAST_PANEL_JSON_KEY, CONTENT_JSON_KEY),
                PanelDimensions.getSidePanel());
    }

    @Override
    public JPanel getTutorialSouthPanelDefault() {
        return new TextPanel(
                dataManager.getTutorialPanelsFields(SOUTH_PANEL_JSON_KEY, TITLE_JSON_KEY),
                dataManager.getTutorialPanelsFields(SOUTH_PANEL_JSON_KEY, CONTENT_JSON_KEY),
                PanelDimensions.getPlayersPanel());
    }

    @Override
    public JPanel getTutorialCenterPanelDefault() {
        return new TextPanel(dataManager.getTutorialPanelsFields(CENTER_PANEL_JSON_KEY, TITLE_JSON_KEY),
                dataManager.getTutorialPanelsFields(CENTER_PANEL_JSON_KEY, CONTENT_JSON_KEY),
                PanelDimensions.getFieldPanel());
    }

    @Override
    public JPanel getCustomizedTextPanel(String title, String text, Dimension size) {
        return new TextPanel(title, text, size);
    }

    private static class TextPanel extends DrawPanel {

        private final static int VERTICAL_PADDING=10;
        private final static int HORIZONTAL_PADDING=30;
        private final static float TITLE_FONT_SIZE = 30f;
        private final static float CONTENT_FONT_SIZE = 20f;

        public TextPanel(final String title,final String content,final Dimension size) {
            super(ImageIconsSupplier.BACKGROUND_FILL_PATTERN,size);

            JLabel title1 = new JLabel(title);
            JTextArea content1 = new JTextArea(content);

            Border padding = BorderFactory.createEmptyBorder(
                    VERTICAL_PADDING,
                    HORIZONTAL_PADDING,
                    VERTICAL_PADDING,
                    HORIZONTAL_PADDING);
            title1.setBorder(padding);
            content1.setBorder(padding);

            content1.setLineWrap(true);
            content1.setWrapStyleWord(true);
            content1.setEditable(false);

            title1.setAlignmentX(JLabel.CENTER_ALIGNMENT);

            title1.setFont(ImageIconsSupplier.getPrimaryFont().deriveFont(TITLE_FONT_SIZE));
            content1.setFont(content1.getFont().deriveFont(CONTENT_FONT_SIZE));

            title1.setOpaque(false);
            content1.setOpaque(false);

            title1.setForeground(ImageIconsSupplier.PRIMARY_COLOR);
            content1.setForeground(ImageIconsSupplier.PRIMARY_COLOR);

            this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
            this.add(title1);
            this.add(content1);
        }
    }
}
