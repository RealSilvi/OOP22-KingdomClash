package it.unibo.view.battle;

import javax.swing.*;

class BattleGuiTest {
    public BattleGuiTest() {
    }

    public static void main(String[] args) {

        JFrame battleFrame = new JFrame();
        battleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        battleFrame.getContentPane().add(new BattlePanelImpl().getPanel());

        battleFrame.setVisible(true);
        battleFrame.pack();
    }
}