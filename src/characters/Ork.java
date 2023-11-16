package characters;

/**
 *  The ork is a beast of brute force and hp, but it's very slow.
 */
public class Ork extends Vilain {
	
	private static final int XP_DROPPED = 50; 

	public Ork() {
		super("Ork", Character.MAX_HP/2, Ork.XP_DROPPED, Vilain.MAX_DAMAGE/10, 1);
		this.setDescription("The ork is a beast of brute force and hp, but it's very slow.");
	}
}
