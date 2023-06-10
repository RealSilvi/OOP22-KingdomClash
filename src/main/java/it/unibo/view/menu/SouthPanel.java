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
    private JButton menuButton;
    private JButton musicButton;

    public SouthPanel(){
        this.battleButton = new JButton("BATTLE");
        this.menuButton = new JButton("MENU");
        this.musicButton = new JButton("MUSIC");
        this.battleButton.setVisible(true);
        this.cityButton = new JButton("CITY");
        this.mapButton = new JButton("MAP");

        this.southPanel = new JPanel();
        this.southPanel.setBackground(Color.blue);
        this.southPanel.setPreferredSize(getMenuPanel());
        this.southPanel.add(this.battleButton);
        this.southPanel.add(this.musicButton);
        this.southPanel.add(this.menuButton);
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
        this.battleButton.addActionListener(actionListener);
    }
    public void setActionListenerMusic(ActionListener actionListener){
        this.musicButton.addActionListener(actionListener);
    }
    public void setActionListenerMenu(ActionListener actionListener){
        this.menuButton.addActionListener(actionListener);
    }
    public void setActionListenerCity(ActionListener actionListener){
        this.cityButton.addActionListener(actionListener);
    }
    public void setActionListenerMap(ActionListener actionListener){
        this.mapButton.addActionListener(actionListener);
    }

    public void showButtonsMap(){
        this.menuButton.setVisible(true);
        this.cityButton.setVisible(true);
        this.mapButton.setVisible(false);
        this.musicButton.setVisible(true);
    }

    public void showButtonsCity(){
        this.menuButton.setVisible(true);
        this.cityButton.setVisible(false);
        this.mapButton.setVisible(true);
        this.musicButton.setVisible(true);
    }

    public void showButtonsBattle(){
        this.menuButton.setVisible(false);
        this.cityButton.setVisible(false);
        this.mapButton.setVisible(false);
        this.musicButton.setVisible(true);
    }

}
