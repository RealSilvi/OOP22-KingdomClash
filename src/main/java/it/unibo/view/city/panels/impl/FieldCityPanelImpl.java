package it.unibo.view.city.panels.impl;

import javax.swing.*;
import java.awt.*;
import java.util.*;

import it.unibo.model.base.BaseModelImpl;
import it.unibo.model.data.GameData;
import it.unibo.view.battle.Troop;
import it.unibo.view.battle.panels.api.FieldPanel;
import it.unibo.view.city.panels.api.FieldCityPanel;

public class FieldCityPanelImpl implements FieldCityPanel{

    GameData data;
    BaseModelImpl function= new BaseModelImpl(data);

    @Override
    public void setfield(int width, int height) {
        final JPanel fieldpanel= new JPanel(new GridLayout(width, height));
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                /*ognuna di esse ha una determinata posizione -aggiungere un listener per ciascun bottone con la quale possa piazzare la struttura 
                 * come stracazzo lo faccio
                */
                final JButton structure= new JButton(""+i+","+j);
                /*il campo viene creato per caso magari aggiungo un metodo che generi immagini e le piazzi per caso */
                structure.setIcon(new ImageIcon("C:\\Users\\abdou\\OneDrive\\Immagini\\tiles\\grass.png"));
                fieldpanel.add(structure);
            }
    }
        }
    @Override
    public float showresources() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showresources'");
    }

  
    
}