package characters;

import locations.Location;

/**
 * An abstract class representing all the characters that can't attack the
 * <br>But can be attacked by the hero
 * @author Lilian
 */
public abstract class NPC extends Character implements Talker{

	private static int XP_DROPPED = 0;

	public NPC(String name, Location loc, int maxHp) {
		super(name, loc, maxHp, NPC.XP_DROPPED);
	}

	@Override
	public boolean hasFinishedToTalk() {
		return this.hasFinishedToTalk;
	}

	@Override
	public void finishedToTalk() {
		// TODO Auto-generated method stub
		this.hasFinishedToTalk = true;
	}
}
