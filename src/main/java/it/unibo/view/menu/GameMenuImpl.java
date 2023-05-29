package it.unibo.view.menu;

import it.unibo.view.battle.panels.entities.DrawPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.image.ImageObserver;

public class GameMenuImpl implements GameMenu{

    public static final Dimension DIMENSION_SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int WIDTH_BUTTON = (int)DIMENSION_SCREEN.getWidth()/20;
    private static final int HEIGHT_BUTTON = (int)DIMENSION_SCREEN.getHeight()/20;
    ImageIcon BACKGROUND_BUTTON = new ImageIcon("C:\\Users\\llafa\\OneDrive\\Documenti\\My Games\\Immagini\\Immagini\\Saved Pictures\\legno massello sfondo.jpg");
    ImageIcon BACKGROUND_PANEL = new ImageIcon("C:\\Users\\llafa\\OneDrive\\Documenti\\My Games\\Immagini\\Immagini\\Saved Pictures\\Robot vs human.jpg");

    public GameMenuImpl(){
        JFrame frame = new JFrame("menu game");
        frame.setSize((int) DIMENSION_SCREEN.getWidth(), (int) DIMENSION_SCREEN.getHeight());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        //frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        Font font = new Font("font", Font.ITALIC,((WIDTH_BUTTON)-(HEIGHT_BUTTON)));
        ex.setFont(font);
        GridBagConstraints grid = new GridBagConstraints();

        JPanel panel = new ImagePanel(BACKGROUND_PANEL.getImage());
        panel.setLayout(new GridBagLayout());

        grid.gridx = 1;
        grid.gridy = 1;
        grid.ipadx = (WIDTH_BUTTON);
        grid.ipady = (HEIGHT_BUTTON);
        grid.insets = new Insets(30, 0, 0, 0);

        new_game.setFont(font);
        new_game.setPreferredSize(ex.getPreferredSize());
        new_game.setForeground(Color.BLACK);
        panel.add(new_game,grid);

        grid.gridy = 2;
        load.setFont(font);
        load.setPreferredSize(ex.getPreferredSize());
        load.setForeground(Color.BLACK);
        panel.add(load,grid);

        grid.gridy = 3;
        options.setFont(font);
        options.setPreferredSize(ex.getPreferredSize());
        options.setForeground(Color.BLACK);
        panel.add(options,grid);

        frame.getContentPane().add(panel);

        frame.setVisible(true);

    }

    public static void main(final String... args) {
        new GameMenuImpl();

    }

}
