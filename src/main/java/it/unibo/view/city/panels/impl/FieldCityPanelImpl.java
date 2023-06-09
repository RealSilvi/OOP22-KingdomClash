package it.unibo.view.city.panels.impl;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

import it.unibo.model.base.BaseModelImpl;
import it.unibo.model.base.internal.BuildingBuilder;
import it.unibo.model.data.GameData;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.city.panels.api.FieldCityPanel;

public class FieldCityPanelImpl implements FieldCityPanel {

    private GameData data;
    private final JPanel mainpanel;
    private BaseModelImpl function;
    private Map<BuildingBuilder.BuildingTypes,Image> buildingmap;
    private Set<Set<Integer>> coordinate;
    private List<List<JButton>> buttonmap;

   

    public FieldCityPanelImpl(int width, int height, Dimension size){

        this.mainpanel= new DrawPanel(new ImageIcon("C:\\Users\\abdou\\OneDrive\\Immagini\\tiles\\grass.png"), size);
        this.data=data;
        this.function= function;

        this.mainpanel.setLayout(new GridLayout(width,height));
        this.setfield(width, height);
        buildingmap=new EnumMap<>(BuildingBuilder.BuildingTypes.class);
        buttonmap= new ArrayList<>();
       

    }

    
    private void setfield(int width, int height) {
       
        
        for (int i = 0; i < width; i++) {
            List<JButton> cols= new ArrayList<>();
            for (int j = 0; j < height; j++) {
                /*ognuna di esse ha una determinata posizione -aggiungere un listener per ciascun bottone con la quale possa piazzare la struttura 
                */
                
                final JButton structure= new JButton();
                cols.add(j, structure);
                /*il campo viene creato per caso magari aggiungo un metodo che generi immagini e le piazzi per caso */
                structure.setOpaque(false);
                structure.setContentAreaFilled(false);
                this.mainpanel.add(structure);
                structure.setBorder(null);
            }
            buttonmap.add(i, cols);
    }
        
        }
    @Override
    public void showresources() {
        this.data.getResources();

    }

    public JPanel getpanel(){
        return this.mainpanel;
    }

    public void getImage(){

    }
}