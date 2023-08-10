package it.unibo.view.menu;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.view.GameGui;
import it.unibo.view.utilities.BattlePanelStyle;
import it.unibo.view.utilities.ImageIconsSupplier;
import it.unibo.view.menu.extensiveclasses.ImageButton;
import it.unibo.view.menu.extensiveclasses.ImagePanel;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * The class implements methods of the interface.
 */
public final class GameMenuImpl implements GameMenu {

    /**
     * This enum it's used to give symbol to the buttons,
     * to help the gui and the controller in searching for buttons.
     */
    public enum BUTTONSMENU {
        /** The symbol represents the new game button.*/
        NEW_GAME("NEW GAME"),
        /** The symbol represents the load button.*/
        LOAD("LOAD"),
        /** The symbol represents the continue button.*/
        CONTINUE("CONTINUE"),
        /** The symbol represents the music button.*/
        MUSIC("MUSIC"),
        /** The symbol represents the info button.*/
        INFO("INFO"),
        /** The symbol represents the exit button.*/
        EXIT("EXIT");

        private final String name;

        BUTTONSMENU(final String name) {
            this.name = name;
        }

        /**
         * Gets the name of the symbol in the enum.
         * @return The name of the symbol.
         */
        public String getName() {
            return this.name;
        }

    }

    /** The distance between each button.*/
    public static final int BUTTONS_DISTANCE = GameGui.DIMENSION_SCREEN.height / 50;
    /** The increment used to enlarge the width.*/
    public static final double WIDTH_INCREMENT = 1.5;
    /** The increment used to enlarge the height.*/
    public static final double HEIGHT_INCREMENT = 1.2;
    /** The path of the image of the buttons.*/
    public static final String PATH_BUTTON = "/it/unibo/game.menu/wood.jpg";
    /** The path of the image of the panels.*/
    public static final String PATH_PANEL = "/it/unibo/game.menu/RvsH.jpg";
    /** The background image of the buttons.*/
    public static final ImageIcon BACKGROUND_BUTTON = ImageIconsSupplier.getScaledImageIcon(PATH_BUTTON,
            new Dimension((int) (GameGui.WIDTH_BUTTON * (WIDTH_INCREMENT * 2)),
                    (int) (GameGui.HEIGHT_BUTTON * (HEIGHT_INCREMENT * 2))));
    /** The background image of the panels.*/
    public static final ImageIcon BACKGROUND_PANEL = ImageIconsSupplier.getScaledImageIcon(PATH_PANEL, GameGui.DIMENSION_SCREEN);
    private final JPanel menuPanel;
    private final Map<BUTTONSMENU, JButton> buttons;

    /**
     * The constructor creates the panel and the buttons to create
     * the menu of the game.
     */
    public GameMenuImpl() {
        this.buttons = new HashMap<>();
        this.buttons.put(BUTTONSMENU.NEW_GAME, new ImageButton(BUTTONSMENU.NEW_GAME.name, BACKGROUND_BUTTON,
                new Dimension(BACKGROUND_BUTTON.getIconWidth(), BACKGROUND_BUTTON.getIconHeight())));
        this.buttons.put(BUTTONSMENU.LOAD, new ImageButton(BUTTONSMENU.LOAD.name, BACKGROUND_BUTTON,
                new Dimension(BACKGROUND_BUTTON.getIconWidth(), BACKGROUND_BUTTON.getIconHeight())));
        final ImageButton continues = new ImageButton(BUTTONSMENU.CONTINUE.name, BACKGROUND_BUTTON,
                new Dimension(BACKGROUND_BUTTON.getIconWidth(), BACKGROUND_BUTTON.getIconHeight()));
        continues.setVisible(false);
        this.buttons.put(BUTTONSMENU.CONTINUE, continues);
        this.buttons.put(BUTTONSMENU.MUSIC, new ImageButton(BUTTONSMENU.MUSIC.name, BACKGROUND_BUTTON,
                new Dimension(BACKGROUND_BUTTON.getIconWidth(), BACKGROUND_BUTTON.getIconHeight())));
        this.buttons.put(BUTTONSMENU.INFO, new ImageButton(BUTTONSMENU.INFO.name, BACKGROUND_BUTTON,
                new Dimension(BACKGROUND_BUTTON.getIconWidth(), BACKGROUND_BUTTON.getIconHeight())));
        this.buttons.put(BUTTONSMENU.EXIT, new ImageButton(BUTTONSMENU.EXIT.name, BACKGROUND_BUTTON,
                new Dimension(BACKGROUND_BUTTON.getIconWidth(), BACKGROUND_BUTTON.getIconHeight())));

        final Font font = BattlePanelStyle.getPrimaryFont();
        GridBagConstraints grid = new GridBagConstraints();

        this.menuPanel = new ImagePanel(BACKGROUND_PANEL.getImage());
        this.menuPanel.setLayout(new GridBagLayout());

        grid.gridx = 1;
        grid.gridy = 1;
        grid.insets = new Insets(BUTTONS_DISTANCE, 0, 0, 0);

        for (int i = 0; i < this.buttons.size(); i++) {
            this.buttons.get(BUTTONSMENU.values()[i]).setFont(font);
            this.buttons.get(BUTTONSMENU.values()[i]).setForeground(Color.BLACK);
            this.menuPanel.add(this.buttons.get(BUTTONSMENU.values()[i]), grid);
            grid.gridy += 1;
        }

    }

    @Override
    @SuppressFBWarnings(value = "EI",
            justification = "I need changes to the panel in its references, "
                    + "so I want to get the reference of the Object")
    public JPanel getPanel() {
        return this.menuPanel;
    }

    @Override
    public void setActionListenerContinue(final ActionListener actionListener) {
        this.buttons.get(BUTTONSMENU.CONTINUE).addActionListener(actionListener);
    }

    @Override
    public void setButtonsVisibilityMenu(final BUTTONSMENU name, final Boolean visibility) {
        this.buttons.get(name).setVisible(visibility);
    }

    @Override
    public void setActionListenerInfo(final ActionListener actionListener) {
        this.buttons.get(BUTTONSMENU.INFO).addActionListener(actionListener);
    }

    @Override
    public void setActionListenerNewGame(final ActionListener actionListener) {
        this.buttons.get(BUTTONSMENU.NEW_GAME).addActionListener(actionListener);
    }

    @Override
    public void setActionListenerLoad(final ActionListener actionListener) {
        this.buttons.get(BUTTONSMENU.LOAD).addActionListener(actionListener);
    }

    @Override
    public void setActionListenerMusic(final ActionListener actionListener) {
        this.buttons.get(BUTTONSMENU.MUSIC).addActionListener(actionListener);
    }

    @Override
    public void setActionListenerExit(final ActionListener actionListener) {
        this.buttons.get(BUTTONSMENU.EXIT).addActionListener(actionListener);
    }

}
