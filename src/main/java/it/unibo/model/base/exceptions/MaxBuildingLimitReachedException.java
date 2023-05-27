package it.unibo.model.base.exceptions;

import it.unibo.model.base.basedata.Building;

public class MaxBuildingLimitReachedException extends BuildingException {
    public MaxBuildingLimitReachedException() {
        super("Cannot exceed the limit of "+Building.MAXBUILDINGS+" buildings!");
    }
    public MaxBuildingLimitReachedException(String msg) {
        super(msg);
    }
    public MaxBuildingLimitReachedException(String msg, Throwable trace) {
        super(msg, trace);
    }
}