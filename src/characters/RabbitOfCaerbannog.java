package characters;

import locations.Location;

/**
 * RabbitOfCaerbannog is a reference to The Holy Grail by Monty Python
 * <br> It's a cute rabbit, but very dangerous
 * <br> Should be kill in the first turn, else the hero die
 * @author Lilian
 */
public class RabbitOfCaerbannog extends Villain {
	
	private static final int XP_DROPPED = 1000;

	public RabbitOfCaerbannog(Location loc) {
		super("Rabbit Of Caerbannog", loc, 1, Villain.MAX_DAMAGE, RabbitOfCaerbannog.XP_DROPPED , Villain.MAX_SPEED);
		this.setDescription("A cute rabbit surrounded by corpses.");
	}
}
