package it.unibo.model.base.exceptions;

/**
 * An exception usually thrown when the player tries to place or relocate
 * a building in an invalid location.
 */
public class InvalidBuildingPlacementException extends BuildingException {
    /**
     * Constructs an InvalidBuildingPlacementException with a
     * standard message for the user.
     */
    public InvalidBuildingPlacementException() {
        super("You can't place that there!");
    }
    /**
     * Constructs an InvalidBuildingPlacementException given a custom message.
     * @param msg   the custom message
     */
    public InvalidBuildingPlacementException(final String msg) {
        super(msg);
    }
    /**
     * Constructs an InvalidBuildingPlacementException given a custom message
     * and a trace.
     * @param msg   the custom message
     * @param trace the trace
     */
    public InvalidBuildingPlacementException(final String msg, final Throwable trace) {
        super(msg, trace);
    }
}
