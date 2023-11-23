package characters;

import hero.Hero;
import locations.Location;

/**
 * An abstract class representing all the characters that can attack the hero
 * @author Lilian
 */
public abstract class Villain extends Character {
	/**
	 * MAX_DAMAGE (int) : 1000
	 */
	public static int MAX_DAMAGE = 1000;
	/**
	 * MAX_SPEED (int) : 10
	 */
	public static int MAX_SPEED = 10;

	/**
	 * damage (int) : Damages that can be inflict to the hero
	 */
	private int damage;
	/**
	 * speed (int) : Determine the first attacker in a fight
	 */
	private int speed;

	private Villain(String name, String description, Location loc, int hp, int xpDropped, int damage, int speed) {
		super(name, loc, hp, xpDropped);
		this.damage = damage;
		this.speed = speed;
		this.setDescription(description);
	}

	public Villain(String name, Location loc, int maxHp, int xpDropped, int damage, int speed) {
		super(name, loc, maxHp, xpDropped);
		this.damage = damage;
		this.speed = speed;
	}


	/**
	 * Give the vilain's damage
	 * @return damage (int)
	 */
	public int getDamage() {
		return this.damage;
	}

	/**
	 * Give the vilain's speed
	 * @return speed (int)
	 */
	public int getSpeed() {
		return this.speed;
	}

	/**
	 * In a fight, attack the player with vilain's damages
	 * @param hero (Hero)
	 */
	public void attack(Hero hero) {
		hero.getAttacked(this.getDamage());
	}

}
