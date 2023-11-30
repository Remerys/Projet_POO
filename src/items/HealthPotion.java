package items;

public class HealthPotion extends Potion {
    private static final String DESCRIPTION = "Curative potion infused with potent herbs and mystical essence. Swiftly restores health, making it a vital asset for adventurers in dire situations.";
    public static final int HEAL = 5;

    public HealthPotion() {
        super(HealthPotion.DESCRIPTION);
    }

    public void use() {
        Potion.hero.heal(HealthPotion.HEAL);
    }

    @Override
    public String getDescription() {
        String description = "";
        description = super.getDescription() + "\nHEAL : " + HealthPotion.HEAL;
        return description;
    }
}
