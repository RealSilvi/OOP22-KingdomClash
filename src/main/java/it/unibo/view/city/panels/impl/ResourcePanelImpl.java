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
import it.unibo.view.utilities.BattlePanelStyle;

/**
 * A simple panel class to show available resources to the player.
 */
public final class ResourcePanelImpl extends JPanel {
    private static final float DEFAULT_FONT_SIZE = 18.0f;

    private final BaseController baseControllerRef;
    private Map<ResourceType, JLabel> labelToResource = new EnumMap<>(ResourceType.class);

    /**
     * Constructs a panel that has the purpose of showing the player's resources.
     * @param baseControllerRef the reference for the baseController
     */
    public ResourcePanelImpl(final BaseController baseControllerRef) {
        this.setOpaque(false);
        this.baseControllerRef = baseControllerRef;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Font textFont = BattlePanelStyle.getPrimaryFont().deriveFont(DEFAULT_FONT_SIZE);
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
            public void update(final UUID buildingId) {
                if (baseControllerRef.requestBuildingMap()
                    .get(buildingId).getProductionProgress() == 100) {
                    updateResourceDisplay();
                }
            }
        });
        updateResourceDisplay();
    }
    /**
     * Updates the resource display of this panel.
     */
    public void updateResourceDisplay() {
        baseControllerRef.requestResourceCount().stream()
            .forEach(resource -> 
                labelToResource.get(resource.getResource())
                    .setText(resource.getResource().name().toUpperCase()
                        + ": " + resource.getAmount())
            );
    }
}
