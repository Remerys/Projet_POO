package characters;

/**
 * An abstract class representing all the characters that can attack
 *
 * @author Lilian
 */
public abstract class Fighter extends Character {

	protected int hp;
	private final int MAX_HP;
	private int xpDropped;
	/**
	 * MAX_DAMAGE (int) : 1000
	 */
	public static int MAX_DAMAGE = 1000;
	/**
	 * MAX_SPEED (int) : 10
	 */
	public static int MAX_SPEED = 10;

	/**
	 * damage (int) : Damages that can be inflict
	 */
	protected int damage;
	/**
	 * speed (int) : Determine the first attacker in a fight
	 */
	protected int speed;

	private Fighter(String name, String description, int maxHp, int xpDropped, int damage, int speed) {
		super(name);
		this.MAX_HP = maxHp;
		this.hp = maxHp;
		this.xpDropped = xpDropped;
		this.damage = damage;
		this.speed = speed;
		this.setDescription(description);
	}

	public Fighter(String name, int maxHp, int xpDropped, int damage, int speed) {
		super(name);
		this.MAX_HP = maxHp;
		this.hp = maxHp;
		this.xpDropped = xpDropped;
		this.damage = damage;
		this.speed = speed;
	}

	/**
	 * Give the fighter's damage
	 *
	 * @return damage (int)
	 */
	public int getDamage() {
		return this.damage;
	}

	/**
	 * Give the fighter's speed
	 *
	 * @return speed (int)
	 */
	public int getSpeed() {
		return this.speed;
	}

	/**
	 * Give the fighter's health points
	 *
	 * @return hp (int)
	 */
	public int getHp() {
		return this.hp;
	}

	/**
	 * Verify if the fighter is dead
	 *
	 * @return isDead (boolean)
	 */
	public boolean isDead() {
		return this.hp == 0;
	}

	/**
	 * Changes the fighter's hp according to damage suffered
	 *
	 * @param damage (int) : the number of damages suffered
	 */
	public void getAttacked(int damage) {
		this.hp -= damage;
		if (this.hp < 0) {
			this.hp = 0;
		}
	}

	/**
	 * Return the experience obtain after kill this fighter
	 *
	 * @return the experience obtain
	 */
	public int getXpDropped() {
		return this.xpDropped;
	}

	/**
	 * In a fight, attack the player with fighter's damages
	 *
	 * @param attacked (Fighter)
	 */
	public void attack(Fighter attacked) {
		attacked.getAttacked(this.getDamage());
	}

	/**
	 * Change the fighter's health points to their maximum
	 *
	 * @param hp (int)
	 */
	public void fullyHeals() {
		this.hp = this.MAX_HP;
	}

	/**
	 * Give the Figther's description/history
	 *
	 * @return description (String)
	 */
	@Override
	public String getDescription() {
		String description = super.getDescription() + "\nHP : " + this.hp + "\nDAMAGE : " + this.damage + "\nSPEED : " + this.speed;
		return description;
	}
}
