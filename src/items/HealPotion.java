package items;

public class HealPotion extends Potion {
    private static final int HEAL = 5;

    public HealPotion() {
        super();
    }
    
    public void use() {
        Potion.hero.heal(HealPotion.HEAL);
    }
}
