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
public final class ExceptionsTest {
    private Random randomGen = new Random();
    /**
     * Tests wether the NotEnoughResourcesException creates a correct error
     * message or not.
     */
    @Test
    @SuppressWarnings("checkstyle:magicnumber")
    public void testNotEnoughResourcesException() {
        int exampleWheat = randomGen.nextInt();
        int exampleWood = randomGen.nextInt();
        Set<Resource> expectedResources = 
            Set.of(new Resource(ResourceType.WHEAT, exampleWheat),
                new Resource(ResourceType.WOOD, exampleWood));
        try {
            throw new NotEnoughResourceException(expectedResources);
        } catch (NotEnoughResourceException e) {
            String[] expected = ("You still need "
                + exampleWheat + " WHEAT "
                + exampleWood + " WOOD to build this!")
                .split(" ");
            Arrays.sort(expected);
            String[] result = e.getMessage().split(" ");
            Arrays.sort(result);
            Assertions.assertArrayEquals(expected, result);
        }
    }
}
