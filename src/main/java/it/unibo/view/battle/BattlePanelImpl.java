package it.unibo.view.battle;

import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.battle.panels.impl.*;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;
import it.unibo.view.battle.panels.utilities.PanelDimensions;
import it.unibo.view.battle.tutorial.TutorialPanel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.*;

public final class BattlePanelImpl implements BattlePanel {

    private final static int BORDER_LAYOUT_GAP = 3;


    private final CardLayout layoutManager;
    private final JPanel mainPanel;
    private final TutorialPanel tutorialPanel;

    private final JPanel gamePanel;

    //private final JPanel MenuPanel;
    private final FieldPanelImpl fieldPanel;
    private final PlayerPanelImpl botPanel;
    private final PlayerPanelImpl playerPanel;
    private final InfoPanelImpl infoPanel;
    private final CommandPanelImpl buttonsPanel;

    /**
     *
     * param nrOfSlots  How many slots has each player.
     * param nrOfTroops How many troops has the game.
     * param nrOfLives  How many lives has each player
     */
    public BattlePanelImpl(final int nrOfFieldSpots, final int nrOfSlots, final int nrOfTroops,final int nrOfLives,final Map<Integer,Troop> botTroops,final Map<Integer,Troop> playerTroops){
        this.layoutManager=new CardLayout();
        this.mainPanel=new JPanel(this.layoutManager);
        this.tutorialPanel=new TutorialPanel();
        this.gamePanel= new DrawPanel(ImageIconsSupplier.DEFAULT_COLOR, PanelDimensions.SCREEN_SIZE);
        this.gamePanel.setLayout(new BorderLayout(BORDER_LAYOUT_GAP,BORDER_LAYOUT_GAP));

        final JPanel topPanel = new JPanel(new BorderLayout(BORDER_LAYOUT_GAP,BORDER_LAYOUT_GAP));

        //this.menuPanel = new MenuPanel();
        this.botPanel = new PlayerPanelImpl(botTroops,nrOfSlots);
        this.playerPanel = new PlayerPanelImpl(playerTroops,nrOfSlots);
        this.infoPanel = new InfoPanelImpl(nrOfTroops);
        this.buttonsPanel = new CommandPanelImpl(nrOfLives);
        this.fieldPanel = new FieldPanelImpl(nrOfFieldSpots);

        //topPanel.add(menuPanel, BorderLayout.NORTH);
        topPanel.add(new JPanel().add(new JButton("QUA CI SARA IL MENU")),BorderLayout.NORTH);
        topPanel.add(botPanel.getPanel(), BorderLayout.SOUTH);

        this.gamePanel.add(topPanel,BorderLayout.NORTH);
        this.gamePanel.add(playerPanel.getPanel(),BorderLayout.SOUTH);
        this.gamePanel.add(infoPanel.getPanel(),BorderLayout.WEST);
        this.gamePanel.add(buttonsPanel.getPanel(),BorderLayout.EAST);
        this.gamePanel.add(fieldPanel.getPanel(),BorderLayout.CENTER);

        this.mainPanel.add(gamePanel, "1");
        this.mainPanel.add(tutorialPanel.getPanel(),"2");

        this.setActionListenerExit();
    }

    public void showTutorialPanel(){
        layoutManager.show(mainPanel,"2");
    }

    public void showGamePanel(){
        layoutManager.show(mainPanel,"1");
    }

    @Override
    public void hitPlayer() {
        this.buttonsPanel.decreasePlayerLive();
    }

    @Override
    public void hitBot() {
        this.buttonsPanel.decreaseBotLive();
    }

    @Override
    public void spinPlayerFreeSlot(final Map<Integer,Troop> troops) {
        this.playerPanel.update(troops);
    }

    @Override
    public void spinBotFreeSlot(final Map<Integer,Troop> troops) {
        this.botPanel.update(troops);
    }

    @Override
    public void drawInfoTable(final Map<Troop, Boolean> troopLv) {
        this.infoPanel.drawTable(troopLv);
    }

    @Override
    public void updateField(final List<Optional<Troop>> playerTroops,final List<Optional<Troop>> botPlayer) {
        this.fieldPanel.redraw(playerTroops,botPlayer);
    }

    @Override
    public void disableBotSlots() {
        this.botPanel.disableAllSlots();
    }

    @Override
    public void enableBotSlots() {
        this.botPanel.enableAllSlots();
    }

    @Override
    public void disablePlayerSlots() {
        this.playerPanel.disableAllSlots();
    }

    @Override
    public void enablePlayerSlots() {
        this.playerPanel.enableAllSlots();
    }

    @Override
    public void disableSpinButton() {
        this.buttonsPanel.disableSpinButton();
    }

    @Override
    public void enableSpinButton() {
        this.buttonsPanel.enableSpinButton();
    }

    @Override
    public void disablePassButton() {
        this.buttonsPanel.disablePassButton();
    }

    @Override
    public void enablePassButton() {
        this.buttonsPanel.enablePassButton();
    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

    public void setActionListenersPlayerSlot(final ActionListener actionListener){
        this.playerPanel.setActionListenersSlot(actionListener);
    }

    public void setActionListenerSpinButton(final ActionListener actionListener){
        this.buttonsPanel.setActionListenerSpin(actionListener);
    }

    public void setActionListenerInfoButton(final ActionListener actionListener){
        this.buttonsPanel.setActionListenerInfo(actionListener);
    }

    public void setActionListenerPass(final ActionListener actionListener){
        this.buttonsPanel.setActionListenerPass(actionListener);
    }

    private void setActionListenerExit(){
        ActionListener actionListenerInfo = e -> { this.showGamePanel();};
        this.tutorialPanel.addActionListenerExit(actionListenerInfo);
    }
}
