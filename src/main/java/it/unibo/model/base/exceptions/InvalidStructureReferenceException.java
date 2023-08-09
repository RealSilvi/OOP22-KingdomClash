package it.unibo.model.base.exceptions;

import java.util.UUID;

/**
 * An exception usually thrown when something in the code references a building
 * that does not exist anymore, this exception is usually caused by an error
 * in the code and not by the user.
 */
public class InvalidStructureReferenceException extends BuildingException {
    private static final long serialVersionUID = 123456789L;

    /**
     * Constructs an InvalidStructureReferenceException with a
     * standard message.
     * @param identifier    the identifier that caused this exception
     */
    public InvalidStructureReferenceException(final UUID identifier) {
        super("Reference for structure identifier: " + identifier.toString() + " does not exist!");
    }
    /**
     * Constructs an InvalidStructureReferenceException with a
     * custom message.
     * @param msg           the message
     */
    public InvalidStructureReferenceException(final String msg) {
        super(msg);
    }
    /**
     * Constructs an InvalidStructureReferenceException with a
     * custom message and trace.
     * @param msg           the message
     * @param trace         the trace
     */
    public InvalidStructureReferenceException(final String msg, final Throwable trace) {
        super(msg, trace);
    }
}
