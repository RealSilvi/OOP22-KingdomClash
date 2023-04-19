package it.unibo.model.data;

import java.nio.file.Path;
import java.util.List;

public class Building {
    public static int MAXLEVEL = 3;
    public static Path buildingTexture;
    private int level;
    private float buildingTime;
    private boolean buildingStatus;
    private int buildingProgess;
    private List<Path> strutture;
    private Pair<Float, Float> structurePos;
    private List<Resource> productionAmount;
}