package it.unibo.kingdomclash.config;

/**
 * Configuration of the battle.
 */
public class BattleConfiguration {

    private static final int NR_OF_SLOTS = 5;
    private static final int NR_OF_LIVES = 8;
    private static final int NR_OF_FIELD_SPOTS = NR_OF_SLOTS * 2;
    private static final int MAX_ROUND = 3;


    private final TextBattleConfiguration textBattleConfiguration;

    private final int nrOfSlots;
    private final int nrOfLives;
    private final int nrOfFieldSpots;
    private final int maxRound;

    /**
     * Initialize the default configuration.
     */
    public BattleConfiguration() {

        nrOfSlots = NR_OF_SLOTS;
        nrOfLives = NR_OF_LIVES;
        nrOfFieldSpots = NR_OF_FIELD_SPOTS;
        maxRound = MAX_ROUND;
        this.textBattleConfiguration = new TextBattleConfiguration();
    }

    /**
     * @return the number of slots for each player.
     */
    public int getNrOfSlots() {
        return nrOfSlots;
    }

    /**
     * @return the number of lives for each player.
     */
    public int getNrOfLives() {
        return nrOfLives;
    }

    /**
     * @return the number of spots in the field panel.
     */
    public int getNrOfFieldSpots() {
        return nrOfFieldSpots;
    }

    /**
     * @return the number of max round in the battle.
     */
    public int getMaxRound() {
        return maxRound;
    }

    /**
     * @return the configuration for the text areas.
     */
    public TextBattleConfiguration getTextConfiguration() {
        return textBattleConfiguration;
    }
}

