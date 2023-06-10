package it.unibo.view.city.panels.impl;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

import it.unibo.controller.base.BaseControllerImpl;
import it.unibo.model.base.BaseModelImpl;
import it.unibo.model.base.internal.BuildingBuilder;
import it.unibo.model.data.GameData;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.city.panels.api.FieldCityPanel;

public class FieldCityPanelImpl implements FieldCityPanel {

    private final JPanel mainpanel;
    private BaseModelImpl function;
    private Map<BuildingBuilder.BuildingTypes,Image> buildingmap;
    private Set<Set<Integer>> coordinate;
    private List<List<JButton>> buttonmap;
    private CityConfiguration cityConfiguration;
    private BaseControllerImpl basedata;

   

    public FieldCityPanelImpl(){

        this.mainpanel= new DrawPanel(new ImageIcon("C:\\Users\\abdou\\OneDrive\\Immagini\\tiles\\grass.png"), size);
        this.function = function;
        this.cityConfiguration = new CityConfiguration();
        this.basedata = basedata;

        this.mainpanel.setLayout(new GridLayout(cityConfiguration.getWidth(),cityConfiguration.getHeight()));
        this.setfield(cityConfiguration.getWidth(), cityConfiguration.getHeight());
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

    public JPanel getPanel(){
        return this.mainpanel;
    }


}