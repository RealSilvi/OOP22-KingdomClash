package it.unibo.view.menu;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.view.GameGui;
import it.unibo.view.battle.panels.utilities.BattlePanelStyle;

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
    public InfoMenuPanel(){
        JButton ex = new JButton("EXIT");
        this.infoPanel = new ImagePanel(BACKGROUND_PANEL.getImage());
        infoPanel.setLayout(new GridBagLayout());
        GridBagConstraints grid1 = new GridBagConstraints();
        grid1.gridx = 1;
        grid1.gridy = 1;
        grid1.ipadx = (WIDTH_BUTTON*10);
        grid1.ipady = (HEIGHT_BUTTON*10);
        grid1.insets = new Insets(30, 0, 0, 0);

        Font font2 = new Font("font", Font.ITALIC, ((WIDTH_BUTTON) - (HEIGHT_BUTTON))/2);
        Font font = BattlePanelStyle.getPrimaryFont();
        ex.setFont(font);

        ImageTextArea textArea = new ImageTextArea();
        textArea.setImage(BACKGROUND_BUTTON.getImage());
        textArea.setFont(font2);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setText("ciaooooooooooooooooooooooooooooooooooooo");
        textArea.setEditable(false);
        textArea.setForeground(Color.WHITE);
        textArea.setPreferredSize(ex.getPreferredSize());
        textArea.setFocusable(false);
        infoPanel.add(textArea, grid1);

        this.exit = new JButton("EXIT", BACKGROUND_BUTTON);
        exit.setHorizontalTextPosition(SwingConstants.CENTER);
        exit.setFocusable(false);
        grid1.gridy = 2;
        grid1.ipadx = (WIDTH_BUTTON);
        grid1.ipady = (HEIGHT_BUTTON);
        exit.setFont(font);
        exit.setPreferredSize(ex.getPreferredSize());
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
