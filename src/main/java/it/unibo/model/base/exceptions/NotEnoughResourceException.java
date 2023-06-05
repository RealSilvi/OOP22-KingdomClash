package it.unibo.model.base.exceptions;

import java.util.Set;

import it.unibo.model.data.Resource;

/**
 * An exception that gets thrown when the player tries to do an action that
 * requires some resources that are not in the warehouse.
 */
public class NotEnoughResourceException extends ResourceException {
    /**
     * Constructs a NotEnoughResourceException with a
     * standard message that states the amount required to make that action.
     * @param resource     the missing resource
     */
    public NotEnoughResourceException(final Resource.ResourceType resource) {
        super("Not enough " + resource.name() + "!");
    }
    /**
     * Constructs a NotEnoughResourceException with a
     * standard message that states the amount required to make that action.
     * @param resources     the missing resources
     */
    public NotEnoughResourceException(final Set<Resource> resources) {
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
    /**
     * Constructs a NotEnoughResourceException with a
     * custom message.
     * @param msg           the message
     */
    public NotEnoughResourceException(final String msg) {
        super(msg);
    }
    /**
     * Constructs a NotEnoughResourceException with a
     * custom message and a trace.
     * @param msg           the message
     * @param trace         the trace
     */
    public NotEnoughResourceException(final String msg, final Throwable trace) {
        super(msg, trace);
    }
}
