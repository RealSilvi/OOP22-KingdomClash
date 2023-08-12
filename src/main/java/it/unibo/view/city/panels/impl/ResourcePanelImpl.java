package it.unibo.view.city.panels.impl;

import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.controller.base.BaseController;
import it.unibo.model.base.api.BuildingObserver;
import it.unibo.model.data.Resource.ResourceType;
import it.unibo.view.city.panels.api.InternalElement;
import it.unibo.view.utilities.BattlePanelStyle;

/**
 * A simple panel class to show available resources to the player.
 */
@SuppressFBWarnings(value = "EI2", 
justification = "Intended behaviour")
public final class ResourcePanelImpl extends InternalElement {
    private static final long serialVersionUID = 123456789L;
    private static final float DEFAULT_FONT_SIZE = 18.0f;

    private transient final BaseController baseControllerRef;
    private final Map<ResourceType, JLabel> labelToResource = new EnumMap<>(ResourceType.class);

    /**
     * Constructs a panel that has the purpose of showing the player's resources.
     * @param baseControllerRef the reference for the baseController
     */
    public ResourcePanelImpl(final BaseController baseControllerRef) {
        this.setOpaque(false);
        this.baseControllerRef = baseControllerRef;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        final Font textFont = BattlePanelStyle.getPrimaryFont().deriveFont(DEFAULT_FONT_SIZE);
        Arrays.stream(ResourceType.values())
            .forEach(resourceType -> {
                final JLabel resourceLabel = new JLabel();
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
                    refreshContent();
                }
            }
        });
        refreshContent();
    }
    /**
     * Updates the resource display of this panel.
     */
    @Override
    public void refreshContent() {
        baseControllerRef.requestResourceCount().stream()
            .forEach(resource -> 
                labelToResource.get(resource.getResource())
                    .setText(resource.getResource().name().toUpperCase(Locale.getDefault()).
                    toLowerCase(Locale.getDefault())    + ": " + resource.getAmount())
            );
    }
}
