package it.unibo.view.battle.panels.entities.impl;

import it.unibo.view.battle.panels.utilities.ImageIconEntitiesManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonsPanelImpl extends JPanel implements it.unibo.view.battle.panels.entities.api.ButtonsPanel {

    private final Image backgroundImage;
    private final JButton spin;
    private final JButton pass;

    public ButtonsPanelImpl() {
        this.spin = new JButton("SPIN");
        this.pass = new JButton("PASS");
        this.backgroundImage =new ImageIcon(
                ImageIconEntitiesManager.BACKGROUND_BUTTONS_URL).getImage();
        this.setLayout(new GridLayout());
        this.add(pass);
        this.add(spin);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
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

}
