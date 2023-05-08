package it.unibo.view.battle.panels.impl;

import it.unibo.view.battle.Troop;
import it.unibo.view.battle.panels.api.PlayerPanel;
import it.unibo.view.battle.panels.entities.api.TroopButton;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.battle.panels.entities.impl.TroopButtonImpl;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.stream.IntStream;

public class PlayerPanelImpl implements PlayerPanel {

    private final static int NUMBER_OF_SLOTS = 5;
    private final static double BUTTON_SCALE = 0.95;

    private final Dimension buttonsDimension;

    private final JPanel mainPanel;
    private List<TroopButtonImpl> slots;

    public PlayerPanelImpl(final Dimension preferredSize) {
        this.mainPanel=new DrawPanel(ImageIconsSupplier.BACKGROUND_PLAYERS);
        this.mainPanel.setPreferredSize(preferredSize);
        this.buttonsDimension= new Dimension(
                (int)(preferredSize.getHeight() * BUTTON_SCALE), (int)(preferredSize.getHeight() * BUTTON_SCALE));

        this.restart();
    }

    @Override
    public void restart(ArrayList<Troop> troops) {

        this.slots = new ArrayList<>();
        troops.forEach(x -> this.slots.add(new TroopButtonImpl(x,true)));

        this.slots.forEach(this.mainPanel::add);
        this.setButtonsSize();

    }

    private void setButtonsSize(){
        this.slots
                .forEach(
                        x -> x.setPreferredSize(this.buttonsDimension));
    }

    @Override
    public void update(Map<Troop,Boolean> troops) {
//chiedi con lafa che struttura ti passa cosi lo fai partire
        IntStream.range(0,this.slots.size()).forEach(x -> {

        });
        this.disableSelectedSlots();
    }

    private void disableSelectedSlots(){
        this.slots.forEach(x -> x.setEnabled(x.getSelectable()));
    }

    @Override
    public void disableSlots(){
        this.slots.forEach(x -> x.setEnabled(false));
    }

    @Override
    public void enableSlots(){
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
