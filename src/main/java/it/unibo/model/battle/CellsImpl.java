package it.unibo.model.battle;

import it.unibo.model.data.TroopType;

/**
 * Implements methods in interface, used to work
 * on the data in the slots.
 */
public final class CellsImpl implements Cells {
    /** The troop in the slot. */
    private TroopType troop;
    /** Shows if the troop is clicked or not. */
    private Boolean clicked;

    /**
     * Constructor takes values and initialize that.
     * @param troop The troop to set in the slot.
     * @param clicked True if the troop is in the field, false otherwise.
     * take it back, false otherwise.
     */
    public CellsImpl(final TroopType troop, final Boolean clicked) {
        this.troop = troop;
        this.clicked = clicked;
    }

    @Override
    public void setTroop(final TroopType troop) {
        this.troop = troop;
    }

    @Override
    public void setClicked(final Boolean clicked) {
        this.clicked = clicked;
    }

    @Override
    public void setChosen(final Boolean chosen) {
        if (chosen) {
            this.clicked = true;
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

}
