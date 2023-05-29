package it.unibo.model.base.exceptions;

public class TroopException extends Exception {
    public TroopException(String msg) {
        super(msg);
    }
    public TroopException(String msg, Throwable trace) {
        super(msg, trace);
    }
}