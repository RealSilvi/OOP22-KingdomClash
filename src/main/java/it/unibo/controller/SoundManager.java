package it.unibo.controller;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

/**
 * The class that starts and manage sounds effect and main theme.
 */
public class SoundManager {

    private static final String SOUND_FILE_EXTENSION = ".wav";
    private static final String SOUND_THEMES_DIRECTORY = System.getProperty("user.dir")
            + File.separator + "src"
            + File.separator + "main"
            + File.separator + "resources"
            + File.separator + "it"
            + File.separator + "unibo"
            + File.separator + "soundThemes"
            + File.separator;

    private static final String MENU_THEME_PATH = SOUND_THEMES_DIRECTORY + "menuTheme" + SOUND_FILE_EXTENSION;
    private static final String CITY_THEME_PATH = SOUND_THEMES_DIRECTORY + "cityTheme" + SOUND_FILE_EXTENSION;
    private static final String BATTLE_THEME_PATH = SOUND_THEMES_DIRECTORY + "battleTheme" + SOUND_FILE_EXTENSION;
    private static final String MAP_THEME_PATH = SOUND_THEMES_DIRECTORY + "mapTheme" + SOUND_FILE_EXTENSION;

    private enum Themes {
        MENU,
        BATTLE,
        CITY,
        MAP;
    }

    private final Map<Themes, Optional<Clip>> themesClips;
    private Optional<Clip> currentTheme;
    private boolean enable;

    public SoundManager() {
        this.currentTheme= Optional.empty();
        this.enable=true;
        this.themesClips = Map.of(
                Themes.MENU, this.createClip(new File(MENU_THEME_PATH)),
                Themes.BATTLE, this.createClip(new File(BATTLE_THEME_PATH)),
                Themes.CITY, this.createClip(new File(CITY_THEME_PATH)),
                Themes.MAP, this.createClip(new File(MAP_THEME_PATH))
        );
    }

    private void setTheme(final Themes theme) {

        this.currentTheme.ifPresent(Line::close);
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

    public void setEnable(boolean enable) {
        this.enable=enable;
        if(enable){
            this.currentTheme.ifPresent(theme -> {
                theme.start();
                theme.loop(Clip.LOOP_CONTINUOUSLY);
            });
        }else{
            this.currentTheme.ifPresent(DataLine::stop);

        }
    }

    private Optional<Clip> createClip(final File themeFile) {
        try {
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(themeFile);
            final Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            return Optional.of(clip);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            return Optional.empty();
        }
    }



//    public static void zombiesAreComing() {
//        playSoundEffect(ResourceFinder.getSoundURL(ZOMBIES_SIREN_FILE_NAME));
//    }


//    private static void playLoopSound(final URL soundUrl) {
//        final Optional<Clip> clip = createClip(soundUrl, MAIN_THEME_VOLUME);
//        clip.ifPresent(c -> {
//            c.start();
//            clip.get().loop(Clip.LOOP_CONTINUOUSLY);
//        });
//    }

//    private static void playSoundEffect(final URL soundUrl) {
//        final Optional<Clip> clip = createClip(soundUrl, SOUND_EFFECT_VOLUME);
//        clip.ifPresent(Clip::start);
//    }

//    private Optional<Clip> createClip(final URL soundUrl, final float volume) {
//        try {
//            final AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl);
//            final Clip clip = AudioSystem.getClip();
//            clip.addLineListener(event -> {
//                if (LineEvent.Type.STOP.equals(event.getType())) {
//                    clip.close();
//                }
//            });
//            clip.open(ais);
//            final FloatControl fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//            fc.setValue(volume);
//
//            return Optional.of(clip);
//        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
//            return Optional.empty();
//        }
//    }

}
