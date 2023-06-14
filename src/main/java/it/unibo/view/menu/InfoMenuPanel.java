package it.unibo.view.menu;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.view.GameGui;
import it.unibo.view.battle.panels.utilities.BattlePanelStyle;
import it.unibo.view.menu.extensiveclasses.ImageButton;
import it.unibo.view.menu.extensiveclasses.ImagePanel;
import it.unibo.view.menu.extensiveclasses.ImageTextArea;

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
    public InfoMenuPanel() {
        this.infoPanel = new ImagePanel(BACKGROUND_PANEL.getImage());
        infoPanel.setLayout(new GridBagLayout());
        GridBagConstraints grid1 = new GridBagConstraints();
        grid1.gridx = 1;
        grid1.gridy = 1;
        grid1.ipadx = ((int) GameGui.DIMENSION_SCREEN.getWidth() / 4);
        grid1.ipady = ((int) GameGui.DIMENSION_SCREEN.getHeight() / 4);
        grid1.insets = new Insets(30, 0, 0, 0);

        Font font2 = new Font("font", Font.ITALIC, ((WIDTH_BUTTON) - (HEIGHT_BUTTON))/2);
        Font font = BattlePanelStyle.getPrimaryFont();

        ImageTextArea textArea = new ImageTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setWheelScrollingEnabled(true);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        textArea.setImage(BACKGROUND_BUTTON.getImage());
        textArea.setFont(font2);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        scrollPane.setPreferredSize(new Dimension((int) GameGui.DIMENSION_SCREEN.getWidth() / 2, (int) GameGui.DIMENSION_SCREEN.getHeight() / 2));
        textArea.setFocusable(false);
        infoPanel.add(scrollPane, grid1);

        this.exit = new ImageButton("EXIT", BACKGROUND_BUTTON, new Dimension(WIDTH_BUTTON*2,(int)(HEIGHT_BUTTON*1.5)));
        exit.setHorizontalTextPosition(SwingConstants.CENTER);
        exit.setFocusable(false);
        grid1.gridy = 2;
        grid1.ipadx = (WIDTH_BUTTON);
        grid1.ipady = (HEIGHT_BUTTON);
        exit.setFont(font);
        exit.setForeground(Color.BLACK);
        //exit.setPreferredSize(new Dimension((int) GameGui.DIMENSION_SCREEN.getWidth() / 10, (int) GameGui.DIMENSION_SCREEN.getHeight() / 10));
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
