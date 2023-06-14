package it.unibo.view.city.panels.impl;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import it.unibo.view.GameGui;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.city.panels.api.BarPanel;
import it.unibo.view.city.utilities.NewWindow;
import it.unibo.controller.base.BaseController;
import it.unibo.controller.base.BaseControllerImpl;
public class BarPanelImpl extends JLabel implements BarPanel {

    private static final Dimension size=new Dimension((int)(GameGui.getAllPanel().getWidth()),
        (int)(GameGui.getAllPanel().getHeight()*0.1));
    private final JPanel mainpanel;
    private BaseController basedata;
    private NewWindow window;
    
    

    public BarPanelImpl(BaseController controller){
        this.mainpanel=new DrawPanel(Color.BLACK, size);
        this.basedata=controller;
        
        //this.isclicked= false;
        
        
        
        
        JButton troop= new JButton("troops button");
        JButton playerinfo= new JButton("player info");
        JButton button = new JButton("Show Popup");

        button.addActionListener(e -> {
            JOptionPane.showMessageDialog(mainpanel, "You can't do this", "ERROR", JOptionPane.ERROR_MESSAGE);
        });
        
       

        
        this.mainpanel.add(troop);
        this.mainpanel.add(playerinfo);
        this.mainpanel.add(button);
        
        
        troop.addActionListener(new ActionListener() {
            private boolean isOpen = false;
            @Override
            public void actionPerformed(ActionEvent arg0) {
                window= new NewWindow(getInfo(size),isOpen);
                troop.addActionListener(window);
                isOpen= !isOpen;
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

    /*public JFrame windFrame(){
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

    }*/
}

    
