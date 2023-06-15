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
    private static final Dimension SCROLLPANE_DIMENSION = new Dimension((int) (GameGui.DIMENSION_SCREEN.getWidth() / 1.5),
            (int) (GameGui.DIMENSION_SCREEN.getHeight() / 1.5));
    private static  final Dimension TEXTAREA = new Dimension(SCROLLPANE_DIMENSION.width * 2, SCROLLPANE_DIMENSION.height * 2);
    private final JPanel infoPanel;
    private final JButton exit;
    public InfoMenuPanel(GameConfiguration gameConfiguration) {
        this.infoPanel = new ImagePanel(GameMenuImpl.BACKGROUND_PANEL.getImage());
        infoPanel.setLayout(new GridBagLayout());
        GridBagConstraints grid1 = new GridBagConstraints();
        grid1.gridx = 1;
        grid1.gridy = 1;
        grid1.insets = new Insets(GameGui.DIMENSION_SCREEN.height/50, 0, 0, 0);

        Font font2 = new Font("font", Font.ITALIC, ((GameGui.WIDTH_BUTTON) - (GameGui.HEIGHT_BUTTON))/2);
        Font font = BattlePanelStyle.getPrimaryFont();

        ImageTextArea textArea = new ImageTextArea(gameConfiguration);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setWheelScrollingEnabled(true);
        scrollPane.setPreferredSize(SCROLLPANE_DIMENSION);

        textArea.setImage(ImageIconsSupplier.getScaledImageIcon(GameMenuImpl.PATH_BUTTON, TEXTAREA).getImage());
        textArea.setFont(font2);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setTextImage(TEXTAREA);
        infoPanel.add(scrollPane, grid1);

        this.exit = new ImageButton("EXIT", GameMenuImpl.BACKGROUND_BUTTON,
                new Dimension(GameMenuImpl.BACKGROUND_BUTTON.getIconWidth(), GameMenuImpl.BACKGROUND_BUTTON.getIconHeight()));
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
