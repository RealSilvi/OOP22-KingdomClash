package it.unibo.view.city;

import it.unibo.controller.base.BaseControllerImpl;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.kingdomclash.config.GameConfiguration;
import it.unibo.view.GameGui;
import it.unibo.kingdomclash.config.PathIconsConfiguration;
import it.unibo.view.battle.panels.entities.DrawPanelImpl;
import it.unibo.view.city.panels.api.BarPanel;
import it.unibo.view.city.panels.api.FieldCityPanel;
import it.unibo.view.city.panels.api.InternalElement;
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

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import javax.swing.JComponent;


/**
 * * This class show the main city panel.
 */
public class CityPanelImpl extends InternalElement implements CityPanel {

    private static final Dimension SIZE = new Dimension((int) (GameGui.getAllPanel().getWidth()),
            (int) (GameGui.getAllPanel().getHeight() * 0.05));
    private final JPanel mainPanel;
    private final BarPanel barPanel;
    private final FieldCityPanel fieldPanel;
    private final Map<BuildingTypes, Map<Integer, Image>> readImages =
        new EnumMap<>(BuildingTypes.class);

    private List<TileClickObserver> tileClickObservers;

    /**
     * @param controller    give the configuration and the parameter for each function
     * @param configuration set the configuration for displaying the panel
     */
    public CityPanelImpl(final BaseControllerImpl controller, final GameConfiguration configuration) {
        this.tileClickObservers = new ArrayList<>();
        PathIconsConfiguration config = configuration.getPathIconsConfiguration();
        for (BuildingTypes buildingType : BuildingTypes.values()) {
            Map<Integer, Image> imageLevel = new HashMap<>();
            for (int index = 0; index < 3; index++) {
                imageLevel.put(index, ImageIconsSupplier.loadImage(config.getBuilding(buildingType, index)));
            }
            readImages.put(buildingType, imageLevel);
        }


        this.mainPanel = new DrawPanelImpl(Color.BLACK,
                new Dimension(configuration.getCityConfiguration().getWidth(),
                        configuration.getCityConfiguration().getHeight()));
        this.mainPanel.setLayout(new BorderLayout());

        this.barPanel = new BarPanelImpl(this, controller, SIZE, readImages, configuration.getPathIconsConfiguration());
        this.fieldPanel = new FieldCityPanelImpl(this, controller, configuration, this.readImages);

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
        this.barPanel.setReturnActionListener(returnActionListener);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(value = "EI", 
    justification = "Returned panel should be adjusted")
    public JPanel getPanel() {
        return this.mainPanel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerTileClickObserver(final TileClickObserver tileClickObservertoRegister) {
        this.tileClickObservers.add(tileClickObservertoRegister);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unregisterTileClickObserver(final TileClickObserver tileClickObservertoUnregister) {
        this.tileClickObservers.remove(tileClickObservertoUnregister);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyTileClick(final JComponent tile, final Point2D.Float position) {
        this.tileClickObservers.stream().forEach(tileObserver ->
                tileObserver.tileClicked(tile, position));
    }
}
