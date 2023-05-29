package it.unibo.view.battle;

import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.battle.panels.impl.*;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;
import it.unibo.view.battle.panels.utilities.PanelDimensions;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.*;

public final class BattlePanelImpl implements BattlePanel {

    private final static int BORDER_LAYOUT_GAP = 3;
    private final static int nrOfSlots=5;
    private final static int nrOfTroops=8;
    private final static int nrOfLives=8;

    private final JPanel mainPanel;

    //private final JPanel MenuPanel;
    private final FieldPanelImpl fieldPanel;
    private final PlayerPanelImpl botPanel;
    private final PlayerPanelImpl playerPanel;
    private final InfoPanelImpl infoPanel;
    private final CommandPanelImpl buttonsPanel;

    /**
     *
     * param nrOfSlots  How many slots has each player.
     * param nrOfTroops How many troops has the game.
     * param nrOfLives  How many lives has each player
     */
    public BattlePanelImpl(/*final int nrOfSlots, final int nrOfTroops,final int nrOfLives*/){
        this.mainPanel= new DrawPanel(ImageIconsSupplier.DEFAULT_COLOR, PanelDimensions.SCREEN_SIZE);
        this.mainPanel.setLayout(new BorderLayout(BORDER_LAYOUT_GAP,BORDER_LAYOUT_GAP));

        final JPanel topPanel = new JPanel(new BorderLayout(BORDER_LAYOUT_GAP,BORDER_LAYOUT_GAP));

        //this.menuPanel = new MenuPanel();
        this.botPanel = new PlayerPanelImpl(null,nrOfSlots);
        this.playerPanel = new PlayerPanelImpl(null,nrOfSlots);
        this.infoPanel = new InfoPanelImpl(nrOfTroops);
        this.buttonsPanel = new CommandPanelImpl(nrOfLives);
        this.fieldPanel = new FieldPanelImpl(nrOfSlots);

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
        this.buttonsPanel.decreaseBotLive();
    }

    @Override
    public void spinPlayerFreeSlot(final Map<Integer,Troop> troops) {
        this.playerPanel.update(troops);
    }

    @Override
    public void spinBotFreeSlot(final Map<Integer,Troop> troops) {
        this.botPanel.update(troops);
    }

    @Override
    public void drawInfoTable(final Map<Troop, Boolean> troopLv) {
        this.infoPanel.drawTable(troopLv);
    }

    @Override
    public void updateField(final List<Optional<Troop>> playerTroops,final List<Optional<Troop>> botPlayer) {
        this.fieldPanel.redraw(playerTroops,botPlayer);
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
