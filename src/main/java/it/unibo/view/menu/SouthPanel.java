package it.unibo.view.menu;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.view.GameGui;
import it.unibo.view.menu.extensiveclasses.ImageButton;
import it.unibo.view.utilities.BattlePanelStyle;
import it.unibo.view.utilities.ImageIconsSupplier;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class SouthPanel {

    public enum BUTTONS_SOUTH {
        MUSIC("MUSIC"),
        MENU("MENU"),
        SAVE("SAVE"),
        QUIT("QUIT");

        private final String name;

        BUTTONS_SOUTH(final String name) {
            this.name = name;
        }

        public String getName(){
            return this.name;
        }

    }

    public static final Dimension DIMENSION_SCREEN = GameGui.DIMENSION_SCREEN;
    private static final double MENU_WIDTH_SCALE = 1;
    private static final double MENU_HEIGHT_SCALE = 0.05;
    private static final Integer NUMBER_BUTTONS = BUTTONS_SOUTH.values().length;
    private static final Dimension BUTTONS_DIMENSION = new Dimension(getMenuPanel().width / (NUMBER_BUTTONS + 1), (int) (getMenuPanel().height * 0.9));
    public static final ImageIcon BACKGROUND_BUTTON_SOUTH = ImageIconsSupplier.getScaledImageIcon(GameMenuImpl.PATH_BUTTON, BUTTONS_DIMENSION);
    private final JPanel southPanel;
    private final Map<BUTTONS_SOUTH, JButton> buttons;

    public SouthPanel(){
        buttons = new HashMap<>();
        this.southPanel = new JPanel();
        this.southPanel.setBackground(Color.BLACK);
        this.southPanel.setPreferredSize(getMenuPanel());
        this.southPanel.setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();

        this.buttons.put(BUTTONS_SOUTH.MUSIC, new ImageButton(BUTTONS_SOUTH.MUSIC.getName(), BACKGROUND_BUTTON_SOUTH, BUTTONS_DIMENSION));
        this.buttons.put(BUTTONS_SOUTH.MENU, new ImageButton(BUTTONS_SOUTH.MENU.getName(), BACKGROUND_BUTTON_SOUTH, BUTTONS_DIMENSION));
        this.buttons.put(BUTTONS_SOUTH.SAVE, new ImageButton(BUTTONS_SOUTH.SAVE.getName(), BACKGROUND_BUTTON_SOUTH, BUTTONS_DIMENSION));
        this.buttons.put(BUTTONS_SOUTH.QUIT, new ImageButton(BUTTONS_SOUTH.QUIT.getName(), BACKGROUND_BUTTON_SOUTH, BUTTONS_DIMENSION));

        Font font = BattlePanelStyle.getPrimaryFont();

        grid.gridx = 0;
        grid.gridy = 0;

        for(int i = 0; i < this.buttons.size(); i++){
            this.buttons.get(BUTTONS_SOUTH.values()[i]).setFont(font);
            this.buttons.get(BUTTONS_SOUTH.values()[i]).setForeground(Color.BLACK);
            this.southPanel.add(this.buttons.get(BUTTONS_SOUTH.values()[i]), grid);
            grid.gridx += 1;
        }
    }

    @SuppressFBWarnings(value = "EI",
            justification = "I need changes to the panel in its references")
    public JPanel getPanel(){
        return this.southPanel;
    }

    public static Dimension getMenuPanel() {
        return new Dimension(
                (int) (DIMENSION_SCREEN.getWidth() * MENU_WIDTH_SCALE),
                (int) (DIMENSION_SCREEN.getHeight() * MENU_HEIGHT_SCALE));
    }

    public void setActionListenerButtons(ActionListener actionListener, BUTTONS_SOUTH name){
        this.buttons.get(name).addActionListener(actionListener);
    }

    public void setButtonsVisibility(BUTTONS_SOUTH name, Boolean visibility){
        this.buttons.get(name).setVisible(visibility);
    }

}
