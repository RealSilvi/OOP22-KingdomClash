package it.unibo.model.base.exceptions;

import it.unibo.model.base.basedata.Building;

public class BuildingMaxedOutException extends BuildingException {
    public BuildingMaxedOutException() {
        super("Could not further upgrade structure!\nMax level is "+Building.MAXLEVEL+"!");
    }
    public BuildingMaxedOutException(String msg) {
        super(msg);
    }
    public BuildingMaxedOutException(String msg, Throwable trace) {
        super(msg, trace);
    }
}