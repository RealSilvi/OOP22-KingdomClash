package it.unibo.model.data;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

/**
 * A simple enum with every troop present in the game and some
 * utility functions that help handle troop types.
 */
public enum TroopType {
    AXE,
    SWORD,
    HAMMER,
    MACE,
    AXE_DEFENCE,
    SWORD_DEFENCE,
    HAMMER_DEFENCE,
    MACE_DEFENCE;

    private static final Map<TroopType, TroopType> counterList =
            Map.of(
                    TroopType.AXE, TroopType.AXE_DEFENCE,
                    TroopType.SWORD, TroopType.SWORD_DEFENCE,
                    TroopType.HAMMER, TroopType.HAMMER_DEFENCE,
                    TroopType.MACE, TroopType.MACE_DEFENCE);

    private static final Random rnGenerator = new Random();

    /**
     * @return Returns a random troop type everytime this method is called
     */
    public static TroopType getRandomTroop() {

        return Arrays.stream(values())
                .skip(rnGenerator.nextInt(values().length))
                .iterator()
                .next();
    }

    /**
     * @param troopToCheck A specific troop type to check for
     * @return the other troop that counters the given troop
     */
    public static Optional<TroopType> getNullable(TroopType troopToCheck) {
        if (counterList.containsKey(troopToCheck)) {
            return Optional.of(counterList.get(troopToCheck));
        }
        return counterList.entrySet().stream()
        .filter(troopEntry -> troopEntry.getValue() == troopToCheck)
        .map(Map.Entry::getKey)
        .findFirst();
    }

    public static boolean isDefense(TroopType troopType){
        return !counterList.containsKey(troopType);
    }
}