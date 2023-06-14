
package it.unibo.view.city.panels.impl;


import java.awt.Image;
import java.io.IOException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;


import it.unibo.model.base.internal.BuildingBuilder;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;




public class CityConfiguration {
   
    
   
    private Map<BuildingBuilder.BuildingTypes,Map<Integer,String>> textureMap;
    private Map<BuildingBuilder.BuildingTypes,Map<Integer,Image>> loadCityImage;
    private int width;
    private int height;

    public CityConfiguration(){

        this.width=10;
        this.height=10;

        textureMap=new EnumMap<>(BuildingBuilder.BuildingTypes.class);
        textureMap.put(BuildingTypes.FARM,Map.of(0,"KingdomClash\\src\\main\\resources\\it\\unibo\\textures\\city\\wooden_buildings_01_1.png",
            1,"\\KingdomClash\\src\\main\\resources\\it\\unibo\\textures\\city\\wooden_buildings_01_2.png",
                2,"\\KingdomClash\\src\\main\\resources\\it\\unibo\\textures\\city\\wooden_buildings_01_4.png"));
        textureMap.put(BuildingTypes.HALL, Map.of(0, "C:\\Users\\abdou\\Downloads\\texture\\yusuf-artun-map-buildings-1_3-removebg-preview.png",
             1,"C:\\Users\\abdou\\Downloads\\texture\\yusuf-artun-map-buildings-1_2-removebg-preview.png",
                 2, "C:\\Users\\abdou\\Downloads\\texture\\yusuf-artun-map-buildings-1_1-removebg-preview.png"));
        textureMap.put(BuildingTypes.LUMBERJACK, Map.of(0,"KingdomClash\\src\\main\\resources\\it\\unibo\\textures\\city\\wooden_buildings_01_13.png",
             1,"KingdomClash\\src\\main\\resources\\it\\unibo\\textures\\city\\wooden_buildings_01_14.png",
              2,"KingdomClash\\src\\main\\resources\\it\\unibo\\textures\\city\\wooden_buildings_01_15.png"));

       loadCityImage= new EnumMap<>(BuildingBuilder.BuildingTypes.class);

    }

    public Map<BuildingBuilder.BuildingTypes, Map<Integer, String>> getTextureMap() {
        return textureMap;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    private void loadAssets() {
        try
        { 
            for (BuildingTypes type : BuildingTypes.values()) {
            Map<Integer,Image> tempImageLevel = new HashMap<>();
            for(int i=0; i<= 3; i++){
                
                tempImageLevel.put(i, ImageIO.read(this.getClass().getResource(this.getTextureMap().get(type).get(i))));
            }
            loadCityImage.put(type, tempImageLevel);
        }} catch(IOException e){

        }
               
    }
    
}

