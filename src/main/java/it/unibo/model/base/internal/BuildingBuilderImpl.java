package it.unibo.model.base.internal;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import it.unibo.model.base.basedata.Building;

/**
 * An implementation of the BuildingBuilderInterface with an internal cache
 * that stores similarly generated buildings.
 */
public final class BuildingBuilderImpl implements BuildingBuilder {
    private Map<BuildingTypes, Map<Integer, Building>> cache;

    /**
     * Constructs a BuildingBuilderImpl.
     */
    public BuildingBuilderImpl() {
        this.cache = new EnumMap<>(BuildingTypes.class);
        Arrays.stream(BuildingTypes.values())
            .forEach(
                buildingType -> this.cache.put(buildingType, new HashMap<>()));
    }

    @Override
    public Building makeStandardBuilding(final BuildingTypes type,
        final Point2D position, final int level) {
        if (cache.get(type).containsKey(level)) {
            return cache.get(type).get(level);
        }
        Building standardizedBuilding = new Building(type,
                level,
                type.getBuildTime(),
                type.getProductionTime(),
                false,
                0,
                0,
                position,
                type.getBaseProduction(level),
                type.getCost(level));
        cache.get(type).put(level, standardizedBuilding);
        return standardizedBuilding;
    }

    @Override
    public Building makeStandardBuilding(final BuildingTypes type, final int level) {
        return makeStandardBuilding(type, new Point2D.Float(0.0f, 0.0f), level);
    }
}
