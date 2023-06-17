package it.unibo.view;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.controller.SoundManager;
import it.unibo.kingdomclash.config.GameConfiguration;
import it.unibo.view.map.MapPanel;
import it.unibo.view.map.MapPanelImpl;
import it.unibo.view.menu.*;

import javax.swing.*;
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
    public static final String MAP_NAME = "MAP" ;
    private final JFrame frame;
    private final CardLayout switchLayout;
    private final CardLayout switchLayout2;
    private final JPanel allPanel;
    private final JPanel mainPanel;
    private final SouthPanel southPanel;
    private final GameMenu menuPanel;
    private final InfoMenuPanel infoPanel;
    private final NamePlayerImpl namePlayer;
    private final MapPanel mapPanel;
    private final SoundManager soundManager;
    private final Map<String, JPanel> panel;

    /**
     * The constructor initialize all the panels,
     * and creates a system to switch panels.
     * @param gameConfiguration Configuration of the game.
     */
    public GameGui(final GameConfiguration gameConfiguration) {
        this.panel = new HashMap<>();
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
        this.namePlayer = new NamePlayerImpl();
        this.southPanel = new SouthPanel();

        this.panel.put(MAP_NAME,this.mapPanel.getAsJPanel());
        this.allPanel.add(this.mapPanel.getAsJPanel(),MAP_NAME);

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
        setActionListenerMenu();
        setActionListenerBack();
        showMenuPanel();

    }

    @Override
    public void addPanels(JPanel panel, String name) {
        if(!this.panel.containsKey(name)){
            this.panel.put(name, panel);
            this.allPanel.add(panel, name);
        }
    }

    @Override
    public void showPanels(String name) {
        if(this.panel.containsKey(name)){
            switchLayout.show(this.mainPanel, "3");
            switchLayout2.show(this.allPanel, name);
        }
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
    public void showNamePanel(){
        switchLayout.show(this.mainPanel, "4");
    }

    @Override
    public Boolean showNewGameOptions(){
        return JOptionPane.showConfirmDialog(null, "Exist a previous save, if you click" +
                        "new game, you will delete it and start a new save, are you sure?",
                "New Game", JOptionPane.YES_NO_OPTION) == 0;
    }

    @Override
    public void showLoadOptions(){
        JOptionPane.showConfirmDialog(null, "There is no past save",
                "Load Game", JOptionPane.DEFAULT_OPTION);
    }

    @Override
    public void setActionListenerButtons(ActionListener actionListener, SouthPanel.BUTTONS_NAME name){
        this.southPanel.setActionListenerButtons(actionListener, name);
    }

    @Override
    public void setButtonsVisibility(SouthPanel.BUTTONS_NAME name, Boolean visibility){
        this.southPanel.setButtonsVisibility(name, visibility);
    }

    @Override
    public void setActionListenerNewGame(ActionListener actionListener) {
        this.menuPanel.setActionListenerNewGame(actionListener);

    }

    @Override
    public void setActionListenerStart(ActionListener actionListener){
        this.namePlayer.setActionListenerStart(actionListener);
    }

    @Override
    public void setActionListenerSave(ActionListener actionListener) {
        this.southPanel.setActionListenerButtons(actionListener, SouthPanel.BUTTONS_NAME.SAVE);
    }

    @Override
    public void setActionListenerLoad(ActionListener actionListener){
        this.menuPanel.setActionListenerLoad(actionListener);
    }

    @Override
    public void setMapBaseActionListener(ActionListener actionListener) {
        this.mapPanel.setBaseActionListener(actionListener);
    }

    @Override
    public void setMapBattleActionListener(ActionListener actionListener) {
        this.mapPanel.setBattleActionListener(actionListener);
    }

    @Override
    public void setActionListenerQuit(ActionListener actionListener) {
        this.southPanel.setActionListenerButtons(actionListener, SouthPanel.BUTTONS_NAME.QUIT);
        this.menuPanel.setActionListenerExit(actionListener);
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
            justification = "I want to return the object to let other classes" +
                    "getting the reference")
    public SoundManager getSoundManager() {
        return this.soundManager;
    }

    private void setActionListenerBack(){
        ActionListener actionListener = e -> showMenuPanel();
        this.namePlayer.setActionListenerBack(actionListener);
    }

    private void setActionListenerMusic() {
        ActionListener actionListener = e -> this.soundManager.changeMute();
        this.menuPanel.setActionListenerMusic(actionListener);
        this.southPanel.setActionListenerButtons(actionListener, SouthPanel.BUTTONS_NAME.MUSIC);
    }

    private void setActionListenerInfo() {
        ActionListener actionListener = e -> showInfoPanel();
        this.menuPanel.setActionListenerInfo(actionListener);
    }

    private void setActionListenerExit(){
        ActionListener actionListener = e -> showMenuPanel();
        this.infoPanel.setActionListenerExit(actionListener);
    }

    private void setActionListenerMenu() {
        ActionListener actionListener = e -> showMenuPanel();
        this.southPanel.setActionListenerButtons(actionListener, SouthPanel.BUTTONS_NAME.MENU);
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
