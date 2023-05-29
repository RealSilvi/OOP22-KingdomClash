package it.unibo.controller.base;

import java.awt.geom.Point2D;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nonnull;

import it.unibo.model.base.BaseModel;
import it.unibo.model.base.basedata.Building;
import it.unibo.model.base.exceptions.BuildingException;
import it.unibo.model.base.exceptions.BuildingMaxedOutException;
import it.unibo.model.base.exceptions.InvalidBuildingPlacementException;
import it.unibo.model.base.exceptions.InvalidStructureReferenceException;
import it.unibo.model.base.exceptions.InvalidTroopLevelException;
import it.unibo.model.base.exceptions.ResourceException;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.model.data.Resource;
import it.unibo.model.data.Resource.ResourceType;
import it.unibo.view.battle.Troop;

public class BaseControllerImpl implements BaseController {

    private BaseModel baseModel;

    //TODO: Remove below comments in this constructor once BaseView is implemented
    public BaseControllerImpl(@Nonnull BaseModel baseModel/*, BaseView baseView */) {
        this.baseModel = baseModel;
    }

    @Override
    public Optional<UUID> handleBuildingPlaced(Point2D position, BuildingTypes type, int startingLevel,
            boolean cheatMode) {
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
    public Optional<UUID> handleBuildingPlaced(Point2D position, BuildingTypes type, int startingLevel) {
        return handleBuildingPlaced(position, type, startingLevel, false);
    }

    @Override
    public Optional<UUID> handleBuildingPlaced(Point2D position, BuildingTypes type) {
        return handleBuildingPlaced(position, type, 0);
    }

    @Override
    public boolean handleStructureUpgrade(UUID structureId, boolean cheatMode) {
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
    public boolean handleStructureUpgrade(UUID structureId) {
        return handleStructureUpgrade(structureId, false);
    }

    @Override
    public boolean handleStructureDestruction(UUID structureId) {
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
    public boolean handleStructureRelocation(Point2D position, UUID structureId) {
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
    public int requestResourceCount(ResourceType type) {
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
    public void setPlayerName(String playerName) {
        baseModel.setPlayerName(playerName);
    }

    @Override
    public Map<Troop, Integer> requestTroopLevels() {
        return baseModel.getTroopMap();
    }

    @Override
    public boolean upgradeTroop(Troop troopToUpgrade, int levelToUpgradeTo) {
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
    public boolean upgradeTroop(Troop troopToUpgrade) {
        return upgradeTroop(troopToUpgrade, baseModel.getTroopMap().get(troopToUpgrade));
    }

    @Override
    public void setTimeRunning(boolean ticktime) {
        baseModel.setClockTicking(ticktime);
    }

    @Override
    public boolean isTimeRunning() {
        return baseModel.isClockTicking();
    }
}