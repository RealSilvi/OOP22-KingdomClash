package it.unibo.view.battle.panels.impl;

import it.unibo.view.battle.Troop;
import it.unibo.view.battle.panels.api.PlayerPanel;
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

    private final static double BUTTON_SCALE = 0.95;

    private final Dimension buttonsDimension;

    private final JPanel mainPanel;
    private final List<TroopButtonImpl> slots;

    public PlayerPanelImpl(final Dimension preferredSize,Map<Integer,Troop> troops,Integer nrOfSlots) {
        this.mainPanel=new DrawPanel(ImageIconsSupplier.BACKGROUND_PLAYERS);
        this.slots = new ArrayList<>();
        IntStream.range(0,nrOfSlots).forEach(x ->this.slots.add(new TroopButtonImpl(troops.get(x),true)));
        this.slots.forEach(this.mainPanel::add);


        this.mainPanel.setPreferredSize(preferredSize);
        this.buttonsDimension= new Dimension(
                (int)(preferredSize.getHeight() * BUTTON_SCALE), (int)(preferredSize.getHeight() * BUTTON_SCALE));
        this.setButtonsSize();

        this.update(troops);
    }

    private void setButtonsSize(){
        this.slots
                .forEach(
                        x -> x.setPreferredSize(this.buttonsDimension));
    }

    @Override
    public void update(Map<Integer,Troop> troops) {
        IntStream.range(0,this.slots.size()).forEach(x -> {
            if(troops.containsKey(x)){
                slots.get(x).setEnabled(true);
                slots.set(x,new TroopButtonImpl(troops.get(x),true));
            }else{
                slots.get(x).setEnabled(false);
            }
        });
    }

    @Override
    public void disableAllSlots(){
        this.slots.forEach(x -> x.setEnabled(false));
    }

    @Override
    public void enableAllSlots(){
        this.slots.forEach(x -> x.setEnabled(true));
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
