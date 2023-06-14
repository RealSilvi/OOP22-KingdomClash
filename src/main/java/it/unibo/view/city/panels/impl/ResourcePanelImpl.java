package it.unibo.view.city.panels.impl;

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
        this.baseControllerRef = baseControllerRef;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Arrays.stream(ResourceType.values())
            .forEach(resourceType -> {
                JLabel resourceLabel = new JLabel();
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
