
package it.unibo.view.city.panels.impl;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.unibo.controller.base.BaseController;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.model.data.GameConfiguration;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import it.unibo.view.GameGui;
import it.unibo.view.battle.config.PathIconsConfiguration;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.city.CityPanel;
import it.unibo.view.city.panels.api.FieldCityPanel;
import it.unibo.view.utilities.GraphicUtils;
import it.unibo.view.utilities.ImageIconsSupplier;
import it.unibo.view.map.MapPanel.ButtonIdentification;

/**
 * This class create the field where you can place the field.
 */
public class FieldCityPanelImpl implements FieldCityPanel {

    private final JPanel mainpanel;
    private final CityPanel cityView;
    private List<List<JButton>> buttonmap;
    private CityConfiguration gameConfiguration;
    private PathIconsConfiguration pathIconsConfiguration;

    /**
     * The costructor create the panel and set the background of the field.
     *
     * @param cityView
     * @param baseController
     * @param gameConfig
     * @param gameConfiguration gave the width and height for the field
     * @param pathIconsConfiguration gave the textures of the building and of the background for the field
     * @param readImages a for each building level gave his texture
     */
    //TODO: Require only 1 configuration and fix names
    public FieldCityPanelImpl(final CityPanel cityView, final BaseController baseController,
        GameConfiguration gameConfig, final CityConfiguration gameConfiguration, final PathIconsConfiguration pathIconsConfiguration,
        final Map<BuildingTypes, Map<Integer, Image>> readImages) {
        this.cityView = cityView;
        //GraphicUtils.resizeImage(new ImageIcon(),JButton.WIDTH,JButton.HEIGHT);
        this.gameConfiguration = gameConfiguration;
        this.pathIconsConfiguration = pathIconsConfiguration;
        this.mainpanel = new DrawPanel(ImageIconsSupplier.loadImage(gameConfig
            .getMapConfiguration()
            .getImageMap().get(ButtonIdentification.TILE)),
            GameGui.getAllPanel());
        this.mainpanel.setLayout(new GridLayout(gameConfiguration.getWidth(), gameConfiguration.getHeight()));
        buttonmap = new ArrayList<>(gameConfiguration.getWidth()* gameConfiguration.getHeight());
        this.setfield(gameConfiguration.getWidth(), gameConfiguration.getHeight());
        baseController.addBuildingStateChangedObserver(responsibleUUID -> {
            if (!baseController.requestBuildingMap().get(responsibleUUID).isBeingBuilt()) {
                BuildingTypes type = baseController.requestBuildingMap().get(responsibleUUID).getType();
                int level = baseController.requestBuildingMap().get(responsibleUUID).getLevel();
                Double xPos = baseController.requestBuildingMap().get(responsibleUUID).getStructurePos().getX();
                Double yPos = baseController.requestBuildingMap().get(responsibleUUID).getStructurePos().getY();
                JButton tile = this.buttonmap.get(xPos.intValue()).get(yPos.intValue());
                tile.setIcon(new ImageIcon(GraphicUtils.resizeImageWithProportion(readImages.get(type).get(level),
                    tile.getWidth(), tile.getHeight())));
            }
        });
    }

    private void setfield(final int width, final int height) {
        for (int i = 0; i < width; i++) {
            List<JButton> cols = new ArrayList<>();
            final int coordX = i;
            for (int j = 0; j < height; j++) {
                final JButton structure = new JButton();
                cols.add(j, structure);
                structure.setOpaque(false);
                structure.setContentAreaFilled(false);
                this.mainpanel.add(structure);
                structure.setBorder(null);               
                final int coordY = j;
                structure.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e) {
                         if (e.getSource() instanceof JComponent) {
                            cityView.notifyTileClick((JComponent) e.getSource(),
                                new Point2D.Float(coordX, coordY));
                        }
                    }
                });
            }
            buttonmap.add(i, cols);
    }
        }
    /**
     * {@inheritDoc}
     */
    public JPanel getPanel() {
        return this.mainpanel;
    }

}
