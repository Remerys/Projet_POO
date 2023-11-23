package characters;

import locations.Location;

/**
 *  The ork is a beast of brute force and hp, but it's very slow.
 *  @author Lilian
 */
public class Ork extends Villain {

	private static final int XP_DROPPED = 50;

	public Ork(Location loc) {
		super("Ork", loc, Character.DEFAULT_MAX_HP/2, Ork.XP_DROPPED, Villain.MAX_DAMAGE/10, 1);
		this.setDescription("The ork is a beast of brute force and hp, but it's very slow.");
	}
}
