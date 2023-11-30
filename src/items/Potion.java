package items;

import characters.Hero;

public abstract class Potion extends Item implements Usable {
    private final String DESCRIPTION;
    private static final int WEIGHT = 1;
    protected static Hero hero;

    public Potion(String description) {
        super(Potion.WEIGHT);
        this.DESCRIPTION = description;
    }

    /**
     * Set the Hero to use some Potions on him
     * @param hero
     */
    public void setHero(Hero hero) {
        Potion.hero = hero;
    }

    public String getDescription() {
        String description = "NAME : " + this.getClass().getSimpleName() + "\nDESCRIPTION : " + this.DESCRIPTION;
        return description;
    }
}
