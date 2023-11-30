package items;

public abstract class Weapon extends Item {
    private final String DESCRIPTION;
    private final int DAMAGE;
    private final int RANGE;

    public Weapon(int weight, int damage, int range, String description) {
        super(weight);
        this.DAMAGE = damage;
        this.RANGE = range;
        this.DESCRIPTION = description;
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

    public String getDescription() {
        String description = "NAME : " + this.getClass().getSimpleName() + "\nDESCRIPTION : " + this.DESCRIPTION + "\nDAMAGE : " + this.DAMAGE + "\nRANGE : " + this.RANGE;
        return description;
    }
}
