package it.unibo.view.menu;

import it.unibo.view.GameGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameMenuImpl implements GameMenu {

    private static final int WIDTH_BUTTON = GameGui.WIDTH_BUTTON;
    private static final int HEIGHT_BUTTON = GameGui.HEIGHT_BUTTON;
    public static final ImageIcon BACKGROUND_BUTTON = new ImageIcon("src/main/resources/it/unibo/game.menu/wood.jpg");
    public static final ImageIcon BACKGROUND_PANEL = new ImageIcon("src/main/resources/it/unibo/game.menu/RvsH.jpg");
    private final JPanel menuPanel;
    private final ImageButton info;
    private final ImageButton new_game;

    public GameMenuImpl() {
        this.new_game = new ImageButton("NEW GAME", BACKGROUND_BUTTON, new Dimension(WIDTH_BUTTON*2,(int)(HEIGHT_BUTTON*1.5)));
        ImageButton load = new ImageButton("LOAD", BACKGROUND_BUTTON, new Dimension(WIDTH_BUTTON*2,(int)(HEIGHT_BUTTON*1.5)));
        ImageButton options = new ImageButton("OPTIONS", BACKGROUND_BUTTON, new Dimension(WIDTH_BUTTON*2,(int)(HEIGHT_BUTTON*1.5)));
        this.info = new ImageButton("INFO", BACKGROUND_BUTTON, new Dimension(WIDTH_BUTTON*2,(int)(HEIGHT_BUTTON*1.5)));

        Font font = new Font("font", Font.ITALIC, ((WIDTH_BUTTON) - (HEIGHT_BUTTON)));
        GridBagConstraints grid = new GridBagConstraints();

        this.menuPanel = new ImagePanel(BACKGROUND_PANEL.getImage());
        this.menuPanel.setLayout(new GridBagLayout());

        grid.gridx = 1;
        grid.gridy = 1;
        grid.ipadx = (WIDTH_BUTTON);
        grid.ipady = (HEIGHT_BUTTON);
        grid.insets = new Insets(30, 0, 0, 0);

        new_game.setFont(font);
        new_game.setForeground(Color.BLACK);
        menuPanel.add(new_game, grid);

        grid.gridy = 2;
        load.setFont(font);
        load.setForeground(Color.BLACK);
        menuPanel.add(load, grid);

        grid.gridy = 3;
        options.setFont(font);
        options.setForeground(Color.BLACK);
        menuPanel.add(options, grid);

        grid.gridy = 4;
        info.setFont(font);
        info.setForeground(Color.BLACK);
        menuPanel.add(info, grid);

    }

    public JPanel getPanel(){
        return this.menuPanel;
    }

    public void setActionListenerInfo(ActionListener actionListener){
        this.info.addActionListener(actionListener);
    }

    public void setActionListenerNewGame(ActionListener actionListener){
        this.new_game.addActionListener(actionListener);
    }

}
