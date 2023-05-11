package it.unibo.model.battle;

import it.unibo.view.battle.Troop;

public interface Cells {

    public void setTroop(Troop troop);

    public void setClicked(Boolean clicked);

    public void setChosen(Boolean chosen);

    public Troop getTroop();

    public Boolean getClicked();

    public Boolean getChosen();


}
