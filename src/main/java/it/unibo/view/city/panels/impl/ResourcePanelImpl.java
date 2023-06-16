package it.unibo.view.city.panels.impl;

import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.controller.base.BaseController;
import it.unibo.model.base.api.BuildingObserver;
import it.unibo.model.data.Resource.ResourceType;
import it.unibo.view.battle.panels.utilities.BattlePanelStyle;

/**
 * A simple panel class to show available resources to the player.
 */
public final class ResourcePanelImpl extends JPanel {
    private Map<ResourceType, JLabel> labelToResource = new EnumMap<>(ResourceType.class);
    private BaseController baseControllerRef;
    /**
     * Constructs a panel that has the purpose of showing the player's resources.
     * @param baseControllerRef the reference for the baseController
     */
    public ResourcePanelImpl(BaseController baseControllerRef) {
        this.setOpaque(false);
        this.baseControllerRef = baseControllerRef;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Font textFont = BattlePanelStyle.getPrimaryFont().deriveFont(20.0f);
        Arrays.stream(ResourceType.values())
            .forEach(resourceType -> {
                JLabel resourceLabel = new JLabel();
                resourceLabel.setForeground(Color.WHITE);
                resourceLabel.setFont(textFont);
                labelToResource.put(resourceType, resourceLabel);
                this.add(resourceLabel);
            });
        baseControllerRef.addBuildingProductionObserver(new BuildingObserver() {
            @Override
            public void update(UUID buildingId) {
                if (baseControllerRef.requestBuildingMap()
                    .get(buildingId).getProductionProgress() == 100) {
                    updateResourceDisplay();
                }
            }
        });
        updateResourceDisplay();
    }
    /**
     * Updates the displayed Resources.
     */
    private void updateResourceDisplay() {
        baseControllerRef.requestResourceCount().stream()
            .forEach(resource -> 
                labelToResource.get(resource.getResource())
                    .setText(resource.getResource().name().toUpperCase()
                        +": "+resource.getAmount())
            );
    }
}
