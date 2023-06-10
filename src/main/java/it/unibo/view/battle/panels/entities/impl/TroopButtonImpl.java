package it.unibo.view.battle.panels.entities.impl;

import it.unibo.model.data.TroopType;
import it.unibo.view.battle.panels.entities.api.TroopButton;
import it.unibo.view.battle.panels.utilities.BattlePanelStyle;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;

import javax.swing.*;
import java.awt.*;

public class TroopButtonImpl implements TroopButton {

    private TroopType troop;
    private final Dimension size;
    private final PositionJbutton button;

    /**
     * @param troop  the troop to set on this button
     * @param size   the dimension of this button
     */
    public TroopButtonImpl(final TroopType troop, final Dimension size, final int position) {
        this.button = new PositionJbutton(position);
        this.troop = troop;
        this.size = size;


        this.button.setPreferredSize(size);

        this.button.setIcon(ImageIconsSupplier.getImageIconFromTroop(this.troop, this.size));
        this.button.setDisabledIcon(ImageIconsSupplier.getImageIconFromTroop(this.troop, this.size));
        this.button.setBackground(Color.BLACK);
        this.button.setOpaque(true);
    }

    @Override
    public TroopType getTroop() {
        return this.troop;
    }

    public void setTroop(final TroopType troop, final int delay) {
        this.troop = troop;
        Timer timer = new Timer(delay, e -> {
            // the actionPerformed call-back code
            button.setIcon(ImageIconsSupplier.getImageIconFromTroop(troop, size));
            button.setDisabledIcon(ImageIconsSupplier.getImageIconFromTroop(troop, size));
        });
        timer.setRepeats(false);
        timer.start();
    }


    /**
     * Overwritten the method to change the border of the button
     * based on isEnable()
     *
     * @param b true to enable the button, otherwise false
     */
    @Override
    public void setEnabled(final boolean b) {
        this.button.setEnabled(b);
    }

    public JButton getButton() {
        return this.button;
    }

    public static class PositionJbutton extends JButton {

        private final int position;
        private boolean selectedBorder;

        public PositionJbutton(int position) {
            this.position = position;
            this.selectedBorder = false;
            this.setEnabled(true);
        }

        public int getPosition() {
            return position;
        }

        public void updateBorder() {
            this.selectedBorder = !this.selectedBorder;
            if (!selectedBorder) {
                this.setBorder(BorderFactory.createLineBorder(BattlePanelStyle.PRIMARY_COLOR, 4, true));
            } else {
                this.setBorder(BorderFactory.createLineBorder(BattlePanelStyle.SECONDARY_COLOR, 4, true));
            }
        }

        @Override
        public void setEnabled(boolean b) {
            super.setEnabled(b);
            this.selectedBorder = false;
            if (b) {
                this.setBorder(BorderFactory.createLineBorder(BattlePanelStyle.PRIMARY_COLOR, 4, true));
            } else {
                this.setBorder(BorderFactory.createLineBorder(BattlePanelStyle.DEFAULT_COLOR, 4, true));
            }
        }
    }
}

