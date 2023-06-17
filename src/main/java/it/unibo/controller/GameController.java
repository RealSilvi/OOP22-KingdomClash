package it.unibo.controller;


import it.unibo.controller.base.BaseControllerImpl;
import it.unibo.controller.battle.BattleControllerImpl;
import it.unibo.model.GameModel;
import it.unibo.view.GameGui;

import java.awt.event.ActionListener;

public class GameController {

    private enum PANELS_NAME {
        BATTLE("BATTLE"),
        CITY("CITY");

        private String name;

        PANELS_NAME(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    final GameModel gameModel;
    final GameGui gameGui;

    Controller battleController;
    Controller baseController;

    ActionListener toMainPanel;

    public GameController() {

        this.gameModel = new GameModel();

//        this.battleController = new BattleControllerImpl(gameModel.getGameData());
//        this.baseController = new BaseControllerImpl(gameModel.getGameData());

        this.gameGui = new GameGui(new LoadConfiguration().getConfiguration());

        this.toMainPanel = this.backActionListener();
//        battleController.setReturnActionListener(toMainPanel);
//        baseController.setReturnActionListener(toMainPanel);

        this.init();
        this.setActionListenerName();
        this.setActionListenerSave();
        this.setActionListenerLoad();


    }

    private void init() {
        final ActionListener actionListener = e -> {
            if (this.gameModel.isSaved()) {
                if (Boolean.TRUE.equals(this.gameGui.showNewGameOptions())) {
                    this.gameModel.resetSaved();
                    this.battleController = new BattleControllerImpl(gameModel.getGameData());
                    this.baseController = new BaseControllerImpl(gameModel.getGameData());
                    this.loadGui();
                    this.gameGui.showNamePanel();
                }
            } else {
                this.gameModel.resetSaved();
                this.battleController = new BattleControllerImpl(gameModel.getGameData());
                this.baseController = new BaseControllerImpl(gameModel.getGameData());
                this.loadGui();
                this.gameGui.showNamePanel();
            }

        };
        this.gameGui.setActionListenerNewGame(actionListener);
    }

    private void loadGui() {
        this.gameGui.addPanels(this.baseController.getGuiPanel(), PANELS_NAME.CITY.getName());
        this.gameGui.addPanels(this.battleController.getGuiPanel(), PANELS_NAME.BATTLE.getName());

        gameGui.setBeatenLevels(gameModel.getCurrentLevel() - 1);
        gameGui.setActivateBattle(gameModel.getCurrentLevel());

    }

    private void setActionListenerLoad() {
        this.gameGui.setActionListenerLoad(e -> {
            if (this.gameModel.isSaved()) {
                this.gameModel.load();
                this.battleController = new BattleControllerImpl(gameModel.getGameData());
                this.baseController = new BaseControllerImpl(gameModel.getGameData());
                this.loadGui();
                this.gameGui.showPanels(GameGui.MAP_NAME);
            } else {
                this.gameGui.showLoadOptions();
            }
        });
    }

    private void setActionListenerName() {
        final ActionListener actionListener = e -> this.gameGui.showPanels(GameGui.MAP_NAME);
        this.gameGui.setActionListenerStart(actionListener);
    }

    private void setActionListenerSave() {
        this.gameGui.setActionListenerSave(e -> {
            this.gameModel.serializeGameData();
        });
    }

    private ActionListener backActionListener() {
        return e -> {
            this.gameGui.showPanels(GameGui.MAP_NAME);
            this.gameGui.setActivateBattle(gameModel.getCurrentLevel());
            this.gameGui.setBeatenLevels(gameModel.getCurrentLevel() - 1);
        };
    }

}