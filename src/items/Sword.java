package items;

public class Sword extends Weapon {
    private static final String DESCRIPTION = "Legendary weapon with the ability to unleash devastating lightning upon foes. Adorned with luminous runes, it embodies the perfect fusion of brute strength and magic.";
    private static final int WEIGHT = 10;
    private static final int DAMAGE = 10;
    private static final int RANGE = 10;

    public Sword() {
        super(Sword.WEIGHT, Sword.DAMAGE, Sword.RANGE, Sword.DESCRIPTION);
    }
}
