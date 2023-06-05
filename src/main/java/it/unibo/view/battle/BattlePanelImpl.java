package it.unibo.view.battle;

import it.unibo.model.data.TroopType;
import it.unibo.view.battle.config.BattlePanelConfiguration;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.battle.panels.impl.*;
import it.unibo.view.battle.panels.utilities.BattlePanelStyle;
import it.unibo.view.battle.panels.utilities.PanelDimensions;
import it.unibo.view.battle.tutorial.TutorialPanel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.*;

public final class BattlePanelImpl implements BattlePanel {

    private final static int BORDER_LAYOUT_GAP = 3;

    private final CardLayout layoutManager;
    private final JPanel mainPanel;
    private final TutorialPanel tutorialPanel;

    private final FieldPanelImpl fieldPanel;
    private final PlayerPanelImpl botPanel;
    private final PlayerPanelImpl playerPanel;
    private final InfoPanelImpl infoPanel;
    private final CommandPanelImpl buttonsPanel;

    /**
     * param nrOfSlots  How many slots has each player.
     * param nrOfTroops How many troops has the game.
     * param nrOfLives  How many lives has each player
     */
    public BattlePanelImpl(final Map<Integer, TroopType> botTroops, final Map<Integer, TroopType> playerTroops,final BattlePanelConfiguration configuration) {
        this.layoutManager = new CardLayout();
        this.mainPanel = new JPanel(this.layoutManager);
        this.tutorialPanel = new TutorialPanel(configuration.getTutorialPanelConfiguration());
        JPanel gamePanel = new DrawPanel(BattlePanelStyle.DEFAULT_COLOR, PanelDimensions.SCREEN_SIZE);
        gamePanel.setLayout(new BorderLayout(BORDER_LAYOUT_GAP, BORDER_LAYOUT_GAP));

        final JPanel topPanel = new JPanel(new BorderLayout(BORDER_LAYOUT_GAP, BORDER_LAYOUT_GAP));

        this.botPanel = new PlayerPanelImpl(botTroops, configuration.getNrOfSlots());
        this.playerPanel = new PlayerPanelImpl(playerTroops,configuration.getNrOfSlots());
        this.infoPanel = new InfoPanelImpl(configuration.getNrOfTroops() );
        this.buttonsPanel = new CommandPanelImpl(configuration.getNrOfLives());
        this.fieldPanel = new FieldPanelImpl(configuration.getNrOfFieldSpots());

        topPanel.add(new JPanel().add(new JButton("QUA CI SARA IL MENU")), BorderLayout.NORTH);
        topPanel.add(botPanel.getPanel(), BorderLayout.SOUTH);

        gamePanel.add(topPanel, BorderLayout.NORTH);
        gamePanel.add(playerPanel.getPanel(), BorderLayout.SOUTH);
        gamePanel.add(infoPanel.getPanel(), BorderLayout.WEST);
        gamePanel.add(buttonsPanel.getPanel(), BorderLayout.EAST);
        gamePanel.add(fieldPanel.getPanel(), BorderLayout.CENTER);

        this.mainPanel.add(gamePanel, "1");
        this.mainPanel.add(tutorialPanel.getPanel(), "2");

        this.setActionListenerExitButton();
        this.setActionListenerInfoButton();
    }

    public void showTutorialPanel() {
        layoutManager.show(mainPanel, "2");
    }

    public void showGamePanel() {
        layoutManager.show(mainPanel, "1");
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
    public void spinPlayerFreeSlot(final Map<Integer, TroopType> troops) {
        this.playerPanel.update(troops);
    }

    @Override
    public void spinBotFreeSlot(final Map<Integer, TroopType> troops) {
        this.botPanel.update(troops);
    }

    @Override
    public void drawInfoTable(final Map<TroopType, Boolean> troopLv) {
        this.infoPanel.drawTable(troopLv);
    }

    @Override
    public void updateField(final List<Optional<TroopType>> playerTroops, final List<Optional<TroopType>> botPlayer) {
        this.fieldPanel.redraw(playerTroops, botPlayer);
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

    public void setActionListenersPlayerSlot(final ActionListener actionListener) {
        this.playerPanel.setActionListenersSlot(actionListener);
    }

    public void setActionListenerSpinButton(final ActionListener actionListener) {
        this.buttonsPanel.setActionListenerSpin(actionListener);
    }

    private void setActionListenerInfoButton() {
        ActionListener actionListenerInfo = e -> this.showTutorialPanel();
        this.buttonsPanel.setActionListenerInfo(actionListenerInfo);
    }

    public void setActionListenerPass(final ActionListener actionListener) {
        this.buttonsPanel.setActionListenerPass(actionListener);
    }

    private void setActionListenerExitButton() {
        ActionListener actionListenerInfo = e -> this.showGamePanel();
        this.tutorialPanel.addActionListenerExit(actionListenerInfo);
    }


}
