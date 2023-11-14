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
	private static final int DEFAULT_HP = 10;
	private static final int DEFAULT_MP = 0;
	private static final int DEFAULT_XP = 0;
	private static final int DEFAULT_LEVEL = 1;
	private static Hero hero;
	public int maxHp = DEFAULT_HP;
	public int maxMp = DEFAULT_MP;
	
	
	private final String NAME;
	private Location loc;
	private Weapon weapon;
	//private Inventory inventory;
	private int hp;
	private int mp;
	private int xp;
	private int level;
	
	private Hero(String name, Location loc, int hp, int mp, int xp, int level) {
		this.NAME = name;
		this.loc = loc;
		this.hp = hp;
		this.mp = mp;
		this.xp = xp;
		this.level = level;
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
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public void setMp(int mp) {
		this.mp = mp;
	}
	
	public void addXp(int xp) {
		this.xp += xp;
	}
	
	public void heal(int hp) {
		this.hp += hp;
	}
	
	public Location getLocation() {
		return this.loc;
	}
	
	public void setLocation(Location loc) {
		this.loc = loc;
	}
	
	public Weapon getWeaponEquiped() {
		return this.weapon;
	}
	public void changeEquipedWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	public void useItem(Usable item) {
		item.use();
	}
	
	public void getAttacked(int damage) {
		this.hp -= damage;
		if (this.hp < 0) {
			this.hp = 0;
		}
	}
	
	public void attack(Character character) {
		character.getAttacked(hero.getWeaponEquiped().getDamage());
	}
}
