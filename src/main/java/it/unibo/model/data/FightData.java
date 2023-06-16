package it.unibo.model.data;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.battle.entitydata.EntityData;
import it.unibo.model.battle.entitydata.EntityDataImpl;
import it.unibo.view.battle.config.BattleConfiguration;

/**
 * This class contains the player data and
 * the bot data, with the constants of the battle.
 */
public class FightData {

    private EntityData botData;
    private EntityData playerData;

    public FightData(final BattleConfiguration battlePanelConfiguration) {
        this.botData = new EntityDataImpl(battlePanelConfiguration);
        this.playerData = new EntityDataImpl(battlePanelConfiguration);
    }

    @SuppressFBWarnings(value = "EI2",
            justification = "I want to store the input, to get its reference and use it")
    public void setPlayerData(EntityDataImpl entityData){
        this.playerData = entityData;
    }

    @SuppressFBWarnings(value = "EI2",
            justification = "I want to store the input, to get its reference and use it")
    public void setBotData(EntityDataImpl entityData){
        this.botData = entityData;
    }

    @SuppressFBWarnings(value = "EI", justification = "I need changes to playerData to be reflected on all references")
    public EntityData getPlayerData() {
        return this.playerData;
    }

    @SuppressFBWarnings(value = "EI", justification = "I need changes to botData to be reflected on all references")
    public EntityData getBotData() {
        return this.botData;
    }
}
