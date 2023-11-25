package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import characters.*;
import characters.Character;
import items.Item;
import items.Sword;
import locations.Location;
import quests.MainQuest;
//import quests.Quest;

/**
 * The principal game
 *
 * @author Romain
 * @author Lilian
 */
public class Game {
    public Hero hero;
    private List<Location> locations = new ArrayList<Location>(); //TODO remove
    private List<Character> characters = new ArrayList<Character>(); //same
    private List<Fighter> fighters = new ArrayList<Fighter>(); //Same
    private List<NPC> npcs = new ArrayList<NPC>(); //same
    private MainQuest mainQuest;
    public static final String SEPARATION = "--------------------------------------------------------------------";

    public Game() {
        // Location.createLocations();
        this.init();
    }

    public void init() {
        Command.setGame(this);

        // String name = Command.getName();

        this.hero = Hero.createHero("Player", null);
        Character.setHero(this.hero);

        this.mainQuest = new MainQuest();

        // Command.handleCommands();
    }

    //To move in Location
    private Character getCharacter(String character) {// , Location loc) {
        this.characters.add(Diogene.getDiogene());
        this.characters.add(new Healer());

        for (Character c : this.characters) {
            if (c.getName().toUpperCase().equals(character.toUpperCase())) {
                return c;
            }
        }
        return null;
    }

    private void printSeparation() {
        System.out.println(Game.SEPARATION);
    }

    private NPC getNpc(String npcName, Location loc) {
        this.npcs.add(new Healer());
        this.npcs.add(Diogene.getDiogene());

        for (NPC npc : this.npcs) {
            if (npc.getName().toUpperCase().equals(npcName.toUpperCase())) {
                return npc;

            }
        }
        return null;
    }

    private Fighter getFighter(String enemyName, Location loc) {
        // TODO Initialisation des locs :
        // il faudrait normalement le faire à chaque fois qu'on se déplace d'une map à
        // une autre avec des fonctions du style
        // this.characters = loc.getCharacters();
        // this.enemys = this.characters.getEnemys();
        // fin initialisation locs

        /* TEST VU QUE J'AI PAS LES LISTES POUR L'INSTANT */
        this.fighters.add(new Crab()); // A SUPPRIMER QUAND ON AURA LES LISTES
        this.fighters.add(new RabbitOfCaerbannog()); // A SUPPRIMER QUAND ON AURA LES LISTES
        this.fighters.add(new Crab()); // A SUPPRIMER QUAND ON AURA LES LISTES
        this.fighters.add(new RabbitOfCaerbannog()); // A SUPPRIMER QUAND ON AURA LES LISTES

        for (Fighter fighter : this.fighters) {
            if (fighter.getName().toUpperCase().equals(enemyName.toUpperCase())) {
                return fighter;
            }
        }
        return null;
    }

    /*
     * -----------------------------------------------------------------------------
     * ----------------------------------------------
     */
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

    public void attack(String enemyName) {
        Fighter fighterAttacked = this.getFighter(enemyName, hero.getLocation());

        if (fighterAttacked != null) {
            int turnNumber = 0;
            System.out.println(hero.getName() + " attacks " + fighterAttacked.getName() + ":\n");
            System.out.println("--------------------------------------------------------------------");

            while (fighterAttacked.getHp() != 0 & this.hero.getHp() != 0) {
                System.out.println("Turn " + turnNumber);
                turnNumber++;
                Fighter attacker;
                Fighter attacked;

                if (hero.getSpeed() > fighterAttacked.getSpeed()) {
                    attacker = this.hero;
                    attacked = fighterAttacked;
                } else {
                    attacker = fighterAttacked;
                    attacked = this.hero;
                }

                System.out.println(attacker.getName() + " attacks first :");
                attack_aux(attacker, attacked);

                /*
                 * -----------------------------------------------------------------------------
                 * ---------------------------
                 */
                if (attacked.getHp() != 0) {
                    attack_aux(attacked, attacker);
                }
                System.out.println("--------------------------------------------------------------------");
                printSeparation();
            }

            if (fighterAttacked.getHp() != 0) {
                fighterAttacked.fullyHeals();
            }
        } else {
            System.out.println("This character doesn't exist");
        }
    }

    private void attack_aux(Fighter attacker, Fighter attacked) {
        System.out.print(attacked.getName() + " takes " + attacker.getDamage() + " damage ");
        attacker.attack(attacked);
        System.out.println("| HP remaining :" + attacked.getHp());
    }

    public void talk(String character) {
    	NPC npcTalked = this.getNpc(character, hero.getLocation());

        if (npcTalked != null) {
            npcTalked.startsTalking();
            printSeparation();

            Scanner scanner = new Scanner(System.in);
            System.out.println(hero.getName() + " talks to " + character);

            System.out.println(npcTalked.talk(null));

            while (!npcTalked.hasFinishedTalking()) {
                System.out.print("What's your answer ? ");
                String answer = scanner.nextLine();
                System.out.println(npcTalked.talk(answer));
            }
            scanner.close();
            printSeparation();
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

    public void addXp(int xp) { // to test
        System.out.println("Gain d'xp :" + xp);
        System.out.println();
        this.hero.addXp(xp);
    }

    public void addSword() {
        System.out.println("add Sword");
        try {
            this.hero.addItem(new Sword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void use(String itemName) {
        System.out.println("Utilisation de : " + itemName);
        Item itemUsed = this.hero.getItem(itemName);
        try {
			this.hero.useItem(itemUsed);
		} catch (Exception e) {
			System.out.println("This item isn't usable.");
		}
    }
}
