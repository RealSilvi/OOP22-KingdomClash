package it.unibo.controller.base;

import java.awt.geom.Point2D;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.swing.JPanel;

import it.unibo.controller.Controller;
import it.unibo.model.base.BaseModel;
import it.unibo.model.base.BaseModelImpl;
import it.unibo.model.base.basedata.Building;
import it.unibo.model.base.exceptions.BuildingException;
import it.unibo.model.base.exceptions.BuildingMaxedOutException;
import it.unibo.model.base.exceptions.InvalidBuildingPlacementException;
import it.unibo.model.base.exceptions.InvalidStructureReferenceException;
import it.unibo.model.base.exceptions.InvalidTroopLevelException;
import it.unibo.model.base.exceptions.ResourceException;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.model.data.GameData;
import it.unibo.model.data.Resource;
import it.unibo.model.data.TroopType;
import it.unibo.model.data.Resource.ResourceType;
import it.unibo.view.city.CityPanel;
import it.unibo.view.city.CityPanelImpl;

/**
 * A simple BaseController implementation.
 */
public final class BaseControllerImpl implements Controller, BaseController {

    private BaseModel baseModel;
    private CityPanel baseView;

    /**
     * Builds a controller for the Base part of the game using the provided
     * game data.
     * @param gameData a non null gameData object representing the state
     * of the game
     */
    public BaseControllerImpl(final @Nonnull GameData gameData) {
        this.baseModel = new BaseModelImpl(gameData);
        this.baseView = new CityPanelImpl(this, gameData.getGameConfiguration());
        this.baseModel.refreshBuildings();
    }

    @Override
    public Optional<UUID> handleBuildingPlaced(final Point2D position,
        final BuildingTypes type, final int startingLevel,
        final boolean cheatMode) {
        Optional<UUID> providedUUID;
        try {
            providedUUID = Optional.of(baseModel.buildStructure(position, type, startingLevel, cheatMode));
        } catch (BuildingException | ResourceException e) {
            // TODO: Warn the user that he can't do that and why!
            providedUUID = Optional.empty();
        }
        return providedUUID;
    }

    @Override
    public Optional<UUID> handleBuildingPlaced(final Point2D position,
        final BuildingTypes type, final int startingLevel) {
        return handleBuildingPlaced(position, type, startingLevel, false);
    }

    @Override
    public Optional<UUID> handleBuildingPlaced(final Point2D position,
        final BuildingTypes type) {
        return handleBuildingPlaced(position, type, 0);
    }

    @Override
    public boolean handleStructureUpgrade(final UUID structureId,
        final boolean cheatMode) {
        boolean upgradeSucceded;
        try {
            baseModel.upgradeStructure(structureId, cheatMode);
            upgradeSucceded = true;
        } catch (ResourceException | BuildingMaxedOutException | InvalidStructureReferenceException e) {
            // TODO: Warn the user that he can't do that and why!
            upgradeSucceded = false;
        }
        return upgradeSucceded;
    }

    @Override
    public boolean handleStructureUpgrade(final UUID structureId) {
        return handleStructureUpgrade(structureId, false);
    }

    @Override
    public boolean handleStructureDestruction(final UUID structureId) {
        boolean demolitionSucceded;
        try {
            baseModel.demolishStructure(structureId);
            demolitionSucceded = true;
        } catch (InvalidStructureReferenceException e) {
            // TODO: Warn the user that he can't do that and why!
            demolitionSucceded = false;
        }
        return demolitionSucceded;
    }

    @Override
    public boolean handleStructureRelocation(final Point2D position,
        final UUID structureId) {
        boolean relocationSucceded;
        try {
            baseModel.relocateStructure(position, structureId);
            relocationSucceded = true;
        } catch (InvalidBuildingPlacementException | InvalidStructureReferenceException e) {
            // TODO: Warn the user that he can't do that and why!
            relocationSucceded = false;
        }
        return relocationSucceded;
    }

    @Override
    public int requestResourceCount(final ResourceType type) {
        return baseModel.getResourceCount(type);
    }

    @Override
    public Set<Resource> requestResourceCount() {
        return baseModel.getResourceCount();
    }

    @Override
    public Map<UUID, Building> requestBuildingMap() {
        return baseModel.getBuildingMap();
    }

    @Override
    public String requestPlayerName() {
        return baseModel.getPlayerName();
    }

    @Override
    public void setPlayerName(final String playerName) {
        baseModel.setPlayerName(playerName);
    }

    @Override
    public Map<TroopType, Integer> requestTroopLevels() {
        return baseModel.getTroopMap();
    }

    @Override
    public boolean upgradeTroop(final TroopType troopToUpgrade, final int levelToUpgradeTo) {
        boolean operationSuccessful = false;
        try {
            baseModel.upgradeTroop(troopToUpgrade, levelToUpgradeTo);
            operationSuccessful = true;
        } catch (InvalidTroopLevelException e) {
            // TODO: Warn the user that he can't do that and why!
        }
        return operationSuccessful;
    }

    @Override
    public boolean upgradeTroop(final TroopType troopToUpgrade) {
        return upgradeTroop(troopToUpgrade, baseModel.getTroopMap().get(troopToUpgrade));
    }

    @Override
    public void setTimeRunning(final boolean ticktime) {
        baseModel.setClockTicking(ticktime);
    }

    @Override
    public boolean isTimeRunning() {
        return baseModel.isClockTicking();
    }

    @Override
    public JPanel getGuiPanel() {
        return this.baseView.getPanel();
    }
}
