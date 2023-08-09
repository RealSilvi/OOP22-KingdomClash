package it.unibo.model.base.exceptions;

/**
 * A type of exception that signals a problem relating the troops.
 */
public class TroopException extends Exception {
    private static final long serialVersionUID = 123456789L;

    /**
     * Creates a TroopException with a given message.
     * @param msg   The exception's message
     */
    public TroopException(final String msg) {
        super(msg);
    }
    /**
     * Creates a TroopException with a given message and a stacktrace.
     * @param msg   The exception's message
     * @param trace The error's stacktrace
     */
    public TroopException(final String msg, final Throwable trace) {
        super(msg, trace);
    }
}
