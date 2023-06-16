package it.unibo.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unibo.model.data.GameConfiguration;
import it.unibo.model.data.GameData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.instrument.IllegalClassFormatException;
import java.util.Optional;
import java.util.logging.Logger;

public final class GameModel {

    private GameData gameData;

    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final File saveDataLocation;


    transient private GameConfiguration configuration;

    /**Intended behaviour of File.mkdirs();*/
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public GameModel() {
        this.saveDataLocation = new File(getAppData()+File.separator+"game.dat");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String configDir = getAppData() + File.separator + "configuration.json";


        try (FileReader content = new FileReader(configDir)) {
            this.configuration=gson.fromJson(content, GameConfiguration.class);
        } catch (FileNotFoundException e) {
            this.configuration=new GameConfiguration();
            File file = new File(configDir);
            file.getParentFile().mkdirs();

            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(gson.toJson(this.configuration));
            } catch (IOException ex) {
                logger.severe("Configuration saving FAILURE");
                ex.printStackTrace();
            }
        } catch (IOException e) {
            this.configuration=new GameConfiguration();
            logger.severe("Configuration loading FAILURE");
            e.printStackTrace();
        }

        this.newGame();
    }

    public void newGame(){
        this.gameData=new GameData(this.configuration);
    }

    public boolean load() {
        Optional<GameData> gameDataOptional = deserializeGameData(this.configuration);
        if (gameDataOptional.isPresent()) {
            this.gameData = new GameData(gameDataOptional.get(), this.configuration);
            return true;
        } else {
            this.gameData = new GameData(this.configuration);
            logger.warning("Generating new game data!");
            return false;
        }
    }

    public boolean serializeGameData() {
        try (ObjectOutputStream gameDataOutputStream = new ObjectOutputStream(new FileOutputStream(saveDataLocation))) {
            gameDataOutputStream.writeObject(gameData);
        } catch (IOException exc) {
            logger.severe("Could not write game data to folder!");
            return false;
        }
        return true;
    }

    /**
     * Detects the host's OS and returns a path to appdata folder.
     * @return a path to the appdata folder
     */
    private String getAppData() {
        String osHome = System.getProperty("os.name").toLowerCase();
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
     * Creates a GameData object by reading a compatible
     * serialized object on disk.
     * @param configuration the configuration to apply to gameData
     * @return              if readable, a GameData object
     */
    private Optional<GameData> deserializeGameData(GameConfiguration configuration) {
        if (this.saveDataLocation.exists()) {
            try (ObjectInputStream gameDataInputStream =
                         new ObjectInputStream(
                                 new FileInputStream(this.saveDataLocation))) {

                Object data = gameDataInputStream.readObject();
                if (data instanceof GameData retrievedGameData) {
                    return Optional.of(new GameData(retrievedGameData, configuration));
                } else {
                    throw new IllegalClassFormatException();
                }
            } catch (FileNotFoundException | ClassNotFoundException exc) {
                logger.warning("Cannot read game data!");
                exc.printStackTrace();
            } catch (IOException exc) {
                logger.severe("IOException occourred while loading GameData!");
                exc.printStackTrace();
            } catch (IllegalArgumentException | SecurityException
                     | IllegalClassFormatException exc) {
                logger.severe("Data class is incompatible or a different version!");
                exc.printStackTrace();
            }
        }
        return Optional.empty();
    }

    public GameData getGameData() {
        return gameData;
    }

    public int getCurrentLevel(){
        return this.gameData.getCurrentLevel();
    }

    public void incrementCurrenetLevel(){
        this.gameData.incrementLevel();
    }

    public String getPlayerName(){
        return this.gameData.getPlayerName();
    }

    public void setPlayerName(String name){
        this.gameData.setPlayerName(name);
    }
}
