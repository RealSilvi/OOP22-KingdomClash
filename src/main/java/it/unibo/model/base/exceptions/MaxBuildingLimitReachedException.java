package it.unibo.model.base.exceptions;

import java.io.Serial;

import it.unibo.model.base.basedata.Building;

/**
 * An exception usually thrown when the player tries to build more structures
 * than is allowed.
 */
public class MaxBuildingLimitReachedException extends BuildingException {
    @Serial
    private static final long serialVersionUID = 123456789L;

    /**
     * Constructs an MaxBuildingLimitReachedException with a
     * standard message.
     */
    public MaxBuildingLimitReachedException() {
        super("Cannot exceed the limit of " + Building.MAXBUILDINGS + " buildings!");
    }
    /**
     * Constructs an MaxBuildingLimitReachedException with a
     * custom message.
     * @param msg           the message
     */
    public MaxBuildingLimitReachedException(final String msg) {
        super(msg);
    }
    /**
     * Constructs an MaxBuildingLimitReachedException with a
     * custom message and a trace.
     * @param msg           the message
     * @param trace         the trace
     */
    public MaxBuildingLimitReachedException(final String msg, final Throwable trace) {
        super(msg, trace);
    }
}
