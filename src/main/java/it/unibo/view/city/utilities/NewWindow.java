package it.unibo.view.city.utilities;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

public class NewWindow {

        final JFrame frame= new JFrame();
        boolean visibility;

        public NewWindow(Component content){
            
            this.visibility = false;
            frame.setSize(500, 500);
            frame.setVisible(visibility);
            frame.add(content);

            
        }

       public void changeVisibility() {
        this.visibility = !this.visibility;
       }

    
    }
