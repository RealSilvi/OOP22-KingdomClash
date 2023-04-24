package it.unibo.model.base.basedata;

/**
 * A very simple factory to simplify the creation of buildings
 */
public interface BuildingFactory {
    public enum BuildingTypes {
        HALL,
        LUMBERJACK,
        FARM
    }
    /**
     * Creates an instance of a building given a type and a level
     * @param type the type of the building
     * @param level the starting level of the building
     * @return an instance of the building
     */
    public Building makeBuilding(BuildingTypes type, int level);
    /**
     * Works like {@link BuildingFactory#makeBuilding(BuildingTypes type, int level)}
     * with the main difference that the building's level defaults to 0
     * @param type the type of the building
     */
    public Building makeBuilding(BuildingTypes type);
}