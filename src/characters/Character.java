package characters;

import hero.Hero;
import locations.Location;

/**
 * An abstract class representing all the characters in the world except the hero
 * @author Lilian
 */
public abstract class Character {
	/**
	 * MAX_HP (int) : 500
	 */
	public static int MAX_HP = 500;
	
	
	private final String NAME;
	private String description;
	private int hp;
	private int xpDropped;
	private Location loc;
	protected static Hero hero;
	
	protected boolean hasFinishedToTalk;
	
	public Character(String name, String description, Location loc, int hp, int xpDropped) {
		this.NAME = name;
		this.description = name;
		this.loc = loc;
		this.hp = hp;
		this.xpDropped = xpDropped;
		this.hasFinishedToTalk = false;
	}
	
	public Character(String name, Location loc, int hp, int xpDropped) {
		this(name, "", loc, hp, xpDropped);
	}
	
	
	/*------------------------------------------------------------------------------------
	 * 
	 * Getters
	 * 
	 ------------------------------------------------------------------------------------*/
	
	/**
	 * Give the character's name
	 * @return NAME (String)
	 */
	public String getName() {
		return this.NAME;
	}
	
	/**
	 * Give the character's description/history
	 * @return description (String)
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Give the character's location
	 * @return location (Location)
	 */
	public Location getLocation() {
		return this.loc;
	}
	
	/**
	 * Give the character's health points
	 * @return hp (int)
	 */
	public int getHp() {
		return this.hp;
	}
	
	/**
	 * Verify if the character is dead
	 * @return isDead (boolean)
	 */
	public boolean isDead() {
		return this.hp == 0;
	}
	
	/**
	 * Changes the character's hp according to damage suffered
	 * @param damage (int) : the number of damages suffered 
	 */
	public void getAttacked(int damage) {
		this.hp -= damage;
		if (this.hp < 0) {
			this.hp = 0;
		}		
	}
	
	/**
	 * Return the experience obtain after kill this character
	 * @return the experience obtain
	 */
	public int getXpDropped() {
		return this.xpDropped;
	}

	/*------------------------------------------------------------------------------------
	 * 
	 * Setters
	 * 
	 ------------------------------------------------------------------------------------*/
	
	/**
	 * Changes the character's description
	 * @param description (String)
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Define the hero for the class
	 * @param hero (Hero)
	 */
	public static void setHero(Hero hero) {
		Character.hero = hero;
	}
}
