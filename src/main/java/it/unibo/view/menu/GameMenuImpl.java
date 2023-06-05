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
        this.new_game = new ImageButton();
        ImageButton load = new ImageButton();
        ImageButton options = new ImageButton();
        this.info = new ImageButton();

        new_game.setText("NEW GAME");
        new_game.setHorizontalTextPosition(SwingConstants.CENTER);
        new_game.setFocusable(false);

        new_game.setImage(BACKGROUND_BUTTON.getImage());
        load.setImage(BACKGROUND_BUTTON.getImage());
        options.setImage(BACKGROUND_BUTTON.getImage());
        this.info.setImage(BACKGROUND_BUTTON.getImage());


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
        load.setPreferredSize(new_game.getPreferredSize());
        load.setForeground(Color.BLACK);
        menuPanel.add(load, grid);

        grid.gridy = 3;
        options.setFont(font);
        options.setPreferredSize(new_game.getPreferredSize());
        options.setForeground(Color.BLACK);
        menuPanel.add(options, grid);

        grid.gridy = 4;
        info.setFont(font);
        info.setPreferredSize(new_game.getPreferredSize());
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
