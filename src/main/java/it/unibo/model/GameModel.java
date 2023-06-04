package it.unibo.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unibo.model.data.GameConfiguration;
import it.unibo.model.data.GameData;

import java.io.*;

public class GameModel {
    private GameData gameData;

    //Intended behaviour of File.mkdirs();
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public GameModel() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String os = System.getProperty("os.name").toLowerCase();
        String appDataDir;

        if (os.contains("win")) {
            appDataDir = System.getenv("APPDATA");
        } else if (os.contains("mac")) {
            appDataDir = System.getProperty("user.home")
                    + File.separator + "Library"
                    + File.separator + "Application Support";
        } else {
            appDataDir = System.getProperty("user.home")
                    + File.separator + ".local"
                    + File.separator + "share";
        }
        appDataDir = appDataDir + File.separator + "KingdomClash" + File.separator + "configuration.json";


        try (FileReader content = new FileReader(appDataDir)) {
            gameData = new GameData(gson.fromJson(content, GameConfiguration.class));
        } catch (FileNotFoundException e) {
            gameData = new GameData();
            File file = new File(appDataDir);
            file.getParentFile().mkdirs();

            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(gson.toJson(gameData.getGameConfiguration()));
            } catch (IOException ex) {
                throw new RuntimeException();
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }


    public GameData getGameData() {
        return gameData;
    }

}