package it.unibo.model.base.basedata;

import java.net.URL;
import java.util.List;
import java.util.Optional;

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
    private List<Resource> productionAmount;

    //The high parameter count is necessary to set all of the properties of the class
    @SuppressWarnings("java:S107")
    public Building(Optional<URL> buildingOverlay, List<URL> buildingTextures, BuildingTypes type, int level,
            float buildingTime, boolean beingBuilt, int buildingProgess, Point2D structurePos,
            List<Resource> productionAmount) {
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

    public Optional<URL> getBuildingOverlay() {
        return buildingOverlay;
    }
    public void setBuildingOverlay(Optional<URL> buildingOverlay) {
        this.buildingOverlay = buildingOverlay;
    }
    public List<URL> getBuildingTextures() {
        return buildingTextures;
    }
    public void setBuildingTextures(List<URL> buildingTextures) {
        this.buildingTextures = buildingTextures;
    }
    public BuildingTypes getType() {
        return type;
    }
    public void setType(BuildingTypes type) {
        this.type = type;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public float getBuildingTime() {
        return buildingTime;
    }
    public void setBuildingTime(float buildingTime) {
        this.buildingTime = buildingTime;
    }
    public boolean isBeingBuilt() {
        return beingBuilt;
    }
    public void setBeingBuilt(boolean beingBuilt) {
        this.beingBuilt = beingBuilt;
    }
    public int getBuildingProgess() {
        return buildingProgess;
    }
    public void setBuildingProgess(int buildingProgess) {
        this.buildingProgess = buildingProgess;
    }
    public Point2D getStructurePos() {
        return structurePos;
    }
    public void setStructurePos(Point2D structurePos) {
        this.structurePos = structurePos;
    }
    public List<Resource> getProductionAmount() {
        return productionAmount;
    }
    public void setProductionAmount(List<Resource> productionAmount) {
        this.productionAmount = productionAmount;
    }   
}