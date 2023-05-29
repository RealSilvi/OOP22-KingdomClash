package it.unibo.model.base;

import java.util.Arrays;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import it.unibo.model.base.exceptions.NotEnoughResourceException;
import it.unibo.model.data.Resource;
import it.unibo.model.data.Resource.ResourceType;

public class ExceptionsTest {
    @Test
    public void testNotEnoughResourcesException() {
        Set<Resource> resources = Set.of(new Resource(ResourceType.WHEAT, 490), new Resource(ResourceType.WOOD, 400));
        try {
            throw new NotEnoughResourceException(resources);
        } catch (NotEnoughResourceException e) {
            String[] expected = "You still need 490 WHEAT 400 WOOD to build this!".split(" ");
            Arrays.sort(expected);
            String[] result = e.getMessage().split(" ");
            Arrays.sort(result);
            Assertions.assertArrayEquals(expected, result);
        }
    }
}