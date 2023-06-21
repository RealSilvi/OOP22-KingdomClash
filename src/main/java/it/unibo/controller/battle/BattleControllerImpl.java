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

import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.List;

import static it.unibo.model.battle.BattleModelImpl.WIN_BOT;
import static it.unibo.model.battle.BattleModelImpl.WIN_PLAYER;

/**
 * The class implements BattleController methods.
 */
public final class BattleControllerImpl implements BattleController, Controller {

    /**
     * Constant use to represent the PLAYER.
     */
    public static final int PLAYER = 1;
    /**
     * Constant use to represent the BOT.
     */
    public static final int BOT = 0;
    /**
     * NO_SKIP means to update all the troops in the field.
     */
    public static final int NO_SKIP = 0;
    /**
     * PLAYER_FINISH It's used to indicate to the bot that the player has clicked all the troops.
     */
    public static final int PLAYER_FINISH = 1;
    /**
     * CONTINUE let the bot know that the player has not yet chosen all the troops.
     */
    public static final int CONTINUE = 0;

    private final BattleModel battleModel;
    private final FightData fightData;
    private final BattlePanelImpl battlePanel;

    private final int playerTroops;
    private final int maxRound;

    /**
     * The constructor takes care to create Objects, disable buttons in panel,
     * and call actionListeners to set functionality to the buttons.
     *
     * @param gameData data of the game.
     */
    public BattleControllerImpl(final GameData gameData) {
        if (gameData.getFightData() != null) {
            this.fightData = gameData.getFightData();
        } else {
            this.fightData = new FightData(gameData.getGameConfiguration().getBattleConfiguration());
            gameData.setFightData(this.fightData);
        }
        this.playerTroops = gameData.getGameConfiguration().getBattleConfiguration().getNrOfSlots();
        this.maxRound = gameData.getGameConfiguration().getBattleConfiguration().getMaxRound();
        this.battleModel = new BattleModelImpl(gameData);

        this.battlePanel = new BattlePanelImpl(
                gameData.getGameConfiguration().getBattleConfiguration(),
                gameData.getGameConfiguration().getPathIconsConfiguration());
        this.battlePanel.spinBotFreeSlot(fightData.getBotData().changeNotSelectedTroop());
        this.battlePanel.spinPlayerFreeSlot(fightData.getPlayerData().changeNotSelectedTroop());
        this.battlePanel.disableSpinButton();
        this.battlePanel.disableBotSlots();
        this.battlePanel.drawInfoTable(this.battleModel.getInfoTable());
        this.setActionListenerSpin();
        this.setActionListenerPass();
        this.setActionListenerSlots();

    }

    @Override
    public void pass() {
        this.battlePanel.enableBotSlots();
        this.battlePanel.disablePassButton();
        this.battlePanel.disablePlayerSlots();
        this.battlePanel.disableSpinButton();
        battlePanel.spinBotFreeSlot(this.battleModel.battleSpin(BOT));
        if (fightData.getPlayerData().getSelected().size() == playerTroops) {
            for (int i = this.battleModel.getCountedRound(); i < this.maxRound; i++) {
                this.battleModel.battlePass(PLAYER_FINISH);
                update(NO_SKIP);
            }
        } else {
            this.battleModel.battlePass(CONTINUE);
            update(NO_SKIP);
        }

        if (this.battleModel.getCountedRound() == this.maxRound) {
            battle();
        } else if (fightData.getPlayerData().getSelected().size() < playerTroops) {
            this.battlePanel.enableSpinButton();
        }
        this.battlePanel.disableBotSlots();
    }

    @Override
    public void spin() {
        battlePanel.disableSpinButton();
        this.battlePanel.enablePassButton();
        battlePanel.spinPlayerFreeSlot(this.battleModel.battleSpin(PLAYER));
    }

    @Override
    public void battle() {
        int total = EntityDataImpl.getOrderedField(fightData.getPlayerData(), fightData.getBotData()).size() / 2;
        update(NO_SKIP);
        int cont = 0;
        for (int i = 0; i < total; i++) {
            int value = this.battleModel.battleCombat(i);
            if (value == BOT) {
                botLifeDecrease();
            } else if (value == PLAYER) {
                playerLifeDecrease();
            } else if (value == WIN_BOT) {
                end(WIN_BOT);
                i = total;
                cont = 1;
            } else if (value == WIN_PLAYER) {
                end(WIN_PLAYER);
                i = total;
                cont = 1;
            }
            if (i != total) {
                update(i + 1);
            }
        }
        if (cont == 0) {
            this.battleModel.reset();
            update(NO_SKIP);
        }
    }

    @Override
    public void end(final Integer entity) {
        this.battleModel.endFight(entity == WIN_PLAYER);
        spin();
        this.battleModel.battleSpin(BOT);
        this.battlePanel.disableSpinButton();
        this.battlePanel.disableBotSlots();
        this.battlePanel.enablePassButton();
        this.battlePanel.enablePlayerSlots();
        this.battlePanel.showEndPanel(entity == WIN_PLAYER);
        this.battlePanel.drawInfoTable(this.battleModel.getInfoTable());
    }

    @Override
    public void clickedButtonPlayer(final Integer key) {
        if (Boolean.TRUE.equals(fightData.getPlayerData().getCells(key).getClicked())) {
            fightData.getPlayerData().removeEntityTroop(key);
        } else {
            fightData.getPlayerData().addEntityTroop(key);
        }
        update(NO_SKIP);
    }

    @Override
    public void update(final Integer skip) {
        long delay = 100L * skip;
        Timer timer = new Timer();
        List<Optional<TroopType>> orderedList = EntityDataImpl.exOrdered(fightData.getBotData(), fightData.getPlayerData());
        List<Optional<TroopType>> pList = new ArrayList<>(orderedList.subList(0, orderedList.size() / 2));
        List<Optional<TroopType>> bList = new ArrayList<>(orderedList.subList(orderedList.size() / 2, orderedList.size()));
        if (skip > 0) {
            for (int a = 0; a < skip; a++) {
                pList.set(a, Optional.empty());
                bList.set(a, Optional.empty());
            }
        }
        bList.addAll(pList);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (skip == (pList.size())) {
                    battlePanel.enableSpinButton();
                }
                battlePanel.updateField(bList);
            }
        }, delay);

    }

    @Override
    public void playerLifeDecrease() {
        battlePanel.hitPlayer();
    }

    @Override
    public void botLifeDecrease() {
        battlePanel.hitBot();
    }

    @Override
    public JPanel getGuiPanel() {
        return this.battlePanel.getPanel();
    }

    @Override
    public void setReturnActionListener(final ActionListener returnActionToAdd) {
        this.battlePanel.setBackActionListener(returnActionToAdd);
    }

    private void setActionListenerPass() {
        ActionListener actionListenerInfo = e -> pass();
        this.battlePanel.setActionListenerPass(actionListenerInfo);
    }

    private void setActionListenerSpin() {
        ActionListener actionListenerInfo = e -> spin();
        this.battlePanel.setActionListenerSpinButton(actionListenerInfo);
    }

    @SuppressWarnings(value = "unchecked")
    private void setActionListenerSlots() {
        ActionListener actionListenerInfo = e -> {
            if (e.getSource() instanceof TroopButtonImpl.DataJButton<?>) {
                var button = (TroopButtonImpl.DataJButton<Integer>) e.getSource();
                clickedButtonPlayer(button.getData());
                button.updateBorder();
            }
        };
        this.battlePanel.setActionListenersPlayerSlot(actionListenerInfo);
    }


}
