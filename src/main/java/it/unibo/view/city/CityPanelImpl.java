package it.unibo.view.city;

import it.unibo.controller.base.BaseControllerImpl;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.model.data.GameConfiguration;
import it.unibo.view.GameGui;
import it.unibo.view.battle.config.PathIconsConfiguration;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.city.panels.api.TileClickObserver;
import it.unibo.view.city.panels.impl.BarPanelImpl;
import it.unibo.view.city.panels.impl.FieldCityPanelImpl;
import it.unibo.view.utilities.ImageIconsSupplier;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class CityPanelImpl implements CityPanel {

    private static final Dimension size=new Dimension((int)(GameGui.getAllPanel().getWidth()),
        (int)(GameGui.getAllPanel().getHeight()*0.05));
    private final JPanel mainPanel;
    private final BarPanelImpl barPanel;
    private final FieldCityPanelImpl fieldPanel;
    private final Map<BuildingTypes, Map<Integer, Image>> readImages = new EnumMap<>(BuildingTypes.class);

    private List<TileClickObserver> tileClickObservers;

    private ActionListener returnActionListener;

    public CityPanelImpl(BaseControllerImpl controller, GameConfiguration configuration) {
        this.tileClickObservers = new ArrayList<>();
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

        this.barPanel = new BarPanelImpl(this, controller, size, readImages);
        this.fieldPanel = new FieldCityPanelImpl(this,
            configuration.getCityConfiguration(), configuration.getPathIconsConfiguration());

        this.mainPanel.add(barPanel.getPanel(), BorderLayout.NORTH);
        this.mainPanel.add(fieldPanel.getPanel(), BorderLayout.CENTER);
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

    @Override
    public void setReturnActionListener(ActionListener returnActionListener) {
        this.returnActionListener = returnActionListener;
    }

    public JPanel getPanel() {
        return this.mainPanel;
    }

    @Override
    public void registerTileClickObserver(TileClickObserver tileClickObservertoRegister) {
        this.tileClickObservers.add(tileClickObservertoRegister);
    }

    @Override
    public void unregisterTileClickObserver(TileClickObserver tileClickObservertoUnregister) {
        this.tileClickObservers.remove(tileClickObservertoUnregister);
    }

    @Override
    public void notifyTileClick(JComponent tile, Point2D.Float position) {
        this.tileClickObservers.stream().forEach(tileObserver -> 
            tileObserver.tileClicked(tile, position));
    }
}
