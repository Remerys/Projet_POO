package characters;

/**
 * An abstract class representing all the characters that can't attack the 
 * <br>But can be attacked by the hero
 */
abstract class NPC extends Character implements Talker{
	
	private static int XP_DROPPED = 0;

	public NPC(String name, int hp) {
		super(name, hp, NPC.XP_DROPPED);
	}

}
