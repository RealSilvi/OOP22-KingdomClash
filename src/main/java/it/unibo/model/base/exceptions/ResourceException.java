package it.unibo.model.base.exceptions;

/**
 * A type of exception that signals a problem relating resources.
 */
public class ResourceException extends Exception {
    /**
     * Creates a ResourceException with a given message.
     * @param msg   The exception's message
     */
    public ResourceException(final String msg) {
        super(msg);
    }
    /**
     * Creates a ResourceException with a given message and a stacktrace.
     * @param msg   The exception's message
     * @param trace The error's stacktrace
     */
    public ResourceException(final String msg, final Throwable trace) {
        super(msg, trace);
    }
}
