package characters;

import locations.Location;

/**
 * A odd person in a barrel
 * @author Lilian
 */
public class Diogene extends NPC {

	private static Diogene instance = null;

	private Diogene(Location loc) {
		super("Diogene", loc,  1);
		this.setDescription("A odd person in a barrel.");
	}

	public static Diogene getDiogene(Location loc) {
		//singleton
		if (instance == null) {
			instance = new Diogene(loc);
		}
		return instance;
	}

	@Override
	public String talk(String choice) {
		super.stopsTalking();
		return "Get out of my sun!";
	}
}
