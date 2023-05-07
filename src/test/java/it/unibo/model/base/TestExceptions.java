package it.unibo.model.base;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import it.unibo.model.base.exceptions.NotEnoughResourceException;
import it.unibo.model.data.Resource;
import it.unibo.model.data.Resource.ResourceType;

public class TestExceptions {
    @Test
    public void testNotEnoughResourcesException() {
        Set<Resource> resources = Set.of(new Resource(ResourceType.WHEAT, 490), new Resource(ResourceType.WOOD, 400));
        try {
            throw new NotEnoughResourceException(resources);
        } catch (NotEnoughResourceException e) {
            Assertions.assertArrayEquals("You still need 490.0 WHEAT 400.0 WOOD to build this!".split(" "),
                e.getMessage().split(" "));
        }
    }
}