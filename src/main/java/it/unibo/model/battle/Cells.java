package it.unibo.model.battle;

import it.unibo.view.battle.Troop;

public interface Cells {

    /**
     * Sets the troop.
     * @param troop the troop which substitute the old one.
     */

    public void setTroop(Troop troop);

    /**
     * Change the status of a troop.
     * @param clicked if true, the troop is clicked,
     * it means that it is in the field, if its false,
     * it means that the troop is being removed from the field.
     */

    public void setClicked(Boolean clicked);

    /**
     * Change the status of a troop.
     * @param chosen if the given status is true,
     * the troop will be immutable. If the given status is false,
     * the troop will not be anymore immutable.
     */

    public void setChosen(Boolean chosen);

    /**
     * Takes the troop.
     * @return the troop.
     */

    public Troop getTroop();

    /**
     * Takes the status.
     * @return the status, true if clicked, false if not.
     */

    public Boolean getClicked();

    /**
     * Takes the status.
     * @return the status, true if chosen, false if not.
     */

    public Boolean getChosen();


}
