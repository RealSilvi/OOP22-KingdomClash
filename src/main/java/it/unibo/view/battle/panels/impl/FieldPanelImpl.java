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

    private final List<TroopLabelImpl> armyPlayer;
    private final List<TroopLabelImpl> armyBot;

    private final int nrOfFieldSpot ;

    /**
     *
     * @param nrOfSlots ho many slots the player has in the PlayerPanel
     */
    public FieldPanelImpl(final int nrOfSlots) {
        this.nrOfFieldSpot=nrOfSlots*2;
        this.mainPanel=new DrawPanel(ImageIconsSupplier.BACKGROUND_FILL_PATTERN,PanelDimensions.getFieldPanel());
        this.armyPlayer=new ArrayList<>();
        this.armyBot=new ArrayList<>();

        this.mainPanel.setLayout(new GridLayout(ROWS,nrOfSlots));
        IntStream.range(0,nrOfFieldSpot).forEach(x-> this.armyBot.add(new TroopLabelImpl(LABEL_DIMENSION)));
        IntStream.range(0,nrOfFieldSpot).forEach(x-> this.armyPlayer.add(new TroopLabelImpl(LABEL_DIMENSION)));

        this.restart();
        this.armyBot.forEach(this.mainPanel::add);
        this.armyPlayer.forEach(this.mainPanel::add);

    }

    @Override
    public void restart(){
        this.armyBot.forEach(TroopLabelImpl::setEmpty);
        this.armyPlayer.forEach(TroopLabelImpl::setEmpty);
    }

    @Override
    public void redraw(final List<Optional<Troop>> playerTroops,final List<Optional<Troop>> botTroops) {
        if(playerTroops.isEmpty() || botTroops.isEmpty()){
            this.restart();
        }
        IntStream.range(0,playerTroops.size()).forEach( x-> {
            if(playerTroops.get(x).isEmpty()){
                this.armyPlayer.get(x).setEmpty();
            }else{
                this.armyPlayer.get(x).setTroop(playerTroops.get(x).get());
            }
            if(botTroops.get(x).isEmpty()){
                this.armyBot.get(x).setEmpty();
            }else{
                this.armyBot.get(x).setTroop(botTroops.get(x).get());
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

}
