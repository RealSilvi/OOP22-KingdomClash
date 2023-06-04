package it.unibo.view;

import it.unibo.view.menu.GameMenu;
import it.unibo.view.menu.GameMenuImpl;
import it.unibo.view.menu.InfoMenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View {

    public static final Dimension DIMENSION_SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int WIDTH_BUTTON = (int) DIMENSION_SCREEN.getWidth() / 20;
    public static final int HEIGHT_BUTTON = (int) DIMENSION_SCREEN.getHeight() / 20;
    private final JFrame frame;
    private final CardLayout SwitchLayout;
    private final JPanel mainPanel;
    //private final JPanel southPanel;
    private final GameMenu menuPanel;
    private final InfoMenuPanel infoPanel;
    //private final JPanel newgamePanel;
    //private final JPanel loadPanel;

    public View(){
        frame = new JFrame();
        frame.setSize((int) DIMENSION_SCREEN.getWidth(), (int) DIMENSION_SCREEN.getHeight());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.SwitchLayout = new CardLayout();
        this.mainPanel = new JPanel(this.SwitchLayout);

        this.menuPanel = new GameMenuImpl();
        this.infoPanel = new InfoMenuPanel();

        this.mainPanel.add(this.menuPanel.getPanel(),"1");
        this.mainPanel.add(this.infoPanel.getPanel(),"2");

        frame.setContentPane(this.mainPanel);
        frame.setVisible(true);
        frame.revalidate();

        setActionListenerInfo();
        setActionListenerExit();
    }

    public void showMenuPanel() {
        SwitchLayout.show(mainPanel, "1");
    }

    public void showInfoPanel() {
        SwitchLayout.show(mainPanel, "2");
    }

    private void setActionListenerInfo() {
        ActionListener actionListener = e -> showInfoPanel();
        this.menuPanel.setActionListenerInfo(actionListener);
    }

    private void setActionListenerExit() {
        ActionListener actionListener = e -> showMenuPanel();
        this.infoPanel.setActionListenerExit(actionListener);
    }

    public static void main(final String... args) {
        new View();

    }

}
