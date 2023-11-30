package entities;

/**
 * An interface allowing the characters to talk
 *
 * @author Lilian
 */
public interface Talker {

	/**
	 * Determine the text to return in the first interact
	 *
	 * @return (String) : the text talked
	 */
	public String talk();

	/**
	 * Determine the text to return in function of a choice
	 *
	 * @param choice (String)
	 * @return (String) : the text talked
	 */
	public String talk(String choice); // Apporter une réponse

	/**
	 * Verify if the talker has finished to talk
	 *
	 * @return (boolean) : has finished to talk
	 */
	public boolean hasFinishedToTalk();

	/**
	 * Set the talker to finished talk.
	 */
	public void finishedToTalk();

	/**
	 * Reset the status of talker to be able to retalk
	 */
	public void resetTalkState();
}
