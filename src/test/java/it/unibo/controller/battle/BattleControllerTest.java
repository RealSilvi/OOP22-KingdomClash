package it.unibo.controller.battle;

import it.unibo.model.data.FightData;
import it.unibo.model.data.GameData;
import it.unibo.view.battle.BattlePanel;
import it.unibo.view.battle.BattlePanelImpl;
import it.unibo.view.battle.tutorial.TutorialPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class BattleControllerTest {

    public static void main(String[] args) {

        GameData gameData = new GameData();
        FightData fightData = new FightData(gameData.getGameConfiguration().getBattleControllerConfiguration());
        //BattleControllerImpl battleController=new BattleControllerImpl(new GameData());

    }
}
