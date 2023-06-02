package it.unibo.model.base.exceptions;

import java.util.Set;

import it.unibo.model.data.Resource;

public class NotEnoughResourceException extends ResourceException {
    public NotEnoughResourceException(Resource.ResourceType resource) {
        super("Not enough " + resource.name() + "!");
    }

    public NotEnoughResourceException(Set<Resource> resources) {
        super("You still need "
                + resources
                .stream()
                .collect(StringBuilder::new,
                        (strb, res) -> {
                            strb.append(res.getAmount() + " " + res.getResource().name() + " ");
                        },
                        StringBuilder::append)
                + "to build this!");
    }

    public NotEnoughResourceException(String msg) {
        super(msg);
    }

    public NotEnoughResourceException(String msg, Throwable trace) {
        super(msg, trace);
    }
}