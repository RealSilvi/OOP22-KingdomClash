package it.unibo.model.base.exceptions;

import it.unibo.model.data.TroopType;

/**
 * An exception usually thrown when the user tries to upgrade a troop over
 * a certain limit.
 */
public class InvalidTroopLevelException extends TroopException {
    /**
     * Constructs an InvalidTroopLevelException with a
     * standard message.
     * @param troopType     the type of troop that is maxed out
     * @param level         the maximum level of the troop
     */
    public InvalidTroopLevelException(final TroopType troopType, final int level) {
        //TODO: Ouptut max level to string when ready
        super("Level " + level + " is invalid for troop: "
                + troopType.name() + " Max level is: TO BE ADDED");
    }
    /**
     * Constructs an InvalidTroopLevelException with a
     * custom message.
     * @param msg           the message
     */
    public InvalidTroopLevelException(final String msg) {
        super(msg);
    }
    /**
     * Constructs an InvalidTroopLevelException with a
     * custom message and a trace.
     * @param msg           the message
     * @param trace         the trace
     */
    public InvalidTroopLevelException(final String msg, final Throwable trace) {
        super(msg, trace);
    }
}
