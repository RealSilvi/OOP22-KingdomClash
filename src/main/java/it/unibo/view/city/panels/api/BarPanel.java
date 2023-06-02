package it.unibo.view.city.panels.api;

import java.awt.*;
import java.awt.List;
import java.util.*;

import it.unibo.model.data.GameData;
import it.unibo.model.data.Resource;
import it.unibo.view.battle.Troop;

public interface BarPanel {

    void switchbutton();

    void getTroopInfo(Set<Troop> type);

    void getPlayerInfo(GameData gameData);

}
