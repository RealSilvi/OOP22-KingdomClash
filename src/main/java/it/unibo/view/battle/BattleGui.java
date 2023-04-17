package it.unibo.view.battle;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BattleGui {

    void hitPlayer();

    void hitBot();

    void spinPlayerFreeSlot();

    void spinBotFreeSlot();

    void drawInfoTable(final Map<Troop,Boolean> troopLv);

    void updateField(final List<Optional<Troop>> field);

}
