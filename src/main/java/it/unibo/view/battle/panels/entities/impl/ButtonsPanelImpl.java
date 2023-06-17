package it.unibo.view.battle.panels.entities.impl;

import it.unibo.kingdomclash.config.PathIconsConfiguration;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.battle.panels.entities.api.ButtonsPanel;
import it.unibo.view.utilities.BattlePanelStyle;
import it.unibo.view.utilities.ImageIconsSupplier;
import it.unibo.view.battle.panels.PanelDimensions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonsPanelImpl implements ButtonsPanel {

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

    public ButtonsPanelImpl(final PathIconsConfiguration pathIconsConfiguration) {
        this.mainPanel = new DrawPanel(ImageIconsSupplier.loadImageIcon(pathIconsConfiguration.getBackgroundFillPattern()), PanelDimensions.getSideButtonsPanel());
        this.spin = this.getCostumeButton(ImageIconsSupplier.getScaledImageIcon(pathIconsConfiguration.getSpin(), GAME_BUTTON_DIMENSION)
                , GAME_BUTTON_DIMENSION);
        this.pass = this.getCostumeButton(ImageIconsSupplier.getScaledImageIcon(pathIconsConfiguration.getPass(), GAME_BUTTON_DIMENSION), GAME_BUTTON_DIMENSION);
        this.info = this.getCostumeButton(ImageIconsSupplier.getScaledImageIcon(pathIconsConfiguration.getInfo(), INFO_BUTTON_DIMENSION), INFO_BUTTON_DIMENSION);

        this.mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.mainPanel.add(spin);
        this.mainPanel.add(pass);
        this.mainPanel.add(info);

    }

    private JButton getCostumeButton(final ImageIcon icon, final Dimension size) {
        final JButton button = new JButton() {
            @Override
            public void setEnabled(final boolean b) {
                super.setEnabled(b);
                if (b) {
                    this.setBorder(BorderFactory.createLineBorder(BattlePanelStyle.SECONDARY_COLOR, 4, true));
                } else {
                    this.setBorder(BorderFactory.createLineBorder(BattlePanelStyle.PRIMARY_COLOR, 4, true));
                }
            }
        };

        button.setIcon(icon);
        button.setDisabledIcon(icon);

        button.setPreferredSize(size);

        button.setBorder(BorderFactory.createLineBorder(BattlePanelStyle.SECONDARY_COLOR, 4, true));
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
