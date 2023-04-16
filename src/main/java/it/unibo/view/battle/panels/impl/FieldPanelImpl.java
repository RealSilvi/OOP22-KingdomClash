package it.unibo.view.battle.panels.impl;

import it.unibo.view.battle.Troop;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.IntStream;

public class FieldPanelImpl extends JPanel {

    private final Dimension preferredSize;
    private final Image backgroundImage;

    private List<Optional<JLabel>> botArmy;
    private List<Optional<JLabel>> playerArmy;

    public FieldPanelImpl(Dimension preferredSize) {
        this.preferredSize=preferredSize;
        this.setLayout(new GridLayout(3,10));

        this.botArmy=new ArrayList<>();
        this.playerArmy=new ArrayList<>();


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


    public void restart(final List<Optional<Troop>> botField, final List<Optional<Troop>> playerField) {
        this.playerArmy=new ArrayList<>();
        this.botArmy=new ArrayList<>();
        IntStream.iterate(0,x -> ++x).limit(10).forEach( x-> {
            if(botField.get(x).isEmpty()){
                botArmy.add(Optional.empty());
            }else{
                botArmy.add(Optional.of(new JLabel()));
            }
        });

    }
}
