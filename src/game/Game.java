package game;

import java.util.ArrayList;
import java.util.List;

import hero.Hero;
import locations.Location;

public class Game {
    Hero hero;
    List<Location> locations = new ArrayList<Location>();

    public Game() {
        // Location.createLocations();
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

    public static void displayInventory() {

    }

    public static void heal() {
        
    }

    public static void goTo(String direction) {
        
    }

    public static void attack(String characterName) {
        
    }

    public static void talk(String characterName) {
        
    }

    public static void stop() {
        
    }

    public static void stats() {
        
    }

    public static void quests() {
        System.out.println("List of available quests :");
    }

    public static void quest(String questName) {
        
    }
}
