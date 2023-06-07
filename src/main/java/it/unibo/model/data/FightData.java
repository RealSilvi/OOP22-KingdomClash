package it.unibo.model.data;

import java.io.Serializable;

import it.unibo.model.battle.entitydata.*;

public class FightData implements Serializable {

    public static final int PLAYER_LIFE = 8;
    public static final int BOT_LIFE = 8;
    public static final int MAX_ROUND = 3;
    public static final int BOT_TROOPS = 5;
    public static final int PLAYER_TROOPS = 5;
    public static final int HAND_TROOPS = 5;
    public static final int TOTAL_TROOPS = PLAYER_TROOPS + BOT_TROOPS;
    public static final int TOTAL_DIFFERENT_TROOP = 8;

    private EntityData botData;
    private EntityData playerData;

    public FightData() {
        this.botData = new EntityDataImpl();
        this.playerData = new EntityDataImpl();
    }

    public void setPlayerData(EntityData playerData) {
        this.playerData = playerData;
    }

    public void setBotData(EntityData botData) {
        this.botData = botData;
    }

    public EntityData getPlayerData() {
        return this.playerData;
    }

    public EntityData getBotData() {
        return this.botData;
    }
}