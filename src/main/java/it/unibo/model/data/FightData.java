package it.unibo.model.data;

import it.unibo.model.battle.entitydata.BotData;
import it.unibo.model.battle.entitydata.BotDataImpl;
import it.unibo.model.battle.entitydata.PlayerData;
import it.unibo.model.battle.entitydata.PlayerDataImpl;

public class FightData {

    public static final int PLAYER_LIFE = 10;
    public static final int BOT_LIFE = 10;
    public static final int MAX_ROUND = 3;
    public static final int BOT_TROOPS = 5;
    public static final int PLAYER_TROOPS = 5;
    public static final int TOTAL_TROOPS = PLAYER_TROOPS+BOT_TROOPS;

    private BotData botData;
    private PlayerData playerData;

    public FightData(GameData gameData) {
        this.botData = new BotDataImpl(gameData);
        this.playerData = new PlayerDataImpl(gameData);
    }

    public void setPlayerData(PlayerData playerData){
        this.playerData = playerData;
    }

    public void setBotData(BotData botData) {
        this.botData = botData;
    }

    public PlayerData getPlayerData() {
        return this.playerData;
    }

    public BotData getBotData(){
        return this.botData;
    }



}
