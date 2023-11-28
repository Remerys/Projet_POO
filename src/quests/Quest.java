package quests;
import java.util.HashMap;

/**
 * The abstract class of quests
 * @author Lilian
 */
public abstract class Quest {
	private final String NAME;
	private String description;
	private HashMap <String, QuestStatus> steps = new HashMap <String, QuestStatus>();
	private QuestStatus state;
	protected boolean isQuestFinished = false;

	public abstract void updateQuest() throws Exception;;
	
	public Quest(String name, String description) {
		this.NAME = name;
		this.description = description;
		this.state = QuestStatus.Todo;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isQuestFinished() {
		return this.isQuestFinished;
	}

	private String printState(QuestStatus state) {
		switch (state) {
			case Todo:
				return "Todo";
			case Ongoing:
				return "Ongoing";
			case Done:
				return "Done";
			default:
				return "Not Evaluate";
		}
	}

	public void printQuest() {
		System.out.println(this.NAME + " : " + printState(this.state));
		System.out.println(this.description);
		System.out.println("Steps : ");
		this.steps.forEach((key, value) -> {
		      System.out.println("\t- " + key + " : " + printState(value));
		    }
		);
	}

	public void addStep(String name) {
		this.steps.put(name, QuestStatus.Todo);
	}

	public void OngoingQuest() {
		this.state = QuestStatus.Ongoing;
	}

	public void DoneQuest() {
		this.state = QuestStatus.Done;
	}

	public void OngoingStep(String name) {
		this.steps.put(name, QuestStatus.Ongoing);
	}

	public void DoneStep(String name) {
		this.steps.put(name, QuestStatus.Done);
	}
}
