package items;

import characters.Hero;

public abstract class Potion extends Item implements Usable {
    private static final int WEIGHT = 1;
    protected static Hero hero;

    public Potion() {
        super(Potion.WEIGHT);
    }

    /**
     * Set the Hero to use some Potions on him
     * @param hero
     */
    public void setHero(Hero hero) {
        Potion.hero = hero;
    }
}
