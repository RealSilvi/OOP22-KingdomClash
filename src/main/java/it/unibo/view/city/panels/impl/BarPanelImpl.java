package it.unibo.view.city.panels.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import it.unibo.view.GameGui;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.city.panels.api.BarPanel;
import it.unibo.controller.base.BaseControllerImpl;
import it.unibo.view.city.NewWindow;
public class BarPanelImpl extends JLabel implements BarPanel {

    private static final Dimension size=new Dimension((int)(GameGui.getAllPanel().getWidth()),(int)(GameGui.getAllPanel().getHeight()*0.1));
    private final JPanel mainpanel;
    private BaseControllerImpl basedata;
    private JPanel secondaryPanel;
    private PopupFactory popupFactory;
    private Popup popup;
    private NewWindow window;
    

    public BarPanelImpl(){
        this.mainpanel=new DrawPanel(Color.BLACK, size);
        this.basedata=basedata;
        this.secondaryPanel= new JPanel();
        this.popupFactory=new PopupFactory();
        this.secondaryPanel=new JPanel(new BorderLayout());
        
        
        
        
        JButton troop= new JButton("troops button");
        JButton playerinfo= new JButton("player info");
        
       

        
        this.mainpanel.add(troop);
        this.mainpanel.add(playerinfo);
        
         

        /*troop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                
                secondaryPanel.add(getInfo(size));
                *a way to close the panel on a second click 
                secondaryPanel.setVisible(false);
                popup= popupFactory.getPopup(troop, playerinfo, 10, 10);
            }
        });
        */
        window= new NewWindow(getInfo(size));
        troop.addActionListener(window);
        



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

    public JFrame windFrame(){
        final JFrame frame= new JFrame();
        popup = popupFactory.getPopup(secondaryPanel, getInfo(size), 10, 10);
        popup.show();
        JButton close = new JButton("close");
        secondaryPanel.add(close, BorderLayout.PAGE_END);
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a){
                System.exit(0);
            }
        });
        frame.getContentPane().add(secondaryPanel);
        frame.setVisible(true);
        return frame;

    }
}

    
