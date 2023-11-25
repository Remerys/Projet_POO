package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import characters.*;
import items.Flute;
import items.HealPotion;
import items.Item;
import items.Sword;
import locations.Location;
import quests.MainQuest;
//import quests.Quest;

/**
 * The main game
 *
 * @author Romain
 * @author Lilian
 */
public class Game {
    public Hero hero;
    private List<Location> locations = new ArrayList<Location>();
    private List<Item> items = new ArrayList<Item>();
    private List<Fighter> fighters = new ArrayList<Fighter>();
    private List<Talker> talkers = new ArrayList<Talker>();
    private MainQuest mainQuest;
    public static final String SEPARATION = "--------------------------------------------------------------------";
    public static final Scanner SCANNER = new Scanner(System.in);

    public Game() {
        this.init();
    }

    public void init() {
        Command.setGame(this);

        this.locations = Location.createGameLocations();
        Location startLocation = this.locations.get(0);

        // String playerName = Command.getName(); // TODO Remettre à la fin
        // this.hero = Hero.createHero(playerName, startLocation); // TODO Remettre à la fin

        this.hero = Hero.createHero("Player", startLocation); // TODO Enlever à la fin
        Item flute = new Flute();
        try {
            this.hero.addItem(flute);
        } catch (Exception e) {
            System.out.println("ERROR addItem(flute)");
        }

        this.mainQuest = new MainQuest();

        Command.handleCommands();
    }

    public static void printSeparation() {
        System.out.println(Game.SEPARATION);
    }

    private Talker getTalker(String talkerName) {
        for (Talker talker : this.talkers) {
            if (talker.toString().toUpperCase().equals(talkerName.toUpperCase())) {
                return talker;
            }
        }
        return null;
    }

    private Fighter getFighter(String enemyName) {
        for (Fighter fighter : this.fighters) {
            if (fighter.getName().toUpperCase().equals(enemyName.toUpperCase())) {
                return fighter;
            }
        }
        return null;
    }

    /* --------------------------------------------------------------------------------------------------------------------------- */
    /* ALL COMMANDS */

    public void displayAvailableCommands() {
        System.out.println("List of available commands :");
        Game.printSeparation();
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
        System.out.println("/use <item name> - Use an item.");
        System.out.println("/map - Displays information from the current map.");
    }

    public void displayInventory() {
        this.hero.printInventory();
    }

    public void heal() {
        if (hero.hasPotion()) {
            System.out.println(hero.getName() + " heals himself");
            hero.getPotion().use();
        } else {
            System.out.println("No potions available");
        }
    }

    public void goTo(String locationName) {
        try {
            Location location = this.hero.getLocation().exitTo(locationName);
            System.out.println(hero.getName() + " go to " + locationName);

            this.hero.setLocation(location);
            this.items = location.getItems();
            this.fighters = location.getFighters();
            this.talkers = location.getTalkers();
        } catch (Exception e) {
            System.out.println("You can't access this map OR it doesn't exist : " + locationName);
        }
    }

    public void attack(String enemyName) {
        Fighter fighterAttacked = this.getFighter(enemyName);

        if (fighterAttacked != null) {
            int turnNumber = 0;
            System.out.println(hero.getName() + " attacks " + fighterAttacked.getName() + " :");

            while (fighterAttacked.getHp() != 0 & this.hero.getHp() != 0) {
                Game.printSeparation();
                System.out.println("Turn " + turnNumber);
                Game.printSeparation();
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

                /* -------------------------------------------------------------------------------------------------------- */

                if (attacked.getHp() != 0) {
                    attack_aux(attacked, attacker);
                }
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
    	Talker npcTalked = this.getTalker(character);

        if (npcTalked != null) {
            npcTalked.resetTalkState();

            System.out.println(hero.getName() + " talks to " + character);

            Game.printSeparation();

            System.out.println(npcTalked.talk());

            while (!npcTalked.hasFinishedToTalk()) {
                System.out.print("What's your answer ? ");
                String answer = SCANNER.nextLine();
                System.out.println(npcTalked.talk(answer));
            }

        } else {
            System.out.println("This character doesn't exist");
        }
    }

    public void stop() {
        System.out.println("GAME STOP");
        Game.SCANNER.close();
        System.exit(0);
    }

    public void stats() {
        System.out.println("STATISTICS :");
        Game.printSeparation();
        this.hero.printStats();
    }

    public void quests() {
        System.out.println("List of available quests :");
        Game.printSeparation();
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
        System.out.println("Use of : " + itemName);

        Item itemUsed = this.hero.getItem(itemName);
        try {
			this.hero.useItem(itemUsed);
		} catch (Exception e) {
			System.out.println("This item isn't usable.");
		}
    }

    public void map() {
        Location location = this.hero.getLocation();
        System.out.println("Map : " + location.getName());

        String locationDescription = location.getDescription();
        System.out.println("A short description : " + locationDescription + "\n");

        // printList("List of maps you can go :", location.getExits()); // TODO Remettre quand la fonction getExits existera
        printList("List of items in this map :", this.items);
        printList("List of mobs in this map :", this.fighters);
        printList("List of NPCs you can talk to in this map :", this.talkers);

    }

    private void printList(String title, List<?> list) {
        System.out.println(title);

        if (list.isEmpty()) {
            System.out.println("\t" + "None");
        } else {
            list.forEach(object -> System.out.println("\t" + object));
        }
    }
}
