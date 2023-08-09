package it.unibo.controller.base;

import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import it.unibo.controller.Controller;
import it.unibo.model.base.BaseModel;
import it.unibo.model.base.BaseModelImpl;
import it.unibo.model.base.api.BuildingObserver;
import it.unibo.model.base.basedata.Building;
import it.unibo.model.base.exceptions.BuildingException;
import it.unibo.model.base.exceptions.BuildingMaxedOutException;
import it.unibo.model.base.exceptions.InvalidBuildingPlacementException;
import it.unibo.model.base.exceptions.InvalidStructureReferenceException;
import it.unibo.model.base.exceptions.InvalidTroopLevelException;
import it.unibo.model.base.exceptions.NotEnoughResourceException;
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

    private final BaseModel baseModel;
    private final CityPanel baseView;
    private static final String ERROR_TITLE = "HEY!";

    /**
     * Builds a controller for the Base part of the game using the provided
     * game data.
     * @param gameData a non null gameData object representing the state
     * of the game
     */
    public BaseControllerImpl(final @Nonnull GameData gameData) {
        this.baseModel = new BaseModelImpl(gameData);
        this.baseView = new CityPanelImpl(this,
            gameData.getGameConfiguration());
        this.baseModel.refreshBuildings();
        final int validResources = this.baseModel
            .getResourceCount().stream().filter(resource ->
                resource.getAmount() != 0).toList().size();
        final int buildings = this.baseModel.getBuildingMap().size();
        final Point2D centerPosition = new Point2D.Float(
            gameData.getGameConfiguration().getCityConfiguration().getWidth() / 2.0f,
            gameData.getGameConfiguration().getCityConfiguration().getHeight() / 2.0f);
        if (validResources == 0 && buildings == 0) {
            this.handleBuildingPlaced(centerPosition,
                BuildingTypes.HALL, 0, true);
        }
    }

    @Override
    public Optional<UUID> handleBuildingPlaced(final Point2D position,
        final BuildingTypes type, final int startingLevel,
        final boolean cheatMode) {
        Optional<UUID> providedUUID;
        try {
            providedUUID = Optional.of(baseModel.buildStructure(position, type, startingLevel, cheatMode));
        } catch (BuildingException | ResourceException e) {
            JOptionPane.showMessageDialog(null,
                e.getMessage(), ERROR_TITLE,
                JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null,
                e.getMessage(), ERROR_TITLE,
                JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null,
                e.getMessage(), ERROR_TITLE,
                JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null,
                e.getMessage(), ERROR_TITLE,
                JOptionPane.ERROR_MESSAGE);
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
        } catch (InvalidTroopLevelException | NotEnoughResourceException e) {
            JOptionPane.showMessageDialog(null,
                e.getMessage(), ERROR_TITLE,
                JOptionPane.ERROR_MESSAGE);
        }
        return operationSuccessful;
    }

    @Override
    public boolean upgradeTroop(final TroopType troopToUpgrade) {
        return upgradeTroop(troopToUpgrade,
            baseModel.getTroopMap().get(troopToUpgrade));
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

    @Override
    public void addBuildingStateChangedObserver(final BuildingObserver stateChangedObserver) {
        baseModel.addBuildingStateChangedObserver(stateChangedObserver);
    }

    @Override
    public void removeBuildingStateChangedObserver(final BuildingObserver stateChangedObserver) {
        baseModel.removeBuildingStateChangedObserver(stateChangedObserver);
    }

    @Override
    public void addBuildingProductionObserver(final BuildingObserver productionObserver) {
        baseModel.addBuildingProductionObserver(productionObserver);
    }

    @Override
    public void removeBuildingProductionObserver(final BuildingObserver productionObserver) {
        baseModel.removeBuildingProductionObserver(productionObserver);
    }

    @Override
    public void setReturnActionListener(final ActionListener returnActionToAdd) {
        this.baseView.setReturnActionListener(returnActionToAdd);
    }

    @Override
    public void closureOperation() {
        this.baseModel.deactivateModel();
    }
}
