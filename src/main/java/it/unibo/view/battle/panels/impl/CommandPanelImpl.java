package it.unibo.view.battle.panels.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.kingdomclash.config.PathIconsConfiguration;
import it.unibo.view.battle.panels.api.CommandPanel;
import it.unibo.view.battle.panels.entities.api.ButtonsPanel;
import it.unibo.view.battle.panels.entities.api.LifePanel;
import it.unibo.view.battle.panels.entities.impl.ButtonsPanelImpl;
import it.unibo.view.battle.panels.entities.DrawPanelImpl;
import it.unibo.view.battle.panels.entities.impl.LifePanelImpl;
import it.unibo.view.utilities.ImageIconsSupplier;
import it.unibo.view.battle.panels.PanelDimensions;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;

public final class CommandPanelImpl implements CommandPanel {

    private final JPanel mainPanel;

    private final LifePanel playerLivesPanel;
    private final LifePanel botLivesPanel;
    private final ButtonsPanel buttonsPanel;

    /**
     * Constructs an instance of the CommandPanel
     *
     * @param numberOfLives          sets the number of lives for each player.
     * @param pathIconsConfiguration where are defined the paths of the textures.
     */
    public CommandPanelImpl(final int numberOfLives, final PathIconsConfiguration pathIconsConfiguration) {
        this.mainPanel = new DrawPanelImpl(ImageIconsSupplier.loadImageIcon(pathIconsConfiguration.getBackgroundFillPattern()), PanelDimensions.getSidePanel());
        this.botLivesPanel = new LifePanelImpl(numberOfLives, pathIconsConfiguration);
        this.playerLivesPanel = new LifePanelImpl(numberOfLives, pathIconsConfiguration);
        this.buttonsPanel = new ButtonsPanelImpl(pathIconsConfiguration);


        this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));
        this.mainPanel.add(botLivesPanel.getPanel());
        this.mainPanel.add(buttonsPanel.getPanel());
        this.mainPanel.add(playerLivesPanel.getPanel());
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
    public void disableSpinButton() {
        this.buttonsPanel.disableSpinButton();
    }

    @Override
    public void enableSpinButton() {
        this.buttonsPanel.enableSpinButton();
    }

    @Override
    public void decreasePlayerLive() {
        this.playerLivesPanel.decreaseLife();
    }

    @Override
    public void decreaseBotLive() {
        this.botLivesPanel.decreaseLife();
    }

    @Override
    public void setActionListenerPass(final ActionListener actionListener) {
        this.buttonsPanel.setActionListenerPass(actionListener);
    }

    @Override
    public void setActionListenerSpin(final ActionListener actionListener) {
        this.buttonsPanel.setActionListenerSpin(actionListener);
    }

    @Override
    public void setActionListenerInfo(final ActionListener actionListener) {
        this.buttonsPanel.setActionListenerInfo(actionListener);
    }

    @Override
    public void reset() {
        this.botLivesPanel.reset();
        this.playerLivesPanel.reset();
    }

    @SuppressFBWarnings(value = "EI",
            justification = "This panel has to be mutable from the class which uses it.")
    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

}
