package it.unibo.controller;


import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * The class that starts and manage sounds effect and main theme.
 */
public class SoundManager {

    private static final String SOUND_FILE_EXTENSION = ".wav";
    private static final String SOUND_THEMES_DIRECTORY = "/it/unibo/soundThemes/";


    private static final String MENU_THEME_PATH = SOUND_THEMES_DIRECTORY + "menuTheme" + SOUND_FILE_EXTENSION;
    private static final String CITY_THEME_PATH = SOUND_THEMES_DIRECTORY + "cityTheme" + SOUND_FILE_EXTENSION;
    private static final String BATTLE_THEME_PATH = SOUND_THEMES_DIRECTORY + "battleTheme" + SOUND_FILE_EXTENSION;
    private static final String MAP_THEME_PATH = SOUND_THEMES_DIRECTORY + "mapTheme" + SOUND_FILE_EXTENSION;

    private enum Themes {
        MENU,
        BATTLE,
        CITY,
        MAP
    }

    private final Map<Themes, Optional<Clip>> themesClips;
    private Optional<Clip> currentTheme;
    private boolean enable;

    public SoundManager() {
        this.currentTheme= Optional.empty();
        this.enable=true;
        this.themesClips = Map.of(
                Themes.MENU, this.createClip(MENU_THEME_PATH),
                Themes.BATTLE, this.createClip(BATTLE_THEME_PATH),
                Themes.CITY, this.createClip(CITY_THEME_PATH),
                Themes.MAP, this.createClip(MAP_THEME_PATH)
        );
    }

    private void setTheme(final Themes theme) {

        this.currentTheme.ifPresent(DataLine::stop);
        this.currentTheme=Optional.empty();

        this.themesClips.get(theme).ifPresent( t ->{
            this.currentTheme=Optional.of(t);
            if(enable){
                this.currentTheme.get().stop();
                this.currentTheme.get().loop(Clip.LOOP_CONTINUOUSLY);
            }
        });
    }


    public void startCityTheme() {
        this.setTheme(Themes.CITY);
    }

    public  void startMapTheme() {
        this.setTheme(Themes.MAP);
    }

    public void startBattleTheme() {
        this.setTheme(Themes.BATTLE);
    }

    public void startMenuTheme() {
        this.setTheme(Themes.MENU);
    }

    public void changeMute() {
        this.enable=!this.enable;
        if(enable){
            this.currentTheme.ifPresent(theme -> {
                theme.start();
                theme.loop(Clip.LOOP_CONTINUOUSLY);
            });
        }else{
            this.currentTheme.ifPresent(DataLine::stop);

        }
    }

    private Optional<Clip> createClip(final String themeFile) {
        try {
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(SoundManager.class.getResourceAsStream(themeFile)));
            final Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            return Optional.of(clip);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            return Optional.empty();
        }
    }

}
