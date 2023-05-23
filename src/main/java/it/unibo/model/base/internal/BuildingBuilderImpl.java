package it.unibo.model.base.internal;

import java.awt.geom.Point2D;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import it.unibo.model.base.basedata.Building;
import it.unibo.model.data.Resource;

public class BuildingBuilderImpl implements BuildingBuilder {
    private Map<BuildingTypes, Map<Integer, Building>> cache;

    public BuildingBuilderImpl() {
        this.cache = new EnumMap<>(BuildingTypes.class);
        this.cache.forEach((buildingType, levelBuildingMap)->new HashMap<>());
    }

    @Override
    public Building makeStandardBuilding(BuildingTypes type, Point2D position, int level) {
        if (cache.get(type).containsKey(level)) {
            return cache.get(type).get(level);
        }
        Building standardizedBuilding = new Building(type,
        level,
        type.getBuildTime(),
        false,
        0,
        0,
        position,
        type.getBaseProduction().stream().map(x->new Resource(x.getResource(), x.getAmount()*level)).collect(Collectors.toSet()));
        cache.get(type).put(level, standardizedBuilding);
        return standardizedBuilding;
    }

    @Override
    public Building upgradeBuildingByLevel(Building building, int level) {
        return makeStandardBuilding(building.getType(), building.getStructurePos(), level);
    }
}