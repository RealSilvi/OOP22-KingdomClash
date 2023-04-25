package it.unibo.model.base.exceptions;

public class InvalidStructureReferenceException extends BuildingException {
    public InvalidStructureReferenceException(int identifier) {
        super("Reference for structure identifier: "+identifier+" does not exist!");
    }
    public InvalidStructureReferenceException(String msg) {
        super(msg);
    }
    public InvalidStructureReferenceException(String msg, Throwable trace) {
        super(msg, trace);
    }
}