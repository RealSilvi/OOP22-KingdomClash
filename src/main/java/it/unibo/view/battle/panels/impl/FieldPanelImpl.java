package it.unibo.view.battle.panels.impl;

import it.unibo.view.battle.Troop;
import it.unibo.view.battle.panels.api.FieldPanel;
import it.unibo.view.battle.panels.entities.impl.DrawPanel;
import it.unibo.view.battle.panels.entities.impl.TroopLabelImpl;
import it.unibo.view.battle.panels.utilities.ImageIconEntitiesManager;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.IntStream;

public class FieldPanelImpl implements FieldPanel{

    private final JPanel mainPanel;

    private List<TroopLabelImpl> army;

    public FieldPanelImpl(Dimension preferredSize) {
        this.mainPanel=new DrawPanel(ImageIconEntitiesManager.BACKGROUND_FIELD_URL);
        this.army=new ArrayList<>();

        this.mainPanel.setLayout(new GridLayout(2,10));
        this.mainPanel.setPreferredSize(preferredSize);
    }


    @Override
    public void redraw(final List<Optional<Troop>> field) {
        this.army=new ArrayList<>();

        IntStream.range(0,field.size()).forEach( x-> {
            if(field.get(x).isEmpty()){
                army.add(new TroopLabelImpl());
            }else{
                army.add(new TroopLabelImpl(ImageIconEntitiesManager.getImageFromTroop(
                        field.get(x).get(),
                        true
                )));
            }
        });

        this.army.forEach(this.mainPanel::add);
    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

}
