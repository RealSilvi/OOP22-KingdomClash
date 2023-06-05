package it.unibo.model.base.internal;

import java.awt.geom.Point2D;
import java.util.Collections;
import java.util.Set;

import it.unibo.model.base.basedata.Building;
import it.unibo.model.data.Resource;
import it.unibo.model.data.Resource.ResourceType;

/**
 * A very simple builder to easily create standardized buildings.
 */
public interface BuildingBuilder {
    //TODO: Tweak values for balance
    /**
     * Defines what type of building should be built.
     */
    enum BuildingTypes {
        /**
         * A hall building type that can produce a small amount of all
         * type of resources.
         * @see {@link it.unibo.model.data.Resource.ResourceType}
         */
        HALL(30_000,
                5_000,
                Set.of(new Resource(ResourceType.WHEAT, 10),
                        new Resource(ResourceType.WOOD, 10)),
                Set.of(new Resource(ResourceType.WOOD, 50),
                        new Resource(ResourceType.WHEAT, 30))),
        /**
         * A lumberjack building type that can produce a good amount of wood.
         * @see {@link it.unibo.model.data.Resource.ResourceType#WOOD}
         */
        LUMBERJACK(30_000,
                5_000,
                Set.of(new Resource(ResourceType.WOOD, 30)),
                Set.of(new Resource(ResourceType.WOOD, 50),
                        new Resource(ResourceType.WHEAT, 30))),
        /**
         * A farm building type that can produce a good amount of wheat.
         * @see {@link it.unibo.model.data.Resource.ResourceType#WHEAT}
         */
        FARM(30_000,
                5_000,
                Set.of(new Resource(ResourceType.WHEAT, 30)),
                Set.of(new Resource(ResourceType.WOOD, 50),
                        new Resource(ResourceType.WHEAT, 30)));

        private long defaultBuildTime;
        private long defaultProductionTime;
        private Set<Resource> baseProduction;
        private Set<Resource> cost;

        /**
         * Defines a standard building with
         * standardized values.
         * @param defaultBuildTime      The time that should take the building
         *                              to be ready
         * @param defaultProductionTime The time that the building will wait
         *                              before producing a resource set
         * @param baseProduction        The initial resource set that the building
         *                              will produce
         * @param cost                  The cost as a resource set of the building
         */
        BuildingTypes(final long defaultBuildTime, final long defaultProductionTime,
            final Set<Resource> baseProduction, final Set<Resource> cost) {
            this.defaultBuildTime = defaultBuildTime;
            this.defaultProductionTime = defaultProductionTime;
            this.baseProduction = baseProduction;
            this.cost = cost;
        }
        /**
         * @return The initial resource set that the building
         * will produce
         */
        public Set<Resource> getBaseProduction() {
            return baseProduction;
        }
        /**
         * Returns the base production of a type of building given a level.
         * @param level level of the building
         * @return The initial resource set that the building
         * will produce
         */
        public Set<Resource> getBaseProduction(final int level) {
            return BuildingBuilder.applyIncrementToResourceSet(
                    baseProduction, Building.PRODUCTION_MULTIPLIER_PERCENTAGE * level);
        }

        /**
         * @return The time that should take the building
         * to be ready
         */
        public long getBuildTime() {
            return defaultBuildTime;
        }
        /**
         * @return The time that the building will wait
         * before producing a resource set
         */
        public long getProductionTime() {
            return defaultProductionTime;
        }
        /**
         * @return The cost as a resource set of the building
         */
        public Set<Resource> getCost() {
            return Collections.unmodifiableSet(cost);
        }
        /**
         * Returns the cost of a type of building given a level.
         * @param level level of the building
         * @return The cost as a resource set of the building
         */
        public Set<Resource> getCost(final int level) {
            return BuildingBuilder.applyIncrementToResourceSet(
                    cost, Building.UPGRADE_TAX_PERCENTAGE * level);
        }
    }

    /**
     * Builds a standardized building given some standard values.
     * @param type      the type of the building to generate
     * @param position  the starting position of the building to generate
     * @param level     the starting level of the building to generate
     * @return          a complete building
     */
    Building makeStandardBuilding(BuildingTypes type, Point2D position, int level);
    /**
     * Builds a standardized building given some standard values.
     * @param type      the type of the building to generate
     * @param level     the starting level of the building to generate
     * @return          a complete building
     */
    Building makeStandardBuilding(BuildingTypes type, int level);

    /**
     * Applies an increment to a given resource set.
     * @param resourceSet           the resource set to apply the increment to
     * @param incrementPercentage   the increment as a percentage
     * @return                      the set with the applied increment
     */
    //Intended behaviour
    @SuppressWarnings("java:S2153")
    static Set<Resource> applyIncrementToResourceSet(Set<Resource> resourceSet,
        int incrementPercentage) {
        Set<Resource> modifiedSet = Resource.deepCopySet(resourceSet);
        modifiedSet.forEach(
                resource ->
                        resource.setAmount(
                            Double.valueOf(
                                applyIncrementToDouble(
                                    resource.getAmount(), incrementPercentage))
                                    .intValue()));
        return modifiedSet;
    }

    /**
     * Applies an increment to a given double.
     * @param valueToIncrement      the double to increment
     * @param incrementPercentage   the increment as a percentage
     * @return                      the double with the applied increment
     */
    static double applyIncrementToDouble(double valueToIncrement, int incrementPercentage) {
        double increment = valueToIncrement * (incrementPercentage / 100.0);
        return valueToIncrement + increment;
    }
}
