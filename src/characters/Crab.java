package characters;

/**
 * Crabs have eight legs and two claws, varying in size depending on the
 * species, two eyes and a carapace. They move by walking on their side.
 * 
 * @author Lilian
 */
public class Crab extends Fighter {

	private static final int XP_DROPPED = 10;

	public Crab() {
		super("Crab", Character.DEFAULT_MAX_HP / 50, Crab.XP_DROPPED, Fighter.MAX_DAMAGE / 200, 2);
		this.setDescription(
				"Crabs have eight legs and two claws, varying in size depending on the species, two eyes and a carapace. They move by walking on their side.");
	}

}
