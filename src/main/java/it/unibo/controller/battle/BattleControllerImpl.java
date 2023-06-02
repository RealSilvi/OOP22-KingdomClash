package it.unibo.controller.battle;

import it.unibo.model.battle.BattleModel;
import it.unibo.model.battle.BattleModelImpl;
import it.unibo.model.data.FightData;
import it.unibo.model.data.GameData;
import it.unibo.view.battle.BattlePanel;
import it.unibo.view.battle.BattlePanelImpl;
import it.unibo.view.battle.panels.entities.impl.TroopButtonImpl;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.*;

import static it.unibo.model.battle.BattleModelImpl.*;
import static it.unibo.model.data.FightData.PLAYER_TROOPS;

public class BattleControllerImpl implements BattleController {

    private JFrame frame;
    private final static int nrOfSlots = 5;
    private final static int nrOfTroops = 8;
    private final static int nrOfLives = 8;
    private final static int nrOfFieldSpots = nrOfSlots * 2;

    public static final int PLAYER = 1;
    public static final int NOSKIP = 0;
    public static final int PLAYER_FINISH = 1;
    public static final int CONTINUE = 0;

    private JPanel currentPanel;
    private final BattleModel battleModel;
    private Optional<FightData> fightData;
    private final BattlePanelImpl battlePanel;

    public BattleControllerImpl(BattleModel battleModel, GameData gameData) {
        this.battleModel = battleModel;
        this.battlePanel = new BattlePanelImpl(nrOfFieldSpots, nrOfSlots, nrOfTroops, nrOfLives, fightData.get().getBotData().changeNotSelectedTroop(), fightData.get().getPlayerData().changeNotSelectedTroop());
        if (gameData.getFightData().isPresent()) {
            this.fightData = gameData.getFightData();
        }
    }

    public BattleControllerImpl(GameData gameData) {
        this.battleModel = new BattleModelImpl(gameData);
        this.battlePanel = new BattlePanelImpl(nrOfFieldSpots, nrOfSlots, nrOfTroops, nrOfLives, fightData.get().getBotData().changeNotSelectedTroop(), fightData.get().getPlayerData().changeNotSelectedTroop());
        if (gameData.getFightData().isPresent()) {
            this.fightData = gameData.getFightData();
        }
    }

    public BattleControllerImpl(Optional<FightData> fightData) {
        this.battleModel = new BattleModelImpl(fightData);
        this.battlePanel = new BattlePanelImpl(nrOfFieldSpots, nrOfSlots, nrOfTroops, nrOfLives, fightData.get().getBotData().changeNotSelectedTroop(), fightData.get().getPlayerData().changeNotSelectedTroop());
        this.battlePanel.disableSpinButton();
        this.setActionListenerInfo();
        this.setActionListenerSpin();
        this.setActionListenerPass();
        this.setActionListenerSlots();
        this.fightData = fightData;


        this.frame = new JFrame();
        currentPanel = battlePanel.getPanel();
        this.frame.getContentPane().add(currentPanel);

    }

    public void pass() {
        this.battlePanel.enableBotSlots();
        this.battlePanel.disablePassButton();
        this.battlePanel.disablePlayerSlots();
        this.battlePanel.disableSpinButton();
        battlePanel.spinBotFreeSlot(this.battleModel.battleSpin(BOT));
        if (fightData.get().getPlayerData().getSelected().size() == PLAYER_TROOPS) {
            for (int i = this.battleModel.getCountedRound(); i < MAX_ROUND; i++) {
                this.battleModel.battlePass(PLAYER_FINISH);
                update(NOSKIP);
            }
        } else {
            this.battleModel.battlePass(CONTINUE);
        }
        update(NOSKIP);
        if (this.battleModel.getCountedRound() == MAX_ROUND) {
            battle();
        } else if (fightData.get().getPlayerData().getSelected().size() < PLAYER_TROOPS) {
            this.battlePanel.enableSpinButton();
        }
        this.battlePanel.disableBotSlots();
    }

    public void spin() {
        battlePanel.disableSpinButton();
        this.battlePanel.enablePassButton();
        battlePanel.spinPlayerFreeSlot(this.battleModel.battleSpin(PLAYER));
    }

    public void battle() {
        int total = 0;
        if (fightData.get().getBotData().getOrderedField(fightData.get().getPlayerData()).size() >
                fightData.get().getPlayerData().getOrderedField(fightData.get().getBotData()).size()) {
            total = fightData.get().getBotData().getOrderedField(fightData.get().getPlayerData()).size();
        } else {
            total = fightData.get().getPlayerData().getOrderedField(fightData.get().getBotData()).size();
        }
        update(NOSKIP);
        for (int i = 0; i < total; i++) {
            if (this.battleModel.battleCombat(i) == BOT) {
                botLifeDecrease();
            } else if (this.battleModel.battleCombat(i) == PLAYER) {
                playerLifeDecrease();
            } else if (this.battleModel.battleCombat(i) == WIN_BOT) {
                end(WIN_BOT);
            } else if (this.battleModel.battleCombat(i) == WIN_PLAYER) {
                end(WIN_PLAYER);
            }
            update(i + 1);
        }
        this.battleModel.reset();
        battlePanel.spinBotFreeSlot(this.battleModel.battleSpin(BOT));
        spin();
        update(NOSKIP);

    }

    public void end(Integer entity) {

    }

    public void clickedButtonPlayer(Integer key) {
        if (fightData.get().getPlayerData().getCells(key).getClicked()) {
            fightData.get().getPlayerData().removePlayerTroop(key);
            update(NOSKIP);
        } else {
            fightData.get().getPlayerData().addPlayerTroop(key);
            update(NOSKIP);
        }
    }

    public void update(Integer skip) {
        battlePanel.updateField(fightData.get().getPlayerData().ExOrdered(fightData.get().getBotData()).stream().skip(skip).toList(),
                fightData.get().getBotData().ExOrdered(fightData.get().getPlayerData()).stream().skip(skip).toList());
    }

    public void playerLifeDecrease() {
        battlePanel.hitPlayer();
    }

    public void botLifeDecrease() {
        battlePanel.hitBot();
    }

    public BattleModel getBattleModel() {
        return battleModel;
    }

    public Optional<FightData> getFightData() {
        return fightData;
    }

    public BattlePanel getBattlePanel() {
        return battlePanel;
    }

    public JPanel getCurrentPanel() {
        return this.currentPanel;
    }

    //TESTING
    public JFrame getFrame() {
        return frame;
    }

    private void setActionListenerInfo() {
        ActionListener actionListenerInfo = e -> this.battlePanel.showTutorialPanel();
        this.battlePanel.setActionListenerInfoButton(actionListenerInfo);
    }


    private void setActionListenerPass() {
        ActionListener actionListenerInfo = e -> pass();
        this.battlePanel.setActionListenerPass(actionListenerInfo);
    }

    private void setActionListenerSpin() {
        ActionListener actionListenerInfo = e -> {
            spin();
        };
        this.battlePanel.setActionListenerSpinButton(actionListenerInfo);
    }

    private void setActionListenerSlots() {
        ActionListener actionListenerInfo = e -> {
            TroopButtonImpl.PositionJbutton button = (TroopButtonImpl.PositionJbutton) e.getSource();
            clickedButtonPlayer(button.getPosition());
            button.updateBorder();
        };
        this.battlePanel.setActionListenersPlayerSlot(actionListenerInfo);
    }

}
