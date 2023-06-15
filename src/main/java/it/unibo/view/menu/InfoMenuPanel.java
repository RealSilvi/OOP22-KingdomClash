package it.unibo.view.menu;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.data.GameConfiguration;
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
    private static final Dimension TEXTAREA_SCROLLPANE_DIMENSION = new Dimension((int) (GameGui.DIMENSION_SCREEN.width / 1.5),
            (int) (GameGui.DIMENSION_SCREEN.height / 1.5));
    private final JPanel infoPanel;
    private final JButton exit;
    public InfoMenuPanel(GameConfiguration gameConfiguration) {
        this.infoPanel = new ImagePanel(GameMenuImpl.BACKGROUND_PANEL.getImage());
        infoPanel.setLayout(new GridBagLayout());
        GridBagConstraints grid1 = new GridBagConstraints();
        grid1.gridx = 1;
        grid1.gridy = 1;
        grid1.insets = new Insets(30, 0, 0, 0);

        Font font2 = new Font("font", Font.ITALIC, ((GameGui.WIDTH_BUTTON) - (GameGui.HEIGHT_BUTTON))/2);
        Font font = BattlePanelStyle.getPrimaryFont();

        ImageTextArea textArea = new ImageTextArea(gameConfiguration, TEXTAREA_SCROLLPANE_DIMENSION);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setWheelScrollingEnabled(true);
        scrollPane.setPreferredSize(TEXTAREA_SCROLLPANE_DIMENSION);

        textArea.setImage(ImageIconsSupplier.loadImageIcon(GameMenuImpl.PATH_BUTTON).getImage());
        textArea.setFont(font2);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        infoPanel.add(scrollPane, grid1);

        this.exit = new ImageButton("EXIT", GameMenuImpl.BACKGROUND_BUTTON,
                new Dimension(GameMenuImpl.BACKGROUND_BUTTON.getIconWidth(), GameMenuImpl.BACKGROUND_BUTTON.getIconHeight()));
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
