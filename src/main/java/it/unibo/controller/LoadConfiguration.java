package it.unibo.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unibo.kingdomclash.config.GameConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * This class is responsible for loading correctly the configuration of the game.
 * In case that the configuration doesn't exist, it will return a configuration
 * with the default values.
 */
public final class LoadConfiguration {

    private GameConfiguration configuration;

    /**
     * Intended behaviour of File.mkdirs().
     */
    public LoadConfiguration() {

        final Gson gson = new GsonBuilder().setPrettyPrinting().create();

        final String configDir = getAppData() + File.separator + "configuration.json";


        Logger logger = Logger.getLogger(this.getClass().getName());
        try (FileReader content = new FileReader(configDir)) {
            this.configuration = gson.fromJson(content, GameConfiguration.class);
        } catch (FileNotFoundException e) {
            this.configuration = new GameConfiguration();
            final File file = new File(configDir);
            if (file.getParentFile().mkdirs()) {
                try (FileWriter fileWriter = new FileWriter(file)) {
                    fileWriter.write(gson.toJson(this.configuration));
                } catch (IOException ex) {
                    logger.severe("Configuration saving FAILURE");
                    logger.severe(ex.getMessage());
                }
            }
        } catch (IOException e) {
            this.configuration = new GameConfiguration();
            logger.severe("Configuration loading FAILURE");
            logger.severe(e.getMessage());
        }

    }

    /**
     * Detects the host's OS and returns a path to appdata folder.
     *
     * @return a path to the appdata folder
     */
    public static String getAppData() {
        final String osHome = System.getProperty("os.name").toLowerCase();
        String appData;

        if (osHome.contains("win")) {
            appData = System.getenv("APPDATA");
        } else if (osHome.contains("mac")) {
            appData = System.getProperty("user.home")
                    + File.separator + "Library"
                    + File.separator + "Application Support";
        } else {
            appData = System.getProperty("user.home")
                    + File.separator + ".local"
                    + File.separator + "share";
        }

        return appData + File.separator + "KingdomClash";
    }

    /**
     * @return the configuration of the game.
     */
    public GameConfiguration getConfiguration() {
        return this.configuration;
    }
}
