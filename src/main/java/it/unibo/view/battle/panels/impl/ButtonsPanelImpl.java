package it.unibo.view.battle.panels.impl;

import it.unibo.view.battle.panels.api.ButtonsPanel;
import it.unibo.view.battle.panels.entities.api.LivesLabel;
import it.unibo.view.battle.panels.entities.impl.LivesLabelImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ButtonsPanelImpl extends JPanel implements ButtonsPanel {
    
    private final static double BUTTON_HORIZONTAL_SCALE=0.1;

    private final Dimension preferredDimension;

    private final JButton spin;
    private final JButton pass;
    private  ArrayList<LivesLabelImpl> botLives;
    private  ArrayList<LivesLabelImpl> playerLives;

    private final int numberOfLives = 8;
    
    public ButtonsPanelImpl(Dimension preferredDimension /*numberOfLives*/) {
        
        this.preferredDimension=preferredDimension;
        this.spin=new JButton("SPIN");
        this.pass=new JButton("PASS");

        this.setPreferredSize(preferredDimension);

        this.setOpaque(false);

        this.restart();
        this.setButtonsSize();
    }

    @Override
    public void restart() {
        JPanel topPanel=new JPanel();
        JPanel centerPanel=new JPanel();
        JPanel bottomPanel=new JPanel();

        this.botLives = new ArrayList<>();
        this.playerLives = new ArrayList<>();

        for(int i = 0; i< numberOfLives; i++){
            this.botLives.add(new LivesLabelImpl(true));
            this.playerLives.add(new LivesLabelImpl(true));
        }

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(topPanel);
        this.add(centerPanel);
        this.add(bottomPanel);

        this.botLives.forEach(topPanel::add);

        centerPanel.add(pass);
        centerPanel.add(spin);

        this.playerLives.forEach(bottomPanel::add);
    }

    private void setButtonsSize(){
        this.spin.setPreferredSize(new Dimension(
                (int)(preferredDimension.getHeight() * BUTTON_HORIZONTAL_SCALE),
                (int)(preferredDimension.getHeight() * BUTTON_HORIZONTAL_SCALE)));

        this.pass.setPreferredSize(new Dimension(
                (int)(preferredDimension.getHeight() * BUTTON_HORIZONTAL_SCALE)
                ,(int)(preferredDimension.getHeight() * BUTTON_HORIZONTAL_SCALE)));
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
    public void decreasePlayerLive(){
        this.playerLives.stream().findFirst().ifPresent(LivesLabel::changeStatus);
    }
    
    @Override
    public void decreaseBotLive(){
        this.botLives.stream().findFirst().ifPresent(LivesLabel::changeStatus);
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
