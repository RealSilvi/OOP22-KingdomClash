
package it.unibo.view.city.panels.impl;


import java.awt.Dimension;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;

import it.unibo.model.base.internal.BuildingBuilder;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.model.data.GameData;


public class CityConfiguration {
   
    
   
    private Map<BuildingBuilder.BuildingTypes,Map<Integer,String>> textureMap;
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
    
    
}

