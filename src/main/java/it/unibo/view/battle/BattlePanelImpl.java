package it.unibo.view.battle;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.data.TroopType;
import it.unibo.kingdomclash.config.BattleConfiguration;
import it.unibo.kingdomclash.config.PathIconsConfiguration;
import it.unibo.view.battle.panels.api.CommandPanel;
import it.unibo.view.battle.panels.api.FieldPanel;
import it.unibo.view.battle.panels.api.InfoPanel;
import it.unibo.view.battle.panels.api.PlayerPanel;
import it.unibo.view.battle.panels.entities.DrawPanelImpl;
import it.unibo.view.battle.panels.impl.CommandPanelImpl;
import it.unibo.view.battle.panels.impl.PlayerPanelImpl;
import it.unibo.view.battle.panels.impl.InfoPanelImpl;
import it.unibo.view.battle.panels.impl.FieldPanelImpl;
import it.unibo.view.battle.panels.impl.TutorialPanel;
import it.unibo.view.battle.panels.impl.TextPanelImpl;
import it.unibo.view.utilities.BattlePanelStyle;
import it.unibo.view.battle.panels.PanelDimensions;
import it.unibo.view.utilities.ImageIconsSupplier;

import java.awt.Dimension;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;

/**
 * This class implements BattlePanel and handle all the features of the Battle View.
 */
public final class BattlePanelImpl implements BattlePanel {

    private static final int BORDER_LAYOUT_GAP = 3;
    private static final int BORDER_THICKNESS = 2;
    private static final Dimension EXIT_DIMENSION = new Dimension(50, 25);

    private final BattleConfiguration battleConfiguration;
    private final CardLayout layoutManager;

    private final JPanel mainPanel;
    private final TutorialPanel tutorialPanel;
    private final TextPanelImpl endPanel;

    private final FieldPanel fieldPanel;
    private final PlayerPanel botPanel;
    private final PlayerPanel playerPanel;
    private final InfoPanel infoPanel;
    private final CommandPanel buttonsPanel;

    private final JButton closeButton;

    /**
     * Construct an instance of the BattlePanel.
     *
     * @param battleConfiguration    where are defined the configuration data.
     * @param pathIconsConfiguration where are defined the paths of the textures.
     */
    public BattlePanelImpl(final BattleConfiguration battleConfiguration, final PathIconsConfiguration pathIconsConfiguration) {
        this.battleConfiguration = battleConfiguration;
        this.tutorialPanel = new TutorialPanel(battleConfiguration.getTextConfiguration(), pathIconsConfiguration);
        this.endPanel = new TextPanelImpl(PanelDimensions.MAIN_PANEL_SIZE, pathIconsConfiguration);
        this.layoutManager = new CardLayout();
        this.mainPanel = new JPanel(this.layoutManager);
        this.closeButton = new JButton(
                ImageIconsSupplier.getScaledImageIcon(pathIconsConfiguration.getExit(), EXIT_DIMENSION));

        final JPanel gamePanel = new DrawPanelImpl(BattlePanelStyle.DEFAULT_COLOR, PanelDimensions.MAIN_PANEL_SIZE);
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

        this.mainPanel.add(gamePanel, PanelsName.FIGHT.name);
        this.mainPanel.add(tutorialPanel.getPanel(), PanelsName.TUTORIAL.name);
        this.mainPanel.add(endPanel, PanelsName.END.name);

        this.closeButton.setBorder(
                BorderFactory.createLineBorder(BattlePanelStyle.PRIMARY_COLOR, BORDER_THICKNESS, true));
        this.closeButton.setOpaque(false);
        this.endPanel.add(closeButton);

        this.setActionListenerExitButton();
        this.setActionListenerInfoButton();
    }

    @Override
    public void showEndPanel(@Nonnull final Boolean winner) {
        if (Boolean.TRUE.equals(winner)) {
            this.endPanel.setTitle(this.battleConfiguration.getTextConfiguration().getEndWinPanelTitle());
            this.endPanel.setContent(this.battleConfiguration.getTextConfiguration().getEndWinPanelText());
        } else {
            this.endPanel.setTitle(this.battleConfiguration.getTextConfiguration().getEndLosePanelTitle());
            this.endPanel.setContent(this.battleConfiguration.getTextConfiguration().getEndLosePanelText());
        }
        layoutManager.show(mainPanel, PanelsName.END.name);
    }

    @Override
    public void showTutorialPanel() {
        layoutManager.show(mainPanel, PanelsName.TUTORIAL.name);
    }

    @Override
    public void showGamePanel() {
        layoutManager.show(mainPanel, PanelsName.FIGHT.name);
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

    @SuppressFBWarnings(value = "EI",
            justification = "This panel has to be mutable from the class which uses it.")
    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

    @Override
    public void setActionListenersPlayerSlot(final ActionListener actionListener) {
        this.playerPanel.setActionListenersSlot(actionListener);
    }

    @Override
    public void setActionListenerSpinButton(final ActionListener actionListener) {
        this.buttonsPanel.setActionListenerSpin(actionListener);
    }


    @Override
    public void setActionListenerPass(final ActionListener actionListener) {
        this.buttonsPanel.setActionListenerPass(actionListener);
    }

    @Override
    public void setBackActionListener(final ActionListener actionListener) {
        this.closeButton.addActionListener(e -> {
            reset();
            showGamePanel();
        });
        this.closeButton.addActionListener(actionListener);
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

    private enum PanelsName {

        FIGHT("FIGHT"),

        TUTORIAL("TUTORIAL"),

        END("END");

        private final String name;

        PanelsName(final String name) {
            this.name = name;
        }
    }

}
