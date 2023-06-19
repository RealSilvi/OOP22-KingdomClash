package it.unibo.model.data;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.battle.entitydata.EntityData;
import it.unibo.model.battle.entitydata.EntityDataImpl;
import it.unibo.kingdomclash.config.BattleConfiguration;

/**
 * This class contains the player data and
 * the bot data, with the constants of the battle.
 */
public final class FightData {

    private EntityData botData;
    private EntityData playerData;

    /**
     * Creates data of the bot and the player.
     * @param battlePanelConfiguration The configurations of the battle.
     */
    public FightData(final BattleConfiguration battlePanelConfiguration) {
        this.botData = new EntityDataImpl(battlePanelConfiguration);
        this.playerData = new EntityDataImpl(battlePanelConfiguration);
    }

    /**
     * Set the data of the player.
     * @param entityData The player's data to set.
     */
    @SuppressFBWarnings(value = "EI2",
            justification = "I want to store the input, to get its reference and use it")
    public void setPlayerData(final EntityDataImpl entityData) {
        this.playerData = entityData;
    }

    /**
     * set the data of the bot.
     * @param entityData The robot's data to set.
     */
    @SuppressFBWarnings(value = "EI2",
            justification = "I want to store the input, to get its reference and use it")
    public void setBotData(final EntityDataImpl entityData) {
        this.botData = entityData;
    }

    /**
     * Gets the data of the player.
     * @return The data of the player.
     */
    @SuppressFBWarnings(value = "EI", justification = "I need changes to playerData to be reflected on all references")
    public EntityData getPlayerData() {
        return this.playerData;
    }

    /**
     * Gets the data of the bot.
     * @return The data of the bot.
     */
    @SuppressFBWarnings(value = "EI", justification = "I need changes to botData to be reflected on all references")
    public EntityData getBotData() {
        return this.botData;
    }
}
