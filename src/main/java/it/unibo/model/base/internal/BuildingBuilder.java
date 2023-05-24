package it.unibo.model.base.internal;

import java.awt.geom.Point2D;
import java.util.Set;

import it.unibo.model.base.basedata.Building;
import it.unibo.model.data.Resource;
import it.unibo.model.data.Resource.ResourceType;
//TODO FIX PRODUCTION TIME
/**
 * A very simple builder to easily create standardized buildings
 */
public interface BuildingBuilder {
    //TODO: Fix building costs
    public enum BuildingTypes {
        HALL(30_000,
            5_000,
            Set.of(new Resource(ResourceType.WHEAT, 10),
                new Resource(ResourceType.WOOD, 10)),
            Set.of(new Resource(ResourceType.WOOD, 50),
                new Resource(ResourceType.WHEAT, 30))),
        LUMBERJACK(30_000,
            5_000,
            Set.of(new Resource(ResourceType.WOOD, 30)),
            Set.of(new Resource(ResourceType.WOOD, 50),
                new Resource(ResourceType.WHEAT, 30))),
        FARM(30_000,
            5_000,
            Set.of(new Resource(ResourceType.WHEAT, 30)),
            Set.of(new Resource(ResourceType.WOOD, 50),
                new Resource(ResourceType.WHEAT, 30)));

        private long defaultBuildTime;
        private long defaultProductionTime;
        private Set<Resource> baseProduction;
        private Set<Resource> cost;

        BuildingTypes(long defaultBuildTime, long defaultProductionTime, Set<Resource> baseProduction, Set<Resource> cost) {
            this.defaultBuildTime = defaultBuildTime;
            this.defaultProductionTime = defaultProductionTime;
            this.baseProduction = baseProduction;
            this.cost = cost;
        }

        public Set<Resource> getBaseProduction() {
            return baseProduction;
        }

        public long getBuildTime() {
            return defaultBuildTime;
        }

        public long getProductionTime() {
            return defaultProductionTime;
        }

        public Set<Resource> getCost() {
            return cost;
        }
    }
    /**
     * Works like {@link BuildingBuilder#makeBuilding(BuildingTypes type, int level)}
     * with the main difference that the building's level defaults to 0
     * @param type the type of the building
     */
    public Building makeStandardBuilding(BuildingTypes type, Point2D position, int level);
    public Building makeStandardBuilding(BuildingTypes type, int level);
    public Building upgradeBuildingByLevel(Building building, int level);
}