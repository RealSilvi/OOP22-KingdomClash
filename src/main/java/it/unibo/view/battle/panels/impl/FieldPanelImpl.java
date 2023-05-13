package it.unibo.view.battle.panels.impl;

import it.unibo.view.battle.Troop;
import it.unibo.view.battle.panels.api.FieldPanel;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.battle.panels.entities.impl.TroopLabelImpl;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;
import it.unibo.view.battle.panels.utilities.PanelDimensions;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.IntStream;

public class FieldPanelImpl implements FieldPanel{

    private final JPanel mainPanel;

    private final List<TroopLabelImpl> army;

    public FieldPanelImpl(final int nrOfSlots) {
        this.mainPanel=new DrawPanel(ImageIconsSupplier.BACKGROUND_FIELD);
        this.army=new ArrayList<>();

        this.mainPanel.setLayout(new GridLayout(2,nrOfSlots));
        IntStream.range(0,nrOfSlots*4).forEach(x->  this.army.add(new TroopLabelImpl()));
        this.army.forEach(this.mainPanel::add);


        this.mainPanel.setMinimumSize(PanelDimensions.getFieldPanel());
        this.mainPanel.setMaximumSize(PanelDimensions.getFieldPanel());
    }

    @Override
    public void restart(){
        this.army.forEach(TroopLabelImpl::setEmpty);
    }


    @Override
    public void redraw(final List<Optional<Troop>> playerTroops,final List<Optional<Troop>> botPlayer) {
        List<Optional<Troop>> field=new ArrayList<>(playerTroops);
        field.addAll(botPlayer);

        IntStream.range(0,field.size()).forEach( x-> {
            if(field.get(x).isEmpty()){
                this.army.get(x).setEmpty();
            }else{
                this.army.get(x).setTroop(field.get(x).get());
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

}
