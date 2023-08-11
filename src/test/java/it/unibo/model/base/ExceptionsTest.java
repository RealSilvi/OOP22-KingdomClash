package it.unibo.model.base;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import it.unibo.model.base.exceptions.NotEnoughResourceException;
import it.unibo.model.data.Resource;
import it.unibo.model.data.Resource.ResourceType;
/**
 * Tests the Base Model's exception logic.
 */
final class ExceptionsTest {
    private final Random randomGen = new Random();
    /**
     * Tests wether the NotEnoughResourcesException creates a correct error
     * message or not.
     */
    @Test
    @SuppressWarnings("checkstyle:magicnumber")
    void testNotEnoughResourcesException() {
        final int exampleWheat = randomGen.nextInt();
        final int exampleWood = randomGen.nextInt();
        final Set<Resource> expectedResources = 
            Set.of(new Resource(ResourceType.WHEAT, exampleWheat),
                new Resource(ResourceType.WOOD, exampleWood));
        try {
            throw new NotEnoughResourceException(expectedResources);
        } catch (NotEnoughResourceException e) {
            final String[] expected = ("You still need "
                + exampleWheat + " WHEAT "
                + exampleWood + " WOOD to build this!")
                .split(" ");
            Arrays.sort(expected);
            final String[] result = e.getMessage().split(" ");
            Arrays.sort(result);
            Assertions.assertArrayEquals(expected, result);
        }
    }
}
