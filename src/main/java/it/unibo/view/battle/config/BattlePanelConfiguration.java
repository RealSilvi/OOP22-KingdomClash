package it.unibo.view.battle.config;

import it.unibo.model.data.TroopType;

public  class BattlePanelConfiguration {

    private final TextConfiguration textConfiguration;

    private final int nrOfSlots;
    private final int nrOfLives;
    private final int nrOfFieldSpots;
    private final int maxRound;

    public BattlePanelConfiguration() {
        nrOfSlots = 5;
        nrOfLives = 8;
        nrOfFieldSpots = nrOfSlots * 2;
        maxRound = 3;
        this.textConfiguration=new TextConfiguration();
    }

    public int getNrOfSlots() {
        return nrOfSlots;
    }

    public int getNrOfLives() {
        return nrOfLives;
    }

    public int getNrOfFieldSpots() {
        return nrOfFieldSpots;
    }

    public int getMaxRound() {
        return maxRound;
    }

    public TextConfiguration getTextConfiguration() {
        return textConfiguration;
    }
}

