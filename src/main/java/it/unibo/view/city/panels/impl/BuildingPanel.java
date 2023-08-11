package it.unibo.view.city.panels.impl;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.EnumMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.model.data.Resource;
import it.unibo.view.city.panels.api.InternalElement;
import it.unibo.view.utilities.GraphicUtils;

/**
 * A panel that displays in an ordered grid the type of buildings that the
 * player can build.
 */
public final class BuildingPanel extends InternalElement {
    private static final int ELEMENT_SPACING = 5;
    private static final int INITIAL_IMAGE_DIM = 5;

    private Map<BuildingTypes, JButton> buildingButtonType;
    private final Map<BuildingTypes, Map<Integer, Image>> buildingImages;
    /**
     * Constructs a BuildingPanel given a map of textures.
     * @param buildingImages    a map composed of already loaded images
     *                          representing the various type of buildings and
     *                          all of the available levels.
     */
    @SuppressFBWarnings(value = "EI2", 
    justification = "Intended behaviour")
    public BuildingPanel(final Map<BuildingTypes, Map<Integer, Image>> buildingImages) {
        this.buildingButtonType = new EnumMap<>(BuildingTypes.class);
        this.buildingImages = buildingImages;
        this.setOpaque(false);
        GridLayout gridLayout = new GridLayout();
        gridLayout.setHgap(10);
        this.setLayout(gridLayout);
        this.setBorder(new EmptyBorder(ELEMENT_SPACING,
            ELEMENT_SPACING, ELEMENT_SPACING, ELEMENT_SPACING));
        for (BuildingTypes type : BuildingTypes.values()) {
            JButton buildingToBuildBtn = new JButton();
            buildingToBuildBtn.setIcon(new ImageIcon(GraphicUtils
                .resizeImageWithProportion(this.buildingImages.get(type).get(0),
                    INITIAL_IMAGE_DIM, INITIAL_IMAGE_DIM)));
            buildingToBuildBtn.setToolTipText("<html>" + type.name()
                .substring(0, 1).toUpperCase(getLocale())
                + type.name().substring(1).toLowerCase(getLocale())
                + "<br>"
                + Resource.beautifyToString(type.getCost())
                .replace("\n", "<br>") + "</html>");
            buildingToBuildBtn.setActionCommand(type.name());
            this.buildingButtonType.put(type, buildingToBuildBtn);
            this.add(buildingToBuildBtn);
        }
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                super.componentResized(e);
                refreshContent();
            }
        });
    }
    @Override
    public void setEnabled(final boolean isEnabled) {
        super.setEnabled(isEnabled);
        buildingButtonType.forEach((buildingType, button) -> 
            button.setEnabled(isEnabled)
        );
    }
    @Override
    public void refreshContent() {
        buildingButtonType.keySet().stream().forEach(buttonType -> 
            buildingButtonType.get(buttonType).setIcon(new ImageIcon(GraphicUtils
                .resizeImageWithProportion(this.buildingImages.get(buttonType).get(0),
                    buildingButtonType.get(buttonType).getWidth(),
                    buildingButtonType.get(buttonType).getHeight()))));
    }
    /**
     * Adds a listener that gets called whenever a building type is selected.
     * @param buildSelectActionListener the listener to call when this event happens
     */
    @Override
    public void addSelectionObserver(
        final ActionListener buildSelectActionListener) {
        buildingButtonType.keySet().stream().forEach(
            buttonType -> buildingButtonType
                .get(buttonType)
                .addActionListener(buildSelectActionListener));
    }
    /**
     * Removes a listener that gets called whenever a building type is selected.
     * @param buildSelectActionListener the listener to remove
     */
    @Override
    public void removeSelectionObserver(
        final ActionListener buildSelectActionListener) {
        buildingButtonType.keySet().stream().forEach(
            buttonType -> buildingButtonType
                .get(buttonType)
                .removeActionListener(buildSelectActionListener));
    }
}
