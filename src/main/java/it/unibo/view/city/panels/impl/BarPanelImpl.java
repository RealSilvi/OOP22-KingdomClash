package it.unibo.view.city.panels.impl;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import it.unibo.view.GameGui;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.city.panels.api.BarPanel;
import it.unibo.view.city.utilities.ResourcePopupPanel;
import it.unibo.view.city.utilities.TroopPopupPanel;
import it.unibo.controller.base.BaseController;
import it.unibo.controller.base.BaseControllerImpl;
public class BarPanelImpl extends JLabel implements BarPanel {

    
    private final JPanel mainpanel;
    private BaseController basedata;
    private ResourcePopupPanel resourcepopup;
    private TroopPopupPanel trooppopup;
    
    

    public BarPanelImpl(BaseController controller, Dimension size){
        this.mainpanel=new DrawPanel(Color.BLACK, size);
        this.basedata=controller;
        this.resourcepopup = new ResourcePopupPanel(mainpanel, 100, 0, new ResourcePanelImpl(controller));
        this.trooppopup = new TroopPopupPanel(mainpanel, 500, 0);
        
        //this.isclicked= false;
        
        
        
        
        JButton troop= new JButton("troops button");
        JButton playerinfo= new JButton("player info");
        JButton resourcebutton = new JButton("resources");
        
        
       

        
        this.mainpanel.add(troop);
        this.mainpanel.add(playerinfo);
        this.mainpanel.add(resourcebutton);
        
        
        troop.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                trooppopup.changeVisibility();
                
            }
        });

        resourcebutton.addActionListener( new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
               resourcepopup.changeVisibility();
            }
            
        });
        
        //playerinfo



    }
    
    


    


    public JTextArea getInfo(Dimension size) {

        JTextArea info = new JTextArea();
        info.setSize(size);
       
        basedata.requestTroopLevels().keySet().stream().forEach(
            singletroop -> info.append("\n"+singletroop.name()+"\n"
                +basedata.requestTroopLevels().get(singletroop))

            
        );
        
       
        info.setEditable(false);
        


        
        return info;

    }

    /*devo riportare una lista delle truppe con il loro nome e il livello */

    @Override
    public void getPlayerInfo(BaseControllerImpl basedata) {
        System.out.println(""+basedata.requestPlayerName());
        /*aggiungere il tempo trascorso?? */
    }
    

    public JPanel getPanel(){
        return this.mainpanel;
    }

}

    
