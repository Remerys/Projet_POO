package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Fighter;
import entities.Hero;
import entities.NPC;
import entities.Talker;
import items.Flute;
import items.HealthPotion;
import items.Item;
import items.Sword;
import items.Weapon;
import locations.Location;
import quests.MainQuest;

/**
 * The main game
 *
 * @author Romain
 * @author Lilian
 */
public class Game {
    /**
     * Le joueur
     */
    public Hero hero;
    /**
     * Liste des maps
     */
    private List<Location> locations = new ArrayList<Location>();
    /**
     * Liste des items de la map actuelle
     */
    private List<Item> items = new ArrayList<Item>();
    /**
     * Liste des fighters de la map actuelle
     */
    private List<Fighter> fighters = new ArrayList<Fighter>();
    /**
     * Liste des talkers de la map actuelle
     */
    private List<Talker> talkers = new ArrayList<Talker>();
    private MainQuest mainQuest;
    /**
     * Seul scanner permettant de récupérer ce que le joueur écrit
     */
    public static final Scanner SCANNER = new Scanner(System.in);

    public Game() {
        this.init();
    }

    private void init() {
        Command.setGame(this);

        this.locations = Location.createGameLocations();
        Location startLocation = this.locations.get(1);

        String playerName = Game.setPseudo();
        this.hero = Hero.createHero(playerName, startLocation);

        Item flute = new Flute();
        Weapon sword = new Sword();
        HealthPotion healthPotion = new HealthPotion();
        try {
            this.hero.addItem(flute);
            this.hero.addItem(sword);
            this.hero.addItem(healthPotion);
        } catch (Exception e) {
            System.out.println("ERROR addItem(flute)");
        }

        this.mainQuest = new MainQuest(this.locations.get(0));
    }

    public static void start() {
        Command.handleCommands();
    }

    /**
     * Permet au joueur d'entrer son nom de personnage
     */
    private static String setPseudo() {
        String name = "";

        while (name == "") {
            System.out.print("Enter your name : ");
            name = Game.SCANNER.nextLine();
        }

        return name;
    }

    /**
     * Affichage de séparation
     */
    public static void printSeparation() {
        System.out.println("--------------------------------------------------------------------");
    }

    /**
     * Permet d'obtenir un Talker selon son nom s'il n'existe pas alors la méthode renvoie null
     * @param talkerName
     * @return Talker | null
     */
    private Talker getTalker(String talkerName) {
        for (Talker talker : this.talkers) {
            if (talker.toString().toUpperCase().equals(talkerName.toUpperCase())) {
                return talker;
            }
        }
        return null;
    }

    /**
     * Permet d'obtenir un Fighter selon son nom s'il n'existe pas alors la méthode renvoie null
     * @param enemyName
     * @return Fighter | null
     */
    private Fighter getFighter(String enemyName) {
        for (Fighter fighter : this.fighters) {
            if (fighter.getName().toUpperCase().equals(enemyName.toUpperCase())) {
                return fighter;
            }
        }
        return null;
    }

    /**
     * Permet d'obtenir un Item selon son nom s'il n'existe pas alors la méthode renvoie null
     * @param itemName
     * @return Item | null
     */
    private Item getItem(String itemName) {
        for (Item item : this.items) {
            if (item.toString().toUpperCase().equals(itemName.toUpperCase())) {
                return item;
            }
        }
        return null;
    }

    /* --------------------------------------------------------------------------------------------------------------------------- */
    /* ALL COMMANDS */

    /**
     * Affichage de toutes les commandes disponibles grâce à /help
     */
    public static void displayAvailableCommands() {
        System.out.println("List of available commands :");
        Game.printSeparation();
        System.out.println("/help - Displays the list of available commands.");
        System.out.println("/inventory - Display the inventory.");
        System.out.println("/stats - Display the statistics of the hero.");
        System.out.println("/go <Map Name> - Go to another map.");
        System.out.println("/heal - Heal the Hero.");
        System.out.println("/attack <Character name> - Attack a character if possible.");
        System.out.println("/talk <Character name> - Talk to a character if possible.");
        System.out.println("/quests - Display the list of available quests.");
        System.out.println("/quest <Quest name> - Display a specific quest with more information.");
        System.out.println("/use <item name> - Use an item.");
        System.out.println("/map - Displays information from the current map.");
        System.out.println("/take <Item Name> - Take an item on the map");
        System.out.println("/unlock <Code> <Location name> - Unlock a locked exit");
        System.out.println("/look <Item Name> | <Character Name> - Display de description of the item");
        System.out.println("/stop - Stop the game.");
    }

    /**
     * Affichage de l'inventaire grâce à /inventory
     */
    public void displayInventory() {
        this.hero.printInventory();
    }

    /**
     * Soigne le personnage avec à une potion s'il en a une grâce à la commande /heal
     */
    public void heal() {
        if (hero.hasPotion()) {
            System.out.println(hero.getName() + " heals himself");
            hero.getPotion().use();
        } else {
            System.out.println("No potions available");
        }
    }

    /**
     * Permet au joueur de se déplacer d'une map à une autre grâce à la commande /go <Map Name>
     * @param locationName
     */
    public void goTo(String locationName) {
        try {
            Location location = this.hero.getLocation().exitTo(locationName);
            System.out.println(hero.getName() + " go to " + locationName);

            // Met à jour la map et tout ce qu'il y a dedans
            this.hero.setLocation(location);
            this.items = location.getItems();
            this.fighters = location.getFighters();
            this.talkers = location.getTalkers();

            this.mainQuest.updateQuest();
        } catch (Exception e) {
            System.out.println("You can't access this map OR it doesn't exist : " + locationName);
        }
    }

    /**
     * Permet au joueur d'attaquer un ennemie grâce à la commande /attack <Character Name>
     * @param enemyName
     */
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
            } else {
                this.hero.addXp(fighterAttacked.getXpDropped());
                try {
                    this.hero.getLocation().removeCharacter(fighterAttacked);
                } catch (Exception e) {
                    System.out.println("Attack : Problème au niveau de la suppression du character");
                }
            }

        } else {
            System.out.println("This character doesn't exist");
        }
    }

    /**
     * Méthode auxiliaire à la méthode attack permettant un affichage plus concis
     * @param attacker
     * @param target
     */
    private void attack_aux(Fighter attacker, Fighter target) {
        System.out.print(target.getName() + " takes " + attacker.getDamage() + " damage ");
        attacker.attack(target);
        System.out.println("| HP remaining :" + target.getHp());
    }

    /**
     * Permet au joueur de parler à un PNJ grâce à la commande /talk <Character Name>
     * @param characterName
     */
    public void talk(String characterName) {
    	Talker npcTalked = this.getTalker(characterName);

        if (npcTalked != null) {
            npcTalked.resetTalkState();

            System.out.println(hero.getName() + " talks to " + characterName);

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

    /**
     * Permet d'arrêter la partie grâce à la commande /stop
     */
    public static void stop() {
        System.out.println("GAME STOP");
        Game.SCANNER.close();
        System.exit(0);
    }

    /**
     * Permet au joueur d'afficher ses statistiques grâce à la commande /stats
     */
    public void stats() {
        System.out.println("STATISTICS :");
        Game.printSeparation();
        this.hero.printStats();
    }

    /**
     * Permet au joueur de voir la liste des quêtes qu'il peut faire grâce à la commande /quests
     */
    public void quests() {
        System.out.println("List of available quests :");
        Game.printSeparation();
        this.mainQuest.printQuest();
    }

    /**
     * Permet au joueur de voir une quête plus en détails
     * @param questName
     */
    public void quest(String questName) {
        System.out.println("Details of : " + questName);
    }

    /**
     * Permet au joueur de se donner de l'xp grâce à la commande /addXp <amount>
     * @param xp
     */
    public void addXp(int xp) { // to test
        System.out.println("Gain d'xp : " + xp);

        this.hero.addXp(xp);
    }

    /**
     * Permet au joueur de se donner une épée grâce à la commande /addSword
     */
    public void addSword() {
        System.out.println("add Sword");

        try {
            this.hero.addItem(new Sword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet au joueur d'utiliser un item grâce à la commande /use <Item Name>
     * @param itemName
     */
    public void use(String itemName) {
        System.out.println("Use of : " + itemName);

        Item itemUsed = this.hero.getItem(itemName);
        try {
			this.hero.useItem(itemUsed);
		} catch (Exception e) {
			System.out.println("This item isn't usable.");
		}
    }

    /**
     * Permet au joueur d'afficher les détails de la map sur laquelle il se trouve grâce à la commande /map
     */
    public void map() {
        Location location = this.hero.getLocation();
        System.out.println("Map : " + location.getName());

        String locationDescription = location.getDescription();
        System.out.println("A short description : " + locationDescription + "\n");

        // printList("List of maps you can go :", location.getExits()); // TODO Remettre quand la fonction getExits existera
        printList("Looking for exits, ", location.getExitDescriptions());
        printList("List of items in this map :", this.items);
        printList("List of mobs in this map :", this.fighters);
        printList("List of NPCs you can talk to in this map :", this.talkers);
    }

    /**
     * Méthode auxiliaire à la méthode map permettant un affichage plus concis
     * @param title
     * @param list
     */
    private void printList(String title, List<?> list) {
        System.out.println(title);

        if (list.isEmpty()) {
            System.out.println("\t" + "None");
        } else {
            list.forEach(object -> System.out.println("\t" + object));
        }
    }

    /**
     * Permet au joueur de prendre un item sur la map actuelle grâce à la commande /take <Item Name>
     * @param itemName
     * @throws Exception
     */
    public void take(String itemName) throws Exception {
        System.out.println("Take : " + itemName);

        Location location = this.hero.getLocation();
        Item item = this.getItem(itemName);

        if (item != null) {
            // Si l'item est présent sur la map on l'ajoute à l'inventaire et on le retire de la map
            this.hero.addItem(item);
            location.removeItem(item);
            System.out.println("Item taken successfully");
        } else {
            System.out.println("This item doesn't exist");
        }
    }

    /**
     * Permet au joueur d'équiper une arme
     * @param weaponName
     */
    public void equip(String weaponName) {
        Weapon weapon = (Weapon)this.hero.getItem(weaponName);

        if (weapon != null) {
            this.hero.changeEquipedWeapon(weapon);
            System.out.println("Weapon changed successfully");
        } else {
            System.out.println("You don't have this weapon");
        }
    }

    public boolean isMainQuestFinished() {
    	return this.mainQuest.isQuestFinished();
    }

    public void unlockExitWithCode(String exit, String code) {
        Location loc = hero.getLocation();

        try {
            System.out.println(loc.enterExitCode(exit, code));
            System.out.println(loc.unlock(exit));
        } catch (Exception e) {
            System.out.println("The exit is locked !");
        }
    }

    public void look(String objectName) {
        Item testItem = this.getItem(objectName);
        if (testItem == null) {
            testItem = this.hero.getItem(objectName);
        }
        Fighter testFighter = this.getFighter(objectName);
        NPC testTalker = (NPC)this.getTalker(objectName);

        if (testItem != null) {
            System.out.println(testItem.getDescription());
        } else if (testFighter != null) {
            System.out.println(testFighter.getDescription());
        } else if (testTalker != null) {
            System.out.println(testTalker.getDescription());
        } else {
            System.out.println(objectName + " not found");
        }
    }
}
