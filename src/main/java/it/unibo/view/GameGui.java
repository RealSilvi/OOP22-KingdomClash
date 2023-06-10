package it.unibo.view;

import it.unibo.controller.SoundManager;
import it.unibo.model.data.GameConfiguration;
import it.unibo.view.map.MapPanel;
import it.unibo.view.map.MapPanelImpl;
import it.unibo.view.menu.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameGui {

    public static final Dimension DIMENSION_SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int WIDTH_BUTTON = (int) DIMENSION_SCREEN.getWidth() / 20;
    public static final int HEIGHT_BUTTON = (int) DIMENSION_SCREEN.getHeight() / 20;
    private final CardLayout SwitchLayout;
    private final CardLayout SwitchLayout2;
    private final JPanel allPanel;
    private final JPanel mainPanel;
    private final SouthPanel southPanel;
    private final GameMenu menuPanel;
    private final InfoMenuPanel infoPanel;
    private final MapPanel mapPanel;
    private final SoundManager soundManager;

    public GameGui(JPanel battlePanel, JPanel cityPanel, GameConfiguration gameConfiguration, SoundManager soundManager){
        JFrame frame = new JFrame();
        frame.setSize((int) DIMENSION_SCREEN.getWidth(), (int) DIMENSION_SCREEN.getHeight());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.soundManager = soundManager;

        this.mapPanel = new MapPanelImpl(gameConfiguration);

        this.SwitchLayout = new CardLayout();
        this.mainPanel = new JPanel(this.SwitchLayout);

        this.SwitchLayout2 = new CardLayout();
        this.allPanel = new JPanel(this.SwitchLayout2);

        JPanel BorderPanel = new JPanel(new BorderLayout());

        this.menuPanel = new GameMenuImpl();
        this.infoPanel = new InfoMenuPanel();
        this.southPanel = new SouthPanel();

        this.allPanel.add(battlePanel,"1");
        this.allPanel.add(cityPanel,"2");
        this.allPanel.add(this.mapPanel.getAsJPanel(),"3");

        BorderPanel.add(this.allPanel, BorderLayout.CENTER);
        BorderPanel.add(this.southPanel.getPanel(),BorderLayout.SOUTH);

        this.mainPanel.add(this.menuPanel.getPanel(), "1");
        this.mainPanel.add(this.infoPanel.getPanel(), "2");
        this.mainPanel.add(BorderPanel, "3");

        frame.setContentPane(this.mainPanel);
        frame.setVisible(true);
        frame.revalidate();

        setActionListenerInfo();
        setActionListenerExit();
        setActionListenerNewGame();
        setActionListenerMusic();
        setActionListenerBattle();
        setActionListenerMenu();
        setActionListenerCity();
        setActionListenerMap();
        setMapBaseActionListener();
        setMapBattleActionListener();
        showMenuPanel();

    }

    public void showMenuPanel() {
        this.soundManager.startMenuTheme();
        SwitchLayout.show(this.mainPanel, "1");
    }

    public void showInfoPanel() {
        SwitchLayout.show(this.mainPanel, "2");
    }

    public void showBattle(){
        this.soundManager.startBattleTheme();
        this.southPanel.showButtonsBattle();
        SwitchLayout.show(this.mainPanel, "3");
        SwitchLayout2.show(this.allPanel, "1");
    }

    public void showCity(){
        this.soundManager.startCityTheme();
        this.southPanel.showButtonsCity();
        SwitchLayout.show(this.mainPanel, "3");
        SwitchLayout2.show(this.allPanel, "2");
    }

    public void showMap(){
        this.soundManager.startMapTheme();
        this.southPanel.showButtonsMap();
        SwitchLayout.show(this.mainPanel, "3");
        SwitchLayout2.show(this.allPanel, "3");
    }

    private void setActionListenerNewGame() {
        ActionListener actionListener = e -> showCity();
        this.menuPanel.setActionListenerNewGame(actionListener);
    }

    private void setActionListenerMusic() {
        ActionListener actionListener = e -> this.soundManager.changeMute();
        this.menuPanel.setActionListenerMusic(actionListener);
        this.southPanel.setActionListenerMusic(actionListener);
    }

    private void setActionListenerInfo() {
        ActionListener actionListener = e -> showInfoPanel();
        this.menuPanel.setActionListenerInfo(actionListener);
    }

    private void setActionListenerExit() {
        ActionListener actionListener = e -> showMenuPanel();
        this.infoPanel.setActionListenerExit(actionListener);
    }

    private void setActionListenerBattle() {
        ActionListener actionListener = e -> showBattle();
        this.southPanel.setActionListenerBattle(actionListener);
    }

    private void setActionListenerMenu() {
        ActionListener actionListener = e -> showMenuPanel();
        this.southPanel.setActionListenerMenu(actionListener);
    }

    private void setActionListenerCity() {
        ActionListener actionListener = e -> showCity();
        this.southPanel.setActionListenerCity(actionListener);
    }

    private void setActionListenerMap() {
        ActionListener actionListener = e -> showMap();
        this.southPanel.setActionListenerMap(actionListener);
    }

    private void setMapBaseActionListener(){
        ActionListener actionListener = e -> showCity();
        this.mapPanel.setBaseActionListener(actionListener);
    }

    private void setMapBattleActionListener(){
        ActionListener actionListener = e -> showBattle();
        this.mapPanel.setBattleActionListener(actionListener);
    }

    public void setActivateBattle(Integer level){
        //this.mapPanel.setBeatenLevels(level-1);
        this.mapPanel.setActiveBattle(level);
    }

    public void setBeatenLevels(Integer levels){
        this.mapPanel.setBeatenLevels(levels);
    }

    public static Dimension getAllPanel(){
        double width = SouthPanel.getMenuPanel().getWidth();
        double height = DIMENSION_SCREEN.getHeight() - SouthPanel.getMenuPanel().getHeight();
        return new Dimension((int)width, (int)height);
    }

}
