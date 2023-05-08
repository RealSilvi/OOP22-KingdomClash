package it.unibo.view.battle;

import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.battle.panels.impl.*;
import it.unibo.view.battle.panels.utilities.*;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.*;

public final class BattlePanelImpl implements BattlePanel {

    private final static int BORDER_LAYOUT_GAP = 3;

    private final JPanel mainPanel;

    //private final JPanel MenuPanel;
    private final FieldPanelImpl fieldPanel;
    private final PlayerPanelImpl botPanel;
    private final PlayerPanelImpl playerPanel;
    private final InfoPanelImpl infoPanel;
    private final CommandPanelImpl buttonsPanel;

    public BattlePanelImpl() {
        this.mainPanel= new DrawPanel(Color.gray);
        this.mainPanel.setLayout(new BorderLayout(BORDER_LAYOUT_GAP,BORDER_LAYOUT_GAP));

        final JPanel topPanel = new JPanel(new BorderLayout(BORDER_LAYOUT_GAP,BORDER_LAYOUT_GAP));

        //this.menuPanel = new MenuPanel();
        this.botPanel = new PlayerPanelImpl(PanelDimensions.getPlayersPanel());
        this.playerPanel = new PlayerPanelImpl(PanelDimensions.getPlayersPanel());
        this.infoPanel = new InfoPanelImpl(PanelDimensions.getSidePanel(),8);
        this.buttonsPanel = new CommandPanelImpl(PanelDimensions.getSidePanel() , 8);
        this.fieldPanel = new FieldPanelImpl(PanelDimensions.getFieldPanel());

        //topPanel.add(menuPanel, BorderLayout.NORTH);
        topPanel.add(new JPanel().add(new JButton("QUA CI SARA IL MENU")),BorderLayout.NORTH);
        topPanel.add(botPanel.getPanel(), BorderLayout.SOUTH);

        this.mainPanel.add(topPanel,BorderLayout.NORTH);
        this.mainPanel.add(playerPanel.getPanel(),BorderLayout.SOUTH);
        this.mainPanel.add(infoPanel.getPanel(),BorderLayout.WEST);
        this.mainPanel.add(buttonsPanel.getPanel(),BorderLayout.EAST);
        this.mainPanel.add(fieldPanel.getPanel(),BorderLayout.CENTER);
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

    @Override
    public void disableBotSlots() {
        this.botPanel.disableAllSlots();
    }

    @Override
    public void enableBotSlots() {
        this.botPanel.enableAllSlots();
    }

    @Override
    public void disablePlayerSlots() {
        this.playerPanel.disableAllSlots();
    }

    @Override
    public void enablePlayerSlots() {
        this.playerPanel.enableAllSlots();
    }

    @Override
    public void disableSpinButton() {
        this.buttonsPanel.disableSpinButton();
    }

    @Override
    public void enableSpinButton() {
        this.buttonsPanel.enableSpinButton();
    }

    @Override
    public void disablePassButton() {
        this.buttonsPanel.disablePassButton();
    }

    @Override
    public void enablePassButton() {
        this.buttonsPanel.enablePassButton();
    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

}
