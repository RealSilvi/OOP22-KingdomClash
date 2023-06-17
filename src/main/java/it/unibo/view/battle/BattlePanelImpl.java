package it.unibo.view.battle;

import it.unibo.model.data.TroopType;
import it.unibo.kingdomclash.config.BattleConfiguration;
import it.unibo.kingdomclash.config.PathIconsConfiguration;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.battle.panels.impl.*;
import it.unibo.view.battle.utilities.BattlePanelStyle;
import it.unibo.view.battle.utilities.PanelDimensions;
import it.unibo.view.battle.panels.impl.TextPanel;
import it.unibo.view.battle.panels.impl.TutorialPanel;
import it.unibo.view.utilities.ImageIconsSupplier;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.swing.*;

public final class BattlePanelImpl implements BattlePanel {

    private static final int BORDER_LAYOUT_GAP = 3;
    private static final Dimension EXIT_DIMENSION=new Dimension(80,50);

    private final BattleConfiguration battleConfiguration;

    private final CardLayout layoutManager;

    private final JPanel mainPanel;
    private final TutorialPanel tutorialPanel;
    private final TextPanel endPanel;

    private final FieldPanelImpl fieldPanel;
    private final PlayerPanelImpl botPanel;
    private final PlayerPanelImpl playerPanel;
    private final InfoPanelImpl infoPanel;
    private final CommandPanelImpl buttonsPanel;

    private final JButton closeButton;

    /**
     * param nrOfSlots  How many slots has each player.
     * param nrOfTroops How many troops has the game.
     * param nrOfLives  How many lives has each player
     */
    public BattlePanelImpl(final BattleConfiguration battleConfiguration, final PathIconsConfiguration pathIconsConfiguration) {
        this.battleConfiguration=battleConfiguration;
        this.layoutManager = new CardLayout();
        this.closeButton = new JButton(ImageIconsSupplier.getScaledImageIcon(pathIconsConfiguration.getExit(), EXIT_DIMENSION));
        this.mainPanel = new JPanel(this.layoutManager);
        this.tutorialPanel = new TutorialPanel(battleConfiguration.getTextConfiguration(), pathIconsConfiguration);
        this.endPanel = new TextPanel(PanelDimensions.MAIN_PANEL_SIZE, pathIconsConfiguration);

        this.closeButton.setBorder(BorderFactory.createLineBorder(BattlePanelStyle.PRIMARY_COLOR, 2, true));
        this.closeButton.setOpaque(false);
        this.endPanel.add(closeButton);

        final JPanel gamePanel = new DrawPanel(BattlePanelStyle.DEFAULT_COLOR, PanelDimensions.MAIN_PANEL_SIZE);
        gamePanel.setLayout(new BorderLayout(BORDER_LAYOUT_GAP, BORDER_LAYOUT_GAP));

        this.botPanel = new PlayerPanelImpl(battleConfiguration.getNrOfSlots(), pathIconsConfiguration);
        this.playerPanel = new PlayerPanelImpl(battleConfiguration.getNrOfSlots(), pathIconsConfiguration);
        this.infoPanel = new InfoPanelImpl(TroopType.values().length, pathIconsConfiguration);
        this.buttonsPanel = new CommandPanelImpl(battleConfiguration.getNrOfLives(), pathIconsConfiguration);
        this.fieldPanel = new FieldPanelImpl(battleConfiguration.getNrOfFieldSpots(), pathIconsConfiguration);

        gamePanel.add(botPanel.getPanel(), BorderLayout.NORTH);
        gamePanel.add(playerPanel.getPanel(), BorderLayout.SOUTH);
        gamePanel.add(infoPanel.getPanel(), BorderLayout.WEST);
        gamePanel.add(buttonsPanel.getPanel(), BorderLayout.EAST);
        gamePanel.add(fieldPanel.getPanel(), BorderLayout.CENTER);

        this.mainPanel.add(gamePanel, "1");
        this.mainPanel.add(tutorialPanel.getPanel(), "2");
        this.mainPanel.add(endPanel, "3");

        this.setActionListenerExitButton();
        this.setActionListenerInfoButton();
    }

    public void showEndPanel(@Nonnull final Boolean winner) {
        if(Boolean.TRUE.equals(winner)){
            this.endPanel.setTitle(this.battleConfiguration.getTextConfiguration().getEndWinPanelTitle());
            this.endPanel.setContent(this.battleConfiguration.getTextConfiguration().getEndWinPanelText());
        }else{
            this.endPanel.setTitle(this.battleConfiguration.getTextConfiguration().getEndLosePanelTitle());
            this.endPanel.setContent(this.battleConfiguration.getTextConfiguration().getEndLosePanelText());
        }
        layoutManager.show(mainPanel, "3");
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
    public void updateField(final List<Optional<TroopType>> botPlayer) {
        this.fieldPanel.redraw(botPlayer);
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


    public void setActionListenerPass(final ActionListener actionListener) {
        this.buttonsPanel.setActionListenerPass(actionListener);
    }

    private void setActionListenerExitButton() {
        this.tutorialPanel.addActionListenerExit(e -> this.showGamePanel());
    }

    private void setActionListenerInfoButton() {
        this.buttonsPanel.setActionListenerInfo(e -> this.showTutorialPanel());
    }

    private void reset() {
        this.buttonsPanel.reset();
        this.fieldPanel.restart();
    }

    public void setBackActionListener(final ActionListener actionListener) {
        this.closeButton.addActionListener(e -> {
            reset();
            showGamePanel();
        });
        this.closeButton.addActionListener(actionListener);
    }

}
