package it.unibo.view.city;

import it.unibo.controller.base.BaseController;
import it.unibo.controller.base.BaseControllerImpl;
import it.unibo.model.data.GameConfiguration;
import it.unibo.view.GameGui;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.city.panels.impl.BarPanelImpl;
import it.unibo.view.city.panels.impl.CityConfiguration;
import it.unibo.view.city.panels.impl.FieldCityPanelImpl;

import javax.swing.*;
import java.awt.*;

public class CityPanelImpl implements CityPanel {

    private static final Dimension size=new Dimension((int)(GameGui.getAllPanel().getWidth()),
        (int)(GameGui.getAllPanel().getHeight()*0.05));
    private final JPanel mainPanel;
    private final BarPanelImpl barPanel;
    private final FieldCityPanelImpl fieldPanel;
    

    public CityPanelImpl(BaseControllerImpl controller, GameConfiguration configuration) {

        
        this.mainPanel = new DrawPanel(Color.BLACK,
                new Dimension(configuration.getCityConfiguration().getWidth(),
                        configuration.getCityConfiguration().getHeight()));
        this.mainPanel.setLayout(new BorderLayout());

        this.barPanel = new BarPanelImpl(controller, size);
        this.fieldPanel = new FieldCityPanelImpl(configuration.getCityConfiguration(), configuration.getPathIconsConfiguration());


        this.mainPanel.add(barPanel.getPanel(), BorderLayout.NORTH);
        this.mainPanel.add(fieldPanel.getPanel(), BorderLayout.CENTER);
        

    }

    /*public CityPanelImpl(BaseController controller) {

        this.mainPanel = new DrawPanel(Color.BLACK, new Dimension(10, 10));
        this.mainPanel.setLayout(new BorderLayout());

        this.barPanel = new BarPanelImpl(controller);
        this.fieldPanel = new FieldCityPanelImpl(new CityConfiguration());


        this.mainPanel.add(barPanel.getPanel(), BorderLayout.NORTH);
        this.mainPanel.add(fieldPanel.getPanel(), BorderLayout.CENTER);
    }
    */


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


}
