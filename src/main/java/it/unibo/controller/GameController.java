package it.unibo.controller;


import it.unibo.controller.base.BaseControllerImpl;
import it.unibo.controller.battle.BattleControllerImpl;
import it.unibo.model.GameModel;
import it.unibo.view.GameGui;

import java.awt.event.ActionListener;

public class GameController {

    final GameModel gameModel;
    final GameGui gameGui;

    final Controller battleController;
    final Controller baseController;

    ActionListener toMainPanel;

    public GameController() {

        this.gameModel = new GameModel();

        this.battleController = new BattleControllerImpl(gameModel.getGameData());
        this.baseController = new BaseControllerImpl(gameModel.getGameData());

        this.gameGui = new GameGui(battleController.getGuiPanel(), baseController.getGuiPanel(), gameModel.getGameData().getGameConfiguration());

        this.toMainPanel = this.backActionListener();
        battleController.setReturnActionListener(toMainPanel);
        baseController.setReturnActionListener(toMainPanel);

        this.setActionListenerNewGame();
        this.setActionListenerBattle();
        this.setActionListenerMap();
        this.setActionListenerCity();

        gameGui.setBeatenLevels(gameModel.getCurrentLevel() - 1);
        gameGui.setActivateBattle(gameModel.getCurrentLevel());
    }

    private void setActionListenerNewGame() {
        final ActionListener actionListener = e -> this.gameGui.showCity();
        this.gameGui.setActionListenerNewGame(actionListener);
    }

    private void setActionListenerCity() {
        this.gameGui.setActionListenerCity(e -> this.gameGui.showCity());
    }

    private void setActionListenerMap() {
        this.gameGui.setActionListenerMap(e -> this.gameGui.showMap());
    }

    private void setActionListenerBattle() {
        this.gameGui.setActionListenerBattle(e -> this.gameGui.showBattle());
    }

    private ActionListener backActionListener() {
        return e -> {
            this.gameGui.showMap();
            this.gameGui.setActivateBattle(gameModel.getCurrentLevel());
            this.gameGui.setBeatenLevels(gameModel.getCurrentLevel() - 1);
        };
    }

}