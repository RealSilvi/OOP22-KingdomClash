
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
        textureMap.put(BuildingTypes.FARM,Map.of(0,"",1,"",2,""));
        textureMap.put(BuildingTypes.HALL, Map.of(0, "", 1, "", 2, ""));
        textureMap.put(BuildingTypes.LUMBERJACK, Map.of(0, "", 1, "", 2, ""));

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

