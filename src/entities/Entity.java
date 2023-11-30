package entities;

/**
 * An abstract class representing all the characters in the world except the
 * hero
 *
 * @author Lilian
 */
public abstract class Entity {

	protected final String NAME;
	private String description;

	protected boolean hasFinishedToTalk;

	public Entity(String name, String description) {
		this.NAME = name;
		this.description = name;
		this.hasFinishedToTalk = false;
	}

	public Entity(String name) {
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
		String description = "NAME : " + this.NAME + "\nDESCRIPTION : " + this.description;
		return description;
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
