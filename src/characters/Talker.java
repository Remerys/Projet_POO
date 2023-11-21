package characters;

/**
 * An interface allowing the characters to talk
 * @author Lilian
 */
public interface Talker {
	
	/**
	 * Determine the text to return in function of the choice
	 * @param choice (String)
	 * @return (String) : the text talked
	 */
	public String talk(String choice);
	
	public boolean hasFinishedToTalk();
	
	public void finishedToTalk();
}
