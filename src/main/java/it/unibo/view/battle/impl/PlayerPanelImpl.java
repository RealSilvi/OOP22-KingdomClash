package it.unibo.view.battle.impl;

import it.unibo.view.battle.api.PlayerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class PlayerPanelImpl extends JPanel implements PlayerPanel {

    private final static int NUMBER_OF_SLOTS = 5;
    private final static double BUTTON_SCALE = 0.12;

    private final Dimension preferredSize;
    private final Image backgroundImage;

    private List<TroopButton> slots;


    public PlayerPanelImpl(final Dimension preferredSize) {
        this.preferredSize=preferredSize;

        //questo path poi va preso dal model?
        this.backgroundImage = new ImageIcon(
                "src/main/resources/it/unibo/icons/battle/battleUpDown.png"
        ).getImage();
        this.setOpaque(false);
        this.setPreferredSize(preferredSize);

        this.restart();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, this);
    }

    @Override
    public void restart() {
        this.slots = new ArrayList<>();

        for(int i=0; i<NUMBER_OF_SLOTS; i++){
            this.slots.add(new TroopButton());
        }

        this.slots.forEach(this::add);

        this.setButtonsSize();

    }

    private void setButtonsSize(){
        this.slots
                .forEach(
                        x -> x.setPreferredSize(new Dimension(
                                (int)(this.preferredSize.getWidth() * BUTTON_SCALE),
                                (int)(this.preferredSize.getWidth() * BUTTON_SCALE))));
    }

    @Override
    public void update(Observable o, Object arg) {
        setRandomSlots();
        this.disableSelectedSlots();
    }

    private void setRandomSlots() {
        this.slots
                .stream()
                .filter(TroopButton::getSelectable)
                .forEach(TroopButton::changeTroop);
    }

    private void disableSelectedSlots(){
        this.slots.forEach(x -> x.setEnabled(x.getSelectable()));
    }

    @Override
    public void disableAllSlots(){
        this.slots.forEach(x -> x.setEnabled(false));
    }

    @Override
    public void setActionListenersSlot(ActionListener actionListener){
        this.slots.forEach(x -> x.addActionListener(actionListener));
    }
}
