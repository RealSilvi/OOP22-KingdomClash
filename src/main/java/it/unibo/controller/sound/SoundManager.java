package it.unibo.controller.sound;

/**
 * Defines which actions are allowed at the SoundManager<br>
 * It is responsible for the music management in the application.
 */
public interface SoundManager {

    /**
     * Starts the city theme
     */
    void startCityTheme();

    /**
     * Starts the map theme
     */
    void startMapTheme();

    /**
     * Starts the battle theme
     */
    void startBattleTheme();

    /**
     * Starts the menu theme
     */
    void startMenuTheme();

    /**
     * Mutes or starts all the themes.
     */
    void changeMute();

}
