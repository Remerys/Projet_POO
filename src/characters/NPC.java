package characters;

/**
 * An abstract class representing all the characters that can't attack the
 * <br>
 * But can be attacked by the hero
 * 
 * @author Lilian
 */
public abstract class NPC extends Character implements Talker {

	public NPC(String name) {
		super(name);
	}

	@Override
	public boolean hasFinishedToTalk() {
		return this.hasFinishedToTalk;
	}

	@Override
	public void finishedToTalk() {
		this.hasFinishedToTalk = true;
	}

	@Override
	public void resetTalkState() {
		this.hasFinishedToTalk = false;
	}
	
	@Override
	public String talk(String choice) {
		return talk();
	}
}
