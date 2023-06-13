package it.unibo.view.city;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class NewWindow implements ActionListener{

        final JFrame frame= new JFrame();
        

        public NewWindow(Component content){
            
             frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
             frame.setSize(100, 100);
            frame.setVisible(true);
            frame.add(content);
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
        }

    
    }
