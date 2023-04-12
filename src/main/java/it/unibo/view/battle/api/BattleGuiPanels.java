package it.unibo.view.battle.api;

import java.awt.*;
import java.util.Observer;

public interface BattleGuiPanels extends Observer {

    /**
     * @param g the <code>Graphics</code> object to protect
     *      *          The method is overwritten to start the panel with a background image
     */
     void paintComponent(Graphics g);

    /**
     * This method initialises the panel
     */
    void restart();

}
