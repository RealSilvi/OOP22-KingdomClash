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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GameMenuImpl implements GameMenu {

    public enum BUTTONS_MENU {
        NEW_GAME("NEW GAME"),
        LOAD("LOAD"),
        CONTINUE("CONTINUE"),
        MUSIC("MUSIC"),
        INFO("INFO"),
        EXIT("EXIT");

        private final String name;

        BUTTONS_MENU(final String name) {
            this.name = name;
        }

        public String getName(){
            return this.name;
        }

    }

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
    private final Map<BUTTONS_MENU, JButton> buttons;

    public GameMenuImpl() {
        this.buttons = new HashMap<>();
        this.buttons.put(BUTTONS_MENU.NEW_GAME, new ImageButton(BUTTONS_MENU.NEW_GAME.name, BACKGROUND_BUTTON,
                new Dimension(BACKGROUND_BUTTON.getIconWidth(), BACKGROUND_BUTTON.getIconHeight())));
        this.buttons.put(BUTTONS_MENU.LOAD, new ImageButton(BUTTONS_MENU.LOAD.name, BACKGROUND_BUTTON,
                new Dimension(BACKGROUND_BUTTON.getIconWidth(), BACKGROUND_BUTTON.getIconHeight())));
        ImageButton continues = new ImageButton(BUTTONS_MENU.CONTINUE.name, BACKGROUND_BUTTON,
                new Dimension(BACKGROUND_BUTTON.getIconWidth(), BACKGROUND_BUTTON.getIconHeight()));
        continues.setVisible(false);
        this.buttons.put(BUTTONS_MENU.CONTINUE, continues);
        this.buttons.put(BUTTONS_MENU.MUSIC, new ImageButton(BUTTONS_MENU.MUSIC.name, BACKGROUND_BUTTON,
                new Dimension(BACKGROUND_BUTTON.getIconWidth(), BACKGROUND_BUTTON.getIconHeight())));
        this.buttons.put(BUTTONS_MENU.INFO, new ImageButton(BUTTONS_MENU.INFO.name, BACKGROUND_BUTTON,
                new Dimension(BACKGROUND_BUTTON.getIconWidth(), BACKGROUND_BUTTON.getIconHeight())));
        this.buttons.put(BUTTONS_MENU.EXIT, new ImageButton(BUTTONS_MENU.EXIT.name, BACKGROUND_BUTTON,
                new Dimension(BACKGROUND_BUTTON.getIconWidth(), BACKGROUND_BUTTON.getIconHeight())));

        Font font = BattlePanelStyle.getPrimaryFont();
        GridBagConstraints grid = new GridBagConstraints();

        this.menuPanel = new ImagePanel(BACKGROUND_PANEL.getImage());
        this.menuPanel.setLayout(new GridBagLayout());

        grid.gridx = 1;
        grid.gridy = 1;
        grid.insets = new Insets(GameGui.DIMENSION_SCREEN.height/50, 0, 0, 0);

        for(int i = 0; i < this.buttons.size(); i++){
            this.buttons.get(BUTTONS_MENU.values()[i]).setFont(font);
            this.buttons.get(BUTTONS_MENU.values()[i]).setForeground(Color.BLACK);
            this.menuPanel.add(this.buttons.get(BUTTONS_MENU.values()[i]), grid);
            grid.gridy += 1;
        }

    }

    @SuppressFBWarnings(value = "EI",
            justification = "I need changes to the panel in its references, " +
                    "so I want to get the reference of the Object")
    public JPanel getPanel(){
        return this.menuPanel;
    }

    @Override
    public void setActionListenerContinue(ActionListener actionListener) {
        this.buttons.get(BUTTONS_MENU.CONTINUE).addActionListener(actionListener);
    }

    @Override
    public void setButtonsVisibilityMenu(BUTTONS_MENU name, Boolean visibility) {
        this.buttons.get(name).setVisible(visibility);
    }

    @Override
    public void setActionListenerInfo(ActionListener actionListener) {
        this.buttons.get(BUTTONS_MENU.INFO).addActionListener(actionListener);
    }

    @Override
    public void setActionListenerNewGame(ActionListener actionListener) {
        this.buttons.get(BUTTONS_MENU.NEW_GAME).addActionListener(actionListener);
    }

    @Override
    public void setActionListenerLoad(ActionListener actionListener) {
        this.buttons.get(BUTTONS_MENU.LOAD).addActionListener(actionListener);
    }

    @Override
    public void setActionListenerMusic(ActionListener actionListener) {
        this.buttons.get(BUTTONS_MENU.MUSIC).addActionListener(actionListener);
    }

    @Override
    public void setActionListenerExit(ActionListener actionListener){
        this.buttons.get(BUTTONS_MENU.EXIT).addActionListener(actionListener);
    }

}
