package it.unibo.view;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.controller.sound.SoundManagerImpl;
import it.unibo.kingdomclash.config.GameConfiguration;
import it.unibo.view.battle.panels.impl.TextPanelImpl;
import it.unibo.view.map.MapPanel;
import it.unibo.view.map.MapPanelImpl;
import it.unibo.view.menu.GameMenu;
import it.unibo.view.menu.GameMenuImpl;
import it.unibo.view.menu.InfoMenuPanel;
import it.unibo.view.menu.NamePlayerImpl;
import it.unibo.view.menu.SouthPanel;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

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

    /** Used as a symbol to represent the map panel.*/
    public static final String MAP_NAME = "MAP";
    /** Used as a symbol to represent the end panel.*/
    public static final String END_NAME = "END";
    private final CardLayout switchLayout;
    private final CardLayout switchLayout2;
    private final JPanel allPanel;
    private final JPanel mainPanel;
    private final SouthPanel southPanel;
    private final GameMenu menuPanel;
    private final InfoMenuPanel infoPanel;
    private final NamePlayerImpl namePlayer;
    private final MapPanel mapPanel;
    private final SoundManagerImpl soundManagerImpl;
    private final Map<String, JPanel> panel;
    private final JFrame frame;

    /**
     * The constructor initialize all the panels,
     * and creates a system to switch panels.
     * @param gameConfiguration Configuration of the game.
     */
    public GameGui(final GameConfiguration gameConfiguration) {
        this.panel = new HashMap<>();
        this.frame = new JFrame();
        frame.setSize((int) (DIMENSION_SCREEN.getWidth()), (int) (DIMENSION_SCREEN.getHeight()));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.soundManagerImpl = new SoundManagerImpl();
        this.soundManagerImpl.changeMute();

        this.mapPanel = new MapPanelImpl(gameConfiguration);
        final TextPanelImpl endPanel = new TextPanelImpl(getAllPanel(), gameConfiguration.getPathIconsConfiguration());
        endPanel.setTitle("YOU WIN - END GAME");

        this.switchLayout = new CardLayout();
        this.mainPanel = new JPanel(this.switchLayout);

        this.switchLayout2 = new CardLayout();
        this.allPanel = new JPanel(this.switchLayout2);

        final JPanel borderPanel = new JPanel(new BorderLayout());

        this.menuPanel = new GameMenuImpl();
        this.infoPanel = new InfoMenuPanel(gameConfiguration);
        this.namePlayer = new NamePlayerImpl();
        this.southPanel = new SouthPanel();

        this.panel.put(MAP_NAME, this.mapPanel.getAsJPanel());
        this.panel.put(END_NAME, endPanel);
        this.allPanel.add(this.mapPanel.getAsJPanel(), MAP_NAME);
        this.allPanel.add(endPanel, END_NAME);

        borderPanel.add(this.allPanel, BorderLayout.CENTER);
        borderPanel.add(this.southPanel.getPanel(), BorderLayout.SOUTH);

        this.mainPanel.add(this.menuPanel.getPanel(), "1");
        this.mainPanel.add(this.infoPanel.getPanel(), "2");
        this.mainPanel.add(this.namePlayer.getPanel(), "4");
        this.mainPanel.add(borderPanel, "3");

        frame.setContentPane(this.mainPanel);
        frame.setVisible(true);
        frame.revalidate();

        setActionListenerInfo();
        setActionListenerExit();
        setActionListenerMusic();
        showMenuPanel();

    }

    @Override
    public void addPanels(final JPanel panel, final String name) {
        if (!this.panel.containsKey(name)) {
            this.panel.put(name, panel);
            this.allPanel.add(panel, name);
        }
    }

    @Override
    public void showPanels(final String name) {
        if (this.panel.containsKey(name)) {
            switchLayout.show(this.mainPanel, "3");
            switchLayout2.show(this.allPanel, name);
        }
    }

    @Override
    public void showMenuPanel() {
        this.soundManagerImpl.startMenuTheme();
        switchLayout.show(this.mainPanel, "1");
    }

    @Override
    public void showInfoPanel() {
        switchLayout.show(this.mainPanel, "2");
    }

    @Override
    public void showNamePanel() {
        switchLayout.show(this.mainPanel, "4");
    }

    @Override
    public Boolean showNewGameOptions() {
        return JOptionPane.showConfirmDialog(null, "Exist a previous save, if you click"
                        + "new game, you will delete it and start a new save, are you sure?",
                "New Game", JOptionPane.YES_NO_OPTION) == 0;
    }

    @Override
    public void showLoadOptions() {
        JOptionPane.showConfirmDialog(null, "There is no past save",
                "Load Game", JOptionPane.DEFAULT_OPTION);
    }

    @Override
    public void setButtonsVisibilityMenu(final GameMenuImpl.BUTTONSMENU name, final Boolean visibility) {
        this.menuPanel.setButtonsVisibilityMenu(name, visibility);
    }

    @Override
    public void setActionListenerButtons(final ActionListener actionListener, final SouthPanel.BUTTONSSOUTH name) {
        this.southPanel.setActionListenerButtons(actionListener, name);
    }

    @Override
    public void setButtonsVisibility(final SouthPanel.BUTTONSSOUTH name, final Boolean visibility) {
        this.southPanel.setButtonsVisibility(name, visibility);
    }

    @Override
    public void setActionListenerNewGame(final ActionListener actionListener) {
        this.menuPanel.setActionListenerNewGame(actionListener);
    }

    @Override
    public void setActionListenerContinue(final ActionListener actionListener) {
        this.menuPanel.setActionListenerContinue(actionListener);
    }

    @Override
    public void setActionListenerStart(final ActionListener actionListener) {
        this.namePlayer.setActionListenerStart(actionListener);
    }

    @Override
    public void setActionListenerLoad(final ActionListener actionListener) {
        this.menuPanel.setActionListenerLoad(actionListener);
    }

    @Override
    public void setActionListenerExit(final ActionListener actionListener) {
        this.menuPanel.setActionListenerExit(actionListener);
    }

    @Override
    public void setMapBaseActionListener(final ActionListener actionListener) {
        this.mapPanel.setBaseActionListener(actionListener);
    }

    @Override
    public void setMapBattleActionListener(final ActionListener actionListener) {
        this.mapPanel.setBattleActionListener(actionListener);
    }

    @Override
    public void setActivateBattle(final Integer level) {
        this.mapPanel.setActiveBattle(level);
    }

    @Override
    public void setBeatenLevels(final Integer levels) {
        this.mapPanel.setBeatenLevels(levels);
    }

    @Override
    @SuppressFBWarnings(value = "EI",
            justification = "I want to return the object to let other classes"
                    + "getting the reference")
    public SoundManagerImpl getSoundManager() {
        return this.soundManagerImpl;
    }

    @Override
    public String getPlayerName() {
        return this.namePlayer.getPlayerName();
    }

    @Override
    public void closeGui() {
        this.frame.dispose();
    }

    /**
     * Sets the action listener to the music button.
     */
    private void setActionListenerMusic() {
        final ActionListener actionListener = e -> this.soundManagerImpl.changeMute();
        this.menuPanel.setActionListenerMusic(actionListener);
    }

    /**
     * Sets the action listener to the info button.
     */
    private void setActionListenerInfo() {
        final ActionListener actionListener = e -> showInfoPanel();
        this.menuPanel.setActionListenerInfo(actionListener);
    }

    /**
     * Sets the action listener to the exit button in the info panel.
     */
    private void setActionListenerExit() {
        final ActionListener actionListener = e -> showMenuPanel();
        this.infoPanel.setActionListenerExit(actionListener);
    }

    /**
     * Takes the dimension of the main panel.
     * @return The dimension of the panel.
     */
    public static Dimension getAllPanel() {
        final double width = SouthPanel.getMenuPanel().getWidth();
        final double height = DIMENSION_SCREEN.getHeight() - SouthPanel.getMenuPanel().getHeight();
        return new Dimension((int) width, (int) height);
    }

}
