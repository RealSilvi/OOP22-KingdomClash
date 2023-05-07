package it.unibo.model.base.api;

import java.util.UUID;

public interface BuildingObserver {
    public void update(UUID buildingId);
}