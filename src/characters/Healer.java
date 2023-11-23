package characters;

import java.util.regex.Pattern;

import locations.Location;

/**
 * A NPC who can heal the hero
 * @author Lilian
 */
public class Healer extends NPC {

	public Healer(Location loc) {
		super("Healer", loc, Character.DEFAULT_MAX_HP/50);
		this.setDescription("A cute cosplayer with a wood stick.");
	}

	@Override
	public String talk(String choice) {
		if (choice == null) {
			return "Do you want to be healed ? (yes/no)";
		}
		else {
			if (Pattern.matches("[Yy][Ee][Ss]", choice)) {
				this.heal();
				return "I'm healing you";
			} else if (Pattern.matches("[Nn][Oo]", choice)) {
				return "Oh ... ok";
			} else {
				this.finishedToTalk();
				return "What are you talking about ?";
			}
		}
	}

	public void heal() {
		Character.hero.heal(Character.hero.getMaxHp());
	}

}
