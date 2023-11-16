package characters;

/**
 * A odd person in a barrel
 */
public class Diogene extends NPC {

	private static Diogene instance = null;
	
	private Diogene() {
		super("Diogene", 1);
		this.setDescription("A odd person in a barrel.");
	}

	public static Diogene getDiogene() {
		//singleton
		if (instance == null) {
			instance = new Diogene();
		}
		return instance;
	}
	
	@Override
	public String talk(String choice) {
		return "Get out of my sun!";
	}
}
