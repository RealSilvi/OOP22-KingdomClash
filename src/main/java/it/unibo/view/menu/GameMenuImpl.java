package it.unibo.view.menu;

import it.unibo.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameMenuImpl implements GameMenu {

    private static final int WIDTH_BUTTON = View.WIDTH_BUTTON;
    private static final int HEIGHT_BUTTON = View.HEIGHT_BUTTON;
    public static final ImageIcon BACKGROUND_BUTTON = new ImageIcon("src/main/resources/it/unibo/game.menu/wood.jpg");
    public static final ImageIcon BACKGROUND_PANEL = new ImageIcon("src/main/resources/it/unibo/game.menu/RvsH.jpg");
    private final JPanel menuPanel;
    private final JButton info;

    public GameMenuImpl() {
        JButton ex = new JButton("NEW GAME");
        ex.setHorizontalTextPosition(SwingConstants.CENTER);
        ex.setFocusable(false);
        JButton new_game = new JButton("NEW GAME", BACKGROUND_BUTTON);
        new_game.setHorizontalTextPosition(SwingConstants.CENTER);
        new_game.setFocusable(false);
        JButton load = new JButton("LOAD", BACKGROUND_BUTTON);
        load.setHorizontalTextPosition(SwingConstants.CENTER);
        load.setFocusable(false);
        JButton options = new JButton("OPTIONS", BACKGROUND_BUTTON);
        options.setHorizontalTextPosition(SwingConstants.CENTER);
        options.setFocusable(false);
        this.info = new JButton("INFO", BACKGROUND_BUTTON);
        info.setHorizontalTextPosition(SwingConstants.CENTER);
        info.setFocusable(false);

        Font font = new Font("font", Font.ITALIC, ((WIDTH_BUTTON) - (HEIGHT_BUTTON)));
        ex.setFont(font);
        GridBagConstraints grid = new GridBagConstraints();

        this.menuPanel = new ImagePanel(BACKGROUND_PANEL.getImage());
        this.menuPanel.setLayout(new GridBagLayout());

        grid.gridx = 1;
        grid.gridy = 1;
        grid.ipadx = (WIDTH_BUTTON);
        grid.ipady = (HEIGHT_BUTTON);
        grid.insets = new Insets(30, 0, 0, 0);

        new_game.setFont(font);
        new_game.setPreferredSize(ex.getPreferredSize());
        new_game.setForeground(Color.BLACK);
        menuPanel.add(new_game, grid);

        grid.gridy = 2;
        load.setFont(font);
        load.setPreferredSize(ex.getPreferredSize());
        load.setForeground(Color.BLACK);
        menuPanel.add(load, grid);

        grid.gridy = 3;
        options.setFont(font);
        options.setPreferredSize(ex.getPreferredSize());
        options.setForeground(Color.BLACK);
        menuPanel.add(options, grid);

        grid.gridy = 4;
        info.setFont(font);
        info.setPreferredSize(ex.getPreferredSize());
        info.setForeground(Color.BLACK);
        menuPanel.add(info, grid);

    }

    public JPanel getPanel(){
        return this.menuPanel;
    }

    public void setActionListenerInfo(ActionListener actionListener){
        this.info.addActionListener(actionListener);
    }

}
