package it.unibo.controller;


import it.unibo.controller.base.BaseControllerImpl;
import it.unibo.controller.battle.BattleControllerImpl;
import it.unibo.model.GameModel;
import it.unibo.view.GameGui;

public class GameController {

    public GameController() {

        GameModel gameModel = new GameModel();

        Controller battleController = new BattleControllerImpl(gameModel.getGameData());
        Controller baseController = new BaseControllerImpl(gameModel.getGameData());

        GameGui gameGui = new GameGui(battleController.getGuiPanel(), baseController.getGuiPanel(), gameModel.getGameData().getGameConfiguration());
        baseController.setReturnActionListener(gameGui.getActionListenerMap());
        gameGui.setBeatenLevels(0);
        gameGui.setActivateBattle(1);
    }

}