package game;

import java.util.ArrayList;
import java.util.List;

import hero.Hero;
import locations.Location;
import quests.Quest;

public class Game {
    private Hero hero;
    private List<Location> locations = new ArrayList<Location>();
    private boolean cont; // pas utile non plus

    public Game() {
        // Location.createLocations();
    	this.init(); // Pareil qu'en bas
    	this.loop(); // Pareil qu'en bas
    }
    
    public void init() {
    	this.cont = true; // Pas vraiment besoin je pense même raison qu'en bas : | <-- magnifique flèche
    }                                                                          // | <-- magnifique flèche
                                                                               // | <-- magnifique flèche
    public void loop() {                                                       // v <-- magnifique flèche
    	while(this.cont) { // Pas vraiment non plus --> Le jeu tourne tant que la commande stop est pas écrite --> le while true est dans le handleCommands de la classe Command qui elle est appellé dans le main donc pas d'intérêt de faire 2 boucles true
    		
    	}
    }

    /* --------------------------------------------------------------------------------------------------------------------------- */
    /* ALL COMMANDS */

    public static void displayAvailableCommands() {
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

    public static void displayInventory() {
        System.out.println("INVENTORY :");
    }

    public void heal() {
        //le hero utilise une potion
    	if (hero.hasPotion()) {
            System.out.println(hero.getName() + " heals himself");
    		//hero.heal(hero.get);
        }
    }

    public void goTo(Location location) {
        System.out.println(hero.getName() + " go to " + location); // location devrait afficher north east west ou south
    }

    public void attack(Character character) {
        // lance un fight
        System.out.println(hero.getName() " attack : " + character.getName());
    }

    public static void talk(Character character) {
        System.out.println(hero.getName() + " talk to " + character.getName());
    }

    public static void stop() {
        System.out.println("GAME STOP");
        System.exit(0);
    }

    public static void stats() {
        System.out.println("STATISTICS :");
    }

    public static void quests() {
        System.out.println("List of available quests :");
    }

    public static void quest(Quest quest) {
        
    }
}
