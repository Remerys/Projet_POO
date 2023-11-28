package items;

public class HealthPotion extends Potion {
    public static final int HEAL = 5;

    public HealthPotion() {
        super();
    }

    public void use() {
        Potion.hero.heal(HealthPotion.HEAL);
    }
}
