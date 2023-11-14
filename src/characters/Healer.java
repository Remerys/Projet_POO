package characters;

import java.util.regex.Pattern;

import hero.Hero;


/**
 * A NPC who can heal the hero
 */
public class Healer extends NPC {
	
	public Healer() {
		super("Healer", Character.MAX_HP/50);
		this.setDescription("A cute cosplayer with a wood stick.");
	}

	@Override
	public String talk(Hero hero, String choice) {
		if (choice == null) {
			return "Do you want to be healed ? (yes/no)";
		}
		else {
			if (Pattern.matches("[Yy][Ee][Ss]", choice)) {
				this.heal(hero);
				return "I'm healing you";
			} else if (Pattern.matches("[Nn][Oo]", choice)) {
				return "Oh ... ok";
			} else {
				return "What are you talking about ?";
			}
		}
	}
	
	public void heal(Hero hero) {
		hero.setHp(Hero.MAX_HP);
	}

}
