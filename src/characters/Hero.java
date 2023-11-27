package characters;

import items.*;
import locations.Location;

/**
 * Principal player of the history
 *
 * @author Lilian
 */
public class Hero extends Fighter {
	// static
	private static final int DEFAULT_HP = 10;
	private static final int DEFAULT_MP = 0;
	private static final int DEFAULT_XP = 0;
	private static final int DEFAULT_LEVEL = 1;
	private static final int DEFAULT_SPEED = 5;
	private static final int MULTIPLIER_LEVEL = 2;
	private static final double CONSTANT_PROGRESSION = 1.2;
	private static Hero hero;
	// private static final Weapon DEFAULT_WEAPON = new Fist();

	// attribute
	private Location loc;
	private Weapon weapon;
	private Inventory inventory;
	private int maxHp;
	private int mp;
	private int maxMp;
	private int xp;
	private int level;
	private int xpNewLvl;

	private Hero(String name, Location loc, int hp, int mp, int xp, int level) {
		super(name, hp, 0, 0, Hero.DEFAULT_SPEED);
		this.loc = loc;
		this.maxHp = hp;

		this.mp = mp;
		this.maxMp = mp;

		this.xp = xp;
		this.level = level;
		this.xpNewLvl = 10;
		this.inventory = new Inventory();

	}

	private Hero(String name, Location loc) {
		this(name, loc, Hero.DEFAULT_HP, Hero.DEFAULT_MP, Hero.DEFAULT_XP, Hero.DEFAULT_LEVEL);
	}

	/**
	 * @param name (String)
	 * @param loc  (Location)
	 * @return a new hero if it didn't exist
	 */
	public static Hero createHero(String name, Location loc) {
		if (Hero.hero == null) {
			Hero.hero = new Hero(name, loc);
		}
		return Hero.hero;
	}

	/**
	 * When the hero exist
	 *
	 * @return the hero
	 * @throws Exception no hero has been created
	 */
	public static Hero getHero() throws Exception {
		if (Hero.hero != null) {
			return Hero.hero;
		} else {
			throw new Exception("getHero : No hero has been created !");
		}
	}

	/**
	 * Destroy the instance of hero
	 */
	public static void destroyHero() {
		Hero.hero = null;
	}

	@Override
	public String toString() {
		return String.format("%s :\n hp %d\n lvl %d \n", this.NAME, this.hp, this.level);
	}

	/*------------------------------------------------------------------------------------
	 *
	 * Getters
	 *
	 ------------------------------------------------------------------------------------*/

	/**
	 * Get the hero's location
	 *
	 * @return hero's location
	 */
	public Location getLocation() {
		return this.loc;
	}

	/**
	 * Get the hero's weapon equiped
	 *
	 * @return hero's weapon
	 */
	public Weapon getWeaponEquiped() {
		return this.weapon;
	}

	/**
	 * Get the hero's max health points
	 *
	 * @return hero's max health points
	 */
	public int getMaxHp() {
		return this.maxHp;
	}

	/**
	 * Get the hero's magic points
	 *
	 * @return hero's magic points
	 */
	public int getMp() {
		return this.mp;
	}

	/**
	 * Get the hero's max magic points
	 *
	 * @return hero's max magic points
	 */
	public int getMaxMp() {
		return this.maxMp;
	}

	/**
	 * Get the hero's level
	 *
	 * @return hero's level
	 */
	public int getLevel() {
		return this.level;
	}

	/**
	 * Get the hero's experience
	 *
	 * @return hero's current experience
	 */
	public int getXp() {
		return this.xp;
	}

	/**
	 * Get the hero's xp to next level
	 *
	 * @return experiences to obtain to level up
	 */
	public int getXpNewLevel() {
		return this.xpNewLvl;
	}

	/**
	 * Get the hero's damage
	 *
	 * @return hero's damage
	 */
	@Override
	public int getDamage() {
		if (this.weapon != null) {
			return this.weapon.getDamage();
		} else {
			return this.getLevel();
		}

	}

	/**
	 * Get the hero's range
	 *
	 * @return hero's range
	 */
	public int getRange() {
		return this.weapon.getRange();
	}

	/**
	 * Get a hero's item
	 *
	 * @param itemType
	 * @return
	 */
	public Item getItem(String itemType) {
		return this.inventory.getItem(itemType);
	}

	/**
	 * Get a hero's potion
	 *
	 * @return a hero's potion
	 */
	public Potion getPotion() {
		return this.inventory.getPotion();
	}

	/*------------------------------------------------------------------------------------
	 *
	 * Setters
	 *
	 ------------------------------------------------------------------------------------*/

	/**
	 * Change the hero's health points
	 *
	 * @param hp (int)
	 */
	public void setHp(int hp) {
		this.hp = hp;
		if (this.hp > this.maxHp) {
			this.hp = this.maxHp;
		}
		if (this.hp < 0) {
			this.hp = 0;
		}
	}

	/**
	 * Add to the hero's health points, a number of health points
	 *
	 * @param hp (int)
	 */
	public void heal(int hp) {
		setHp(this.hp + hp);
	}

	/**
	 * Remove to the hero's health points, a number of health points
	 *
	 * @param damage (int)
	 */
	public void getAttacked(int damage) {
		setHp(this.hp - damage);
	}

	/**
	 * Change the hero's magic points
	 *
	 * @param mp
	 */
	public void setMp(int mp) {
		this.mp = mp;
		if (this.mp > this.maxMp) {
			this.mp = this.maxMp;
		}
		if (this.mp < 0) {
			this.mp = 0;
		}
	}

	/**
	 * Add to the hero's magic points, a number of magic points
	 *
	 * @param mp (int)
	 */
	public void gainMp(int mp) {
		setMp(this.mp + mp);
	}

	/**
	 * Remove to the hero's magic points, a number of magic points
	 *
	 * @param mp (int)
	 */
	public void useMp(int mp) {
		setMp(this.mp - mp);
	}

	/**
	 * Change some variables linked by hp when level up
	 */
	private void changeHpLvl() {
		this.maxHp *= CONSTANT_PROGRESSION;
		this.hp *= CONSTANT_PROGRESSION;
	}

	/**
	 * Change some variables linked by mp when level up
	 */
	private void changeMpLvl() {
		if (this.maxMp == 0) {
			this.maxMp += 10;
			this.mp += 10;
		} else {
			this.maxMp *= CONSTANT_PROGRESSION;
			this.mp *= CONSTANT_PROGRESSION;
		}
	}

	/**
	 * Add experience and level up if there is enough xp
	 *
	 * @param xp (int)
	 */
	public void addXp(int xp) {
		this.xp += xp;
		if (this.xpNewLvl <= this.xp) {
			this.levelUp();
		}
	}

	/**
	 * Increase hero's stats
	 */
	public void levelUp() {
		this.level++;
		this.xp -= this.xpNewLvl;
		// 2^(level-1) x 10
		this.xpNewLvl = (int) Math.pow(Hero.MULTIPLIER_LEVEL, this.level - 1) * 10;

		this.changeHpLvl();
		this.changeMpLvl();

		this.addXp(0); // if there is another level to gain
	}

	/**
	 * Change hero's location
	 *
	 * @param loc (Location)
	 */
	public void setLocation(Location loc) {
		this.loc = loc;
	}

	/**
	 * Change hero's equiped weapon
	 */
	public void changeEquipedWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	/**
	 * Add an item in hero's inventory
	 *
	 * @param item (Item)
	 * @throws Exception : Cannot add another item
	 */
	public void addItem(Item item) throws Exception {
		this.inventory.addItem(item);
	}

	/**
	 * Remove an item in hero's inventory
	 *
	 * @param item (Item)
	 * @throws Exception : Cannot remove an item not present in the inventory
	 */
	public void removeItem(Item item) throws Exception {
		this.inventory.removeItem(item);
	}

	/*------------------------------------------------------------------------------------
	 *
	 * Others
	 *
	 ------------------------------------------------------------------------------------*/

	/**
	 * Use the effect of the item and remove it from hero's inventory
	 *
	 * @param item (Item)
	 * @throws Exception : This item isn't usable
	 */
	public void useItem(Item item) throws Exception {
		if (this.hasItem(item)) {
			if (item instanceof Usable) {
				((Usable) item).use();
				this.inventory.removeItem(item);
			} else {
				throw new Exception("This item isn't usable");
			}
		}
	}

	/**
	 * Verify there an item in the inventory
	 *
	 * @param item (Item)
	 * @return if there is the item
	 */
	public boolean hasItem(Item item) {
		return this.inventory.hasItem(item);
	}

	/**
	 * Verify there a potion in the inventory
	 *
	 * @return if there is a potion
	 */
	public boolean hasPotion() {
		return this.inventory.hasPotion();
	}

	/**
	 * Attack a character with the hero's weapon
	 *
	 * @param character (Character)
	 */
	public void attack(Fighter fighter) {
		fighter.getAttacked(hero.getDamage());
	}

	/**
	 * Print the hero's inventory
	 */
	public void printInventory() {
		this.inventory.printInventory();
	}

	/**
	 * Print the hero's statistics
	 */
	public void printStats() {
		System.out.println("Name  : " + this.NAME);
		System.out.println("Level : " + this.level);
		System.out.println(String.format("XP : %d / %d", this.xp, this.xpNewLvl));
		System.out.println(String.format("HP : %d / %d", this.hp, this.maxHp));
		System.out.println(String.format("MP : %d / %d", this.mp, this.maxMp));
		System.out.println(String.format("Inventory Capacity : %d / %d", this.inventory.getWeight(),
				this.inventory.getMaxWeight()));

		if (this.loc != null) {
			System.out.println("Location : " + this.loc.getName());
		} else {
			System.out.println("Location : None");
		}

		if (this.weapon != null) {
			System.out.println("Equipped Weapon : " + this.weapon);
		} else {
			System.out.println("Equipped Weapon : None");
		}
	}
}
