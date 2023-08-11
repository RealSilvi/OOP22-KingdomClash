package it.unibo.kingdomclash;

import it.unibo.controller.GameController;

/**
 * Entry point of the application.
 */
public final class KingdomClash {

    /**
     * Private constructor
     */
    private KingdomClash() {
    }

    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(final String[] args) {
        new GameController();
    }
}
