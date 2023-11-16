package game;

import java.util.ArrayList;
import java.util.List;

import hero.Hero;
import locations.Location;
import quests.Quest;

public class Game {
    private Hero hero;
    private List<Location> locations = new ArrayList<Location>();
    private boolean cont;

    public Game() {
        // Location.createLocations();
    	this.init();
    	this.loop();
    }
    
    public void init() {
    	this.cont = true;
    }
    
    public void loop() {
    	while(this.cont) {
    		
    	}
    }

    /* --------------------------------------------------------------------------------------------------------------------------- */
    /* ALL COMMANDS */

    public static void displayAvailableCommands() {
        System.out.println("\033[0;1m" + "List of available commands :\n");
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
    	
    }

    public void heal() {
        //le hero utilise une potion
    	if (hero.hasPotion()) {
    		//hero.heal(hero.get);
    	}
    }

    public void goTo(Location location) {
        
    }

    public void attack(Character character) {
        //lance un fight
    }

    public static void talk(Character character) {
        
    }

    public void stop() {
        this.cont = false;
    }

    public static void stats() {
        
    }

    public static void quests() {
        System.out.println("List of available quests :");
    }

    public static void quest(Quest quest) {
        
    }
}
