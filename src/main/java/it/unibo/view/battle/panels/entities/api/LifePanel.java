package it.unibo.view.battle.panels.entities.api;

import javax.swing.*;

public interface LifePanel {
    void restart();

    void decreaseLife();

    JPanel getPanel();
}

