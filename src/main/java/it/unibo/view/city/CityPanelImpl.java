
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
import javax.swing.JPanel;
import javax.swing.JComponent;


/**
 * * This class show the main city panel.
 */
public class CityPanelImpl implements CityPanel {

    private static final Dimension SIZE = new Dimension((int) (GameGui.getAllPanel().getWidth()),
        (int) (GameGui.getAllPanel().getHeight() * 0.05));
    private final JPanel mainPanel;
    private final BarPanelImpl barPanel;
    private final FieldCityPanelImpl fieldPanel;
    private final Map<BuildingTypes, Map<Integer, Image>> readImages = new EnumMap<>(BuildingTypes.class);

    private List<TileClickObserver> tileClickObservers;
    private ActionListener returnActionListener;
    /**
    *
     * @param controller give the configuration and the parameter for each function
     * @param configuration set the configuration for displaying the panel
     */
    public CityPanelImpl(final BaseControllerImpl controller,  final GameConfiguration configuration) {
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

        this.barPanel = new BarPanelImpl(this, controller, SIZE, readImages);
        this.fieldPanel = new FieldCityPanelImpl(this, controller,
            configuration, configuration.getCityConfiguration(), configuration.getPathIconsConfiguration(), this.readImages);

        this.mainPanel.add(barPanel.getPanel(), BorderLayout.NORTH);
        this.mainPanel.add(fieldPanel.getPanel(), BorderLayout.CENTER);
    }
    /**
     * {@inheritDoc}
     */
    public void disposeAll() {
        barPanel.disposeAllPopups();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setReturnActionListener(final ActionListener returnActionListener) {
        this.returnActionListener = returnActionListener;
    }
    /**
     * {@inheritDoc}
     */
    public JPanel getPanel() {
        return this.mainPanel;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void registerTileClickObserver(TileClickObserver tileClickObservertoRegister) {
        this.tileClickObservers.add(tileClickObservertoRegister);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void unregisterTileClickObserver(TileClickObserver tileClickObservertoUnregister) {
        this.tileClickObservers.remove(tileClickObservertoUnregister);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyTileClick(JComponent tile, Point2D.Float position) {
        this.tileClickObservers.stream().forEach(tileObserver -> 
            tileObserver.tileClicked(tile, position));
    }
}
