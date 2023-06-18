package it.unibo.kingdomclash.config;

/**
 * Configuration of the battle.
 */
public class BattleConfiguration {

    private final TextConfigurationBattle textConfigurationBattle;

    private final int nrOfSlots;
    private final int nrOfLives;
    private final int nrOfFieldSpots;
    private final int maxRound;

    public BattleConfiguration() {
        nrOfSlots = 5;
        nrOfLives = 8;
        nrOfFieldSpots = nrOfSlots * 2;
        maxRound = 3;
        this.textConfigurationBattle = new TextConfigurationBattle();
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
     * @return the number of max raund in the battle.
     */
    public int getMaxRound() {
        return maxRound;
    }

    /**
     * @return the configuration for the text areas.
     */
    public TextConfigurationBattle getTextConfiguration() {
        return textConfigurationBattle;
    }
}

