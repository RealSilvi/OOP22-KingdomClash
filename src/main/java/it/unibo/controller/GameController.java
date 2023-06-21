package it.unibo.controller;


import it.unibo.controller.base.BaseControllerImpl;
import it.unibo.controller.battle.BattleControllerImpl;
import it.unibo.model.GameModel;
import it.unibo.view.GameGui;
import it.unibo.view.menu.GameMenuImpl;
import it.unibo.view.menu.SouthPanel;

import java.awt.event.ActionListener;

/**
 * The game controller handles the communication between the controllers, model and the views.
 */
public class GameController {

    private final GameModel gameModel;
    private final GameGui gameGui;

    private Controller battleController;
    private Controller baseController;

    private final ActionListener toMainPanel;

    /**
     * Initialize the game.
     */
    public GameController() {

        this.gameModel = new GameModel();
        this.gameGui = new GameGui(gameModel.getDefaultConfiguration());

        this.toMainPanel = this.backActionListener();

        this.setActionListenerLoad();
        this.setActionListenerExit();

        this.init();
    }

    private void init() {
        final ActionListener actionListener = e -> {
            if (this.gameModel.isSaved()) {
                if (Boolean.TRUE.equals(this.gameGui.showNewGameOptions())) {
                    loadGame();
                }
            } else {
                loadGame();
            }

        };
        this.gameGui.setActionListenerNewGame(actionListener);
    }

    private void loadGame() {
        this.gameModel.resetSaved();
        this.gameModel.setPlayerName(this.gameGui.getPlayerName());
        this.battleController = new BattleControllerImpl(gameModel.getGameData());
        this.baseController = new BaseControllerImpl(gameModel.getGameData());
        this.battleController.setReturnActionListener(toMainPanel);
        this.baseController.setReturnActionListener(toMainPanel);
        this.loadGui();
        this.gameGui.showNamePanel();
    }

    private void loadGui() {
        this.gameGui.setButtonsVisibilityMenu(GameMenuImpl.BUTTONS_MENU.LOAD, false);
        this.gameGui.setButtonsVisibilityMenu(GameMenuImpl.BUTTONS_MENU.NEW_GAME, false);
        this.gameGui.setButtonsVisibilityMenu(GameMenuImpl.BUTTONS_MENU.CONTINUE, true);

        this.gameGui.addPanels(this.baseController.getGuiPanel(), PanelsName.CITY.getName());
        this.gameGui.addPanels(this.battleController.getGuiPanel(), PanelsName.BATTLE.getName());

        this.gameGui.setBeatenLevels(gameModel.getCurrentLevel() - 1);
        this.gameGui.setActivateBattle(gameModel.getCurrentLevel());

        this.setActionListenerName();
        this.setActionListenerBattle();
        this.setActionListenerCity();
        this.setActionListenerMenu();
        this.setActionListenerMusic();
        this.setActionListenerSave();
        this.setActionListenerContinue();
        this.setActionListenerQuit();
        this.gameGui.setActionListenerExit(e -> this.baseController.closureOperation());

    }

    private void setActionListenerLoad() {
        this.gameGui.setActionListenerLoad(e -> {
            if (this.gameModel.isSaved()) {
                this.gameModel.load();
                this.battleController = new BattleControllerImpl(gameModel.getGameData());
                this.baseController = new BaseControllerImpl(gameModel.getGameData());
                this.loadGui();
                this.gameGui.getSoundManager().startMapTheme();
                this.gameGui.showPanels(GameGui.MAP_NAME);
            } else {
                this.gameGui.showLoadOptions();
            }
        });
    }

    private ActionListener backActionListener() {
        return e -> {
            this.gameGui.getSoundManager().startMapTheme();
            this.gameGui.showPanels(GameGui.MAP_NAME);
            this.gameGui.setButtonsVisibility(SouthPanel.BUTTONS_SOUTH.SAVE, true);
            this.gameGui.setButtonsVisibility(SouthPanel.BUTTONS_SOUTH.MENU, true);
            this.gameGui.setActivateBattle(gameModel.getCurrentLevel());
            this.gameGui.setBeatenLevels(gameModel.getCurrentLevel() - 1);
        };
    }

    private void setActionListenerName() {
        final ActionListener actionListener = e -> {
            this.gameGui.getSoundManager().startMapTheme();
            this.gameModel.setPlayerName(this.gameGui.getPlayerName());
            this.gameGui.showPanels(GameGui.MAP_NAME);
        };
        this.gameGui.setActionListenerStart(actionListener);
    }

    private void setActionListenerSave() {
        this.gameGui.setActionListenerButtons(e -> this.gameModel.serializeGameData(), SouthPanel.BUTTONS_SOUTH.SAVE);
    }

    private void setActionListenerContinue() {
        this.gameGui.setActionListenerContinue(e -> {
            this.gameGui.showPanels(GameGui.MAP_NAME);
            this.gameGui.getSoundManager().startMapTheme();
        });
    }

    private void setActionListenerMenu() {
        this.gameGui.setActionListenerButtons(e -> {
            this.gameGui.getSoundManager().startMenuTheme();
            this.gameGui.showMenuPanel();
        }, SouthPanel.BUTTONS_SOUTH.MENU);
    }

    private void setActionListenerMusic() {
        this.gameGui.setActionListenerButtons(
                e -> this.gameGui.getSoundManager().changeMute(), SouthPanel.BUTTONS_SOUTH.MUSIC);
    }

    private void setActionListenerBattle() {
        this.gameGui.setMapBattleActionListener(e -> {
            this.gameGui.getSoundManager().startBattleTheme();
            this.gameGui.showPanels(PanelsName.BATTLE.getName());
            this.gameGui.setButtonsVisibility(SouthPanel.BUTTONS_SOUTH.SAVE, false);
            this.gameGui.setButtonsVisibility(SouthPanel.BUTTONS_SOUTH.MENU, false);
        });
    }

    private void setActionListenerCity() {
        this.gameGui.setMapBaseActionListener(e -> {
            this.gameGui.getSoundManager().startCityTheme();
            this.gameGui.showPanels(PanelsName.CITY.getName());
        });
    }

    private void setActionListenerExit() {
        this.gameGui.setActionListenerExit(e -> this.gameGui.closeGui());
    }

    private void setActionListenerQuit(){
        ActionListener actionListener= e -> {
            this.baseController.closureOperation();
            this.gameGui.closeGui();
        };
        this.gameGui.setActionListenerButtons(actionListener, SouthPanel.BUTTONS_SOUTH.QUIT);
    }


    private enum PanelsName {
        BATTLE("BATTLE"),
        CITY("CITY");

        private final String name;

        PanelsName(final String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}
