package it.unibo.view.battle.panels.impl;

import it.unibo.view.battle.Troop;
import it.unibo.view.battle.panels.api.PlayerPanel;
import it.unibo.view.battle.panels.entities.api.TroopButton;
import it.unibo.view.battle.panels.entities.impl.DrawPanel;
import it.unibo.view.battle.panels.entities.impl.TroopButtonImpl;
import it.unibo.view.battle.panels.utilities.ImageIconEntitiesManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class PlayerPanelImpl implements PlayerPanel {

    private final static int NUMBER_OF_SLOTS = 5;
    private final static double BUTTON_SCALE = 0.12;

    private final JPanel mainPanel;
    private List<TroopButtonImpl> slots;

    public PlayerPanelImpl(final Dimension preferredSize) {
        this.mainPanel=new DrawPanel(ImageIconEntitiesManager.BACKGROUND_PLAYERS_URL);
        this.mainPanel.setPreferredSize(preferredSize);

        this.restart();
    }

    @Override
    public void restart() {
        this.slots = new ArrayList<>();

        for(int i=0; i<NUMBER_OF_SLOTS; i++){
            this.slots.add(new TroopButtonImpl(Troop.getRandomTroop()));
        }

        this.slots.forEach(this.mainPanel::add);

        this.setButtonsSize();

    }

    private void setButtonsSize(){
        this.slots
                .forEach(
                        x -> x.setPreferredSize(new Dimension(
                                (int)(this.mainPanel.getWidth() * BUTTON_SCALE),
                                (int)(this.mainPanel.getWidth() * BUTTON_SCALE))));
    }

    @Override
    public void update() {
        setRandomSlots();
        this.disableSelectedSlots();
    }

    private void setRandomSlots() {
        this.slots
                .stream()
                .filter(TroopButton::getSelectable)
                .forEach(TroopButton::changeTroop);
    }

    private void disableSelectedSlots(){
        this.slots.forEach(x -> x.setEnabled(x.getSelectable()));
    }

    @Override
    public void disableAllSlots(){
        this.slots.forEach(x -> x.setEnabled(false));
    }

    @Override
    public void enableAllSlots(){
        this.slots.stream().filter(TroopButtonImpl::getSelectable).forEach(x -> x.setEnabled(true));
    }

    @Override
    public void setActionListenersSlot(ActionListener actionListener){
        this.slots.forEach(x -> x.addActionListener(actionListener));
    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }
}
