package it.unibo.view.city.panels.impl;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import it.unibo.controller.base.BaseController;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.kingdomclash.config.GameConfiguration;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.view.GameGui;
import it.unibo.view.battle.panels.entities.DrawPanelImpl;
import it.unibo.view.city.CityPanel;
import it.unibo.view.city.panels.api.FieldCityPanel;
import it.unibo.view.city.panels.api.InternalElement;
import it.unibo.view.utilities.GraphicUtils;
import it.unibo.view.utilities.ImageIconsSupplier;
import it.unibo.view.map.MapPanel.ButtonIdentification;

/**
 * This class create the field where you can place the field.
 */
@SuppressFBWarnings(value = "Se", 
justification = "This GUI element will never be serialized")
public class FieldCityPanelImpl extends InternalElement implements FieldCityPanel {
    private static final long serialVersionUID = 123456789L;
    private final JPanel mainpanel;
    private final CityPanel cityView;
    private final BaseController baseController;
    private final Map<BuildingTypes, Map<Integer, Image>> readImages;
    private final List<List<JButton>> buttonmap;
    private final Map<UUID, Point2D> buildingTilePositions;

    /**
     * The constructor create the panel and set the background of the field.
     *
     * @param cityView          reference of the main city view
     * @param baseController    reference of the base controller
     * @param gameConfig        reference of the game configuration
     * @param readImages        reference of a map composed of loaded images
     *                          with textures for building types and level
     */
    @SuppressFBWarnings(value = "EI2", 
    justification = "Intended behaviour")
    public FieldCityPanelImpl(final CityPanel cityView,
                              final BaseController baseController,
                              final GameConfiguration gameConfig,
                              final Map<BuildingTypes, Map<Integer, Image>> readImages) {
        this.buildingTilePositions = new HashMap<>();
        this.cityView = cityView;
        this.baseController = baseController;
        this.readImages = readImages;
        final CityConfiguration gameConfiguration = gameConfig.getCityConfiguration();
        this.mainpanel = new DrawPanelImpl(ImageIconsSupplier.loadImage(gameConfig
                .getPathIconsConfiguration()
                .getImageMap().get(ButtonIdentification.TILE)),
                GameGui.getAllPanel());
        this.mainpanel.setLayout(
            new GridLayout(
                gameConfiguration.getWidth(), gameConfiguration.getHeight()));
        buttonmap = new ArrayList<>(gameConfiguration.getWidth() * gameConfiguration.getHeight());
        this.setfield(gameConfiguration.getWidth(), gameConfiguration.getHeight());
        this.baseController.addBuildingStateChangedObserver(this::updateBuildingOnField);
        this.baseController.requestBuildingMap()
            .keySet().forEach(this::updateBuildingOnField);
        this.mainpanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                refreshContent();
            }
        });
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void refreshContent() {
        baseController.requestBuildingMap()
            .keySet()
            .forEach(FieldCityPanelImpl.this::updateBuildingOnField);
    }
    private void setfield(final int width, final int height) {
        for (int i = 0; i < width; i++) {
            final List<JButton> cols = new ArrayList<>();
            final int coordX = i;
            for (int j = 0; j < height; j++) {
                final JButton structure = new JButton();
                cols.add(j, structure);
                structure.setOpaque(false);
                structure.setContentAreaFilled(false);
                structure.setFocusable(false);
                structure.setBorderPainted(false);
                this.mainpanel.add(structure);
                structure.setBorder(null);
                final int coordY = j;
                structure.addActionListener(e -> {
                    if (e.getSource() instanceof JComponent) {
                        cityView.notifyTileClick((JComponent) e.getSource(),
                                new Point2D.Float(coordX, coordY));
                    }
                });
            }
            buttonmap.add(i, cols);
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(value = "EI", 
    justification = "Returned panel should be adjusted")
    @Override
    public JPanel getPanel() {
        return this.mainpanel;
    }

    private void updateBuildingOnField(final UUID buildingToUpdate) {
        BuildingTypes type;
        int level;
        double xPos;
        double yPos;
        JButton tile;
        if (!this.baseController.requestBuildingMap()
                .containsKey(buildingToUpdate)) {
            xPos = this.buildingTilePositions.get(buildingToUpdate).getX();
            yPos = this.buildingTilePositions.get(buildingToUpdate).getY();
            tile = this.buttonmap.get((int) xPos).get((int) yPos);
            tile.setIcon(null);
        } else {
            if (!this.baseController.requestBuildingMap().get(buildingToUpdate).isBeingBuilt()) {
                type = this.baseController.requestBuildingMap().get(buildingToUpdate).getType();
                level = this.baseController.requestBuildingMap().get(buildingToUpdate).getLevel();
                xPos = this.baseController.requestBuildingMap().get(buildingToUpdate).getStructurePos().getX();
                yPos = this.baseController.requestBuildingMap().get(buildingToUpdate).getStructurePos().getY();
                this.buildingTilePositions.put(buildingToUpdate,
                        this.baseController
                                .requestBuildingMap()
                                .get(buildingToUpdate).getStructurePos());
                tile = this.buttonmap.get((int) xPos).get((int) yPos);
                tile.setIcon(new ImageIcon(GraphicUtils.resizeImageWithProportion(this.readImages.get(type).get(level),
                    tile.getWidth(), tile.getHeight())));
            }
        }
    }
}
