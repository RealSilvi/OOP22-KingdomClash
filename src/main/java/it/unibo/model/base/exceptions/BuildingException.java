package it.unibo.model.base.exceptions;

public class BuildingException extends Exception {
    public BuildingException(String msg) {
        super(msg);
    }
    public BuildingException(String msg, Throwable trace) {
        super(msg, trace);
    }
}