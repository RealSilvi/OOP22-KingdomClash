package it.unibo.model.base.exceptions;

import java.io.Serial;

import it.unibo.model.base.basedata.Building;

/**
 * An exception usually thrown when the user tries to upgrade a building beyond
 * a certain limit.
 */
public class BuildingMaxedOutException extends BuildingException {
    @Serial
    private static final long serialVersionUID = 123456789L;

    /**
     * Creates the exception stating the max building's level.
     */
    public BuildingMaxedOutException() {
        super("Could not further upgrade structure!\nMax level is " + Building.MAXLEVEL + "!");
    }
    /**
     * Creates the exception given a message.
     * @param msg   the message
     */
    public BuildingMaxedOutException(final String msg) {
        super(msg);
    }
    /**
     * Creates the exception given a message and a trace.
     * @param msg   the message
     * @param trace the trace
     */
    public BuildingMaxedOutException(final String msg, final Throwable trace) {
        super(msg, trace);
    }
}
