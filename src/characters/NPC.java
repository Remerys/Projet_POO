package characters;

/**
 * An abstract class representing all the characters that can't attack the 
 * <br>But can be attacked by the hero
 */
abstract class NPC extends Character implements Talker{

	public NPC(String name, String description, int hp) {
		super(name, description, hp);
	}

}
