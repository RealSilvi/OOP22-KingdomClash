package it.unibo.view.battle.config;

public  class BattlePanelConfiguration {

    private final TextConfiguration textConfiguration;

    private final int nrOfSlots;
    private final int nrOfTroops;
    private final int nrOfLives;
    private final int nrOfFieldSpots;

    public BattlePanelConfiguration() {
        nrOfSlots = 5;
        nrOfTroops = 8;
        nrOfLives = 8;
        nrOfFieldSpots = nrOfSlots * 2;
        this.textConfiguration=new TextConfiguration();
    }

    public int getNrOfSlots() {
        return nrOfSlots;
    }

    public int getNrOfTroops() {
        return nrOfTroops;
    }

    public int getNrOfLives() {
        return nrOfLives;
    }

    public int getNrOfFieldSpots() {
        return nrOfFieldSpots;
    }

    public TextConfiguration getTutorialPanelConfiguration() {
        return textConfiguration;
    }
}

