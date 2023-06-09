
package it.unibo.view.city.panels.impl;


import java.util.EnumMap;
import java.util.Map;
import it.unibo.model.base.internal.BuildingBuilder;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;


public class SetTexture {
   
    
   
    private Map<BuildingBuilder.BuildingTypes,Map<Integer,String>> textureMap;

    public SetTexture(){
        textureMap=new EnumMap<>(BuildingBuilder.BuildingTypes.class);
        textureMap.put(BuildingTypes.FARM,Map.of(0,"",1,"",2,""));
        textureMap.put(BuildingTypes.HALL, Map.of(0, "", 1, "", 2, ""));
        textureMap.put(BuildingTypes.LUMBERJACK, Map.of(0, "", 1, "", 2, ""));

    }
    
}

