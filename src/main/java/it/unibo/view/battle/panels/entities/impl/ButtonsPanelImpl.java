package it.unibo.view.battle.panels.entities.impl;

import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.battle.panels.entities.api.ButtonsPanel;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonsPanelImpl implements ButtonsPanel {

    private final JButton spin;
    private final JButton pass;
    private final JPanel mainPanel;

    public ButtonsPanelImpl() {
        this.mainPanel = new DrawPanel(ImageIconsSupplier.BACKGROUND_BUTTONS);
        this.spin = new JButton("SPIN");
        this.pass = new JButton("PASS");

        this.mainPanel.setLayout(new GridLayout());
        this.mainPanel.add(pass);
        this.mainPanel.add(spin);
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
    public void setActionListenerPass(ActionListener actionListener){
        this.pass.addActionListener(actionListener);
    }


    @Override
    public void setActionListenerSpin(ActionListener actionListener){
        this.spin.addActionListener(actionListener);
    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }


}
