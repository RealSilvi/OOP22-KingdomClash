package it.unibo.model.data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.kingdomclash.config.GameConfiguration;
import it.unibo.model.base.basedata.Building;

/**
 * A simple data class to store all the game's information.
 */
public final class GameData implements Serializable {
    /**
     * Serial version UID.
     */
    @Serial
    private static final long serialVersionUID = 164372586L;

    private String playerName;
    private int currentLevel;
    private Set<Resource> resources;
    private ConcurrentMap<UUID, Building> buildings;
    private Map<TroopType, Integer> playerArmyLevel;

    private final transient GameConfiguration configuration;

    private transient FightData fightData;

    /**
     * Constructs an instance of GameData creating a default game configuration.
     */
    public GameData() {
        this(new GameConfiguration());
    }

    /**
     * Construct an instance following the game configuration.
     *
     * @param gameConfiguration the configuration of the game.
     */
    public GameData(final GameConfiguration gameConfiguration) {
        this.currentLevel = 1;
        this.resources = new HashSet<>();
        this.buildings = new ConcurrentHashMap<>();
        this.playerArmyLevel = new EnumMap<>(TroopType.class);
        this.configuration = gameConfiguration;
        this.fightData = new FightData(this.configuration.getBattleConfiguration());
        Arrays.stream(TroopType.values()).forEach(troopType -> this.playerArmyLevel.put(troopType, 1));
    }

    /**
     * Constructs a GameData instance given an already existing GameData.
     * And a configuration in order to easily restore transient fields.
     *
     * @param gameData      an already existing GameData object.
     * @param configuration the configuration for the game.
     */
    public GameData(@NonNull final GameData gameData, @NonNull final GameConfiguration configuration) {
        this.currentLevel = gameData.currentLevel;
        this.playerName = gameData.getPlayerName();
        this.resources = gameData.getResources();
        this.buildings = gameData.getBuildings();
        this.playerArmyLevel = gameData.getPlayerArmyLevel();
        this.configuration = configuration;
        this.fightData = gameData.getFightData();
    }

    /**
     * Increment by one the game level.
     */
    public void incrementLevel() {
        this.currentLevel = this.currentLevel + 1;
    }

    /**
     * @return the game level.
     */
    public int getCurrentLevel() {
        return this.currentLevel;
    }

    /**
     * Gets the player's name.
     *
     * @return a string representing the player's name.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Sets the player's name.
     *
     * @param playerName a string representing the player's name.
     */
    public void setPlayerName(final String playerName) {
        this.playerName = playerName;
    }


    /* No defensive copy needed because the base model already
     * handles data integrity
     */

    /**
     * Gets the resources owned by the player.
     *
     * @return a set of resources owned by the player.
     */
    @SuppressFBWarnings(value = "EI2",
            justification = "No encapsulation needed as BaseModel handles everything")

    public synchronized Set<Resource> getResources() {
        return resources;
    }


    /* No defensive copy needed because the base model already
     * handles data integrity
     */

    /**
     * Sets the resources owned by the player.
     *
     * @param resources A set representing the resources owned.
     */
    @SuppressFBWarnings(value = "EI2",
            justification = "No encapsulation needed as BaseModel handles everything")

    public synchronized void setResources(final Set<Resource> resources) {
        this.resources = resources;
    }


    /* No defensive copy needed because the base model already
     * handles data integrity
     */

    /**
     * Gets the currently built buildings owned by the player.
     *
     * @return a map containing all the buildings and their corresponding identifier.
     */
    @SuppressFBWarnings(value = "EI2",
            justification = "No encapsulation needed as BaseModel handles everything")

    public ConcurrentMap<UUID, Building> getBuildings() {
        return buildings;
    }


    /* No defensive copy needed because the base model already
     * handles data integrity
     */

    /**
     * Sets a map of buildings currently owned by the player.
     *
     * @param buildings a map containing all buildings owned by the player and
     *                  their corresponding identifier.
     */
    @SuppressFBWarnings(value = "EI2",
            justification = "No encapsulation needed as BaseModel handles everything")

    public void setBuildings(final ConcurrentMap<UUID, Building> buildings) {
        this.buildings = buildings;
    }


    /* No defensive copy needed because the base model already
     * handles data integrity
     */

    /**
     * Gets a map with a troop type, and it's corresponding level for the player.
     *
     * @return a map with the troop and the level of the troop as an integer.
     */
    @SuppressFBWarnings(value = "EI2",
            justification = "No encapsulation needed as BaseModel handles everything")

    public Map<TroopType, Integer> getPlayerArmyLevel() {
        return this.playerArmyLevel;
    }


    /* No defensive copy needed because the base model already
     * handles data integrity
     */

    /**
     * Sets a map with a troop type, and it's corresponding level for the player.
     *
     * @param playerArmyLevel a map with the troop and the level of the troop as an integer.
     */
    @SuppressFBWarnings(value = "EI2",
            justification = "No encapsulation needed as BaseModel handles everything")
    public void setPlayerArmyLevel(final Map<TroopType, Integer> playerArmyLevel) {
        this.playerArmyLevel = playerArmyLevel;
    }

    /**
     * @return the fight data.
     */
    @SuppressFBWarnings(value = "EI",
            justification = "I want to return the object to let other classes"
                    + "getting that reference and use it")
    public FightData getFightData() {
        return fightData;
    }

    /**
     * @param fightData sets new fight data.
     */
    @SuppressFBWarnings(value = "EI2",
            justification = "I want to store the input, to get its reference and use it")
    public void setFightData(final FightData fightData) {
        this.fightData = fightData;
    }

    /**
     * @return The game's configuration.
     */
    public GameConfiguration getGameConfiguration() {
        return this.configuration;
    }
}
