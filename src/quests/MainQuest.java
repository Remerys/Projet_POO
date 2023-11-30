package quests;

import entities.Hero;
import locations.Location;

/**
 * The main quest of the game
 * @author Lilian
 */
public class MainQuest extends Quest {
	
	private Location lastLocation;

	public MainQuest(Location lastLocation) {
		super("Main Quest", "Your goal is to return in your home");
		this.lastLocation = lastLocation;
	}

	@Override
	public void updateQuest() throws Exception {
		if (Hero.getHero().getLocation().equals(this.lastLocation))
			this.done();
	}
}
