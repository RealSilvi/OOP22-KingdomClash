package it.unibo.view;

import it.unibo.view.menu.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameGui {

    public static final Dimension DIMENSION_SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int WIDTH_BUTTON = (int) DIMENSION_SCREEN.getWidth() / 20;
    public static final int HEIGHT_BUTTON = (int) DIMENSION_SCREEN.getHeight() / 20;
    private final JFrame frame;
    private final CardLayout SwitchLayout;
    private final CardLayout SwitchLayout2;
    private final JPanel allPanel;
    private final JPanel mainPanel;
    private final JPanel BorderPanel;
    private final SouthPanel southPanel;
    private final GameMenu menuPanel;
    private final InfoMenuPanel infoPanel;
    //private final JPanel newgamePanel;
    //private final JPanel loadPanel;

    public GameGui(JPanel battlePanel/*, JPanel cityPanel*/){
        frame = new JFrame();
        frame.setSize((int) DIMENSION_SCREEN.getWidth(), (int) DIMENSION_SCREEN.getHeight());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.SwitchLayout = new CardLayout();
        this.mainPanel = new JPanel(this.SwitchLayout);

        this.SwitchLayout2 = new CardLayout();
        this.allPanel = new JPanel(this.SwitchLayout2);

        this.BorderPanel = new JPanel(new BorderLayout());

        this.menuPanel = new GameMenuImpl();
        this.infoPanel = new InfoMenuPanel();
        this.southPanel = new SouthPanel();

        this.allPanel.add(battlePanel,"1");

        this.BorderPanel.add(this.allPanel, BorderLayout.CENTER);
        this.BorderPanel.add(this.southPanel.getPanel(),BorderLayout.SOUTH);

        this.mainPanel.add(this.menuPanel.getPanel(), "1");
        this.mainPanel.add(this.infoPanel.getPanel(), "2");
        this.mainPanel.add(this.BorderPanel, "3");

        frame.setContentPane(this.mainPanel);
        frame.setVisible(true);
        frame.revalidate();

        setActionListenerInfo();
        setActionListenerExit();
        setActionListenerNewGame();
        showMenuPanel();

    }

    public void showMenuPanel() {
        SwitchLayout.show(this.mainPanel, "1");
    }

    public void showInfoPanel() {
        SwitchLayout.show(this.mainPanel, "2");
    }

    public void showNewGame(){
        SwitchLayout.show(this.mainPanel, "3");
        SwitchLayout2.show(this.allPanel, "1");
    }

    private void setActionListenerNewGame() {
        ActionListener actionListener = e -> showNewGame();
        this.menuPanel.setActionListenerNewGame(actionListener);
    }

    private void setActionListenerInfo() {
        ActionListener actionListener = e -> showInfoPanel();
        this.menuPanel.setActionListenerInfo(actionListener);
    }

    private void setActionListenerExit() {
        ActionListener actionListener = e -> showMenuPanel();
        this.infoPanel.setActionListenerExit(actionListener);
    }

    public static Dimension getAllPanel(){
        double width = SouthPanel.getMenuPanel().getWidth();
        double height = DIMENSION_SCREEN.getHeight() - SouthPanel.getMenuPanel().getHeight();
        return new Dimension((int)width, (int)height);
    }

}
