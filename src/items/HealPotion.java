package items;

public class HealPotion extends Potion {
    private static final int HEAL = 5;

    public HealPotion() {
        super();
    }

    /**
     * Heal the Hero
     */
    public void use() {
        Potion.hero.heal(HealPotion.HEAL);
    }
}
