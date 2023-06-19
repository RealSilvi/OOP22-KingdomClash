package it.unibo.view.battle.panels.impl;

import it.unibo.kingdomclash.config.PathIconsConfiguration;
import it.unibo.kingdomclash.config.TextConfigurationBattle;
import it.unibo.view.battle.panels.entities.DrawPanelImpl;
import it.unibo.view.utilities.BattlePanelStyle;
import it.unibo.view.utilities.ImageIconsSupplier;
import it.unibo.view.battle.panels.PanelDimensions;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;

public final class TutorialPanel {

    private static final int BORDER_LAYOUT_GAP = 3;

    private static final Dimension EXIT_DIMENSION = new Dimension(60, 30);

    private static final int BORDER_THICKNESS = 2;

    private final JPanel mainPanel;
    private final JButton turnBack;


    /**
     * Initialize the tutorial panel for the BattlePanel.
     *
     * @param configuration          where are defined the texts.
     * @param pathIconsConfiguration where are defined the paths of the textures.
     */
    public TutorialPanel(final TextConfigurationBattle configuration, final PathIconsConfiguration pathIconsConfiguration) {
        this.turnBack = new JButton(ImageIconsSupplier.getScaledImageIcon(pathIconsConfiguration.getExit(),
                EXIT_DIMENSION));
        final JPanel backPanel = new TextPanelImpl(
                configuration.getTutorialSouthTitle(),
                configuration.getTutorialSouthText(),
                PanelDimensions.getPlayersPanel(),
                pathIconsConfiguration);

        this.turnBack.setPreferredSize(EXIT_DIMENSION);
        this.turnBack.setOpaque(false);
        this.turnBack.setBorder(BorderFactory.createLineBorder(
                BattlePanelStyle.PRIMARY_COLOR, BORDER_THICKNESS, true));

        backPanel.add(this.turnBack);

        this.mainPanel = new DrawPanelImpl(Color.darkGray, PanelDimensions.MAIN_PANEL_SIZE);
        this.mainPanel.setLayout(new BorderLayout(BORDER_LAYOUT_GAP, BORDER_LAYOUT_GAP));

        this.mainPanel.add(backPanel, BorderLayout.SOUTH);
        this.mainPanel.add(new TextPanelImpl(
                        configuration.getTutorialNorthTitle(),
                        configuration.getTutorialNorthText(),
                        PanelDimensions.getPlayersPanel(),
                        pathIconsConfiguration),
                BorderLayout.NORTH);
        this.mainPanel.add(new TextPanelImpl(
                        configuration.getTutorialWestTitle(),
                        configuration.getTutorialWestText(),
                        PanelDimensions.getSidePanel(),
                        pathIconsConfiguration),
                BorderLayout.WEST);
        this.mainPanel.add(new TextPanelImpl(
                        configuration.getTutorialEastTitle(),
                        configuration.getTutorialEastText(),
                        PanelDimensions.getSidePanel(),
                        pathIconsConfiguration),
                BorderLayout.EAST);
        this.mainPanel.add(new TextPanelImpl(
                        configuration.getTutorialCenterTitle(),
                        configuration.getTutorialCenterText(),
                        PanelDimensions.getFieldPanel(),
                        pathIconsConfiguration),
                BorderLayout.CENTER);
    }

    /**
     * Set the action listener to the exitButton.
     *
     * @param actionListener the action listener to set at the button.
     */
    public void addActionListenerExit(final ActionListener actionListener) {
        this.turnBack.addActionListener(actionListener);
    }

    /**
     * Returns itself in a JPanel.
     *
     * @return this instance like a JPanel.
     */
    public JPanel getPanel() {
        return this.mainPanel;
    }


}
