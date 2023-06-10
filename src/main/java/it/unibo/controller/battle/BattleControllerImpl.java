package it.unibo.controller.battle;

import it.unibo.controller.Controller;
import it.unibo.model.battle.BattleModel;
import it.unibo.model.battle.BattleModelImpl;
import it.unibo.model.battle.entitydata.EntityDataImpl;
import it.unibo.model.data.FightData;
import it.unibo.model.data.GameData;
import it.unibo.model.data.TroopType;
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
    private final FightData fightData;
    private final BattlePanelImpl battlePanel;


    public BattleControllerImpl(GameData gameData) {
        if (gameData.getFightData() != null) {
            this.fightData = gameData.getFightData();
        }else{
            this.fightData= new FightData();
            gameData.setFightData(this.fightData);
        }
        this.battleModel = new BattleModelImpl(gameData);
        this.battlePanel = new BattlePanelImpl(fightData.getBotData().changeNotSelectedTroop(), fightData.getPlayerData().changeNotSelectedTroop(), gameData.getGameConfiguration().getBattleControllerConfiguration());
        this.battlePanel.disableSpinButton();
        this.battlePanel.disableBotSlots();
        this.battlePanel.drawInfoTable(this.battleModel.getInfoTable());
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
        if (fightData.getPlayerData().getSelected().size() == PLAYER_TROOPS) {
            for (int i = this.battleModel.getCountedRound(); i < MAX_ROUND; i++) {
                this.battleModel.battlePass(PLAYER_FINISH);
                update(NO_SKIP);
            }
        } else {
            this.battleModel.battlePass(CONTINUE);
            update(NO_SKIP);
        }

        if (this.battleModel.getCountedRound() == MAX_ROUND) {
            battle();
        } else if (fightData.getPlayerData().getSelected().size() < PLAYER_TROOPS) {
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
        int total = EntityDataImpl.getOrderedField(fightData.getPlayerData(), fightData.getBotData()).size()/2;
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
                i = total;
            } else if (value == WIN_PLAYER) {
                System.out.println("win player");
                end(WIN_PLAYER);
                i = total;
            }
            update(i + 1);
        }
        this.battleModel.reset();
        battlePanel.spinBotFreeSlot(this.battleModel.battleSpin(BOT));
        spin();
        update(NO_SKIP);
    }


    public void end(Integer entity) {
        this.battleModel.endFight(entity == WIN_PLAYER);
        this.battlePanel.showEndPanel();
        this.battlePanel.reset();
        this.battlePanel.drawInfoTable(this.battleModel.getInfoTable());
    }

    public void clickedButtonPlayer(Integer key) {
        if (fightData.getPlayerData().getCells(key).getClicked()) {
            fightData.getPlayerData().removeEntityTroop(key);
        } else {
            fightData.getPlayerData().addEntityTroop(key);
        }
        update(NO_SKIP);
    }

    public void update(Integer skip) {
        List<Optional<TroopType>> orderedList = EntityDataImpl.ExOrdered(fightData.getBotData(), fightData.getPlayerData());
        List<Optional<TroopType>> pList = new ArrayList<>(orderedList.subList(0, orderedList.size() / 2));
        List<Optional<TroopType>> bList = new ArrayList<>(orderedList.subList(orderedList.size() / 2, orderedList.size()));
        if(skip > 0){
            for(int a = 0; a < skip; a++){
                pList.set(a,Optional.empty());
                bList.set(a,Optional.empty());
            }
        }
        bList.addAll(pList);
        System.out.println("\n\nORDERED LIST" +orderedList + "\n\n");
        //Collections.reverse(orderedList);
        battlePanel.updateField(bList);
    }

    public void playerLifeDecrease() {
        battlePanel.hitPlayer();
    }

    public void botLifeDecrease() {
        battlePanel.hitBot();
    }

    public JPanel getGuiPanel() {
        return this.battlePanel.getPanel();
    }


    private void setActionListenerPass() {
        ActionListener actionListenerInfo = e -> pass();
        this.battlePanel.setActionListenerPass(actionListenerInfo);
    }

    private void setActionListenerSpin() {
        ActionListener actionListenerInfo = e -> spin();
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
