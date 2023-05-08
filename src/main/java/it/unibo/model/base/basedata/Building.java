package it.unibo.model.base.basedata;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.model.data.Resource;

import java.awt.geom.Point2D;

public class Building {
    public static final int MAXLEVEL = 3;
    public static final int REFUND_TAX_PERCENTAGE = 25;
    public static final String CONSTRUCTION_OVERLAY = "/resources/images/image.png";

    private Optional<URL> buildingOverlay;             /*Texture to use while building structure*/
    private List<URL> buildingTextures;                /*1 texture for every level*/

    private BuildingTypes type;
    private int level;
    private float buildingTime;                        /*milliseconds*/
    private boolean beingBuilt;
    private int buildingProgess;
    private Point2D structurePos;
    private Set<Resource> productionAmount;

    //The high parameter count is necessary to set all of the properties of the class
    @SuppressWarnings("java:S107")
    public Building(Optional<URL> buildingOverlay, List<URL> buildingTextures, BuildingTypes type, int level,
            float buildingTime, boolean beingBuilt, int buildingProgess, Point2D structurePos,
            Set<Resource> productionAmount) {
        this.buildingOverlay = buildingOverlay;
        this.buildingTextures = buildingTextures;
        this.type = type;
        this.level = level;
        this.buildingTime = buildingTime;
        this.beingBuilt = beingBuilt;
        this.buildingProgess = buildingProgess;
        this.structurePos = structurePos;
        this.productionAmount = productionAmount;
    }

    public synchronized Optional<URL> getBuildingOverlay() {
        return buildingOverlay;
    }
    public synchronized void setBuildingOverlay(Optional<URL> buildingOverlay) {
        this.buildingOverlay = buildingOverlay;
    }
    public synchronized List<URL> getBuildingTextures() {
        return buildingTextures;
    }
    public synchronized void setBuildingTextures(List<URL> buildingTextures) {
        this.buildingTextures = buildingTextures;
    }
    public synchronized BuildingTypes getType() {
        return type;
    }
    public synchronized void setType(BuildingTypes type) {
        this.type = type;
    }
    public synchronized int getLevel() {
        return level;
    }
    public synchronized void setLevel(int level) {
        this.level = level;
    }
    public synchronized float getBuildingTime() {
        return buildingTime;
    }
    public synchronized void setBuildingTime(float buildingTime) {
        this.buildingTime = buildingTime;
    }
    public synchronized boolean isBeingBuilt() {
        return beingBuilt;
    }
    public synchronized void setBeingBuilt(boolean beingBuilt) {
        this.beingBuilt = beingBuilt;
    }
    public synchronized int getBuildingProgess() {
        return buildingProgess;
    }
    public synchronized void setBuildingProgess(int buildingProgess) {
        this.buildingProgess = buildingProgess;
    }
    public synchronized Point2D getStructurePos() {
        return structurePos;
    }
    public synchronized void setStructurePos(Point2D structurePos) {
        this.structurePos = structurePos;
    }
    public synchronized Set<Resource> getProductionAmount() {
        return productionAmount;
    }
    public synchronized void setProductionAmount(Set<Resource> productionAmount) {
        this.productionAmount = productionAmount;
    }   
}