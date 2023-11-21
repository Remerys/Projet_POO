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

    public int getWeight() {
        return this.WEIGHT;
    }
}
