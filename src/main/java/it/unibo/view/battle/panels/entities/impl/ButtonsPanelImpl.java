package it.unibo.view.battle.panels.entities.impl;

import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.battle.panels.entities.api.ButtonsPanel;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;
import it.unibo.view.battle.panels.utilities.PanelDimensions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonsPanelImpl implements ButtonsPanel {
    private final static double BUTTON_SCALE = 0.7;
    private final static Dimension BUTTON_DIMENSION= new Dimension(
            (int)(PanelDimensions.getPlayersPanel().getHeight() * BUTTON_SCALE),
            (int)(PanelDimensions.getPlayersPanel().getHeight() * BUTTON_SCALE));

    private final JButton spin;
    private final JButton pass;
    private final JPanel mainPanel;

    public ButtonsPanelImpl() {
        this.mainPanel = new DrawPanel(ImageIconsSupplier.BACKGROUND_FILL_PATTERN,PanelDimensions.getSideButtonsPanel());
        this.spin = new JButton();
        this.pass = new JButton();

        this.mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
        this.mainPanel.add(pass);
        this.mainPanel.add(spin);


        this.pass.setIcon(ImageIconsSupplier.getImageIconPass(BUTTON_DIMENSION));
        this.spin.setIcon(ImageIconsSupplier.getImageIconSpin(BUTTON_DIMENSION));
        this.spin.setBorder(BorderFactory.createLineBorder(Color.GRAY,4,true));
        this.pass.setBorder(BorderFactory.createLineBorder(Color.GRAY,4,true));
        this.pass.setPreferredSize(BUTTON_DIMENSION);
        this.spin.setPreferredSize(BUTTON_DIMENSION);

    }


    @Override
    public void disablePassButton(){
        this.pass.setEnabled(false);
    }


    @Override
    public void enablePassButton(){
        this.pass.setEnabled(true);
    }


    @Override
    public void disableSpinButton(){
        this.spin.setEnabled(false);
    }


    @Override
    public void enableSpinButton(){
        this.spin.setEnabled(true);
    }


    @Override
    public void setActionListenerPass(final ActionListener actionListener){
        this.pass.addActionListener(actionListener);
    }


    @Override
    public void setActionListenerSpin(final ActionListener actionListener){
        this.spin.addActionListener(actionListener);
    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

}
