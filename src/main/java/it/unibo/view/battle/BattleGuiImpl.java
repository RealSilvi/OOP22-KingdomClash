package it.unibo.view.battle;

import it.unibo.view.battle.panels.impl.ButtonsPanelImpl;
import it.unibo.view.battle.panels.impl.FieldPanelImpl;
import it.unibo.view.battle.panels.impl.InfoPanelImpl;
import it.unibo.view.battle.panels.impl.PlayerPanelImpl;
import it.unibo.view.battle.panels.utilities.PanelDimensions;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.*;

public final class BattleGuiImpl extends JFrame implements BattleGui {

    private final static int GAP = 3;

    //private final JPanel MenuPanel;
    private final FieldPanelImpl fieldPanel;
    private final PlayerPanelImpl botPanel;
    private final PlayerPanelImpl playerPanel;
    private final InfoPanelImpl infoPanel;
    private final ButtonsPanelImpl buttonsPanel;

    public BattleGuiImpl() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        final JPanel mainPanel = new JPanel(new BorderLayout(GAP,GAP));
        mainPanel.setBackground(Color.darkGray);

        final JPanel topPanel = new JPanel(new BorderLayout(GAP,GAP));

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

    @Override
    public void hitPlayer() {
        this.buttonsPanel.decreasePlayerLive();
    }

    @Override
    public void hitBot() {
        this.buttonsPanel.decreasePlayerLive();
    }

    @Override
    public void spinPlayerFreeSlot() {
        this.playerPanel.update();
    }

    @Override
    public void spinBotFreeSlot() {
        this.botPanel.update();
    }

    @Override
    public void drawInfoTable(final Map<Troop, Boolean> troopLv) {
        this.infoPanel.drawTable(troopLv);
    }

    @Override
    public void updateField(final List<Optional<Troop>> field) {
        this.fieldPanel.redraw(field);
    }

}
