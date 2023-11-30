package items;

public class MagicWand extends Weapon {
    private static final String DESCRIPTION = "Magical artifact channeling diverse spells, adorned with mystical engravings. Powerful tool for adventurers seeking prowess.";
    private static final int WEIGHT = 5;
    private static final int DAMAGE = 10;
    private static final int RANGE = 10;

    public MagicWand() {
        super(MagicWand.WEIGHT, MagicWand.DAMAGE, MagicWand.RANGE, MagicWand.DESCRIPTION);
    }
}
