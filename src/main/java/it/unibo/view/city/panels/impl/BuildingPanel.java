package it.unibo.view.city.panels.impl;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.model.data.Resource;
import it.unibo.view.utilities.GraphicUtils;

public class BuildingPanel extends JPanel {
    private Map<BuildingTypes, JButton> buildingButtonType;

    public BuildingPanel(Map<BuildingTypes, Map<Integer, Image>> buildingImages) {
        this.buildingButtonType = new EnumMap<>(BuildingTypes.class);
        this.setOpaque(false);
        GridLayout gridLayout = new GridLayout();
        gridLayout.setHgap(10);
        this.setLayout(gridLayout);
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        for (BuildingTypes type : BuildingTypes.values()) {
            JButton buildingToBuildBtn = new JButton();
            buildingToBuildBtn.setPreferredSize(new Dimension(50, 50));
            buildingToBuildBtn.setIcon(new ImageIcon(GraphicUtils
                        .resizeImageWithProportion(buildingImages.get(type).get(0),
                            5,
                            5)));
            buildingToBuildBtn.setToolTipText("<html>"+type.name()
                .substring(0, 1).toUpperCase(getLocale())
                +type.name().substring(1).toLowerCase(getLocale())
                +"<br>"
                +Resource.beautifyToString(type.getCost())
                .replace("\n", "<br>")+"</html>");
            buildingToBuildBtn.setActionCommand(type.name());
            this.buildingButtonType.put(type, buildingToBuildBtn);
            this.add(buildingToBuildBtn);
        }
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                super.componentResized(e);
                buildingButtonType.keySet().stream().forEach(buttonType -> 
                    buildingButtonType.get(buttonType).setIcon(new ImageIcon(GraphicUtils
                        .resizeImageWithProportion(buildingImages.get(buttonType).get(0),
                            buildingButtonType.get(buttonType).getWidth(),
                            buildingButtonType.get(buttonType).getHeight()))));
            }
        });
    }
    @Override
    public void setEnabled(boolean isEnabled) {
        super.setEnabled(isEnabled);
        buildingButtonType.forEach((buildingType, button) -> 
            button.setEnabled(isEnabled)
        );
    }

    public void addBuildingSelectActionListener(ActionListener buildSelectActionListener) {
        buildingButtonType.keySet().stream().forEach(
            buttonType -> buildingButtonType
                .get(buttonType)
                .addActionListener(buildSelectActionListener));
    }
    public void removeBuildingSelectionActionListener(ActionListener buildSelectActionListener) {
        buildingButtonType.keySet().stream().forEach(
            buttonType -> buildingButtonType
                .get(buttonType)
                .removeActionListener(buildSelectActionListener));
    }
}
