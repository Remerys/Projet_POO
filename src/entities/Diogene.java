package entities;

/**
 * A odd person in a barrel
 *
 * @author Lilian
 */
public class Diogene extends NPC {

	private static Diogene instance = null;

	private Diogene() {
		super("Diogene");
		this.setDescription("A odd person in a barrel.");
	}

	public static Diogene getDiogene() {
		// singleton
		if (Diogene.instance == null) {
			Diogene.instance = new Diogene();
		}
		return Diogene.instance;
	}

	@Override
	public String talk() {
		this.finishedToTalk();
		return "Get out of my sun!";
	}

}
