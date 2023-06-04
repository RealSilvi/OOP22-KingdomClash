package it.unibo.controller;

import java.util.logging.Logger;

import it.unibo.controller.base.BaseControllerImpl;
import it.unibo.controller.battle.BattleControllerImpl;
import it.unibo.model.GameModel;
import it.unibo.model.data.GameData;
import it.unibo.view.View;
import it.unibo.view.menu.GameMenu;
import it.unibo.view.menu.GameMenuImpl;

public class GameController {
//    public enum GameStates {
//        MENU,
//        BASE,
//        MAP,
//        BATTLE
//    }

//    private Logger logger = Logger.getLogger(this.getClass().getName());

    private GameModel gameModel;
    private View gameGui;

    private Controller baseController;
    private Controller battleController;

    public GameController() {
        this.gameModel = new GameModel();
        this.gameGui = new View();

        this.battleController= new BattleControllerImpl(gameModel.getGameData());
        this.baseController = new BaseControllerImpl(gameModel.getGameData());
    }

//    public void setGameState(GameStates gameState) {
//
//    }
}