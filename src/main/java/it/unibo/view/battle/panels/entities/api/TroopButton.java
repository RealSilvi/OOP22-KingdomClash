package it.unibo.view.battle.panels.entities.api;

import it.unibo.view.battle.Troop;

public interface TroopButton {
    void changeTroop();

    void changeSelectable();

    Troop getTroop();

    Boolean getSelectable();
}
