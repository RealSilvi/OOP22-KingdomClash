package it.unibo.view.battle.panels.impl;

import it.unibo.view.battle.Troop;
import it.unibo.view.battle.panels.entities.impl.TroopLabelImpl;
import it.unibo.view.battle.panels.utilities.ImageIconEntitiesManager;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.IntStream;

public class FieldPanelImpl extends JPanel implements it.unibo.view.battle.panels.api.FieldPanel {

    private final Dimension preferredSize;
    private final Image backgroundImage;

    private List<TroopLabelImpl> army;

    public FieldPanelImpl(Dimension preferredSize) {
        this.preferredSize=preferredSize;
        this.setLayout(new GridLayout(2,10));

        this.army=new ArrayList<>();


        this.backgroundImage = new ImageIcon(
                "src/main/resources/it/unibo/icons/battle/battleCenter.png"
        ).getImage();

        this.setOpaque(false);

        this.setPreferredSize(preferredSize);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, this);
    }


    @Override
    public void redraw(final List<Optional<Troop>> field) {
        this.army=new ArrayList<>();
        IntStream.iterate(0,x -> ++x).limit(field.size()).forEach( x-> {
            if(field.get(x).isEmpty()){
                army.add(new TroopLabelImpl());
            }else{
                army.add(new TroopLabelImpl(ImageIconEntitiesManager.getImageFromTroop(
                        field.get(x).get(),
                        true
                )));
            }
        });

        this.army.forEach(this::add);
    }

}
