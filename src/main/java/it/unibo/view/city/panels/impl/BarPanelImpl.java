package it.unibo.view.city.panels.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.city.panels.api.BarPanel;
import it.unibo.view.city.utilities.ResourcePopupPanel;
import it.unibo.view.city.utilities.TroopPopupPanel;
import it.unibo.controller.base.BaseController;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
/**
 * This class implement the panel on the top of the city panel.
 */
public class BarPanelImpl extends JLabel implements BarPanel {

    private final JPanel mainpanel;
    private final BaseController basedata;
    private final ResourcePopupPanel resourcepopup;
    private final TroopPopupPanel trooppopup;
    private final List<JComponent> interactionComponents;
    private boolean selectionActive = false;
    private boolean constructionAction = false;
    private boolean upgradeAction = false;
    private boolean demolishAction = false;
        //sopprimere il basecontroller
        /**
         * The costructor create the panel and add the buttons on the panel that show the troops,
         *  which building you can place and applicate an actionlistener on each other.
         * @param controller give all the function the class need 
         * @param size gave the size of the panel
         * @param readImages chiedere a marco
         */
        public BarPanelImpl(BaseController controller, final Dimension size,
        final Map<BuildingTypes, Map<Integer, Image>> readImages) {
        this.mainpanel = new DrawPanel(Color.BLACK, size);
        this.basedata = controller;
        this.resourcepopup = new ResourcePopupPanel(mainpanel, 100, 0, new ResourcePanelImpl(controller));
        this.trooppopup = new TroopPopupPanel(mainpanel, 200, 0, controller);
        this.interactionComponents = new ArrayList<>();
        
        final ActionListener genericBtnAction = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                JComponent responsible = ((JComponent) e.getSource());
                if (selectionActive) {
                    resetConditions();
                }
                setOptionsLocked();
                responsible.setEnabled(true);
                selectionActive = !selectionActive;
            }
        };
        final BuildingPanel buildingPanel = new BuildingPanel(readImages);
        buildingPanel.addBuildingSelectActionListener(genericBtnAction);
        buildingPanel.addBuildingSelectActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                resetConditions();
                constructionAction = true;
            }
        });

        final ResourcePanelImpl resourcePanel = new ResourcePanelImpl(controller);

        GridLayout barGridLayout = new GridLayout();
        barGridLayout.setHgap(5);
        this.mainpanel.setLayout(barGridLayout);
        
        final JButton troop = new JButton("Upgrade Troops");
        final JButton playerinfo = new JButton("player info");
        final JButton upgradeBtn = new JButton("Upgrade Building");
        upgradeBtn.addActionListener(genericBtnAction);
        upgradeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetConditions();
                upgradeAction = true;
            }
        });

        final JButton demolishBtn = new JButton("Demolish Building");
        demolishBtn.addActionListener(genericBtnAction);
        demolishBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
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
        troop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                trooppopup.changeVisibility();
            }
        });
    }
    /**
     * {@inheritDoc}
     */
    public void setOptionsLocked() {
        this.interactionComponents.stream().forEach(component ->
            component.setEnabled(!component.isEnabled())
        );
    }
    /**
     * 
     */
    private void resetConditions() {
        this.constructionAction = false;
        this.upgradeAction = false;
        this.demolishAction = false;
    }
    /**
     * {@inheritDoc}
     */
    public JPanel getPanel() {
        return this.mainpanel;
    }
    /**
     * {@inheritDoc}
     */
    public void disposeAllPopups() {
        resourcepopup.dispose();
        trooppopup.dispose();
    }

}
