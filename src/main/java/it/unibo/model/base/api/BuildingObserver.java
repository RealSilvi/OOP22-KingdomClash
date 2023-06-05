package it.unibo.model.base.api;

import java.util.UUID;

/**
 * A standard interface that represents an observer that gets
 * notified when a specific state changes.
 */
public interface BuildingObserver {
    /**
     * Function that gets called whenever a specific event happens.
     * @param buildingId    the identifier of the building that caused the event
     */
    void update(UUID buildingId);
}
