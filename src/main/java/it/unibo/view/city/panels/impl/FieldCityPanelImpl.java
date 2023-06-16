
package it.unibo.view.city.panels.impl;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import it.unibo.view.GameGui;
import it.unibo.view.battle.config.PathIconsConfiguration;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.city.panels.api.FieldCityPanel;
import it.unibo.view.utilities.ImageIconsSupplier;
/**
 * This class create the field where you can place the field.
 */
public class FieldCityPanelImpl implements FieldCityPanel {

    private final JPanel mainpanel;
    private List<List<JButton>> buttonmap;
    private CityConfiguration gameConfiguration;
    private PathIconsConfiguration pathIconsConfiguration;
    /**
     * The costructor create the panel and set the background of the field.
     * @param gameConfiguration gave the width and height for the field
     * @param pathIconsConfiguration gave the textures of the building and of the background for the field
     */
    public FieldCityPanelImpl(final CityConfiguration gameConfiguration, final PathIconsConfiguration pathIconsConfiguration) {

        this.gameConfiguration = gameConfiguration;
        this.pathIconsConfiguration = pathIconsConfiguration;
        this.mainpanel = new DrawPanel(ImageIconsSupplier.loadImage(pathIconsConfiguration.getBackgroundCity()), 
            GameGui.getAllPanel());
        this.mainpanel.setLayout(new GridLayout(gameConfiguration.getWidth(),gameConfiguration.getHeight()));
        buttonmap = new ArrayList<>(gameConfiguration.getWidth()* gameConfiguration.getHeight());
        this.setfield(gameConfiguration.getWidth(), gameConfiguration.getHeight());
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
                    public void actionPerformed(ActionEvent e) {
                        new Point2D.Float(coordX, coordY);
                        System.out.println(new Point2D.Float(coordX, coordY));
                    }
                    
                });
            }
            buttonmap.add(i, cols);
    }
        }
    /**
     * {@inheritDoc}
     */
    public JPanel getPanel(){
        return this.mainpanel;
    }

}
