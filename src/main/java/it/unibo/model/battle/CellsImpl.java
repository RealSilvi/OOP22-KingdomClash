package it.unibo.model.battle;

import it.unibo.model.data.TroopType;

public class CellsImpl implements Cells {
    private TroopType troop;

    private Boolean clicked;

    private Boolean chosen;

    public CellsImpl(TroopType troop, Boolean clicked, Boolean chosen) {
        this.troop = troop;
        this.clicked = clicked;
        this.chosen = chosen;
    }

    @Override
    public void setTroop(TroopType troop) {

        this.troop = troop;

    }

    @Override
    public void setClicked(Boolean clicked) {

        this.clicked = clicked;

    }

    @Override
    public void setChosen(Boolean chosen) {

        if (chosen) {
            this.chosen = true;
            this.clicked = true;
        } else {
            this.chosen = false;
        }

    }


    @Override
    public TroopType getTroop() {

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
