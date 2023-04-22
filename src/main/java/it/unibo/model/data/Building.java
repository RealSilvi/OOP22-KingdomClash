package it.unibo.model.data;

import java.nio.file.Path;
import java.util.List;

import it.unibo.kingdomclash.util.Pair;

public class Building {
    public static final int MAXLEVEL = 3;

    private Path buildingOverlay;                       /*Texture to use while building structure*/
    private List<Path> buildingTextures;                /*1 texture for every level*/

    private int level;
    private float buildingTime;                         /*milliseconds*/
    private boolean buildingStatus;
    private int buildingProgess;
    private Pair<Float, Float> structurePos;
    private List<Resource> productionAmount;

    public Path getBuildingOverlay() {
        return buildingOverlay;
    }
    public void setBuildingOverlay(Path buildingOverlay) {
        this.buildingOverlay = buildingOverlay;
    }
    public List<Path> getBuildingTextures() {
        return buildingTextures;
    }
    public void setBuildingTextures(List<Path> buildingTextures) {
        this.buildingTextures = buildingTextures;
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
    public boolean isBuildingStatus() {
        return buildingStatus;
    }
    public void setBuildingStatus(boolean buildingStatus) {
        this.buildingStatus = buildingStatus;
    }
    public int getBuildingProgess() {
        return buildingProgess;
    }
    public void setBuildingProgess(int buildingProgess) {
        this.buildingProgess = buildingProgess;
    }
    public Pair<Float, Float> getStructurePos() {
        return structurePos;
    }
    public void setStructurePos(Pair<Float, Float> structurePos) {
        this.structurePos = structurePos;
    }
    public List<Resource> getProductionAmount() {
        return productionAmount;
    }
    public void setProductionAmount(List<Resource> productionAmount) {
        this.productionAmount = productionAmount;
    }
}