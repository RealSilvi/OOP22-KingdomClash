package it.unibo.view;

import it.unibo.controller.SoundManager;
import it.unibo.model.data.GameConfiguration;
import it.unibo.view.map.MapPanel;
import it.unibo.view.map.MapPanelImpl;
import it.unibo.view.menu.GameMenu;
import it.unibo.view.menu.GameMenuImpl;
import it.unibo.view.menu.InfoMenuPanel;
import it.unibo.view.menu.SouthPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

/**
 * This class is the principal GUI, which takes
 * care about switch all the panels in the game,
 * and incorporate action listeners to the buttons.
 */
public final class GameGui implements GameGuiInt {

    /** Dimension of the screen.*/
    public static final Dimension DIMENSION_SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    /** Width of the buttons.*/
    public static final int WIDTH_BUTTON = (int) DIMENSION_SCREEN.getWidth() / 20;
    /** Height of the buttons.*/
    public static final int HEIGHT_BUTTON = (int) DIMENSION_SCREEN.getHeight() / 20;
    private final JFrame frame;
    private final CardLayout switchLayout;
    private final CardLayout switchLayout2;
    private final JPanel allPanel;
    private final JPanel mainPanel;
    private final SouthPanel southPanel;
    private final GameMenu menuPanel;
    private final InfoMenuPanel infoPanel;
    private final MapPanel mapPanel;
    private final SoundManager soundManager;

    /**
     * The constructor initialize all the panels,
     * and creates a system to switch panels.
     * @param battlePanel Panel of the battle.
     * @param cityPanel Panel of the city.
     * @param gameConfiguration Configuration of the game.
     */
    public GameGui(final JPanel battlePanel, final JPanel cityPanel, final GameConfiguration gameConfiguration) {
        this.frame = new JFrame();
        this.frame.setSize((int) (DIMENSION_SCREEN.getWidth()), (int) (DIMENSION_SCREEN.getHeight()));
        this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.soundManager = new SoundManager();
        this.soundManager.changeMute();

        this.mapPanel = new MapPanelImpl(gameConfiguration);

        this.switchLayout = new CardLayout();
        this.mainPanel = new JPanel(this.switchLayout);

        this.switchLayout2 = new CardLayout();
        this.allPanel = new JPanel(this.switchLayout2);

        JPanel borderPanel = new JPanel(new BorderLayout());

        this.menuPanel = new GameMenuImpl();
        this.infoPanel = new InfoMenuPanel(gameConfiguration);
        this.southPanel = new SouthPanel();

        this.allPanel.add(battlePanel, "1");
        this.allPanel.add(cityPanel, "2");
        this.allPanel.add(this.mapPanel.getAsJPanel(), "3");

        borderPanel.add(this.allPanel, BorderLayout.CENTER);
        borderPanel.add(this.southPanel.getPanel(), BorderLayout.SOUTH);

        this.mainPanel.add(this.menuPanel.getPanel(), "1");
        this.mainPanel.add(this.infoPanel.getPanel(), "2");
        this.mainPanel.add(borderPanel, "3");

        frame.setContentPane(this.mainPanel);
        frame.setVisible(true);
        frame.revalidate();

        setActionListenerInfo();
        setActionListenerExit();
        setActionListenerMusic();
        setActionListenerMenu();
        setMapBaseActionListener();
        setMapBattleActionListener();
        showMenuPanel();

    }

    @Override
    public void showMenuPanel() {
        this.soundManager.startMenuTheme();
        switchLayout.show(this.mainPanel, "1");
    }

    @Override
    public void showInfoPanel() {
        switchLayout.show(this.mainPanel, "2");
    }

    @Override
    public void showBattle() {
        this.soundManager.startBattleTheme();
        this.southPanel.showButtonsBattle();
        switchLayout.show(this.mainPanel, "3");
        switchLayout2.show(this.allPanel, "1");
    }

    @Override
    public void showCity() {
        this.soundManager.startCityTheme();
        this.southPanel.showButtonsCity();
        switchLayout.show(this.mainPanel, "3");
        switchLayout2.show(this.allPanel, "2");
    }

    @Override
    public void showMap() {
        this.soundManager.startMapTheme();
        this.southPanel.showButtonsMap();
        switchLayout.show(this.mainPanel, "3");
        switchLayout2.show(this.allPanel, "3");
    }

    @Override
    public void setActionListenerNewGame(ActionListener actionListener) {
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
        actionListener = e ->  System.exit(0);
        this.southPanel.setActionListenerExit(actionListener);
        this.menuPanel.setActionListenerExit(actionListener);
    }

    @Override
    public void setActionListenerBattle(ActionListener actionListener) {
        this.southPanel.setActionListenerBattle(actionListener);
    }

    private void setActionListenerMenu() {
        ActionListener actionListener = e -> showMenuPanel();
        this.southPanel.setActionListenerMenu(actionListener);
    }

    @Override
    public void setActionListenerCity(ActionListener actionListener) {
        this.southPanel.setActionListenerCity(actionListener);
    }

    @Override
    public void setActionListenerMap(ActionListener actionListener) {
        this.southPanel.setActionListenerMap(actionListener);
    }

    @Override
    public ActionListener getActionListenerMap() {
        return e -> showMap();
    }

    private void setMapBaseActionListener() {
        ActionListener actionListener = e -> showCity();
        this.mapPanel.setBaseActionListener(actionListener);
    }

    private void setMapBattleActionListener() {
        ActionListener actionListener = e -> showBattle();
        this.mapPanel.setBattleActionListener(actionListener);
    }

    @Override
    public void setActivateBattle(final Integer level) {
        this.mapPanel.setActiveBattle(level);
    }

    @Override
    public SoundManager getSoundManager() {
        return this.soundManager;
    }

    @Override
    public void setBeatenLevels(final Integer levels) {
        this.mapPanel.setBeatenLevels(levels);
    }

    /**
     * Takes the dimension of the main panel.
     * @return The dimension of the panel.
     */
    public static Dimension getAllPanel() {
        double width = SouthPanel.getMenuPanel().getWidth();
        double height = DIMENSION_SCREEN.getHeight() - SouthPanel.getMenuPanel().getHeight();
        return new Dimension((int) width, (int) height);
    }

}
