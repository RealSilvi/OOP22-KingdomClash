package it.unibo.model.base.internal;

import java.awt.geom.Point2D;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.model.base.basedata.Building;
import it.unibo.model.data.Resource;

public class BuildingBuilderImpl implements BuildingBuilder {
    @Override
    public Building makeStandardBuilding(BuildingTypes type, Point2D position, int level) {
        return new Building(type,
        1,
        type.getBuildTime(),
        false,
        0,
        0,
        position,
        type.getBaseProduction().stream().map(x->new Resource(x.getResource(), x.getAmount()*level)).collect(Collectors.toSet()));
    }

    @Override
    public Building upgradeBuildingByLevel(Building building, int level) {
        return makeStandardBuilding(building.getType(), building.getStructurePos(), level);
    }
}