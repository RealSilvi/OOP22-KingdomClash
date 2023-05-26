package it.unibo.model.data;

import java.util.HashSet;
import java.util.Set;

import it.unibo.model.battle.entitydata.BotData;
import it.unibo.model.battle.entitydata.BotDataImpl;
import it.unibo.model.battle.entitydata.PlayerData;
import it.unibo.model.battle.entitydata.PlayerDataImpl;
import it.unibo.view.battle.Troop;

public class FightData {

    public static final int PLAYER_LIFE = 10;
    public static final int BOT_LIFE = 10;
    public static final int MAX_ROUND = 3;
    public static final int BOT_TROOPS = 5;
    public static final int PLAYER_TROOPS = 5;
    public static final int TOTAL_TROOPS = PLAYER_TROOPS+BOT_TROOPS;
    public static final int TOTAL_DIFFERENT_TROOP = 8;

    private BotData botData;
    private PlayerData playerData;
    private Set<Troop> playerTroopUpgrades;

    public FightData() {
        this.botData = new BotDataImpl();
        this.playerData = new PlayerDataImpl();
        this.playerTroopUpgrades = new HashSet<>();
        this.playerTroopUpgrades.addAll(Set.of(Troop.values()));
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
    public Set<Troop> getPlayerUpgrades() {
        return playerTroopUpgrades;
    }

    public void setPlayerUpgrades(Set<Troop> playerTroopUpgrades) {
        this.playerTroopUpgrades = playerTroopUpgrades;
    }
}