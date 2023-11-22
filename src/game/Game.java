package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import characters.Character;
import characters.Diogene;
import characters.Ork;
import characters.RabbitOfCaerbannog;
import characters.Talker;
import characters.Villain;
import hero.Hero;
import items.Sword;
import locations.Location;
import quests.MainQuest;
import quests.Quest;

/**
 * The principal game
 *
 * @author Romain
 * @author Lilian
 */
public class Game {
    private Hero hero;
    private List<Location> locations = new ArrayList<Location>();
    private List<Character> characters = new ArrayList<Character>();
    private List<Villain> villains = new ArrayList<Villain>();
    private MainQuest mainQuest;

    public Game() {
        // Location.createLocations();
        this.init();
    }

    public void init() {
    	Command.setGame(this);

    	// String name = Command.getName();

    	this.hero = Hero.createHero("Player", null);

    	this.mainQuest = new MainQuest();

        Command.handleCommands();
    }

    private Character getCharacter(String character, Location loc) {
        for (Character c : this.characters) {
            if (c.getName().toUpperCase().equals(character.toUpperCase())) {
                return c;
            }
        }
        return null;
    }

    private Villain getVillain(String villainName, Location loc) {
        // TODO Initialisation des locs :
        // il faudrait normalement le faire à chaque fois qu'on se déplace d'une map à une autre avec des fonctions du style
        // this.characters = loc.getCharacters();
        // this.villains = this.characters.getVillains();
        // fin initialisation locs


        /* TEST VU QUE J'AI PAS LES LISTES POUR L'INSTANT */
        this.villains.add(new Ork(loc));                   // A SUPPRIMER QUAND ON AURA LES LISTES
        this.villains.add(new RabbitOfCaerbannog(loc));      // A SUPPRIMER QUAND ON AURA LES LISTES
        this.villains.add(new Ork(loc));                     // A SUPPRIMER QUAND ON AURA LES LISTES
        this.villains.add(new RabbitOfCaerbannog(loc));      // A SUPPRIMER QUAND ON AURA LES LISTES

        for (Villain villain : this.villains) {
            if (villain.getName().toUpperCase().equals(villainName.toUpperCase())) {
                return villain;
            }
        }
        return null;
    }

    /* --------------------------------------------------------------------------------------------------------------------------- */
    /* ALL COMMANDS */

    public void displayAvailableCommands() {
        System.out.println("List of available commands :\n");
        System.out.println("/help - Displays the list of available commands.");
        System.out.println("/heal - Heal the Hero.");
        System.out.println("/inventory - Display the inventory.");
        System.out.println("/go <Direction> - Go to a direction (North, East, South, West).");
        System.out.println("/attack <Character name> - Attack a character if possible.");
        System.out.println("/talk <Character name> - Talk to a character if possible.");
        System.out.println("/stop - Stop the game.");
        System.out.println("/stats - Display the statistics of the hero.");
        System.out.println("/quests - Display the list of available quests.");
        System.out.println("/quest <Quest name> - Display a specific quest with more information.");
        System.out.println();
    }

    public void displayInventory() {
        System.out.println("INVENTORY :");
        this.hero.printInventory();
    }

    public void heal() {
        if (hero.hasPotion()) {
            System.out.println(hero.getName() + " heals himself");
            hero.getPotion().use();
        }
    }

    public void goTo(String location) {
        System.out.println(hero.getName() + " go to " + location);
    }

    public void attack(String villainName) {
        Villain villainAttacked = this.getVillain(villainName, hero.getLocation());

        if (villainAttacked != null) {
            int turnNumber = 0;
            System.out.println(hero.getName() + " attacks " + villainAttacked.getName() + ":\n");
            System.out.print("--------------------------------------------------------------------");

            while (villainAttacked.getHp() != 0 & this.hero.getHp() != 0) {
                System.out.println("Turn " + turnNumber);
                turnNumber++;

                if (hero.getSpeed() > villainAttacked.getSpeed()) {
                    System.out.println(hero.getName() + " attacks first :");

                    System.out.print(villainAttacked.getName() + " takes " + hero.getDamage() + " damage ");
                    this.hero.attack(villainAttacked);
                    System.out.println("| HP remaining :" + villainAttacked.getHp());

                    /* -------------------------------------------------------------------------------------------------------- */

                    System.out.print(hero.getName() + " takes " + villainAttacked.getDamage() + " damage ");
                    villainAttacked.attack(this.hero);
                    System.out.println("| HP remaining :" + hero.getHp());
                } else {
                    System.out.println(villainAttacked.getName() + " attacks first :");

                    System.out.println(hero.getName() + " takes " + villainAttacked.getDamage() + " damage ");
                    this.hero.attack(villainAttacked);
                    System.out.println("| HP remaining :" + hero.getHp());

                    /* -------------------------------------------------------------------------------------------------------- */

                    System.out.print(villainAttacked.getName() + " takes " + hero.getDamage() + " damage ");
                    villainAttacked.attack(this.hero);
                    System.out.println("| HP remaining :" + villainAttacked.getHp());
                }

                System.out.println("--------------------------------------------------------------------");
            }

            if (villainAttacked.getHp() != 0) {
                villainAttacked.fullyHeals();
            }
        } else {
            System.out.println("This character doesn't exist");
        }
    }

    public void talk(String character) {
    	Character characterTalked = this.getCharacter(character, hero.getLocation());
    	if (characterTalked != null) {
    		Scanner scanner = new Scanner(System.in);
    		System.out.println(hero.getName() + " talk to " + character);

    		Talker talker = (Talker) characterTalked;
    		talker.talk(null);
    		while (!talker.hasFinishedToTalk()) {
    			System.out.print("What's your answer ? ");
                String answer = scanner.nextLine();
                talker.talk(answer);
    		}
    		//Should put FinishedToTalk to false here
    	} else {
    		System.out.println("This character doesn't exist");
    	}
    }

    public void stop() {
        System.out.println("GAME STOP");
        System.exit(0);
    }

    public void stats() {
        System.out.println("STATISTICS :");
        this.hero.printStats();
    }

    public void quests() {
        System.out.println("List of available quests :");
        System.out.println();
        this.mainQuest.printQuest();
    }

    public void quest(String questName) {
        System.out.println("Details of : " + questName);
    }

    public void addXp(int xp) { //to test
    	System.out.println("Gain d'xp :" + xp);
    	System.out.println();
    	this.hero.addXp(xp);
    }

    public void addSword() {
    	System.out.println("add Sword");
    	try {
			this.hero.addItem(new Sword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
