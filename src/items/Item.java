package items;

public abstract class Item {
    private final int WEIGHT;

    public Item(int weight) {
        this.WEIGHT = weight;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    /**
     * @return The weight of the Item
     */
    public int getWeight() {
        return this.WEIGHT;
    }

    public abstract String getDescription();
}
