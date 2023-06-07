package it.unibo.controller.battle;

import it.unibo.controller.Controller;
import it.unibo.model.battle.BattleModel;
import it.unibo.model.battle.BattleModelImpl;
import it.unibo.model.battle.entitydata.EntityDataImpl;
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

public class BattleControllerImpl implements BattleController, Controller {

    public static final int PLAYER = 1;
    public static final int BOT = 0;
    public static final int NO_SKIP = 0;
    public static final int PLAYER_FINISH = 1;
    public static final int CONTINUE = 0;

    private final BattleModel battleModel;
    private Optional<FightData> fightData;
    private final BattlePanelImpl battlePanel;


    public BattleControllerImpl(GameData gameData) {
        if (gameData.getFightData().isPresent()) {
            this.fightData = gameData.getFightData();
        }else{
            this.fightData= Optional.of(new FightData());
            gameData.setFightData(this.fightData);
        }
        this.battleModel = new BattleModelImpl(gameData);
        this.battlePanel = new BattlePanelImpl(fightData.get().getBotData().changeNotSelectedTroop(), fightData.get().getPlayerData().changeNotSelectedTroop(), gameData.getGameConfiguration().getBattleControllerConfiguration());
        this.battlePanel.disableSpinButton();
        this.setActionListenerSpin();
        this.setActionListenerPass();
        this.setActionListenerSlots();

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
                update(NO_SKIP);
            }
        } else {
            this.battleModel.battlePass(CONTINUE);
        }
        update(NO_SKIP);
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
        int total = Math.max(EntityDataImpl.getOrderedField(fightData.get().getPlayerData(), fightData.get().getBotData(), BOT).size(),
                EntityDataImpl.getOrderedField(fightData.get().getPlayerData(), fightData.get().getBotData(), PLAYER).size());
        update(NO_SKIP);
        for (int i = 0; i < total; i++) {
            int value = this.battleModel.battleCombat(i);
            System.out.println("total: " + total);
            if (value == BOT) {
                botLifeDecrease();
            } else if (value == PLAYER) {
                playerLifeDecrease();
            } else if (value == WIN_BOT) {
                System.out.println("win bot");
                end(WIN_BOT);
            } else if (value == WIN_PLAYER) {
                System.out.println("win player");
                end(WIN_PLAYER);
            }
            update(i + 1);
        }
        this.battleModel.reset();
        battlePanel.spinBotFreeSlot(this.battleModel.battleSpin(BOT));
        spin();
        update(NO_SKIP);

    }

    public void end(Integer entity) {
        if(entity == WIN_PLAYER){
            this.battleModel.endFight(true);
        }
        this.battlePanel.showEndPanel();
    }

    public void clickedButtonPlayer(Integer key) {
        if (fightData.get().getPlayerData().getCells(key).getClicked()) {
            fightData.get().getPlayerData().removeEntityTroop(key);
            update(NO_SKIP);
        } else {
            fightData.get().getPlayerData().addEntityTroop(key);
            update(NO_SKIP);
        }
    }

    public void update(Integer skip) {
        battlePanel.updateField(EntityDataImpl.ExOrdered(fightData.get().getBotData(), fightData.get().getPlayerData(), PLAYER).stream().skip(skip).toList(),
                EntityDataImpl.ExOrdered(fightData.get().getBotData(), fightData.get().getPlayerData(), BOT).stream().skip(skip).toList());
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

    public JPanel getGuiPanel() {
        return this.battlePanel.getPanel();
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
