package it.unibo.view.city.panels.impl;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

import it.unibo.view.GameGui;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.city.panels.api.BarPanel;
import it.unibo.controller.base.BaseControllerImpl;

public class BarPanelImpl extends JLabel implements BarPanel {

    private static final Dimension size=new Dimension((int)(GameGui.getAllPanel().getWidth()),(int)(GameGui.getAllPanel().getHeight()*0.1));
    private final JPanel mainpanel;
    private BaseControllerImpl basedata;
    

    public BarPanelImpl(){
        this.mainpanel=new DrawPanel(Color.BLACK, size);
        this.basedata=basedata;
        
        
        JButton troop= new JButton("troops button");
        JButton playerinfo= new JButton("player info");
       

        
        this.mainpanel.add(troop);
        this.mainpanel.add(playerinfo);
        



    }
    
    


    


    public JTextArea getInfo(Dimension size) {

        JTextArea info = new JTextArea();
        info.setSize(size);
       
        basedata.requestTroopLevels().keySet().stream().forEach(
            singletroop -> info.append(""+singletroop.name()+""
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
