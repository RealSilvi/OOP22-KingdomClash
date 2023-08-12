package it.unibo.view.city.panels.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.util.Optional;
import java.util.UUID;
import java.util.Map.Entry;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.view.city.CityPanel;
import it.unibo.view.city.panels.api.BarPanel;
import it.unibo.view.city.panels.api.InternalElement;
import it.unibo.view.city.panels.api.TileClickObserver;
import it.unibo.view.city.utilities.TroopPopupPanel;
import it.unibo.view.menu.GameMenuImpl;
import it.unibo.view.menu.extensiveclasses.ImageButton;
import it.unibo.view.utilities.ImageIconsSupplier;
import it.unibo.controller.base.BaseController;
import it.unibo.kingdomclash.config.PathIconsConfiguration;
import it.unibo.model.base.basedata.Building;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;

/**
 * This class implement the panel on the top of the city panel.
 */
@SuppressFBWarnings(value = "Se", 
justification = "This GUI element will never be serialized")
public final class BarPanelImpl extends InternalElement implements BarPanel {
    private static final long serialVersionUID = 123456789L;
    private static final int HEIGHT = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.2);
    private static final int WIDTH = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.2);
    private static final Dimension DIMENSION_PANEL = new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
    (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 14);
    private static final Dimension DIMENSION_BUTTON = new Dimension(DIMENSION_PANEL.width / 7, DIMENSION_PANEL.height);
    private static final ImageIcon BACKGROUND_BUTTON = ImageIconsSupplier.getScaledImageIcon(GameMenuImpl.PATH_BUTTON,
        DIMENSION_BUTTON);
    private static final int DEFAULT_HGAP = 5;
    private final JButton mapReturnBtn;
    private final CityPanel cityView;
    private final BaseController basedata;
    private final TroopPopupPanel trooppopup;
    private final List<JComponent> interactionComponents;
    private Optional<String> actionCommand = Optional.empty();


    private boolean selectionActive = false;
    private boolean constructionAction = false;
    private boolean upgradeAction = false;
    private boolean demolishAction = false;

    /**
     * The costructor create the panel and add the buttons on the panel that show the troops,
     * which building you can place and applicate an actionlistener on each other.
     * @param cityView  the main panel where it's going to be add
     * @param controller give all the function the class need
     * @param size       gave the size of the panel
     * @param readImages a for each building level gave his texture
     * @param pathIconsConfiguration gave the troop image for the troop popup
     */
    @SuppressFBWarnings(value = "EI2", 
    justification = "Intended behaviour")
    public BarPanelImpl(final CityPanel cityView, final BaseController controller, final Dimension size,
         final Map<BuildingTypes, Map<Integer, Image>> readImages, final PathIconsConfiguration pathIconsConfiguration) {
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(size));
        this.cityView = cityView;
        this.basedata = controller;
        this.trooppopup = new TroopPopupPanel(this, controller, pathIconsConfiguration, WIDTH, HEIGHT);
        this.interactionComponents = new ArrayList<>();

        final ActionListener genericBtnAction = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JComponent responsible = (JComponent) e.getSource();
                if (selectionActive) {
                    resetConditions();
                }
                setOptionsLocked();
                responsible.setEnabled(true);
                selectionActive = !selectionActive;
            }
        };

        final InternalElement buildingPanel = new BuildingPanel(readImages);
        buildingPanel.addSelectionObserver(genericBtnAction);
        buildingPanel.addSelectionObserver(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (actionCommand.isPresent()) {
                    actionCommand = Optional.empty();
                    return;
                }
                constructionAction = true;
                actionCommand = Optional.of(e.getActionCommand());
            }
        });

        final InternalElement resourcePanel = new ResourcePanelImpl(controller);
        final GridLayout barGridLayout = new GridLayout();
        barGridLayout.setHgap(DEFAULT_HGAP);
        this.setLayout(barGridLayout);
        this.setPreferredSize(DIMENSION_PANEL);
        final JButton troop = new ImageButton("Upgrade Troops", BACKGROUND_BUTTON, DIMENSION_BUTTON);
        troop.setForeground(Color.white);

        final JButton playerinfo = new ImageButton("Player Info", BACKGROUND_BUTTON, DIMENSION_BUTTON);
        playerinfo.setForeground(Color.white);
        final JButton upgradeBtn = new ImageButton("Upgrade Building", BACKGROUND_BUTTON, DIMENSION_BUTTON);
        upgradeBtn.setForeground(Color.white);
        this.mapReturnBtn = new ImageButton("Return to Map", BACKGROUND_BUTTON, DIMENSION_BUTTON);
        mapReturnBtn.setForeground(Color.white);
        mapReturnBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                trooppopup.dispose();
            }
        });
        upgradeBtn.addActionListener(genericBtnAction);
        upgradeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                resetConditions();
                upgradeAction = true;
            }
        });

        final JButton demolishBtn = new ImageButton("Demolish Button", BACKGROUND_BUTTON, DIMENSION_BUTTON);
        demolishBtn.setForeground(Color.white);
        demolishBtn.addActionListener(genericBtnAction);
        demolishBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                resetConditions();
                demolishAction = true;
            }
        });

        this.cityView.registerTileClickObserver(new TileClickObserver() {
            @Override
            public void tileClicked(final JComponent tile, final Float position) {
                final Optional<UUID> building = findBuildingbyPosition(position);
                if (building.isEmpty() && (demolishAction || upgradeAction)) {
                    return ;
                }
                if (selectionActive) {
                    if (building.isEmpty() && actionCommand.isPresent()) {
                        if (constructionAction) {
                            controller.handleBuildingPlaced(position,
                                    BuildingTypes.valueOf(actionCommand.get()), 0, true);
                        }
                    } else if (building.isPresent()) {
                        if (upgradeAction) {
                            controller.handleStructureUpgrade(building.get());
                            upgradeBtn.setEnabled(false);

                        } else if (demolishAction) {
                            controller.handleStructureDestruction(building.get());
                            demolishBtn.setEnabled(false);
                        }
                    }
                    resourcePanel.refreshContent();
                    setOptionsLocked();
                    resetConditions();
                    selectionActive = false;
                    tile.setEnabled(true);
                    actionCommand = Optional.empty();
                }
            }
        });

        interactionComponents.add(upgradeBtn);
        interactionComponents.add(demolishBtn);
        interactionComponents.add(buildingPanel);
        this.add(buildingPanel);
        this.add(troop);
        this.add(upgradeBtn);
        this.add(demolishBtn);
        this.add(playerinfo);
        this.add(mapReturnBtn);
        this.add(resourcePanel);
        troop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                trooppopup.changeVisibility();
            }
        });
        playerinfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                JOptionPane.showMessageDialog(BarPanelImpl.this,
                    controller.requestPlayerName(),
                    "Player Name", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
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

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getPanel() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disposeAllPopups() {
        trooppopup.dispose();
    }
    /**
     * {@inheritDoc}
     * @param returnActionListener
     */
    @Override
    public void setReturnActionListener(final ActionListener returnActionListener) {
        this.mapReturnBtn.addActionListener(returnActionListener);
        System.out.println("ci passa");
    }

    private Optional<UUID> findBuildingbyPosition(final Point2D.Float position) {
        Optional<UUID> resultIdentifier = Optional.empty();
        final List<Entry<UUID, Building>> idlist = this.basedata.requestBuildingMap()
                .entrySet().stream().filter(buildingEntry -> buildingEntry.getValue().getStructurePos().equals(position))
                .toList();
        if (idlist.size() == 1) {
            resultIdentifier = Optional.of(idlist.get(0).getKey());
        }
        return resultIdentifier;
    }

}
