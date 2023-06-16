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
        this.setActionListenerName();
        this.setActionListenerSave();
        this.setActionListenerMap();
        this.setActionListenerCity();
        this.setActionListenerLoad();

        gameGui.setBeatenLevels(gameModel.getCurrentLevel() - 1);
        gameGui.setActivateBattle(gameModel.getCurrentLevel());
    }

    private void setActionListenerNewGame() {
        final ActionListener actionListener = e -> {
            if (this.gameModel.isSaved()) {
                if (this.gameGui.showNewGameOptions()) {
                    this.gameModel.newGame();
                    this.gameGui.showNamePanel();
                }
            } else {
                this.gameGui.showNamePanel();
            }

        };
        this.gameGui.setActionListenerNewGame(actionListener);
    }

    private void setActionListenerLoad(){
        this.gameGui.setActionListenerLoad(e-> {
            if(this.gameModel.isSaved()){
                this.gameGui.showCity();
            }else{
                this.gameGui.showLoadOptions();
            }
        });
    }

    private void setActionListenerName() {
        final ActionListener actionListener = e -> this.gameGui.showCity();
        this.gameGui.setActionListenerStart(actionListener);
    }

    private void setActionListenerCity() {
        this.gameGui.setActionListenerCity(e -> this.gameGui.showCity());
    }

    private void setActionListenerMap() {
        this.gameGui.setActionListenerMap(e -> this.gameGui.showMap());
    }

    private void setActionListenerSave() {
        this.gameGui.setActionListenerSave(e -> {
            this.gameModel.serializeGameData();
        });
    }

    private ActionListener backActionListener() {
        return e -> {
            this.gameGui.showMap();
            this.gameGui.setActivateBattle(gameModel.getCurrentLevel());
            this.gameGui.setBeatenLevels(gameModel.getCurrentLevel() - 1);
        };
    }

}