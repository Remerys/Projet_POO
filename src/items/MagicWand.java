package items;

public class MagicWand extends Weapon {
    private static final int WEIGHT = 5;
    private static final int DAMAGE = 10;
    private static final int RANGE = 10;

    public MagicWand() {
        super(MagicWand.WEIGHT, MagicWand.DAMAGE, MagicWand.RANGE);
    }
}
