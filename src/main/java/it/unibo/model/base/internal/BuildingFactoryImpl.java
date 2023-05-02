package it.unibo.model.base.internal;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import it.unibo.model.base.basedata.Building;

public class BuildingFactoryImpl implements BuildingFactory {
    @Override
    public Building makeBuilding(BuildingTypes type, int level) {
        return null;
    }

    @Override
    public Building makeBuilding(BuildingTypes type) {
        return null;
    }

    private List<URL> getTextureList(BuildingTypes type) {
        List<URL> pathList = new ArrayList<>();
        for (int index = 1; index < Building.MAXLEVEL; index++) {
            pathList.add(getClass()
                .getClassLoader()
                .getResource(type.name().toLowerCase()+index));
        }
        return pathList;
    }
}