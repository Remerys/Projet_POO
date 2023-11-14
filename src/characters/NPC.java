package characters;

/**
 * An abstract class representing all the characters that can't attack the 
 * <br>But can be attacked by the hero
 */
abstract class NPC extends Character implements Talker{

	public NPC(String name, int hp) {
		super(name, hp);
	}

}
