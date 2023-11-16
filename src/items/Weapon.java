package items;

public abstract class Weapon extends Item {
    private final int DAMAGE;
    private final int RANGE;

    public Weapon(int weight, int damage, int range) {
        super(weight);
        this.DAMAGE = damage;
        this.RANGE = range;
    }

    /**
     * @return The damage of the weapon
     */
    public int getDamage() {
        return this.DAMAGE;
    }

    /**
     * @return The range of the weapon
     */
    public int getRange() {
        return this.RANGE;
    }
}
