package it.unibo.view.menu;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.view.GameGui;
import it.unibo.view.menu.extensiveclasses.ImageButton;
import it.unibo.view.utilities.BattlePanelStyle;
import it.unibo.view.utilities.ImageIconsSupplier;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * The class creates a new south panel in each panel of the game
 * (except of the menu).
 */
public final class SouthPanel {

    /**
     * This enum it's used to give symbol to the buttons,
     * to help the gui and the controller in searching for buttons.
     */
    public enum BUTTONSSOUTH {
        /** The symbol represents the music button.*/
        MUSIC("MUSIC"),
        /** The symbol represents the menu button.*/
        MENU("MENU"),
        /** The symbol represents the save button.*/
        SAVE("SAVE"),
        /** The symbol represents the quit button.*/
        QUIT("QUIT");

        private final String name;

        BUTTONSSOUTH(final String name) {
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

    /** Used to increment the width of a dimension.*/
    private static final double MENU_WIDTH_SCALE = 1;
    /** Used to increment the height of a dimension.*/
    private static final double MENU_HEIGHT_SCALE = 0.05;
    /** The dimension of the buttons.*/
    private static final Dimension BUTTONS_DIMENSION = new Dimension(getMenuPanel().width / (BUTTONSSOUTH.values().length + 1),
            (int) (getMenuPanel().height * 0.9));
    /** The dimension of the images in the buttons.*/
    private static final ImageIcon BACKGROUND_BUTTON_SOUTH = ImageIconsSupplier.
            getScaledImageIcon(GameMenuImpl.PATH_BUTTON, BUTTONS_DIMENSION);
    /** Used to create distance between buttons.*/
    private static final int DISTANCE_SOUTH_BUTTONS = BUTTONS_DIMENSION.width / 20;
    private final JPanel southPanel;
    private final Map<BUTTONSSOUTH, JButton> buttons;

    /**
     * The constructor takes care to create all the south panel
     * and the buttons which are included in it.
     */
    public SouthPanel() {
        buttons = new HashMap<>();
        this.southPanel = new JPanel();
        this.southPanel.setBackground(Color.BLACK);
        this.southPanel.setPreferredSize(getMenuPanel());
        this.southPanel.setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();

        this.buttons.put(BUTTONSSOUTH.MUSIC, new ImageButton(BUTTONSSOUTH.MUSIC.getName(),
                BACKGROUND_BUTTON_SOUTH, BUTTONS_DIMENSION));
        this.buttons.put(BUTTONSSOUTH.MENU, new ImageButton(BUTTONSSOUTH.MENU.getName(),
                BACKGROUND_BUTTON_SOUTH, BUTTONS_DIMENSION));
        this.buttons.put(BUTTONSSOUTH.SAVE, new ImageButton(BUTTONSSOUTH.SAVE.getName(),
                BACKGROUND_BUTTON_SOUTH, BUTTONS_DIMENSION));
        this.buttons.put(BUTTONSSOUTH.QUIT, new ImageButton(BUTTONSSOUTH.QUIT.getName(),
                BACKGROUND_BUTTON_SOUTH, BUTTONS_DIMENSION));

        Font font = BattlePanelStyle.getPrimaryFont();

        grid.gridx = 0;
        grid.gridy = 0;
        grid.insets = new Insets(0, DISTANCE_SOUTH_BUTTONS, 0, 0);

        for (int i = 0; i < this.buttons.size(); i++) {
            this.buttons.get(BUTTONSSOUTH.values()[i]).setFont(font);
            this.buttons.get(BUTTONSSOUTH.values()[i]).setForeground(Color.BLACK);
            this.southPanel.add(this.buttons.get(BUTTONSSOUTH.values()[i]), grid);
            grid.gridx += 1;
        }
    }

    /**
     * Gets the south panel.
     * @return The panel.
     */
    @SuppressFBWarnings(value = "EI",
            justification = "I need changes to the panel in its references")
    public JPanel getPanel() {
        return this.southPanel;
    }

    /**
     * It's used to get a lower dimension of the entire screen.
     * @return The dimension used by the south panel.
     */
    public static Dimension getMenuPanel() {
        return new Dimension(
                (int) (GameGui.DIMENSION_SCREEN.getWidth() * MENU_WIDTH_SCALE),
                (int) (GameGui.DIMENSION_SCREEN.getHeight() * MENU_HEIGHT_SCALE));
    }

    /**
     * It takes care to set the action listener to the required button.
     * @param actionListener The action listener to set.
     * @param name The name of the button.
     */
    public void setActionListenerButtons(final ActionListener actionListener, final BUTTONSSOUTH name) {
        this.buttons.get(name).addActionListener(actionListener);
    }

    /**
     * It takes care of changing the visibility of the south panel's buttons.
     * @param name the name of the button.
     * @param visibility the visibility required to be set.
     */
    public void setButtonsVisibility(final BUTTONSSOUTH name, final Boolean visibility) {
        this.buttons.get(name).setVisible(visibility);
    }

}
