package it.unibo.view.battle.panels.entities.impl;

import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.battle.panels.entities.api.ButtonsPanel;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;
import it.unibo.view.battle.panels.utilities.PanelDimensions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonsPanelImpl implements ButtonsPanel {
    private final static double GAME_BUTTON_SCALE = 0.5;
    private final static Dimension GAME_BUTTON_DIMENSION = new Dimension(
            (int) (PanelDimensions.getSideButtonsPanel().getHeight() * GAME_BUTTON_SCALE),
            (int) (PanelDimensions.getSideButtonsPanel().getHeight() * GAME_BUTTON_SCALE));
    private final static double INFO_BUTTON_SCALE = 0.3;
    private final static Dimension INFO_BUTTON_DIMENSION = new Dimension(
            (int) (PanelDimensions.getSideButtonsPanel().getWidth() * INFO_BUTTON_SCALE),
            (int) (PanelDimensions.getSideButtonsPanel().getHeight() * INFO_BUTTON_SCALE));

    private final JButton spin;
    private final JButton pass;
    private final JButton info;
    private final JPanel mainPanel;

    public ButtonsPanelImpl() {
        this.mainPanel = new DrawPanel(ImageIconsSupplier.BACKGROUND_FILL_PATTERN, PanelDimensions.getSideButtonsPanel());
        this.spin = this.getCostumeButton(ImageIconsSupplier.getImageIconSpin(GAME_BUTTON_DIMENSION), GAME_BUTTON_DIMENSION);
        this.pass = this.getCostumeButton(ImageIconsSupplier.getImageIconPass(GAME_BUTTON_DIMENSION), GAME_BUTTON_DIMENSION);
        this.info = this.getCostumeButton(ImageIconsSupplier.getImageIconInfo(INFO_BUTTON_DIMENSION), INFO_BUTTON_DIMENSION);

        this.mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.mainPanel.add(spin);
        this.mainPanel.add(pass);
        this.mainPanel.add(info);

    }

    private JButton getCostumeButton(final ImageIcon icon, final Dimension size) {
        JButton button = new JButton() {
            @Override
            public void setEnabled(boolean b) {
                super.setEnabled(b);
                if (b) {
                    this.setBorder(BorderFactory.createLineBorder(ImageIconsSupplier.SECONDARY_COLOR, 4, true));
                } else {
                    this.setBorder(BorderFactory.createLineBorder(ImageIconsSupplier.PRIMARY_COLOR, 4, true));
                }
            }
        };

        button.setIcon(icon);
        button.setDisabledIcon(icon);

        button.setPreferredSize(size);

        button.setBorder(BorderFactory.createLineBorder(ImageIconsSupplier.SECONDARY_COLOR, 4, true));
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
