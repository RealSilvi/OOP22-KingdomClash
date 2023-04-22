package it.unibo.model.data;

public class Resource {
    public enum ResourceType {
        WHEAT,
        WOOD
    }
    private ResourceType resource;
    private float amount;

    public Resource(ResourceType resource, float amount) {
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
    public float getAmount() {
        return amount;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }
}