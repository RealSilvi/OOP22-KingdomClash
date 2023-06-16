package it.unibo.view.battle.panels.impl;

import it.unibo.view.battle.config.PathIconsConfiguration;
import it.unibo.view.battle.panels.api.CommandPanel;
import it.unibo.view.battle.panels.entities.impl.ButtonsPanelImpl;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.battle.panels.entities.impl.LifePanelImpl;
import it.unibo.view.utilities.ImageIconsSupplier;
import it.unibo.view.battle.panels.utilities.PanelDimensions;

import javax.swing.*;
import java.awt.event.ActionListener;

public class CommandPanelImpl implements CommandPanel {

    private final JPanel mainPanel;

    private final LifePanelImpl playerLivesPanel;
    private final LifePanelImpl botLivesPanel;
    private final ButtonsPanelImpl buttonsPanel;

    /**
     * @param numberOfLives how many health points has the players
     */
    public CommandPanelImpl(final int numberOfLives, final PathIconsConfiguration pathIconsConfiguration) {
        this.mainPanel = new DrawPanel(ImageIconsSupplier.loadImageIcon(pathIconsConfiguration.getBackgroundFillPattern()), PanelDimensions.getSidePanel());
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

    public void reset() {
        this.botLivesPanel.reset();
        this.playerLivesPanel.reset();
    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

}
