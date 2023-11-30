package quests;

import java.util.HashMap;

/**
 * The abstract class of quests
 * 
 * @author Lilian
 */
public abstract class Quest {
	private final String NAME;
	private String description;
	private HashMap<String, Quest> steps = new HashMap<String, Quest>();
	private boolean isQuestFinished = false;
	private boolean hasSteps = false;

	public abstract void updateQuest() throws Exception;;

	public Quest(String name, String description) {
		this.NAME = name;
		this.description = description;
	}

	public String getName() {
		return this.NAME;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Verify if the quest is finised
	 *
	 * @return (boolean) : if the quest is finished
	 */
	public boolean isQuestFinished() {
		return this.isQuestFinished;
	}

	/**
	 * Add a step quest
	 *
	 * @param quest (Quest)
	 */
	public void addStep(Quest quest) {
		this.hasSteps = true;
		this.steps.put(quest.getName(), quest);
	}

	public void done() {
		this.steps.forEach((key, value) -> {
			value.done();
		});
		this.isQuestFinished = true;
	}

	public void doneStep(String name) {
		this.steps.get(name).done();
	}

	public void printQuest() {
		System.out.println(this.NAME + " :");
		System.out.println(this.description);
		if (this.hasSteps) {
			System.out.println("Steps : ");
			this.steps.forEach((key, value) -> {
				System.out.println("\t- " + key + " : ");
				value.printQuest();
			});
		}
	}
}
