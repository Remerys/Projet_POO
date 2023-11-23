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
	public boolean hasFinishedTalking() {
		return this.hasFinishedToTalk;
	}

	@Override
	public void stopsTalking() {
		// TODO Auto-generated method stub
		this.hasFinishedToTalk = true;
	}

	@Override
	public void startsTalking() {
		// TODO Auto-generated method stub
		this.hasFinishedToTalk = false;
	}
}
