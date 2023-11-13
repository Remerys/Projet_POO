package characters;

/**
 * An abstract class representing all the characters in the world except the hero
 */
abstract class Character {
	public static int MAX_HP = 500;
	
	private final String NAME;
	private String description;
	private int hp;
	
	public Character(String name, String description, int hp) {
		this.NAME = name;
		this.description = name;
		this.hp = hp;
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
}
