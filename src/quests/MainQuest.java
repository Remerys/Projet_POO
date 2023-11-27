package quests;

/**
 * The main quest of the game
 * @author Lilian
 */
public class MainQuest extends Quest {

	public MainQuest() {
		super("Main Quest", "");
		this.setDescription("Your goal is to ...");
		this.addStep("Kill a rabbit");
	}
}
