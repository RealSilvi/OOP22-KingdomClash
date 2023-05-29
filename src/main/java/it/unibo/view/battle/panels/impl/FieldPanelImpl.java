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

    private static final double LABEL_SCALE=0.1;
    private static final Dimension LABEL_DIMENSION= new Dimension(
            (int)(PanelDimensions.getFieldPanel().getHeight() * LABEL_SCALE),
            (int)(PanelDimensions.getFieldPanel().getHeight() * LABEL_SCALE));
    private static final int ROWS=2;

    private final JPanel mainPanel;

    private final List<TroopLabelImpl> army;

    /**
     *
     * @param nrOfSlots ho many slots the player has in the PlayerPanel
     */
    public FieldPanelImpl(final int nrOfSlots) {
        this.mainPanel=new DrawPanel(ImageIconsSupplier.BACKGROUND_FILL_PATTERN,PanelDimensions.getFieldPanel());
        this.army=new ArrayList<>();

        this.mainPanel.setLayout(new GridLayout(ROWS,nrOfSlots));
        IntStream.range(0,nrOfSlots*4).forEach(x->  this.army.add(new TroopLabelImpl(Troop.getRandomTroop(), LABEL_DIMENSION)));

        this.army.forEach(this.mainPanel::add);

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
