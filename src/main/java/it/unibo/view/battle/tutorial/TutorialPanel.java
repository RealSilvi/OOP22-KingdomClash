package it.unibo.view.battle.tutorial;

import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;
import it.unibo.view.battle.panels.utilities.PanelDimensions;

import java.awt.*;
import javax.swing.*;

public final class TutorialPanel{

    private final static int BORDER_LAYOUT_GAP = 3;
    private final static int nrOfSlots=5;
    private final static int nrOfTroops=8;
    private final static int nrOfLives=8;

    private final JPanel mainPanel;

    private final JPanel fieldPanel;
    private final JPanel botPanel;
    private final JPanel playerPanel;
    private final JPanel infoPanel;
    private final JPanel buttonsPanel;

    public TutorialPanel() {
        this.mainPanel= new DrawPanel(Color.darkGray, PanelDimensions.SCREEN_SIZE);
        this.mainPanel.setLayout(new BorderLayout(BORDER_LAYOUT_GAP,BORDER_LAYOUT_GAP));

        this.botPanel = new TextPanel(
                "titolo",
                "Davide come sta me lo hai mai chiesto" +
                    "chiama un ambulanza frate fai presto," +
                    "che il sogno che avevo non è mai questo" +
                    "mi sveglia, mi prende a calci e poi i da il resto.",
                PanelDimensions.getPlayersPanel());

        this.playerPanel = new TextPanel(
                "titolo",
                "Davide come sta me lo hai mai chiesto" +
                        "chiama un ambulanza frate fai presto," +
                        "che il sogno che avevo non è mai questo" +
                        "mi sveglia, mi prende a calci e poi i da il resto.",
                PanelDimensions.getPlayersPanel());

        this.infoPanel = new TextPanel(
                "titolo",
                "Davide come sta me lo hai mai chiesto" +
                        "chiama un ambulanza frate fai presto," +
                        "che il sogno che avevo non è mai questo" +
                        "mi sveglia, mi prende a calci e poi i da il resto.",
                PanelDimensions.getSidePanel());

        this.buttonsPanel = new TextPanel(
                "titolo",
                "Davide come sta me lo hai mai chiesto" +
                        "chiama un ambulanza frate fai presto," +
                        "che il sogno che avevo non è mai questo" +
                        "mi sveglia, mi prende a calci e poi i da il resto.",
                PanelDimensions.getSidePanel());

        this.fieldPanel = new TextPanel(
                "titolo",
                "Davide come sta me lo hai mai chiesto" +
                        "chiama un ambulanza frate fai presto," +
                        "che il sogno che avevo non è mai questo" +
                        "mi sveglia, mi prende a calci e poi i da il resto.",
                PanelDimensions.getFieldPanel());

        this.mainPanel.add(playerPanel,BorderLayout.SOUTH);
        this.mainPanel.add(botPanel,BorderLayout.NORTH);
        this.mainPanel.add(infoPanel,BorderLayout.WEST);
        this.mainPanel.add(buttonsPanel,BorderLayout.EAST);
        this.mainPanel.add(fieldPanel,BorderLayout.CENTER);
    }

    public JPanel getPanel() {
        return this.mainPanel;
    }

}
