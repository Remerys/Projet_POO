package items;

public class Bow extends Weapon {
    private static final int WEIGHT = 5;
    private static final int DAMAGE = 5;
    private static final int RANGE = 10;

    public Bow() {
        super(Bow.WEIGHT, Bow.DAMAGE, Bow.RANGE);
    }
}
