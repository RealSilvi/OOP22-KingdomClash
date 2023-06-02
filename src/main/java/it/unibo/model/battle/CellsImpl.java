package it.unibo.model.battle;

import it.unibo.view.battle.Troop;

public class CellsImpl implements Cells {
    private Troop troop;

    private Boolean clicked;

    private Boolean chosen;

    public CellsImpl(Troop troop, Boolean clicked, Boolean chosen) {
        this.troop = troop;
        this.clicked = clicked;
        this.chosen = chosen;
    }

    @Override
    public void setTroop(Troop troop) {

        this.troop = troop;

    }

    @Override
    public void setClicked(Boolean clicked) {

        this.clicked = clicked;

    }

    @Override
    public void setChosen(Boolean chosen) {

        if (chosen) {
            this.chosen = chosen;
            this.clicked = true;
        } else {
            this.chosen = chosen;
        }

    }


    @Override
    public Troop getTroop() {

        return this.troop;

    }

    @Override
    public Boolean getClicked() {

        return this.clicked;

    }

    @Override
    public Boolean getChosen() {

        return this.chosen;

    }

}
