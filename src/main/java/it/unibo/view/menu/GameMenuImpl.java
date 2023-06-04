package it.unibo.view.menu;

import javax.swing.*;
import java.awt.*;

public class GameMenuImpl implements GameMenu {

    public static final Dimension DIMENSION_SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int WIDTH_BUTTON = (int) DIMENSION_SCREEN.getWidth() / 20;
    private static final int HEIGHT_BUTTON = (int) DIMENSION_SCREEN.getHeight() / 20;
    ImageIcon BACKGROUND_BUTTON = new ImageIcon("src/main/resources/it/unibo/game.menu/wood.jpg");
    ImageIcon BACKGROUND_PANEL = new ImageIcon("src/main/resources/it/unibo/game.menu/RvsH.jpg");

    public GameMenuImpl() {
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
        JButton info = new JButton("INFO", BACKGROUND_BUTTON);
        info.setHorizontalTextPosition(SwingConstants.CENTER);
        info.setFocusable(false);

        Font font = new Font("font", Font.ITALIC, ((WIDTH_BUTTON) - (HEIGHT_BUTTON)));
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
        panel.add(new_game, grid);

        grid.gridy = 2;
        load.setFont(font);
        load.setPreferredSize(ex.getPreferredSize());
        load.setForeground(Color.BLACK);
        panel.add(load, grid);

        grid.gridy = 3;
        options.setFont(font);
        options.setPreferredSize(ex.getPreferredSize());
        options.setForeground(Color.BLACK);
        panel.add(options, grid);

        grid.gridy = 4;
        info.setFont(font);
        info.setPreferredSize(ex.getPreferredSize());
        info.setForeground(Color.BLACK);
        panel.add(info, grid);

        info.addActionListener(e -> {
            JPanel infopanel = new ImagePanel(BACKGROUND_PANEL.getImage());
            infopanel.setLayout(new GridBagLayout());
            GridBagConstraints grid1 = new GridBagConstraints();
            grid1.gridx = 1;
            grid1.gridy = 1;
            grid1.ipadx = (WIDTH_BUTTON*10);
            grid1.ipady = (HEIGHT_BUTTON*10);
            grid1.insets = new Insets(30, 0, 0, 0);

            Font font2 = new Font("font", Font.ITALIC, ((WIDTH_BUTTON) - (HEIGHT_BUTTON))/2);

            ImageTextArea label = new ImageTextArea();
            label.setImage(BACKGROUND_BUTTON.getImage());
            label.setFont(font2);
            label.setWrapStyleWord(true);
            label.setLineWrap(true);
            label.setText("ciaooooooooooooooooooooooooooooooooooooo");
            label.setEditable(false);
            label.setForeground(Color.BLACK);
            label.setPreferredSize(ex.getPreferredSize());
            label.setFocusable(false);
            infopanel.add(label, grid1);

            JButton exit = new JButton("EXIT", BACKGROUND_BUTTON);
            exit.setHorizontalTextPosition(SwingConstants.CENTER);
            exit.setFocusable(false);
            grid1.gridy = 2;
            grid1.ipadx = (WIDTH_BUTTON);
            grid1.ipady = (HEIGHT_BUTTON);
            exit.setFont(font);
            exit.setPreferredSize(ex.getPreferredSize());
            exit.setForeground(Color.BLACK);
            infopanel.add(exit, grid1);

            exit.addActionListener(e1 -> {
                frame.setContentPane(panel);
                frame.setVisible(true);
                frame.revalidate();
            });

            frame.setContentPane(infopanel);
            frame.setVisible(true);
            frame.revalidate();
        });

        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.revalidate();

    }

    public static void main(final String... args) {
        new GameMenuImpl();

    }

}
