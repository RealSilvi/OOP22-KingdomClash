package it.unibo.controller;

import java.util.logging.Logger;

import it.unibo.model.GameModel;
import it.unibo.view.menu.GameMenu;

public class GameController {
    public enum GameStates {
        MENU,
        BASE,
        MAP,
        BATTLE
    }

    private Logger logger = Logger.getLogger(this.getClass().getName());

    private GameModel gameModel;
    private GameMenu gameMenu;

    private Controller baseController;
    private Controller battleController;

    public GameController(GameModel gameModel, GameMenu gameMenu) {
        this.gameModel = gameModel;
        this.gameMenu = gameMenu;
    }

    public void setGameState(GameStates gameState) {

    }
}