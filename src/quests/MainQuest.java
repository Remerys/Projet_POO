package quests;

import characters.Hero;
import locations.Location;

/**
 * The main quest of the game
 * @author Lilian
 */
public class MainQuest extends Quest {
	
	private Location lastLocation;

	public MainQuest(Location lastLocation) {
		super("Main Quest", "");
		this.setDescription("Your goal is to ...");
		this.addStep("Kill a rabbit");
		this.lastLocation = lastLocation;
	}

	@Override
	public void updateQuest() throws Exception {
		this.isQuestFinished = Hero.getHero().getLocation().equals(this.lastLocation);
	}
}
