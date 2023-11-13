package characters;

import hero.Hero;

/**
 * An abstract class representing all the characters that can attack the hero
 */
abstract class Vilain extends Character {
	public static int MAX_DAMAGE = 1000;
	public static int MAX_SPEED = 10;
	
	private int damage;
	private int speed;

	public Vilain(String name, String description, int hp, int damage, int speed) {
		super(name, description, hp);
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
		//hero.isAttacked(this.getDamage());
	}

}
