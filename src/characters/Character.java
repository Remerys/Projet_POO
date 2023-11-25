package characters;

/**
 * An abstract class representing all the characters in the world except the
 * hero
 *
 * @author Lilian
 */
public abstract class Character {
	/**
	 * DEFAULT_MAX_HP (int) : 500
	 */
	public static final int DEFAULT_MAX_HP = 500;

	protected final String NAME;
	private String description;

	protected boolean hasFinishedToTalk;

	public Character(String name, String description) {
		this.NAME = name;
		this.description = name;
		this.hasFinishedToTalk = false;
	}

	public Character(String name) {
		this(name, "");
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

	/*------------------------------------------------------------------------------------
	 *
	 * Getters
	 *
	 ------------------------------------------------------------------------------------*/

	/**
	 * Give the character's name
	 *
	 * @return NAME (String)
	 */
	public String getName() {
		return this.NAME;
	}

	/**
	 * Give the character's description/history
	 *
	 * @return description (String)
	 */
	public String getDescription() {
		return this.description;
	}

	/*------------------------------------------------------------------------------------
	 *
	 * Setters
	 *
	 ------------------------------------------------------------------------------------*/

	/**
	 * Changes the character's description
	 *
	 * @param description (String)
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
