package characters;

/**
 * An abstract class representing all the characters that can't attack the
 * <br>But can be attacked by the hero
 * @author Lilian
 */
public abstract class NPC extends Character implements Talker{

	public NPC(String name) {
		super(name);
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
