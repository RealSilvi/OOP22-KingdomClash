package it.unibo.view.battle;

import it.unibo.view.battle.tutorial.TutorialPanel;

import javax.swing.*;

class BattleGuiTest {
    public BattleGuiTest() {
    }

    public static void main(String[] args) {

        JFrame battleFrame = new JFrame();
        battleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TutorialPanel panel = new TutorialPanel();
        battleFrame.getContentPane().add(panel.getPanel());

        battleFrame.setVisible(true);
        battleFrame.pack();
    }
}