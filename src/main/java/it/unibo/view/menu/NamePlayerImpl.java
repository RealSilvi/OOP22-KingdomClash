package it.unibo.view.menu;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.view.utilities.BattlePanelStyle;
import it.unibo.view.menu.extensiveclasses.ImageButton;
import it.unibo.view.menu.extensiveclasses.ImagePanel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionListener;

/**
 * The class is used to create the panel when the player
 * can insert his name.
 */
public final class NamePlayerImpl {

    private final JPanel namePanel;
    private final ImageButton start;
    private final JTextField textField;

    /**
     * The constructor creates buttons and panels to make
     * the panel to insert the name and start the game.
     */
    public NamePlayerImpl() {

        final Font font = BattlePanelStyle.getPrimaryFont();
        this.namePanel = new ImagePanel(GameMenuImpl.BACKGROUND_PANEL.getImage());
        this.namePanel.setLayout(new GridBagLayout());
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(GameMenuImpl.BACKGROUND_BUTTON.getIconWidth(),
                GameMenuImpl.BACKGROUND_BUTTON.getIconHeight()));
        textField.setFont(font);
        textField.setForeground(Color.WHITE);
        textField.setBackground(Color.BLACK);

        final GridBagConstraints grid = new GridBagConstraints();
        grid.insets = new Insets(GameMenuImpl.BUTTONS_DISTANCE, 0, 0, 0);

        final JLabel textLabel = new JLabel("Insert your nickname: ");
        textLabel.setFont(font);
        textLabel.setForeground(Color.BLACK);

        this.start = new ImageButton("START", GameMenuImpl.BACKGROUND_BUTTON,
                new Dimension(GameMenuImpl.BACKGROUND_BUTTON.getIconWidth(), GameMenuImpl.BACKGROUND_BUTTON.getIconHeight()));

        grid.gridy = 0;
        grid.gridx = 1;
        this.namePanel.add(textField, grid);

        grid.gridx = 0;
        grid.gridy = 0;
        this.namePanel.add(textLabel, grid);

        grid.gridx = 0;
        grid.gridy = 1;
        grid.gridwidth = (textField.getWidth() + textLabel.getWidth()) / 2;
        start.setFont(font);
        start.setForeground(Color.BLACK);
        this.namePanel.add(start, grid);

    }

    /**
     * Gets the player's name panel.
     * @return The panel.
     */
    @SuppressFBWarnings(value = "EI",
            justification = "I want to return the object to let other classes"
                    + "getting the reference and use it")
    public JPanel getPanel() {
        return this.namePanel;
    }

    /**
     * Gets the player's name from the text field.
     * @return The player's name.
     */
    public String getPlayerName() {
        if ("".equals(this.textField.getText())) {
            return "Guest";
        } else {
            return this.textField.getText();
        }
    }

    /**
     * Sets the action listener to the start button.
     * @param actionListener The action listener to set.
     */
    public void setActionListenerStart(final ActionListener actionListener) {
        this.start.addActionListener(actionListener);
    }

}
