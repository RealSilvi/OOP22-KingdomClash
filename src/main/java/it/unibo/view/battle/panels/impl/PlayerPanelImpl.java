package it.unibo.view.battle.panels.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.data.TroopType;
import it.unibo.kingdomclash.config.PathIconsConfiguration;
import it.unibo.view.battle.panels.api.PlayerPanel;
import it.unibo.view.battle.panels.entities.DrawPanelImpl;
import it.unibo.view.battle.panels.entities.impl.TroopButtonImpl;
import it.unibo.view.utilities.ImageIconsSupplier;
import it.unibo.view.battle.panels.PanelDimensions;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Implementation of PlayerPanel.
 */
public final class PlayerPanelImpl implements PlayerPanel {

    private static final int FIRST_DELAY = 600;
    private static final double BUTTON_SCALE = 0.90;
    private static final int UPDATING_DELAY = 200;
    private static final Dimension BUTTON_DIMENSION = new Dimension(
            (int) (PanelDimensions.getPlayersPanel().getHeight() * BUTTON_SCALE),
            (int) (PanelDimensions.getPlayersPanel().getHeight() * BUTTON_SCALE));

    private final JPanel mainPanel;
    private final List<TroopButtonImpl> slots;
    private final PathIconsConfiguration pathIconsConfiguration;

    /**
     * Construct an instance of the PlayerPanel.
     *
     * @param nrOfSlots              sets how many slots should the player have.
     * @param pathIconsConfiguration where are defined the paths of the textures.
     */
    public PlayerPanelImpl(final Integer nrOfSlots, final PathIconsConfiguration pathIconsConfiguration) {
        this.pathIconsConfiguration = pathIconsConfiguration;
        this.mainPanel = new DrawPanelImpl(
                ImageIconsSupplier.loadImage(pathIconsConfiguration.getBackgroundFillPattern()),
                PanelDimensions.getPlayersPanel()
        );
        this.slots = new ArrayList<>();
        IntStream.range(0, nrOfSlots).forEach(
                position -> this.slots.add(
                        new TroopButtonImpl(
                                TroopType.getRandomTroop(),
                                BUTTON_DIMENSION,
                                position,
                                this.pathIconsConfiguration
                        )));

        this.slots.forEach(x -> mainPanel.add(x.getButton()));

    }


    @Override
    public void update(final Map<Integer, TroopType> troops) {
        int delay = FIRST_DELAY;

        for (int x = 0; x < this.slots.size(); x++) {
            if (troops.containsKey(x)) {
                this.slots.get(x).setEnabled(true);
                this.slots.get(x).setTroop(troops.get(x), delay);
                delay += UPDATING_DELAY;
            } else {
                this.slots.get(x).setEnabled(false);
            }

        }
    }

    @Override
    public void disableAllSlots() {
        this.slots.forEach(x -> x.setEnabled(false));
    }

    @Override
    public void enableAllSlots() {
        this.slots.forEach(x -> x.setEnabled(true));
    }

    @Override
    public void setActionListenersSlot(final ActionListener actionListener) {
        this.slots.forEach(x -> x.getButton().addActionListener(actionListener));
    }

    @SuppressFBWarnings(value = "EI",
            justification = "This panel has to be mutable from the class which uses it.")
    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

}
