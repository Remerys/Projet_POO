package characters;

/**
 * A NPC who can heal the hero
 * 
 * @author Lilian
 */
public class Healer extends NPC {

	public Healer() {
		super("Healer");
		this.setDescription("A cute cosplayer with a wood stick.");
	}
	
	public String talk() {
		return "Do you want to be healed ? (yes/no)";
	}
	
	@Override
	public String talk(String choice) {
		this.finishedToTalk();
		if ("YES".equals(choice.toUpperCase())) {
			this.heal();
			return "I'm healing you";
		} else if ("NO".equals(choice.toUpperCase())) {
			return "Oh ... ok";
		} else {
			return "What are you talking about ?";
		}
	}

	public void heal() {
		try {
			Hero hero = Hero.getHero();
			hero.heal(hero.getMaxHp());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
