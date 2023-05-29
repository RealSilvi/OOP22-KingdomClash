package it.unibo.controller.battle;

import it.unibo.model.data.FightData;
import it.unibo.view.battle.tutorial.TutorialPanel;

import javax.swing.*;
import java.util.Optional;

public class BattleControllerTest {

    public static void main(String[] args) {

        FightData fightData = new FightData();
        BattleControllerImpl battleController=new BattleControllerImpl(Optional.of(fightData));

        JFrame battleFrame = battleController.getFrame();
        battleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TutorialPanel panel = new TutorialPanel();
        battleFrame.getContentPane().add(battleController.getCurrentPanel());


        battleFrame.setVisible(true);
        battleFrame.pack();
    }
}
