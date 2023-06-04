package it.unibo.view.city.panels.impl;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.*;
import javax.swing.*;


import it.unibo.model.data.GameData;
import it.unibo.model.data.Resource;
import it.unibo.model.data.Resource.ResourceType;
import it.unibo.view.battle.Troop;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;
import it.unibo.view.city.panels.api.BarPanel;

public class BarPanelImpl extends JLabel implements BarPanel {

    private final JPanel mainpanel;
    Troop baseTroop;
    GameData gameData;

    public BarPanelImpl(Set<Resource> resources, Set<Troop> type){
        this.mainpanel=new DrawPanel(ImageIconsSupplier.BACKGROUND_FILL_PATTERN, getSize());
        this.gameData= new GameData();

        this.mainpanel.setLayout(new FlowLayout());

        JButton troop= new JButton("troops button");
        JButton playerinfo= new JButton("player info");
        JButton switchbutton = new JButton("change screen");

        this.mainpanel.add(troop);
        this.mainpanel.add(playerinfo);
        this.mainpanel.add(switchbutton);




    }
    
    


    

    @Override
    public void switchbutton() {
        /*classe da implementare a sè */
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'switchbutton'");
    }
    private JTextArea getInfo( Dimension size) {
        
        JTextArea info = new JTextArea();
        info.setSize(size);
        for (Troop itera : Troop.values()) {
            /*info.append("\n"+itera.toString());*/
            info.append("Name:\n" +itera.name()+ "Level:"+itera.getLevel());
        }
        info.setEditable(false);

        return info;

    }

    /*devo riportare una lista delle truppe con il loro nome e il livello */
    @Override
    public void getTroopInfo(Set<Troop> type) {

        JTextArea info = new JTextArea();
        info.setSize(new Dimension(5, 5));
        /*rendere modificabile la lista(renderla una list?, conviene??)
        type = EnumSet.allOf(Troop.class).forEach(Troop -> System.out.println(""+Troop.name() +""+Troop.getLevel()));*/
        type = EnumSet.allOf(Troop.class);

       Iterator troopsiterator = type.iterator();
       while (troopsiterator.hasNext()) {

        info.append(""+troopsiterator.toString());
        System.out.println(troopsiterator);
        troopsiterator.next();
        
       }


       
    }

    @Override
    public void getPlayerInfo( GameData gamedata) {
        System.out.println(""+gamedata.getPlayerName());
        /*aggiungere il tempo trascorso?? */

    }
    
    private JPanel getPanel(){
        return this.mainpanel;
    }
}
