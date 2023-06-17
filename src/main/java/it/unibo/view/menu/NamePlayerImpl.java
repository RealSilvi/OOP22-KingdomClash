package it.unibo.view.menu;

import it.unibo.view.GameGui;
import it.unibo.view.battle.utilities.BattlePanelStyle;
import it.unibo.view.menu.extensiveclasses.ImageButton;
import it.unibo.view.menu.extensiveclasses.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NamePlayerImpl {

    private JPanel namePanel;
    private ImageButton start;
    private ImageButton back;
    private JTextField textField;

    public NamePlayerImpl(){

        Font font = BattlePanelStyle.getPrimaryFont();
        this.namePanel = new ImagePanel(GameMenuImpl.BACKGROUND_PANEL.getImage());
        this.namePanel.setLayout(new GridBagLayout());
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(GameMenuImpl.BACKGROUND_BUTTON.getIconWidth() * 2, GameMenuImpl.BACKGROUND_BUTTON.getIconHeight()));
        textField.setFont(font);
        textField.setForeground(Color.BLACK);

        GridBagConstraints grid = new GridBagConstraints();

        this.start = new ImageButton("START", GameMenuImpl.BACKGROUND_BUTTON,
                new Dimension(GameMenuImpl.BACKGROUND_BUTTON.getIconWidth(), GameMenuImpl.BACKGROUND_BUTTON.getIconHeight()));
        this.back = new ImageButton("BACK", GameMenuImpl.BACKGROUND_BUTTON,
                new Dimension(GameMenuImpl.BACKGROUND_BUTTON.getIconWidth(), GameMenuImpl.BACKGROUND_BUTTON.getIconHeight()));

        grid.gridx = 1;
        grid.gridy = 1;
        grid.insets = new Insets(GameGui.DIMENSION_SCREEN.height/50, 0, 0, 0);
        back.setFont(font);
        back.setForeground(Color.BLACK);
        this.namePanel.add(back, grid);

        grid.gridx = 2;
        start.setFont(font);
        start.setForeground(Color.BLACK);
        this.namePanel.add(start, grid);

        grid.gridy = 0;
        grid.gridx = 0;
        grid.gridwidth = GameGui.DIMENSION_SCREEN.width / 2;
        this.namePanel.add(textField, grid);

    }

    public JPanel getPanel(){
        return this.namePanel;
    }

    public void setActionListenerBack(ActionListener actionListener) {
        this.back.addActionListener(actionListener);
    }

    public void setActionListenerStart(ActionListener actionListener) {
        this.start.addActionListener(actionListener);
    }

}
