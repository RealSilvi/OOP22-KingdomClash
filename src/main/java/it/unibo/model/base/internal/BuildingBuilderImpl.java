package it.unibo.model.base.internal;

import java.awt.geom.Point2D;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.model.base.basedata.Building;
import it.unibo.model.data.Resource;

public class BuildingBuilderImpl implements BuildingBuilder {
    @Override
    public Building makeStandardBuilding(BuildingTypes type, Point2D position, int level) {
        return new Building(Optional.empty(),
        makeTextureList(type),
        type,
        1,
        type.getBuildTime(),
        false,
        0,
        position,
        type.getBaseProduction().stream().map(x->new Resource(x.getResource(), x.getAmount()*level)).collect(Collectors.toList()));
    }

    @Override
    public Building upgradeBuildingByLevel(Building building, int level) {
        return makeStandardBuilding(building.getType(), building.getStructurePos(), level);
    }

    private List<URL> makeTextureList(BuildingTypes type) {
        List<URL> pathList = new ArrayList<>();
        for (int index = 1; index < Building.MAXLEVEL; index++) {
            pathList.add(getClass()
                .getClassLoader()
                .getResource(type.name().toLowerCase()+index));
        }
        return pathList;
    }
}