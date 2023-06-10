package it.unibo.view.city;

import it.unibo.controller.GameController;
import it.unibo.model.base.BaseModel;
import it.unibo.model.data.GameConfiguration;
import it.unibo.model.data.GameData;
import it.unibo.model.data.Resource;
import it.unibo.model.data.TroopType;
import it.unibo.model.data.Resource.ResourceType;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;
import it.unibo.view.city.panels.impl.BarPanelImpl;
import it.unibo.view.city.panels.impl.FieldCityPanelImpl;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.util.*;
import java.awt.*;

public class CityPanelImpl implements CityPanel{

    private final JPanel mainPanel;

    private final BarPanelImpl barPanel;
    private final FieldCityPanelImpl fieldPanel;

    public CityPanelImpl(GameController controller, GameConfiguration configuration){
        /*TO-DO */
        /*find a background image for the wallpaper of the panel */
        this.mainPanel=new DrawPanel(ImageIconsSupplier.BACKGROUND_FILL_PATTERN, size);
        this.mainPanel.setLayout(new BorderLayout());

        this.barPanel=new BarPanelImpl();
        this.fieldPanel=new FieldCityPanelImpl();

        //final JPanel cityPanel= new JPanel(new BorderLayout(BORDER_LAYOUT_GAP, BORDER_LAYOUT_GAP));
        //cityPanel.setBorder(new TitledBorder("box della interfaccia"));

       //cityPanel.add(barPanel.getPanel(),BorderLayout.NORTH); 
       //cityPanel.add(fieldPanel.getpanel(),BorderLayout.CENTER);
       this.mainPanel.add(barPanel.getPanel(),BorderLayout.NORTH);
       this.mainPanel.add(fieldPanel.getpanel(),BorderLayout.CENTER);
       //this.mainPanel.add(cityPanel);
        
    }
    
    public JPanel getPanel(){
        return this.mainPanel;
    }

    @Override
    public void resources() {
       this.fieldPanel.showresources();
    }

    @Override
    public void setBuildings() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setBuildings'");
    }

    @Override
    public void setfield() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setfield'");
    }

   


}
