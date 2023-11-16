package items;

public class Sword extends Weapon {
    private static final int WEIGHT = 10;
    private static final int DAMAGE = 10;
    private static final int RANGE = 10;

    public Sword() {
        super(Sword.WEIGHT, Sword.DAMAGE, Sword.RANGE);
    }
}
