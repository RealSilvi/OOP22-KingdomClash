package it.unibo.view.battle.panels.impl;

import it.unibo.view.battle.Troop;
import it.unibo.view.battle.panels.api.InfoPanel;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;
import it.unibo.view.battle.panels.utilities.PanelDimensions;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InfoPanelImpl implements InfoPanel {
    
    private final JPanel mainPanel;
    private final List<JLabel> table;

    public InfoPanelImpl(final int nrOfTroops) {
        this.mainPanel=new DrawPanel(ImageIconsSupplier.BACKGROUND_SIDE);
        this.table=new ArrayList<>();
        
        this.mainPanel.setLayout(new GridLayout(nrOfTroops,3));


        this.mainPanel.setMinimumSize(PanelDimensions.getSidePanel());
        this.mainPanel.setMaximumSize(PanelDimensions.getSidePanel());
    }

    @Override
    public void drawTable(Map<Troop, Boolean> powerTable) {

    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

}
