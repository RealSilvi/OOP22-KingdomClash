package it.unibo.view.battle;

import it.unibo.view.battle.api.PanelDimensions;
import it.unibo.view.battle.impl.*;

import java.awt.*;

import javax.swing.*;

public final class BattleGui extends JFrame {

    //private final JPanel MenuPanel;
    private final JPanel fieldPanel;
    private final JPanel botPanel;
    private final JPanel playerPanel;
    private final JPanel infoPanel;
    private final JPanel buttonsPanel;


    public BattleGui() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        final JPanel mainPanel = new JPanel(new BorderLayout());

        final JPanel topPanel = new JPanel(new BorderLayout());

        //this.menuPanel = new MenuPanel();
        this.botPanel = new PlayerPanelImpl(PanelDimensions.getPlayersPanel());
        this.playerPanel = new PlayerPanelImpl(PanelDimensions.getPlayersPanel());
        this.infoPanel = new InfoPanelImpl(PanelDimensions.getSidePanel());
        this.buttonsPanel = new ButtonsPanelImpl(PanelDimensions.getSidePanel());
        this.fieldPanel = new FieldPanelImpl(PanelDimensions.getFieldPanel());

        //topPanel.add(menuPanel, BorderLayout.NORTH);
        topPanel.add(new JPanel().add(new JButton("QUA CI SARA IL MENU")),BorderLayout.NORTH);
        topPanel.add(botPanel, BorderLayout.SOUTH);

        mainPanel.add(topPanel,BorderLayout.NORTH);
        mainPanel.add(playerPanel,BorderLayout.SOUTH);
        mainPanel.add(infoPanel,BorderLayout.WEST);
        mainPanel.add(buttonsPanel,BorderLayout.EAST);
        mainPanel.add(fieldPanel,BorderLayout.CENTER);


        this.getContentPane().add(mainPanel);
        this.setVisible(true);
        this.pack();
    }
}
