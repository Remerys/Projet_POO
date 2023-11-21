package hero;

import items.Item;
import items.Usable;
import items.Weapon;
import locations.Location;
import characters.Character;

/**
 * Principal player of the history
 */
public class Hero {
	//static
	private static final int DEFAULT_HP = 10;
	private static final int DEFAULT_MP = 0;
	private static final int DEFAULT_XP = 0;
	private static final int DEFAULT_SPEED = 5;
	private static final int DEFAULT_LEVEL = 1;
	private static final int MULTIPLIER_LEVEL = 2;
	private static final double CONSTANT_PROGRESSION = 1.2;
	private static Hero hero;

	//attribute
	private final String NAME;
	private Location loc;
	private Weapon weapon;
	private Inventory inventory;
	private int hp;
	private int maxHp;
	private int mp;
	private int maxMp;
	private int speed;
	private int xp;
	private int level;
	private int xpNewLvl;

	private Hero(String name, Location loc, int hp, int mp, int xp, int level) {
		this.NAME = name;
		this.loc = loc;

		this.hp = hp;
		this.maxHp = hp;

		this.mp = mp;
		this.maxMp = mp;

		this.speed = Hero.DEFAULT_SPEED;
		this.xp = xp;
		this.level = level;
		this.xpNewLvl = 10;
		this.inventory = new Inventory();
	}

	private Hero(String name, Location loc) {
		this(name, loc, Hero.DEFAULT_HP, Hero.DEFAULT_MP, Hero.DEFAULT_XP, Hero.DEFAULT_LEVEL);
	}

	public static Hero createHero(String name, Location loc) {
		if (Hero.hero == null) {
			Hero.hero = new Hero(name, loc);
		}
		return Hero.hero;
	}

	public static Hero getHero() throws Exception {
		if (Hero.hero != null) {
			return Hero.hero;
		} else {
			throw new Exception("getHero : No hero has been created !");
		}
	}

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

	public Location getLocation() {
		return this.loc;
	}

	public Weapon getWeaponEquiped() {
		return this.weapon;
	}

	public int getHp() {
		return this.hp;
	}

	public int getMaxHp() {
		return this.maxHp;
	}

	public int getMp() {
		return this.mp;
	}

	public int getMaxMp() {
		return this.maxMp;
	}

	public int getSpeed() {
		return this.speed;
	}

	public int getLevel() {
		return this.level;
	}

	public int getXp() {
		return this.xp;
	}

	public int getXpNewLevel() {
		return this.xpNewLvl;
	}

	public int getDamage() {
		return this.weapon.getDamage();
	}

	public int getRange() {
		return this.weapon.getRange();
	}

	/*------------------------------------------------------------------------------------
	 *
	 * Setters
	 *
	 ------------------------------------------------------------------------------------*/

	public void setHp(int hp) {
		this.hp = hp;
		if (this.hp > this.maxHp) {
			this.hp = this.maxHp;
		}
		if (this.hp < 0) {
			this.hp = 0;
		}
	}

	public void heal(int hp) {
		setHp(this.hp + hp);
	}

	public void getAttacked(int damage) {
		setHp(this.hp - damage);
	}

	public void setMp(int mp) {
		this.mp = mp;
		if (this.mp > this.maxMp) {
			this.mp = this.maxMp;
		}
		if (this.mp < 0) {
			this.mp = 0;
		}
	}

	public void gainMp(int mp) {
		setMp(this.mp + mp);
	}

	public void useMp(int mp) {
		setMp(this.mp - mp);
	}

	private void changeHpLvl() {
		this.maxHp *= CONSTANT_PROGRESSION;
		this.hp *= CONSTANT_PROGRESSION;
	}

	private void changeMpLvl() {
		if (this.maxMp == 0) {
			this.maxMp += 10;
			this.mp += 10;
		}
		else {
			this.maxMp *= CONSTANT_PROGRESSION;
			this.mp *= CONSTANT_PROGRESSION;
		}
	}

	public void addXp(int xp) {
		this.xp += xp;
		if (this.xpNewLvl <= this.xp) {
			this.levelUp();
		}
	}

	public void levelUp() {
		this.level++;
		this.xp -= this.xpNewLvl;
		//2^(level-1) x 10
		this.xpNewLvl = (int)Math.pow(Hero.MULTIPLIER_LEVEL, this.level-1)*10;

		this.changeHpLvl();
		this.changeMpLvl();

		this.addXp(0); //if there is another level to gain
	}


	public void setLocation(Location loc) {
		this.loc = loc;
	}

	public void changeEquipedWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public void addItem(Item item) throws Exception{
		this.inventory.addItem(item);
	}

	public void removeItem(Item item) throws Exception{
		this.inventory.removeItem(item);
	}

	/*------------------------------------------------------------------------------------
	 *
	 * Others
	 *
	 ------------------------------------------------------------------------------------*/

	public void useItem(Usable item) {
		item.use();
	}

	public void attack(Character character) {
		character.getAttacked(hero.getWeaponEquiped().getDamage());
	}

	public void printInventory() {
		this.inventory.printInventory();
	}
}
