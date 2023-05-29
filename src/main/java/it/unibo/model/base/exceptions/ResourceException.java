package it.unibo.model.base.exceptions;

public class ResourceException extends Exception {
    public ResourceException(String msg) {
        super(msg);
    }
    public ResourceException(String msg, Throwable trace) {
        super(msg, trace);
    }
}