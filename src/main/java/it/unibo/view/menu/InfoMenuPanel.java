package it.unibo.view.menu;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.data.GameConfiguration;
import it.unibo.model.data.GameData;
import it.unibo.view.GameGui;
import it.unibo.view.battle.panels.utilities.BattlePanelStyle;
import it.unibo.view.menu.extensiveclasses.ImageButton;
import it.unibo.view.menu.extensiveclasses.ImagePanel;
import it.unibo.view.menu.extensiveclasses.ImageTextArea;
import it.unibo.view.utilities.ImageIconsSupplier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InfoMenuPanel {

    private static final ImageIcon BACKGROUND_PANEL = GameMenuImpl.BACKGROUND_PANEL;
    private static final ImageIcon BACKGROUND_BUTTON = GameMenuImpl.BACKGROUND_BUTTON;
    private static final int WIDTH_BUTTON = GameGui.WIDTH_BUTTON;
    private static final int HEIGHT_BUTTON = GameGui.HEIGHT_BUTTON;
    private final JPanel infoPanel;
    private final JButton exit;
    public InfoMenuPanel(GameConfiguration gameConfiguration) {
        this.infoPanel = new ImagePanel(BACKGROUND_PANEL.getImage());
        infoPanel.setLayout(new GridBagLayout());
        GridBagConstraints grid1 = new GridBagConstraints();
        grid1.gridx = 1;
        grid1.gridy = 1;
        grid1.insets = new Insets(30, 0, 0, 0);

        Font font2 = new Font("font", Font.ITALIC, ((WIDTH_BUTTON) - (HEIGHT_BUTTON))/2);
        Font font = BattlePanelStyle.getPrimaryFont();

        ImageTextArea textArea = new ImageTextArea(gameConfiguration);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setWheelScrollingEnabled(true);
        scrollPane.setPreferredSize(new Dimension((int) (GameGui.DIMENSION_SCREEN.width / 1.5), (int) (GameGui.DIMENSION_SCREEN.height / 1.5)));

        textArea.setImage(ImageIconsSupplier.loadImageIcon(GameMenuImpl.PATH_BUTTON).getImage());
        textArea.setFont(font2);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        infoPanel.add(scrollPane, grid1);

        this.exit = new ImageButton("EXIT", BACKGROUND_BUTTON, new Dimension(BACKGROUND_BUTTON.getIconWidth(), BACKGROUND_BUTTON.getIconHeight()));
        exit.setHorizontalTextPosition(SwingConstants.CENTER);
        exit.setFocusable(false);
        grid1.gridy = 2;
        exit.setFont(font);
        exit.setForeground(Color.BLACK);
        infoPanel.add(exit, grid1);

    }

    @SuppressFBWarnings(value = "EI",
            justification = "I need changes to the panel in its references")
    public JPanel getPanel(){
        return this.infoPanel;
    }

    public void setActionListenerExit(ActionListener actionListener){
        this.exit.addActionListener(actionListener);
    }

}
