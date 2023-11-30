package entities;

/**
 * An abstract class representing all the characters that can't attack the
 * <br>
 * But can be attacked by the hero
 *
 * @author Lilian
 */
public abstract class NPC extends Entity implements Talker {

	public NPC(String name) {
		super(name);
	}

	public boolean hasFinishedToTalk() {
		return this.hasFinishedToTalk;
	}

	public void finishedToTalk() {
		this.hasFinishedToTalk = true;
	}

	public void resetTalkState() {
		this.hasFinishedToTalk = false;
	}

	public String talk(String choice) {
		return talk();
	}
}
