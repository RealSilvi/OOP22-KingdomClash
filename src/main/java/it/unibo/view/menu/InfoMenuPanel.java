package it.unibo.view.menu;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.kingdomclash.config.GameConfiguration;
import it.unibo.model.data.TroopType;
import it.unibo.view.GameGui;
import it.unibo.kingdomclash.config.PathIconsConfiguration;
import it.unibo.view.utilities.BattlePanelStyle;
import it.unibo.view.menu.extensiveclasses.ImageButton;
import it.unibo.view.menu.extensiveclasses.ImagePanel;
import it.unibo.view.menu.extensiveclasses.ImageTextArea;
import it.unibo.view.utilities.ImageIconsSupplier;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * The class creates the info panel (tutorial).
 * It creates panels and buttons to make the tutorial panel.
 */
public final class InfoMenuPanel {
    /** The dimension of the scroll bar in the text area.*/
    private static final Dimension SCROLLPANE_DIMENSION = new Dimension(
            (int) (GameGui.DIMENSION_SCREEN.getWidth() / 1.5), (int) (GameGui.DIMENSION_SCREEN.getHeight() / 1.5));
    /** The dimension of the panel which contains troops.*/
    private static final Dimension TROOPS_PANEL_DIMENSION = new Dimension(SCROLLPANE_DIMENSION.width / 4,
            SCROLLPANE_DIMENSION.height);
    /** The distance between each button.*/
    private static final int BUTTONS_DISTANCE = 30;
    /** Used to increment or reduce the size of the font.*/
    private static final double FONT_INCREMENT = 1.5;
    /** Used to increment or reduce the size of the image.*/
    private static final double IMAGE_DIMENSION = 6;
    /** Used to decrement image dimension.*/
    private static final double DECREMENT_IMAGE_DIMENSION = 2.5;
    private final JPanel infoPanel;
    private final JButton exit;
    private final JPanel panelOvest;
    private final Font font2;

    /**
     * The constructor creates the buttons and the panels to make
     * the info panel.
     * @param gameConfiguration The configuration of the game used to get
     * the images of each troop.
     */
    public InfoMenuPanel(final GameConfiguration gameConfiguration) {
        this.infoPanel = new ImagePanel(GameMenuImpl.BACKGROUND_PANEL.getImage());
        JPanel panel = new ImagePanel(GameMenuImpl.BACKGROUND_PANEL.getImage());
        JPanel panelNorth = new ImagePanel(ImageIconsSupplier.
                getScaledImageIcon(GameMenuImpl.PATH_BUTTON, TROOPS_PANEL_DIMENSION).getImage());
        panelNorth.setLayout(new BorderLayout());
        panelNorth.setPreferredSize(TROOPS_PANEL_DIMENSION);
        this.panelOvest = new JPanel();
        panelOvest.setBackground(new Color(0, 0, 0, 0));
        panelOvest.setLayout(new GridBagLayout());
        panel.setLayout(new GridBagLayout());
        infoPanel.setLayout(new BorderLayout());

        GridBagConstraints grid1 = new GridBagConstraints();
        grid1.gridx = 1;
        grid1.gridy = 1;
        grid1.insets = new Insets(BUTTONS_DISTANCE, 0, 0, 0);

        this.font2 = new Font("font", Font.ITALIC, ((GameGui.WIDTH_BUTTON) - (GameGui.HEIGHT_BUTTON)) / 2);
        Font font3 = new Font("font", Font.BOLD, (int) (((GameGui.WIDTH_BUTTON) - (GameGui.HEIGHT_BUTTON)) / FONT_INCREMENT));
        Font font = BattlePanelStyle.getPrimaryFont();

        ImageTextArea textArea = new ImageTextArea();
        textArea.setImage(ImageIconsSupplier.getScaledImageIcon(GameMenuImpl.PATH_BUTTON, SCROLLPANE_DIMENSION).getImage());

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setWheelScrollingEnabled(true);
        scrollPane.setPreferredSize(SCROLLPANE_DIMENSION);

        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setBorder(BorderFactory.createEmptyBorder());
        textArea.setFont(font2);
        panel.add(scrollPane, grid1);

        this.exit = new ImageButton("EXIT", GameMenuImpl.BACKGROUND_BUTTON,
                new Dimension(GameMenuImpl.BACKGROUND_BUTTON.getIconWidth(), GameMenuImpl.BACKGROUND_BUTTON.getIconHeight()));

        JTextField textField = new JTextField("COUNTERS");
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setFont(font3);
        textField.setFocusable(false);
        textField.setEditable(false);
        textField.setForeground(Color.BLUE);
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setOpaque(false);
        textField.setBackground(new Color(0, 0, 0, 0));
        panelNorth.add(textField, BorderLayout.NORTH);

        grid1.gridy = 1;
        grid1.gridx = 0;
        setImageTroops(gameConfiguration.getPathIconsConfiguration(), panelNorth.getPreferredSize());
        panelNorth.add(this.panelOvest, BorderLayout.CENTER);
        panel.add(panelNorth, grid1);

        grid1.gridy = 2;
        grid1.gridwidth = GameGui.DIMENSION_SCREEN.width / 2;
        exit.setFont(font);
        exit.setForeground(Color.BLACK);
        panel.add(exit, grid1);

        infoPanel.add(panel, BorderLayout.CENTER);

    }

    /**
     * Used to insert images of troops in the panel.
     * @param pathIconsConfiguration The path for the images of the troops.
     * @param dimension The dimension of the panel.
     */
    private void setImageTroops(final PathIconsConfiguration pathIconsConfiguration, final Dimension dimension) {
        GridBagConstraints grid = new GridBagConstraints();
        grid.gridx = 0;
        grid.gridy = 0;
        grid.insets = new Insets(10, 0, 0, 0);

        int i;
        int lenght = TroopType.values().length;

        for (i = 0; i < lenght / 2; i++) {
            int finalI = i;
            JLabel label = new JLabel(ImageIconsSupplier.getScaledImageIcon(pathIconsConfiguration.
                            getTroop(Arrays.stream(TroopType.values()).
                            filter(x -> x.ordinal() == finalI).toList().get(0)),
                    new Dimension(((int) (dimension.getWidth() / (IMAGE_DIMENSION - DECREMENT_IMAGE_DIMENSION))),
                            ((int) (dimension.getHeight() / IMAGE_DIMENSION)))));
            label.setText("  <---");
            label.setFont(font2);
            label.setForeground(Color.WHITE);
            label.setHorizontalTextPosition(JLabel.RIGHT);

            this.panelOvest.add(label, grid);
            grid.gridx = 1;
            JLabel label1 = new JLabel(ImageIconsSupplier.getScaledImageIcon(pathIconsConfiguration.
                            getTroop(Arrays.stream(TroopType.values())
                            .filter(x -> x.ordinal() == (lenght / 2) + finalI).toList().get(0)),
                    new Dimension(((int) (dimension.getWidth() / (IMAGE_DIMENSION - DECREMENT_IMAGE_DIMENSION))),
                            ((int) (dimension.getHeight() / IMAGE_DIMENSION)))));
            label1.setText("--->  ");
            label1.setFont(font2);
            label1.setForeground(Color.WHITE);
            label1.setHorizontalTextPosition(JLabel.LEFT);

            this.panelOvest.add(label1, grid);
            grid.gridy += 1;
            grid.gridx = 0;
        }

    }

    /**
     * Gets the info panel.
     * @return The panel.
     */
    @SuppressFBWarnings(value = "EI",
            justification = "I need changes to the panel in its references")
    public JPanel getPanel() {
        return this.infoPanel;
    }

    /**
     * Sets the action listener to the exit button.
     * @param actionListener The action listener to set.
     */
    public void setActionListenerExit(final ActionListener actionListener) {
        this.exit.addActionListener(actionListener);
    }

}
