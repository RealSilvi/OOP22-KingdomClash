package it.unibo.view.menu;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.view.GameGui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

public class SouthPanel {

    public static final Dimension DIMENSION_SCREEN = GameGui.DIMENSION_SCREEN;
    private static final double MENU_WIDTH_SCALE = 1;
    private static final double MENU_HEIGHT_SCALE = 0.05;
    private final JPanel southPanel;
    private final JButton cityButton;
    private final JButton mapButton;
    private final JButton menuButton;
    private final JButton musicButton;
    private final JButton exitButton;
    private final JButton saveButton;

    public SouthPanel(){
        this.saveButton = new JButton("SAVE");
        this.menuButton = new JButton("MENU");
        this.musicButton = new JButton("MUSIC");
        this.cityButton = new JButton("CITY");
        this.mapButton = new JButton("MAP");
        this.exitButton = new JButton("EXIT");

        this.southPanel = new JPanel();
        this.southPanel.setBackground(Color.blue);
        this.southPanel.setPreferredSize(getMenuPanel());
        this.southPanel.add(this.musicButton);
        this.southPanel.add(this.menuButton);
        this.southPanel.add(this.cityButton);
        this.southPanel.add(this.mapButton);
        this.southPanel.add(this.saveButton);
        this.southPanel.add(this.exitButton);
    }

    @SuppressFBWarnings(value = "EI",
            justification = "I need changes to the panel in its references")
    public JPanel getPanel(){
        return this.southPanel;
    }

    public static Dimension getMenuPanel() {
        return new Dimension(
                (int) (DIMENSION_SCREEN.getWidth() * MENU_WIDTH_SCALE),
                (int) (DIMENSION_SCREEN.getHeight() * MENU_HEIGHT_SCALE));
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
    public void setActionListenerExit(ActionListener actionListener){
        this.exitButton.addActionListener(actionListener);
    }
    public void setActionListenerSave(ActionListener actionListener){
        this.saveButton.addActionListener(actionListener);
    }

    public void showButtonsMap(){
        this.menuButton.setVisible(true);
        this.cityButton.setVisible(true);
        this.mapButton.setVisible(false);
        this.musicButton.setVisible(true);
        this.saveButton.setVisible(true);
    }

    public void showButtonsCity(){
        this.menuButton.setVisible(true);
        this.cityButton.setVisible(false);
        this.mapButton.setVisible(true);
        this.musicButton.setVisible(true);
        this.saveButton.setVisible(true);
    }

    public void showButtonsBattle(){
        this.menuButton.setVisible(false);
        this.cityButton.setVisible(false);
        this.mapButton.setVisible(false);
        this.musicButton.setVisible(true);
        this.saveButton.setVisible(false);
    }

}
