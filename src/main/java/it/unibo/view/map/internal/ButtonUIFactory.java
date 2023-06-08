package it.unibo.view.map.internal;

import javax.swing.plaf.ButtonUI;

/**
 * Interface for a factory that creates different types of ButtonUI
 * to simplify the creation of customized buttons.
 */
public interface ButtonUIFactory {
    /**
     * Creates a ButtonUI that WILL NOT grey out buttons when disabled.
     * @return a modified ButtonUI
     */
    ButtonUI buttonUINoGrayOut();
}
