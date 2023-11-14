package characters;

/**
 *  The ork is a beast of brute force and hp, but it's very slow.
 */
public class Ork extends Vilain {

	public Ork() {
		super("Ork", Character.MAX_HP/2, Vilain.MAX_DAMAGE/10, 1);
		this.setDescription("The ork is a beast of brute force and hp, but it's very slow.");
	}
}
