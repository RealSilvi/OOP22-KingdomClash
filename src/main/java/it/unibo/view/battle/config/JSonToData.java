package it.unibo.view.battle.config;

import it.unibo.view.battle.Troop;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.Reader;
import java.util.Map;

public class JSonToData{

    private  JSONObject jsonObject;
    private  JSONObject tutorialPanelData;
    private final JSONParser jsonParser=new JSONParser();

    public JSonToData() {
        try(Reader reader = new FileReader("src/main/java/it/unibo/view/battle/config/package.json")){
            jsonObject = (JSONObject) jsonParser.parse(reader);
            tutorialPanelData = (JSONObject) jsonObject.get("TutorialPanel");
        }catch (Exception ignored){
            jsonObject=new JSONObject(Map.of("bella","zio"));
            tutorialPanelData= new JSONObject(Map.of("adv","dasd"));
        }
    }

    public String getTutorialPanelsFields(String keyPanel, String keyField){
        try{
            JSONObject topPanelData;
            topPanelData = (JSONObject) this.tutorialPanelData.get(keyPanel);
            return (String) topPanelData.get(keyField);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }


    public Map<Troop,Integer> getBotTroop(final int lv){
        return null;
    }

    public Map<Troop,Integer> getPlayerTroop(){
        return this.getBotTroop(1);
    }
}
