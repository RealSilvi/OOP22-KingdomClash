package it.unibo.view.menu;

import it.unibo.view.GameGui;

import javax.swing.*;

import java.awt.*;

public class SouthPanel {

    public static final Dimension DIMENSION_SCREEN = GameGui.DIMENSION_SCREEN;
    private static final double MENU_WIDTH_SCALE = 1;
    private static final double MENU_HEIGHT_SCALE = 0.05;
    private JPanel southPanel;
    public SouthPanel(){
        this.southPanel = new JPanel();
        this.southPanel.setBackground(Color.blue);
        this.southPanel.setPreferredSize(getMenuPanel());
    }

    public JPanel getPanel(){
        return this.southPanel;
    }

    public static Dimension getMenuPanel() {
        return new Dimension(
                (int) (DIMENSION_SCREEN.getWidth() * MENU_WIDTH_SCALE),
                (int) (DIMENSION_SCREEN.getHeight() * MENU_HEIGHT_SCALE));
    }

}
