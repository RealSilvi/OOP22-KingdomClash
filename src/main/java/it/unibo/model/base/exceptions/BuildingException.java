package it.unibo.model.base.exceptions;

import java.io.Serial;

/**
 * Exception that should be thrown when an operation on a building fails.
 */
public class BuildingException extends Exception {
    @Serial
    private static final long serialVersionUID = 123456789L;

    /**
     * @see {@link Exception#Exception(String)}
     * @param msg message
     */
    public BuildingException(final String msg) {
        super(msg);
    }
    /**
     * @see {@link Exception#Exception(String, Throwable)}
     * @param msg message
     * @param trace stacktrace
     */
    public BuildingException(final String msg, final Throwable trace) {
        super(msg, trace);
    }
}
