package it.unibo.model.base.exceptions;

import java.util.UUID;

public class InvalidStructureReferenceException extends BuildingException {
    public InvalidStructureReferenceException(UUID identifier) {
        super("Reference for structure identifier: " + identifier.toString() + " does not exist!");
    }

    public InvalidStructureReferenceException(String msg) {
        super(msg);
    }

    public InvalidStructureReferenceException(String msg, Throwable trace) {
        super(msg, trace);
    }
}