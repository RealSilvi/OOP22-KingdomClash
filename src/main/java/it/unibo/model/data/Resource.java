package it.unibo.model.data;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * A simple data class that stores a type of resource and it's corresponding amount
 */
public class Resource {
    /**
     * An enum containing all type of resources
     */
    public enum ResourceType {
        WHEAT,
        WOOD
    }

    private ResourceType resource;
    private int amount;

    public Resource(ResourceType resource, int amount) {
        this.resource = resource;
        this.amount = amount;
    }

    public Resource(ResourceType resource) {
        this(resource, 0);
    }

    public ResourceType getResource() {
        return resource;
    }

    public void setResource(ResourceType resource) {
        this.resource = resource;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ResourceType: " + resource.name() + "\nAmount: " + amount;
    }

    @Override
    public int hashCode() {
        return getResource().hashCode();
    }

    @Override
    public boolean equals(Object otherResource) {
        if (otherResource == null) {
            return false;
        }
        if ((getClass() == otherResource.getClass()) &&
                (this.getResource() == ((Resource) otherResource).getResource())) {
            return true;
        }
        return super.equals(otherResource);
    }

    public Resource clone() {
        return new Resource(this.resource, this.amount);
    }

    /**
     * Performs a deep copy of a given resource set
     *
     * @param resourceSet the set to be copy
     * @return a deep copy of the given set
     */
    public static Set<Resource> deepCopySet(Set<Resource> resourceSet) {
        return resourceSet.stream()
                .map(Resource::clone)
                .collect(Collectors.toSet());
    }
}