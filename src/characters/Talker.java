package characters;

import hero.Hero;

/**
 * An interface allowing the characters to talk
 */
interface Talker {
	
	/**
	 * Determine the text to return in function of the choice
	 * @param hero (Hero) : the hero in the game
	 * @param choice (String)
	 * @return (String) : the text talked
	 */
	public String talk(String choice);
}
