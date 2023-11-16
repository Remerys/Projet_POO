package game;

import java.util.ArrayList;
import java.util.List;

import characters.Character;
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

    public static void heal() {
        //le hero utilise une potion
    	if (hero.hasPotion()) {
            System.out.println(hero.getName() + " heals himself");
    		//hero.heal(hero.get);
        }
    }
// BTW Je ne vois pas pk elles ne pourraient pas être static sachant qu'elles font tjrs la même chose selon un paramètre spécifique
    public static void goTo(String location) { // Il faut des String car on travaille en ligne de commande et je ne peux récupérer que des String et pas de personnage ou je ne sais quoi directement donc il faut se démerder pour obtenir le character ou location ou n'importe avec une chaîne de caractère
        System.out.println(hero.getName() + " go to " + location);
    }

    public static void attack(String character) { // Il faut des String car on travaille en ligne de commande et je ne peux récupérer que des String et pas de personnage ou je ne sais quoi directement donc il faut se démerder pour obtenir le character ou location ou n'importe avec une chaîne de caractère
        // lance un fight
        Character THECHARACTER = Character.getCharacter(character); // Une solution ? pour récupérer le character à gérer avec des exceptions ect..
        System.out.println(hero.getName() + " attack : " + character);
    }

    public static void talk(String character) { // Il faut des String car on travaille en ligne de commande et je ne peux récupérer que des String et pas de personnage ou je ne sais quoi directement donc il faut se démerder pour obtenir le character ou location ou n'importe avec une chaîne de caractère
        Character THECHARACTER = Character.getCharacter(character); // Une solution ? pour récupérer le character à gérer avec des exceptions ect..
        System.out.println(hero.getName() + " talk to " + character);
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

    public static void quest(String questName) { // Il faut des String car on travaille en ligne de commande et je ne peux récupérer que des String et pas de personnage ou je ne sais quoi directement donc il faut se démerder pour obtenir le character ou location ou n'importe avec une chaîne de caractère
        System.out.println("Details of : " + questName);
    }
}
