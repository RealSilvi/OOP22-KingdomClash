package it.unibo.view.menu;

import it.unibo.view.GameGui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

public class SouthPanel {

    public static final Dimension DIMENSION_SCREEN = GameGui.DIMENSION_SCREEN;
    private static final double MENU_WIDTH_SCALE = 1;
    private static final double MENU_HEIGHT_SCALE = 0.05;
    private JPanel southPanel;
    private JButton battleButton;
    private JButton cityButton;
    private JButton mapButton;
    private JButton lastSelected;

    public SouthPanel(){
        this.lastSelected = new JButton();
        this.battleButton = new JButton("BATTLE");
        this.battleButton.setVisible(false);
        this.cityButton = new JButton("CITY");
        this.mapButton = new JButton("MAP");

        this.southPanel = new JPanel();
        this.southPanel.setBackground(Color.blue);
        this.southPanel.setPreferredSize(getMenuPanel());
        this.southPanel.add(this.battleButton);
        this.southPanel.add(this.cityButton);
        this.southPanel.add(this.mapButton);
    }

    public JPanel getPanel(){
        return this.southPanel;
    }

    public static Dimension getMenuPanel() {
        return new Dimension(
                (int) (DIMENSION_SCREEN.getWidth() * MENU_WIDTH_SCALE),
                (int) (DIMENSION_SCREEN.getHeight() * MENU_HEIGHT_SCALE));
    }

    public void setActionListenerBattle(ActionListener actionListener){
        lastSelected(this.battleButton);
        this.battleButton.addActionListener(actionListener);
        //this.southPanel.remove(this.battleButton);
        //this.southPanel.revalidate();
        System.out.println("dasfasdasddsa");
    }
    public void setActionListenerCity(ActionListener actionListener){
        this.southPanel.add(this.lastSelected);
        lastSelected(this.cityButton);
        this.cityButton.addActionListener(actionListener);
        this.southPanel.remove(this.cityButton);
        //this.southPanel.revalidate();
        System.out.println("dasfasdasddsa");
    }
    public void setActionListenerMap(ActionListener actionListener){
        this.southPanel.add(this.lastSelected);
        lastSelected(this.mapButton);
        this.mapButton.addActionListener(actionListener);
        this.southPanel.remove(this.mapButton);
        //this.southPanel.revalidate();
    }

    public void lastSelected(JButton lastButton){
        this.lastSelected = lastButton;
    }

}
