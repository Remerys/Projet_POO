package characters;

import java.util.regex.Pattern;

import hero.Hero;
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
		String string = "";

		if (choice == null) {
			string = "Do you want to be healed ? (yes/no)";
		} else {
			if (Pattern.matches("[Yy][Ee][Ss]", choice)) {
				this.heal();
				string = "I'm healing you";
			} else if (Pattern.matches("[Nn][Oo]", choice)) {
				string = "Oh ... ok";
			} else {
				string = "What are you talking about ?";
			}
			super.stopsTalking();
		}

		return string;
	}

	public void heal() {
		try {
			hero = Hero.getHero();
			hero.heal(hero.getMaxHp());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
