package it.unibo.view.battle.panels.entities.impl;

import it.unibo.model.data.TroopType;
import it.unibo.kingdomclash.config.PathIconsConfiguration;
import it.unibo.view.battle.panels.entities.api.TroopButton;
import it.unibo.view.utilities.BattlePanelStyle;
import it.unibo.view.utilities.ImageIconsSupplier;


import java.awt.Dimension;
import java.awt.Color;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.Icon;

public class TroopButtonImpl implements TroopButton {

    private TroopType troop;
    private final Dimension size;
    private final PositionJbutton<Integer> button;
    private final PathIconsConfiguration pathIconsConfiguration;

    /**
     * Constructs an instance of a TroopButton.
     *
     * @param troop                  the troop to set as an icon.
     * @param size                   the size of the button.
     * @param position               the logic position of the button.
     * @param pathIconsConfiguration where are defined the paths of the textures.
     */
    public TroopButtonImpl(final TroopType troop, final Dimension size, final int position, final PathIconsConfiguration pathIconsConfiguration) {
        this.pathIconsConfiguration = pathIconsConfiguration;
        this.button = new PositionJbutton<>(position);
        this.troop = troop;
        this.size = size;

        this.button.setPreferredSize(size);

        this.button.setIcon(ImageIconsSupplier.getScaledImageIcon(pathIconsConfiguration.getTroop(this.troop), this.size));
        this.button.setBackground(Color.BLACK);
        this.button.setOpaque(true);
    }

    @Override
    public TroopType getTroop() {
        return this.troop;
    }

    @Override
    public void setTroop(final TroopType troop, final int delay) {
        this.troop = troop;
        final Timer timer = new Timer(delay, e -> button.setIcon(
                ImageIconsSupplier.getScaledImageIcon(this.pathIconsConfiguration.getTroop(troop), size)));
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public void setEnabled(final boolean b) {
        this.button.setEnabled(b);
    }

    @Override
    public JButton getButton() {
        return this.button;
    }

    /**
     * This class represents a JButton which can save a certain logic data.
     * In this way if the action listener of this JButton is implemented
     * outside this class for any reason, it can still access the logic data.<br>
     * ps. This class is not implemented to handle serialization.
     */
    @SuppressWarnings(value = "serial")
    public static class PositionJbutton<X> extends JButton {

        private static final int THICKNESS = 4;

        private final X data;
        private boolean selectedBorder;

        public PositionJbutton(final X data) {
            this.data = data;
            this.selectedBorder = false;
            this.setEnabled(true);
        }

        public X getData() {
            return data;
        }

        /**
         * Update the border of the button by its status.
         */
        public void updateBorder() {
            this.selectedBorder = !this.selectedBorder;
            if (!selectedBorder) {
                this.setBorder(BorderFactory.createLineBorder(BattlePanelStyle.PRIMARY_COLOR, THICKNESS, true));
            } else {
                this.setBorder(BorderFactory.createLineBorder(BattlePanelStyle.SECONDARY_COLOR, THICKNESS, true));
            }
        }

        /**
         * The icon is also used for the disabledIcon.
         *
         * @param defaultIcon the icon used as the default image
         */
        @Override
        public void setIcon(final Icon defaultIcon) {
            super.setIcon(defaultIcon);
            this.setDisabledIcon(defaultIcon);
        }

        /**
         * Set a costume border for the button to represent graphically its status.
         *
         * @param enabled true to enable the button, otherwise false
         */
        @Override
        public void setEnabled(final boolean enabled) {
            super.setEnabled(enabled);
            this.selectedBorder = false;
            if (enabled) {
                this.setBorder(BorderFactory.createLineBorder(BattlePanelStyle.PRIMARY_COLOR, THICKNESS, true));
            } else {
                this.setBorder(BorderFactory.createLineBorder(BattlePanelStyle.DEFAULT_COLOR, THICKNESS, true));
            }
        }
    }
}

