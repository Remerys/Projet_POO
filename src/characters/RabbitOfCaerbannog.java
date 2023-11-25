package characters;

/**
 * RabbitOfCaerbannog is a reference to The Holy Grail by Monty Python
 * <br>
 * It's a cute rabbit, but very dangerous
 * <br>
 * Should be kill in the first turn, else the hero die
 *
 * @author Lilian
 */
public class RabbitOfCaerbannog extends Fighter {

	private static final int XP_DROPPED = 1000;

	public RabbitOfCaerbannog() {
		super("Rabbit Of Caerbannog", 1, Fighter.MAX_DAMAGE, RabbitOfCaerbannog.XP_DROPPED, Fighter.MAX_SPEED);
		this.setDescription("A cute rabbit surrounded by corpses.");
	}
}
