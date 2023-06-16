package it.unibo.view.city.panels.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.Map.Entry;

import javax.swing.*;

import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.city.CityPanel;
import it.unibo.view.city.panels.api.BarPanel;
import it.unibo.view.city.panels.api.TileClickObserver;
import it.unibo.view.city.utilities.ResourcePopupPanel;
import it.unibo.view.city.utilities.TroopPopupPanel;
import it.unibo.controller.base.BaseController;
import it.unibo.controller.base.BaseControllerImpl;
import it.unibo.model.base.basedata.Building;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;

public class BarPanelImpl extends JLabel implements BarPanel {

    private final CityPanel cityView;
    private final JPanel mainpanel;
    private BaseController basedata;
    private ResourcePopupPanel resourcepopup;
    private TroopPopupPanel trooppopup;
    
    private List<JComponent> interactionComponents;

    private Optional<String> actionCommand = Optional.empty();

    private int faus = 9;

    private boolean selectionActive = false;
    private boolean constructionAction = false;
    private boolean upgradeAction = false;
    private boolean demolishAction = false;

    public BarPanelImpl(CityPanel cityView, BaseController controller,
        Dimension size, Map<BuildingTypes, Map<Integer, Image>> readImages){
        this.cityView = cityView;
        this.mainpanel=new DrawPanel(Color.BLACK, size);
        this.basedata=controller;
        this.resourcepopup = new ResourcePopupPanel(mainpanel, 100, 0, new ResourcePanelImpl(controller));
        this.trooppopup = new TroopPopupPanel(mainpanel, 200, 0, controller);
        this.interactionComponents = new ArrayList<>();

        ActionListener genericBtnAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent responsible = ((JComponent) e.getSource());
                if (selectionActive) {
                    resetConditions();
                }
                setOptionsLocked();
                responsible.setEnabled(true);
                selectionActive = !selectionActive;
            }
        };
        BuildingPanel buildingPanel = new BuildingPanel(readImages);
        buildingPanel.addBuildingSelectActionListener(genericBtnAction);
        buildingPanel.addBuildingSelectActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (actionCommand.isPresent()) {
                    System.out.println("Setting false ");
                    actionCommand = Optional.empty();
                    return;
                }
                constructionAction = true;
                actionCommand = Optional.of(e.getActionCommand());
                System.out.println("Pressed "+e.getActionCommand());
                faus = 0;
            }
        });

        this.cityView.registerTileClickObserver(new TileClickObserver() {
            @Override
            public void tileClicked(JComponent tile, Float position) {
                if (selectionActive && actionCommand.isPresent()) {
                    Optional<UUID> building = findBuildingbyPosition(position);
                    if (building.isEmpty()) {
                        if (constructionAction) {
                        controller.handleBuildingPlaced(position,
                            BuildingTypes.valueOf(actionCommand.get()), 0,true);
                        }
                    } else {
                        if (upgradeAction) {
                            controller.handleStructureUpgrade(building.get());
                        } else if (demolishAction) {
                            controller.handleStructureDestruction(building.get());
                        }
                    }
                    setOptionsLocked();
                    resetConditions();
                    selectionActive = false;
                    actionCommand = Optional.empty();
                }
            }
        });

        ResourcePanelImpl resourcePanel = new ResourcePanelImpl(controller);

        GridLayout barGridLayout = new GridLayout();
        barGridLayout.setHgap(5);
        this.mainpanel.setLayout(barGridLayout);
        
        JButton troop= new JButton("Upgrade Troops");
        JButton playerinfo= new JButton("player info");
        
        JButton upgradeBtn = new JButton("Upgrade Building");
        upgradeBtn.addActionListener(genericBtnAction);
        upgradeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetConditions();
                upgradeAction = true;
            }
        });

        JButton demolishBtn = new JButton("Demolish Building");
        demolishBtn.addActionListener(genericBtnAction);
        demolishBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetConditions();
                demolishAction = true;
            }
        });

        interactionComponents.add(upgradeBtn);
        interactionComponents.add(demolishBtn);

        interactionComponents.add(buildingPanel);

        this.mainpanel.add(buildingPanel);
        this.mainpanel.add(troop);
        this.mainpanel.add(playerinfo);
        this.mainpanel.add(upgradeBtn);
        this.mainpanel.add(demolishBtn);
        this.mainpanel.add(resourcePanel);
        //this.mainpanel.add(resourcebutton);

        
        troop.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                trooppopup.changeVisibility();
                
            }
        });
    }
    
    public void setOptionsLocked() {
        this.interactionComponents.stream().forEach(component ->
            component.setEnabled(!component.isEnabled())
        );
    }
    private void resetConditions() {
        this.constructionAction = false;
        this.upgradeAction = false;
        this.demolishAction = false;
    }

    public JTextArea getInfo(Dimension size) {

        JTextArea info = new JTextArea();
        info.setSize(size);
       
        basedata.requestTroopLevels().keySet().stream().forEach(
            singletroop -> info.append("\n"+singletroop.name()+"\n"
                +basedata.requestTroopLevels().get(singletroop))

            
        );
        
       
        info.setEditable(false);
        


        
        return info;

    }

    /*devo riportare una lista delle truppe con il loro nome e il livello */

    @Override
    public void getPlayerInfo(BaseControllerImpl basedata) {
        System.out.println(""+basedata.requestPlayerName());
        /*aggiungere il tempo trascorso?? */
    }
    

    public JPanel getPanel(){
        return this.mainpanel;
    }

    public void disposeAllPopups() {
        resourcepopup.dispose();
        trooppopup.dispose();
    }

    private Optional<UUID> findBuildingbyPosition(Point2D.Float position) {
        Optional<UUID> resultIdentifier = Optional.empty();
        List<Entry<UUID, Building>> idlist = this.basedata.requestBuildingMap()
            .entrySet().stream().filter(buildingEntry -> buildingEntry.getValue().getStructurePos().equals(position))
            .toList();
        if (idlist.size() == 1) {
            resultIdentifier = Optional.of(idlist.get(0).getKey());
        }
        return resultIdentifier;
    }
}
