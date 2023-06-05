package it.unibo.view.battle.tutorial;

import it.unibo.view.battle.panels.entities.DrawPanel;
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
    //TODO aggiungi roba in json e fai i metodi per averli
    public TutorialPanel(TutorialPanelConfiguration configuration) {
        this.turnBack = new JButton(ImageIconsSupplier.getImageExitIndicator(EXIT_DIMENSION));
        JPanel backPanel = new TextPanel(configuration.getSouthTitle(), configuration.getSouthText(), PanelDimensions.getPlayersPanel());

        this.turnBack.setPreferredSize(EXIT_DIMENSION);
        this.turnBack.setOpaque(false);
        this.turnBack.setBorder(BorderFactory.createLineBorder(ImageIconsSupplier.PRIMARY_COLOR, 2, true));

        backPanel.add(this.turnBack);

        this.mainPanel = new DrawPanel(Color.darkGray, PanelDimensions.SCREEN_SIZE);
        this.mainPanel.setLayout(new BorderLayout(BORDER_LAYOUT_GAP, BORDER_LAYOUT_GAP));

        this.mainPanel.add(backPanel, BorderLayout.SOUTH);
        this.mainPanel.add(new TextPanel(configuration.getNorthTitle(),configuration.getNorthText(),PanelDimensions.getPlayersPanel()), BorderLayout.NORTH);
        this.mainPanel.add(new TextPanel(configuration.getWestTitle(),configuration.getWestText(),PanelDimensions.getSidePanel()), BorderLayout.WEST);
        this.mainPanel.add(new TextPanel(configuration.getEastTitle(),configuration.getEastText(),PanelDimensions.getSidePanel()), BorderLayout.EAST);
        this.mainPanel.add(new TextPanel(configuration.getCenterTitle(),configuration.getCenterText(),PanelDimensions.getFieldPanel()), BorderLayout.CENTER);
    }

    public void addActionListenerExit(ActionListener actionListener) {
        this.turnBack.addActionListener(actionListener);
    }

    public JPanel getPanel() {
        return this.mainPanel;
    }

    public static class TutorialPanelConfiguration{
        private final String northTitle;
        private final String northText;
        private final String southTitle;
        private final String southText;
        private final String eastTitle;
        private final String eastText;
        private final String westTitle;
        private final String westText;
        private final String centerTitle;
        private final String centerText;


        public TutorialPanelConfiguration() {
            this.northTitle = " ";
            this.northText = " ";
            this.southTitle = " ";
            this.southText = " ";
            this.eastTitle = " ";
            this.eastText = " ";
            this.westTitle= " ";
            this.westText = " ";
            this.centerTitle = " ";
            this.centerText = " ";
        }

        public String getNorthTitle() {
            return northTitle;
        }

        public String getNorthText() {
            return northText;
        }

        public String getSouthTitle() {
            return southTitle;
        }

        public String getSouthText() {
            return southText;
        }

        public String getEastTitle() {
            return eastTitle;
        }

        public String getEastText() {
            return eastText;
        }

        public String getWestTitle() {
            return westTitle;
        }

        public String getWestText() {
            return westText;
        }

        public String getCenterTitle() {
            return centerTitle;
        }

        public String getCenterText() {
            return centerText;
        }
    }

}
