package it.unibo.model.base.exceptions;

public class InvalidBuildingPlacementException extends BuildingException {
    public InvalidBuildingPlacementException() {
        super("You can't place that there!");
    }
    public InvalidBuildingPlacementException(String msg) {
        super(msg);
    }
    public InvalidBuildingPlacementException(String msg, Throwable trace) {
        super(msg, trace);
    }
}