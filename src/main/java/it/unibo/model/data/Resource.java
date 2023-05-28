package it.unibo.model.data;

public class Resource {
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
        return "ResourceType: "+resource.name()+"\nAmount: "+amount;
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
}