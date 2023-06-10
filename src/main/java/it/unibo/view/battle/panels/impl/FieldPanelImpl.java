package it.unibo.view.battle.panels.impl;

import it.unibo.model.data.TroopType;
import it.unibo.view.battle.panels.api.FieldPanel;
import it.unibo.view.battle.panels.entities.DrawPanel;
import it.unibo.view.battle.panels.entities.impl.TroopLabelImpl;
import it.unibo.view.battle.panels.utilities.ImageIconsSupplier;
import it.unibo.view.battle.panels.utilities.PanelDimensions;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.IntStream;

public class FieldPanelImpl implements FieldPanel {

    private static final double LABEL_SCALE = 0.1;
    private static final Dimension LABEL_DIMENSION = new Dimension(
            (int) (PanelDimensions.getFieldPanel().getHeight() * LABEL_SCALE),
            (int) (PanelDimensions.getFieldPanel().getHeight() * LABEL_SCALE));
    private static final int ROWS = 2;

    private final JPanel mainPanel;

    private final List<TroopLabelImpl> field;

    /**
     * @param nrOfFieldSpot ho many slots the player has in the PlayerPanel
     */
    public FieldPanelImpl(final int nrOfFieldSpot) {
        this.field=new ArrayList<>();
        this.mainPanel = new DrawPanel(ImageIconsSupplier.BACKGROUND_FILL_PATTERN, PanelDimensions.getFieldPanel());

        this.mainPanel.setLayout(new GridLayout(ROWS, nrOfFieldSpot / ROWS));
        IntStream.range(0, nrOfFieldSpot*2).forEach(x -> this.field.add(new TroopLabelImpl(LABEL_DIMENSION)));


        this.restart();
        this.field.forEach(this.mainPanel::add);
    }

    @Override
    public void restart() {
        this.field.forEach(TroopLabelImpl::setEmpty);
    }

    @Override
    public void redraw( final List<Optional<TroopType>> fieldTroops) {
        this.restart();

        final int delay=1500;
        IntStream.range(0, fieldTroops.size()).forEach(x -> {
            if (fieldTroops.get(x).isEmpty()) {
                this.field.get(x).setEmpty();
            } else {
                this.field.get(x).setTroop(fieldTroops.get(x).get(),0);
            }
            if (fieldTroops.get(x).isEmpty()) {
                this.field.get(x).setEmpty();
            } else {
                this.field.get(x).setTroop(fieldTroops.get(x).get(),0);
            }
        });

    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

}
