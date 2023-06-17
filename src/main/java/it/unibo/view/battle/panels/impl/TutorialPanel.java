package it.unibo.view.battle.panels.impl;

import it.unibo.kingdomclash.config.PathIconsConfiguration;
import it.unibo.kingdomclash.config.TextConfiguration;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.battle.utilities.BattlePanelStyle;
import it.unibo.view.utilities.ImageIconsSupplier;
import it.unibo.view.battle.utilities.PanelDimensions;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public final class TutorialPanel {

    private static final int BORDER_LAYOUT_GAP = 3;
    private static final Dimension EXIT_DIMENSION = new Dimension(60, 30);

    private final JPanel mainPanel;

    private final JButton turnBack;


    public TutorialPanel(final TextConfiguration configuration, final PathIconsConfiguration pathIconsConfiguration) {
        this.turnBack = new JButton(ImageIconsSupplier.getScaledImageIcon(pathIconsConfiguration.getExit(), EXIT_DIMENSION));
        final JPanel backPanel = new TextPanel(configuration.getTutorialSouthTitle(), configuration.getTutorialSouthText(), PanelDimensions.getPlayersPanel(), pathIconsConfiguration);

        this.turnBack.setPreferredSize(EXIT_DIMENSION);
        this.turnBack.setOpaque(false);
        this.turnBack.setBorder(BorderFactory.createLineBorder(BattlePanelStyle.PRIMARY_COLOR, 2, true));

        backPanel.add(this.turnBack);

        this.mainPanel = new DrawPanel(Color.darkGray, PanelDimensions.MAIN_PANEL_SIZE);
        this.mainPanel.setLayout(new BorderLayout(BORDER_LAYOUT_GAP, BORDER_LAYOUT_GAP));

        this.mainPanel.add(backPanel, BorderLayout.SOUTH);
        this.mainPanel.add(new TextPanel(configuration.getTutorialNorthTitle(), configuration.getTutorialNorthText(), PanelDimensions.getPlayersPanel(), pathIconsConfiguration), BorderLayout.NORTH);
        this.mainPanel.add(new TextPanel(configuration.getTutorialWestTitle(), configuration.getTutorialWestText(), PanelDimensions.getSidePanel(), pathIconsConfiguration), BorderLayout.WEST);
        this.mainPanel.add(new TextPanel(configuration.getTutorialEastTitle(), configuration.getTutorialEastText(), PanelDimensions.getSidePanel(), pathIconsConfiguration), BorderLayout.EAST);
        this.mainPanel.add(new TextPanel(configuration.getTutorialCenterTitle(), configuration.getTutorialCenterText(), PanelDimensions.getFieldPanel(), pathIconsConfiguration), BorderLayout.CENTER);
    }

    public void addActionListenerExit(final ActionListener actionListener) {
        this.turnBack.addActionListener(actionListener);
    }

    public JPanel getPanel() {
        return this.mainPanel;
    }


}
