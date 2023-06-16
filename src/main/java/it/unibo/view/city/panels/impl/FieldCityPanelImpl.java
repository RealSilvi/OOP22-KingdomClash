package it.unibo.view.city.panels.impl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

import it.unibo.controller.base.BaseController;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.model.data.GameConfiguration;
import it.unibo.view.GameGui;
import it.unibo.view.battle.config.PathIconsConfiguration;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.city.CityPanel;
import it.unibo.view.city.panels.api.FieldCityPanel;
import it.unibo.view.utilities.GraphicUtils;
import it.unibo.view.utilities.ImageIconsSupplier;
import it.unibo.view.map.MapPanel.ButtonIdentification;

public class FieldCityPanelImpl implements FieldCityPanel {

    private final JPanel mainpanel;
    private final CityPanel cityView;
    private List<List<JButton>> buttonmap;
    private CityConfiguration gameConfiguration;
    private PathIconsConfiguration pathIconsConfiguration;
    //TODO: Require only 1 configuration and fix names
    public FieldCityPanelImpl(CityPanel cityView, BaseController baseController,
        GameConfiguration gameConfig, CityConfiguration gameConfiguration, PathIconsConfiguration pathIconsConfiguration,
        final Map<BuildingTypes, Map<Integer, Image>> readImages) {
        this.cityView = cityView;
        //GraphicUtils.resizeImage(new ImageIcon(),JButton.WIDTH,JButton.HEIGHT);
        this.gameConfiguration=gameConfiguration;
        this.pathIconsConfiguration=pathIconsConfiguration;
        this.mainpanel= new DrawPanel(ImageIconsSupplier.loadImage(gameConfig
            .getMapConfiguration()
            .getImageMap().get(ButtonIdentification.TILE)),
            GameGui.getAllPanel());
        this.mainpanel.setLayout(new GridLayout(gameConfiguration.getWidth(),gameConfiguration.getHeight()));
        buttonmap= new ArrayList<>(gameConfiguration.getWidth()* gameConfiguration.getHeight());
        this.setfield(gameConfiguration.getWidth(), gameConfiguration.getHeight());
        baseController.addBuildingStateChangedObserver(responsibleUUID -> {
            if (!baseController.requestBuildingMap().get(responsibleUUID).isBeingBuilt()) {
                BuildingTypes type = baseController.requestBuildingMap().get(responsibleUUID).getType();
                int level = baseController.requestBuildingMap().get(responsibleUUID).getLevel();
                Double xPos = baseController.requestBuildingMap().get(responsibleUUID).getStructurePos().getX();
                Double yPos = baseController.requestBuildingMap().get(responsibleUUID).getStructurePos().getY();
                JButton tile = this.buttonmap.get(xPos.intValue()).get(yPos.intValue());
                tile.setIcon(new ImageIcon(GraphicUtils.resizeImageWithProportion(readImages.get(type).get(level), tile.getWidth(), tile.getHeight())));
            }
        });
    }

    
    private void setfield(int width, int height) {
       
        
        for (int i = 0; i < width; i++) {
            List<JButton> cols= new ArrayList<>();
            final int coordX = i;
            for (int j = 0; j < height; j++) {
                /*ognuna di esse ha una determinata posizione -aggiungere un listener per ciascun bottone con la quale possa piazzare la struttura 
                */
                
                final JButton structure= new JButton();
                cols.add(j, structure);
                /*il campo viene creato per caso magari aggiungo un metodo che generi immagini e le piazzi per caso */
                structure.setOpaque(false);
                structure.setContentAreaFilled(false);
                this.mainpanel.add(structure);
                structure.setBorder(null);
                                
                final int coordY = j;
                structure.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
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

    public JPanel getPanel(){
        return this.mainpanel;
    }

    public void getButtonPos(int x, int y){
        this.buttonmap.get(x).get(y);
    }


}