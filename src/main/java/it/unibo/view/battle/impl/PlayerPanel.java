package it.unibo.view.battle.impl;

import it.unibo.view.battle.api.BattleGuiPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class PlayerPanel extends JPanel implements BattleGuiPanels {

    private final static int NUMBER_OF_SLOTS = 5;
    private final static double PANEL_VERTICAL_SCALE = 0.2;
    private final static double BUTTON_SCALE = 0.12;

    private final  int PREFERRED_WIDTH;
    private final  int PREFERRED_HEIGHT;
    private final Image backgroundImage;


    private List<TroopButton> slots;

    public PlayerPanel(final Dimension screenSize) {
        this.PREFERRED_WIDTH= (int) screenSize.getWidth();
        this.PREFERRED_HEIGHT = (int) (screenSize.getHeight() * PANEL_VERTICAL_SCALE);
        //questo path poi va preso dal model?
        this.backgroundImage = new ImageIcon(
                "src/main/resources/it/unibo/icons/battle/battleUpDown.png"
        ).getImage();
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(this.getPreferredWidth(), this.getPreferredHeight()));

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
                                (int)(this.PREFERRED_WIDTH * BUTTON_SCALE),
                                (int)(this.PREFERRED_WIDTH * BUTTON_SCALE))));
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

    public int getPreferredWidth() {
        return this.PREFERRED_WIDTH;
    }

    public int getPreferredHeight() {
        return this.PREFERRED_HEIGHT;
    }

    public void disableAllSlots(){
        this.slots.forEach(x -> x.setEnabled(false));
    }

    public void setActionListenersSlot(ActionListener actionListener){
        this.slots.forEach(x -> x.addActionListener(actionListener));
    }
}
