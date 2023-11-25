package characters;
/**
 * A NPC who can heal the hero
 * @author Lilian
 */
public class Healer extends NPC {

	public Healer() {
		super("Healer");
		this.setDescription("A cute cosplayer with a wood stick.");
	}

	@Override
	public String talk(String choice) {
		if (choice == null) {
			return "Do you want to be healed ? (yes/no)";
		} else {
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
	}

	public void heal() {
		Character.hero.heal(Character.hero.getMaxHp());
	}

}
