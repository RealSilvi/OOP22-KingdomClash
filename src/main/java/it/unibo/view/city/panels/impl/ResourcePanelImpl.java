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

public class ResourcePanelImpl extends JPanel {
    private Map<ResourceType, JLabel> labelToResource = new EnumMap<>(ResourceType.class);
    
    public ResourcePanelImpl(BaseController controller) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Arrays.stream(ResourceType.values())
            .forEach(resourceType -> {
                JLabel resourceLabel = new JLabel();
                labelToResource.put(resourceType, resourceLabel);
                this.add(resourceLabel);
            });
        controller.addBuildingProductionObserver(new BuildingObserver() {
            @Override
            public void update(UUID buildingId) {
                if (controller.requestBuildingMap()
                    .get(buildingId).getProductionProgress() == 100) {
                    controller.requestResourceCount().stream()
                        .forEach(resource -> {
                            labelToResource.get(resource.getResource())
                                .setText(resource.getResource().name().toUpperCase()
                                    +": "+resource.getAmount());
                        });
                }
            }
        });
    }
}
