package it.unibo.view.battle.tutorial;

import it.unibo.view.battle.config.TextConfiguration;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.battle.panels.utilities.BattlePanelStyle;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;
import it.unibo.view.battle.panels.utilities.PanelDimensions;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public final class TutorialPanel {

    private final static int BORDER_LAYOUT_GAP = 3;
    private final static Dimension EXIT_DIMENSION = new Dimension(60, 30);

    private final JPanel mainPanel;

    private final JButton turnBack;


    //TODO interfacce e commenti
    //TODO tutti i final dei parametri
    //TODO tutti i punti alla fine dei comenti
    //TODO tutti i commenti dei costruttori
    //TODO tutti i commenti delle interfacce da rivedere
    public TutorialPanel(TextConfiguration configuration) {
        this.turnBack = new JButton(ImageIconsSupplier.getImageIconExit(EXIT_DIMENSION));
        JPanel backPanel = new TextPanel(configuration.getTutorialSouthTitle(), configuration.getTutorialSouthText(), PanelDimensions.getPlayersPanel());

        this.turnBack.setPreferredSize(EXIT_DIMENSION);
        this.turnBack.setOpaque(false);
        this.turnBack.setBorder(BorderFactory.createLineBorder(BattlePanelStyle.PRIMARY_COLOR, 2, true));

        backPanel.add(this.turnBack);

        this.mainPanel = new DrawPanel(Color.darkGray, PanelDimensions.SCREEN_SIZE);
        this.mainPanel.setLayout(new BorderLayout(BORDER_LAYOUT_GAP, BORDER_LAYOUT_GAP));

        this.mainPanel.add(backPanel, BorderLayout.SOUTH);
        this.mainPanel.add(new TextPanel(configuration.getTutorialNorthTitle(),configuration.getTutorialNorthText(),PanelDimensions.getPlayersPanel()), BorderLayout.NORTH);
        this.mainPanel.add(new TextPanel(configuration.getTutorialWestTitle(),configuration.getTutorialWestText(),PanelDimensions.getSidePanel()), BorderLayout.WEST);
        this.mainPanel.add(new TextPanel(configuration.getTutorialEastTitle(),configuration.getTutorialEastText(),PanelDimensions.getSidePanel()), BorderLayout.EAST);
        this.mainPanel.add(new TextPanel(configuration.getTutorialCenterTitle(),configuration.getTutorialCenterText(),PanelDimensions.getFieldPanel()), BorderLayout.CENTER);
    }

    public void addActionListenerExit(ActionListener actionListener) {
        this.turnBack.addActionListener(actionListener);
    }

    public JPanel getPanel() {
        return this.mainPanel;
    }



}
