package it.unibo.view.menu;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.view.GameGui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class SouthPanel {

    public enum BUTTONS_NAME {
        QUIT("QUIT"),
        MUSIC("MUSIC"),
        MENU("MENU"),
        SAVE("SAVE");

        private String name;

        BUTTONS_NAME(final String name) {
            this.name = name;
        }

        public String getName(){
            return this.name;
        }

    }

    public static final Dimension DIMENSION_SCREEN = GameGui.DIMENSION_SCREEN;
    private static final double MENU_WIDTH_SCALE = 1;
    private static final double MENU_HEIGHT_SCALE = 0.05;
    private final JPanel southPanel;
    private final Map<BUTTONS_NAME, JButton> buttons;

    public SouthPanel(){
        buttons = new HashMap<>();
        this.buttons.put(BUTTONS_NAME.MUSIC, new JButton(BUTTONS_NAME.MUSIC.getName()));
        this.buttons.put(BUTTONS_NAME.MENU, new JButton(BUTTONS_NAME.MENU.getName()));
        this.buttons.put(BUTTONS_NAME.SAVE, new JButton(BUTTONS_NAME.SAVE.getName()));
        this.buttons.put(BUTTONS_NAME.QUIT, new JButton(BUTTONS_NAME.QUIT.getName()));

        this.southPanel = new JPanel();
        this.southPanel.setBackground(Color.blue);
        this.southPanel.setPreferredSize(getMenuPanel());

        this.buttons.forEach((x,y) -> {
            southPanel.add(y);
        });
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

    public void setActionListenerButtons(ActionListener actionListener, BUTTONS_NAME name){
        this.buttons.get(name).addActionListener(actionListener);
    }

    public void setButtonsVisibility(BUTTONS_NAME name, Boolean visibility){
        this.buttons.get(name).setVisible(visibility);
    }

}
