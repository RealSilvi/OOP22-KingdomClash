package it.unibo.model.base.exceptions;

import it.unibo.model.data.Resource;

public class NotEnoughResourceException extends ResourceException {
    public NotEnoughResourceException(Resource.ResourceType resource) {
        super("Not enough "+resource.name()+"!");
    }
    public NotEnoughResourceException(String msg) {
        super(msg);
    }
    public NotEnoughResourceException(String msg, Throwable trace) {
        super(msg, trace);
    }
}