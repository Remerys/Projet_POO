package items;

public abstract class Weapon extends Item {
    private final int DAMAGE;
    private final int RANGE;

    public Weapon(int weight, int damage, int range) {
        super(weight);
        this.DAMAGE = damage;
        this.RANGE = range;
    }

    public int getDamage() {
        return this.DAMAGE;
    }

    public int getRange() {
        return this.RANGE;
    }
}
