package it.unibo.view.battle;

import java.awt.*;

import javax.swing.*;

public final class BattleGui  extends JFrame {

    private final double WIDTH_PERC = 0.;
    private final double HEIGHT_PERC = 0.3;
    private final Dimension screenSize;

    //private final JPanel MenuPanel;
    private final JPanel fieldPanel;
    private final JPanel botPanel;
    private final JPanel playerPanel;
    private final JPanel infoPanel;
    private final JPanel buttonsPanel;

    /**
     * Builds a new CGUI.
     */
    public BattleGui() {
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        final JPanel mainPanel = new JPanel(new BorderLayout());

        final JPanel topPanel = new JPanel(new BorderLayout());

        //this.menuPanel = new MenuPanel();
        this.botPanel = new BotPanel(this.screenSize);
        this.playerPanel = new PlayerPanel(this.screenSize);
        this.infoPanel = new InfoPanel(this.screenSize);
        this.buttonsPanel = new ButtonsPanel(this.screenSize);
        this.fieldPanel = new FieldPanel(this.screenSize);

        //topPanel.add(menuPanel, BorderLayout.NORTH);
        topPanel.add(new JPanel().add(new JButton("QUA CI SARA IL MENU")),BorderLayout.NORTH);
        topPanel.add(botPanel, BorderLayout.SOUTH);

        mainPanel.add(topPanel,BorderLayout.NORTH);
        mainPanel.add(playerPanel,BorderLayout.SOUTH);
        mainPanel.add(infoPanel,BorderLayout.EAST);
        mainPanel.add(buttonsPanel,BorderLayout.WEST);
        mainPanel.add(fieldPanel,BorderLayout.CENTER);


        this.getContentPane().add(mainPanel);
        this.setVisible(true);
        this.pack();
    }
}
