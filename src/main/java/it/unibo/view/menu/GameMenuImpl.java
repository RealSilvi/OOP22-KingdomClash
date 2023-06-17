package it.unibo.view.menu;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.view.GameGui;
import it.unibo.view.utilities.BattlePanelStyle;
import it.unibo.view.utilities.ImageIconsSupplier;
import it.unibo.view.menu.extensiveclasses.ImageButton;
import it.unibo.view.menu.extensiveclasses.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameMenuImpl implements GameMenu {

    private static final int WIDTH_BUTTON = GameGui.WIDTH_BUTTON;
    private static final int HEIGHT_BUTTON = GameGui.HEIGHT_BUTTON;
    public static final double WIDTH_INCREMENT = 1.5;
    public static final double HEIGHT_INCREMENT = 1.2;
    public static final String PATH_BUTTON = "/it/unibo/game.menu/wood.jpg";
    public static final String PATH_PANEL = "/it/unibo/game.menu/RvsH.jpg";
    public static final ImageIcon BACKGROUND_BUTTON = ImageIconsSupplier.getScaledImageIcon(PATH_BUTTON,
            new Dimension((int) (WIDTH_BUTTON * (WIDTH_INCREMENT * 2)), (int) (HEIGHT_BUTTON * (HEIGHT_INCREMENT * 2))));
    public static final ImageIcon BACKGROUND_PANEL = ImageIconsSupplier.getScaledImageIcon(PATH_PANEL, GameGui.DIMENSION_SCREEN);
    private final JPanel menuPanel;
    private final ImageButton info;
    private final ImageButton exit;
    private final ImageButton new_game;
    private final ImageButton music;
    private final ImageButton load;

    public GameMenuImpl() {
        this.new_game = new ImageButton("NEW GAME", BACKGROUND_BUTTON, new Dimension(BACKGROUND_BUTTON.getIconWidth(), BACKGROUND_BUTTON.getIconHeight()));
        this.load = new ImageButton("LOAD", BACKGROUND_BUTTON, new Dimension(BACKGROUND_BUTTON.getIconWidth(), BACKGROUND_BUTTON.getIconHeight()));
        this.music = new ImageButton("MUSIC", BACKGROUND_BUTTON, new Dimension(BACKGROUND_BUTTON.getIconWidth(), BACKGROUND_BUTTON.getIconHeight()));
        this.info = new ImageButton("INFO", BACKGROUND_BUTTON, new Dimension(BACKGROUND_BUTTON.getIconWidth(), BACKGROUND_BUTTON.getIconHeight()));
        this.exit = new ImageButton("EXIT", BACKGROUND_BUTTON, new Dimension(BACKGROUND_BUTTON.getIconWidth(), BACKGROUND_BUTTON.getIconHeight()));

        Font font = BattlePanelStyle.getPrimaryFont();
        GridBagConstraints grid = new GridBagConstraints();

        this.menuPanel = new ImagePanel(BACKGROUND_PANEL.getImage());
        this.menuPanel.setLayout(new GridBagLayout());

        grid.gridx = 1;
        grid.gridy = 1;
        grid.insets = new Insets(GameGui.DIMENSION_SCREEN.height/50, 0, 0, 0);

        new_game.setFont(font);
        new_game.setForeground(Color.BLACK);
        menuPanel.add(new_game, grid);

        grid.gridy = 2;
        load.setFont(font);
        load.setForeground(Color.BLACK);
        menuPanel.add(load, grid);

        grid.gridy = 3;
        music.setFont(font);
        music.setForeground(Color.BLACK);
        menuPanel.add(music, grid);

        grid.gridy = 4;
        info.setFont(font);
        info.setForeground(Color.BLACK);
        menuPanel.add(info, grid);

        grid.gridy = 5;
        exit.setFont(font);
        exit.setForeground(Color.BLACK);
        menuPanel.add(exit, grid);

    }

    @SuppressFBWarnings(value = "EI",
            justification = "I need changes to the panel in its references, " +
                    "so I want to get the reference of the Object")
    public JPanel getPanel(){
        return this.menuPanel;
    }

    @Override
    public void setActionListenerInfo(ActionListener actionListener){
        this.info.addActionListener(actionListener);
    }

    @Override
    public void setActionListenerNewGame(ActionListener actionListener){
        this.new_game.addActionListener(actionListener);
    }

    @Override
    public void setActionListenerLoad(ActionListener actionListener){
        this.load.addActionListener(actionListener);
    }

    @Override
    public void setActionListenerMusic(ActionListener actionListener){
        this.music.addActionListener(actionListener);
    }

    @Override
    public void setActionListenerExit(ActionListener actionListener){
        this.exit.addActionListener(actionListener);
    }

}
