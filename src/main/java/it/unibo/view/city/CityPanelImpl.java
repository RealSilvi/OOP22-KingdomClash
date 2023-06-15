package it.unibo.view.city;

import it.unibo.controller.base.BaseControllerImpl;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.model.data.GameConfiguration;
import it.unibo.view.GameGui;
import it.unibo.view.battle.config.PathIconsConfiguration;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.city.panels.impl.BarPanelImpl;
import it.unibo.view.city.panels.impl.FieldCityPanelImpl;
import it.unibo.view.utilities.ImageIconsSupplier;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class CityPanelImpl implements CityPanel {

    private static final Dimension size=new Dimension((int)(GameGui.getAllPanel().getWidth()),
        (int)(GameGui.getAllPanel().getHeight()*0.05));
    private final JPanel mainPanel;
    private final BarPanelImpl barPanel;
    private final FieldCityPanelImpl fieldPanel;

    private final Map<BuildingTypes, Map<Integer, Image>> readImages = new EnumMap<>(BuildingTypes.class);

    public CityPanelImpl(BaseControllerImpl controller, GameConfiguration configuration) {
        PathIconsConfiguration config = configuration.getPathIconsConfiguration();
        for (BuildingTypes buildingType : BuildingTypes.values()) {
            Map<Integer, Image> imageLevel = new HashMap<>();
            for (int index = 0; index < 3; index++) {
                imageLevel.put(index, ImageIconsSupplier.loadImage(config.getBuilding(buildingType, index)));
            }
            readImages.put(buildingType, imageLevel);
        }

        
        this.mainPanel = new DrawPanel(Color.BLACK,
                new Dimension(configuration.getCityConfiguration().getWidth(),
                        configuration.getCityConfiguration().getHeight()));
        this.mainPanel.setLayout(new BorderLayout());

        this.barPanel = new BarPanelImpl(controller, size, readImages);
        this.fieldPanel = new FieldCityPanelImpl(configuration.getCityConfiguration(), configuration.getPathIconsConfiguration());


        this.mainPanel.add(barPanel.getPanel(), BorderLayout.NORTH);
        this.mainPanel.add(fieldPanel.getPanel(), BorderLayout.CENTER);
    }

    public JPanel getPanel() {
        return this.mainPanel;
    }


    @Override
    public void setBuildings() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setBuildings'");
    }

    @Override
    public void setfield() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setfield'");
    }

    @Override
    public void resources() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resources'");
    }

    public void disposeAll() {
        barPanel.disposeAllPopups();
    }
}
