package it.unibo.view.battle.panels.entities.impl;

import it.unibo.kingdomclash.config.PathIconsConfiguration;
import it.unibo.view.battle.panels.entities.DrawPanelImpl;
import it.unibo.view.battle.panels.entities.api.ButtonsPanel;
import it.unibo.view.utilities.BattlePanelStyle;
import it.unibo.view.utilities.ImageIconsSupplier;
import it.unibo.view.battle.panels.PanelDimensions;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

public class ButtonsPanelImpl implements ButtonsPanel {

    private static final int BUTTON_BORDER_THICKNESS = 4;

    private static final int H_GAP = 10;

    private static final int V_GAP = 10;

    private static final double GAME_BUTTON_SCALE = 0.5;

    private static final Dimension GAME_BUTTON_DIMENSION = new Dimension(
            (int) (PanelDimensions.getSideButtonsPanel().getHeight() * GAME_BUTTON_SCALE),
            (int) (PanelDimensions.getSideButtonsPanel().getHeight() * GAME_BUTTON_SCALE));

    private static final double INFO_BUTTON_SCALE = 0.3;
    private static final Dimension INFO_BUTTON_DIMENSION = new Dimension(
            (int) (PanelDimensions.getSideButtonsPanel().getWidth() * INFO_BUTTON_SCALE),
            (int) (PanelDimensions.getSideButtonsPanel().getHeight() * INFO_BUTTON_SCALE));

    private final JButton spin;
    private final JButton pass;
    private final JButton info;
    private final JPanel mainPanel;

    /**
     * Constructs an instance of a ButtonPanel.
     *
     * @param pathIconsConfiguration where are defined the paths of the textures.
     */
    public ButtonsPanelImpl(final PathIconsConfiguration pathIconsConfiguration) {
        this.mainPanel = new DrawPanelImpl(
                ImageIconsSupplier.loadImageIcon(pathIconsConfiguration.getBackgroundFillPattern()),
                PanelDimensions.getSideButtonsPanel());
        this.spin = this.getCostumeButton(
                ImageIconsSupplier.getScaledImageIcon(pathIconsConfiguration.getSpin(), GAME_BUTTON_DIMENSION),
                GAME_BUTTON_DIMENSION);
        this.pass = this.getCostumeButton(ImageIconsSupplier.getScaledImageIcon(
                pathIconsConfiguration.getPass(),
                GAME_BUTTON_DIMENSION), GAME_BUTTON_DIMENSION);
        this.info = this.getCostumeButton(ImageIconsSupplier.getScaledImageIcon(
                pathIconsConfiguration.getInfo(),
                INFO_BUTTON_DIMENSION), INFO_BUTTON_DIMENSION);

        this.mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, H_GAP, V_GAP));
        this.mainPanel.add(spin);
        this.mainPanel.add(pass);
        this.mainPanel.add(info);
    }

    private JButton getCostumeButton(final ImageIcon icon, final Dimension size) {
        final JButton button = new JButton() {
            @Override
            public void setEnabled(final boolean enabled) {
                super.setEnabled(enabled);
                if (enabled) {
                    this.setBorder(BorderFactory.createLineBorder(BattlePanelStyle.SECONDARY_COLOR, BUTTON_BORDER_THICKNESS, true));
                } else {
                    this.setBorder(BorderFactory.createLineBorder(BattlePanelStyle.PRIMARY_COLOR, BUTTON_BORDER_THICKNESS, true));
                }
            }
        };

        button.setIcon(icon);
        button.setDisabledIcon(icon);

        button.setPreferredSize(size);

        button.setBorder(BorderFactory.createLineBorder(BattlePanelStyle.SECONDARY_COLOR, BUTTON_BORDER_THICKNESS, true));
        button.setBackground(Color.BLACK);
        button.setOpaque(true);

        return button;
    }

    @Override
    public void disablePassButton() {
        this.pass.setEnabled(false);
    }

    @Override
    public void enablePassButton() {
        this.pass.setEnabled(true);
    }

    @Override
    public void disableSpinButton() {
        this.spin.setEnabled(false);
    }

    @Override
    public void enableSpinButton() {
        this.spin.setEnabled(true);
    }

    @Override
    public void setActionListenerPass(final ActionListener actionListener) {
        this.pass.addActionListener(actionListener);
    }

    @Override
    public void setActionListenerSpin(final ActionListener actionListener) {
        this.spin.addActionListener(actionListener);
    }

    @Override
    public void setActionListenerInfo(final ActionListener actionListener) {
        this.info.addActionListener(actionListener);
    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

}
