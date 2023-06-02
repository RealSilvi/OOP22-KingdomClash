package it.unibo.model.base;

import it.unibo.model.base.internal.BuildingBuilder;
import it.unibo.model.base.internal.BuildingBuilder.BuildingTypes;
import it.unibo.model.data.Resource;
import it.unibo.model.data.Resource.ResourceType;

import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.*;

public interface SetBuilding {

    BuildingBuilder setbuilding(BuildingTypes type, Map<Point2D, JButton> position);

    ResourceType updatequantity(ResourceType resource, int amount);

    boolean isbuildingplaceable();


}
