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

    public static void heal() {
        System.out.println("The Hero heals himself");
    }

    public static void goTo(String direction) {
        System.out.println("The Hero go to " + direction);
    }

    public static void attack(String characterName) {
        System.out.println("Attaque du personnage : " + characterName);
    }

    public static void talk(String characterName) {
        System.out.println("The Hero talk to " + characterName);
    }

    public static void stop() {
        System.out.println("GAME STOP");
        System.exit(0);
    }

    public static void stats() {
        System.out.println("Statistics of the Hero :");
    }

    public static void quests() {
        System.out.println("List of available quests :");
    }

    public static void quest(String questName) {
        System.out.println("Details of : " + questName);
    }
}
