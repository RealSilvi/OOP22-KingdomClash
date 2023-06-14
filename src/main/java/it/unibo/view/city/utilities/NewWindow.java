package it.unibo.view.city.utilities;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

public class NewWindow implements ActionListener{

        final JFrame frame= new JFrame();

        public NewWindow(Component content, boolean visibility){
            
             frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
             frame.setSize(100, 100);
            frame.setVisible(visibility);
            frame.add(content);

            
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
        }

    
    }
