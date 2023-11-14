package characters;

import hero.Hero;

/**
 * An abstract class representing all the characters that can attack the hero
 */
abstract class Vilain extends Character {
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

	private Vilain(String name, String description, int hp, int damage, int speed) {
		super(name, hp);
		this.damage = damage;
		this.speed = speed;
		this.setDescription(description);
	}
	
	public Vilain(String name, int hp, int damage, int speed) {
		super(name, hp);
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
		Character.hero.getAttacked(this.getDamage());
	}

}
