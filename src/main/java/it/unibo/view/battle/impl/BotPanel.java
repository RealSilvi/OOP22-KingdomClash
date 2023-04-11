package it.unibo.view.battle.impl;

import it.unibo.view.battle.api.BattleGuiPanels;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class BotPanel extends JPanel implements BattleGuiPanels {

    private final static double PANEL_VERTICAL_SCALE = 0.2;
    private final static double BUTTON_SCALE = 0.12;

    private final  int PREFERRED_WIDTH;
    private final  int PREFERRED_HEIGHT;
    private final Image backgroundImage;

    private Map<TroopButton,Boolean> troops;

    private Set<TroopButton> selectedTroops;

    public BotPanel(final Dimension screenSize) {

        this.PREFERRED_WIDTH= (int) screenSize.getWidth();
        this.PREFERRED_HEIGHT = (int) (screenSize.getHeight() * PANEL_VERTICAL_SCALE);
        this.restart();

        //questo path poi va preso dal model
        this.backgroundImage = new ImageIcon(
                "src/main/resources/it/unibo/icons/battle/battleUpDown.png"
                ).getImage();

        this.setOpaque(false);

        this.setPreferredSize(new Dimension(this.getPreferredWidth(), this.getPreferredHeight()));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, this);
    }

    @Override
    public void restart() {

        this.troops = new HashMap<>();
        this.selectedTroops = new HashSet<>();

        for(int i=0; i<5; i++){
            this.troops.put(new TroopButton(), false);
        }

        this.troops
                .keySet()
                .forEach(x -> {
                    this.add(x);
                    x.setPreferredSize(
                        new Dimension((int)(this.PREFERRED_WIDTH * BUTTON_SCALE),
                            (int)(this.PREFERRED_WIDTH * BUTTON_SCALE)));
                    x.addActionListener(e -> {
                        selectedTroops.add(x);
                        //qua dovrai notidicare il controller
                     });
                });

    }

    @Override
    public void update(Observable o, Object arg) {
        this.troops
                .entrySet()
                .stream()
                .filter(x -> !x.getValue())
                .map(Map.Entry::getKey)
                .forEach(TroopButton::changeTroop);
    }

    public int getPreferredWidth() {
        return this.PREFERRED_WIDTH;
    }

    public int getPreferredHeight() {
        return this.PREFERRED_HEIGHT;
    }

    public Set<TroopButton> getSelectedTroops() {
        return selectedTroops;
    }
}
