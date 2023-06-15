package it.unibo.view.city.utilities;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import it.unibo.controller.base.BaseController;

public class TroopPopupPanel {
    private Popup popup;
    private final int width;
    private final int height;
    private JPanel contentpanel;
    private boolean visibility;
    private Component container;
    private int xposition;
    private int yposition;

    


    public TroopPopupPanel(Component container, int xposition,int yposition, BaseController data) {
        this.width = 500;
        this.height = 500;
        this.visibility = false;
        this.container = container;
        this.xposition = xposition;
        this.yposition = yposition;

        this.contentpanel = new JPanel();
        this.contentpanel.add(new JLabel("ResourcePopupPanel"));
        this.contentpanel.setLayout(new BoxLayout(contentpanel, BoxLayout.Y_AXIS));
        this.contentpanel.setPreferredSize(new Dimension(this.width, this.height));
        this.popup = new PopupFactory().getPopup(container, contentpanel, xposition, yposition);
       
       
        
       data.requestTroopLevels().keySet().stream().forEach(
            singletroop -> {
               /*JPanel containpanel = new JPanel(new BoxLayout(contentpanel, BoxLayout.PAGE_AXIS)); */ 
                JPanel containpanel = new JPanel();
                containpanel.setLayout(new BorderLayout());

                JLabel label= new JLabel(
                ""+singletroop.name()+""+data.requestTroopLevels().get(singletroop));
                containpanel.add(label,BorderLayout.LINE_START);
                var buttonOK = new JButton("upgrade");
                containpanel.add(buttonOK, BorderLayout.LINE_END);
                
                buttonOK.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                       data.upgradeTroop(singletroop,
                        data.requestTroopLevels().get(singletroop)+1);

                       System.out.println("ciao");
                    }
                    
                });
                contentpanel.add(containpanel);
                
                
                 
                


            });
             
        
       
        
        

     }
   

    public void changeVisibility(){
        this.visibility = !this.visibility;
        if(visibility) {
            popup.show();
        } else {
            popup.hide();
            this.popup = new PopupFactory().getPopup(container, contentpanel, xposition, yposition);
        }
    }

    public void dispose() {
        popup.hide();
    }
}
