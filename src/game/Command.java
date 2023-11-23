package game;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Command {
    private static final Map<String, Runnable> commandMap = new HashMap<>();
    private static Game game;

    static {
        // Initialise la map des commandes
        // Ici qu'il faut ajouter les commandes générales qui ne demandent pas de
        // traitement spécifique
        // Sinon il faut les ajouter dans un else if de handleCommands() et dans une
        // méthode spécifique pour rendre le code plus lisible
        /*commandMap.put("/help", game::displayAvailableCommands);
        commandMap.put("/heal", game::heal);
        commandMap.put("/inventory", game::displayInventory);
        commandMap.put("/stop", game::stop);
        commandMap.put("/stats", game::stats);
        commandMap.put("/quests", game::quests);*/
    }

    public static void setGame(Game game) {
    	Command.game = game;
    	commandMap.put("/help", game::displayAvailableCommands);
        commandMap.put("/heal", game::heal);
        commandMap.put("/inventory", game::displayInventory);
        commandMap.put("/stop", game::stop);
        commandMap.put("/stats", game::stats);
        commandMap.put("/quests", game::quests);
        commandMap.put("/addSword", game::addSword);
    }

    // Gestionnaire des commandes
    public static void handleCommands() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                // Attend la commande de l'utilisateur
                System.out.print("Enter a command : ");
                String command = scanner.nextLine();

                // Vérifie la commande et exécute la méthode correspondante

                 String start = command.split(" ")[0];
                 //Pour gerer les arguments
                 switch (start) {
                 	case "/go":
                 		handleGoCommand(command);
                        break;
                 	case "/attack":
                 		handleInteractionCommand(command);
                        break;
                 	case "/talk":
                 		handleInteractionCommand(command);
                        break;
                 	case "/quest":
                 		handleQuestCommand(command);
                        break;
                 	case "/addXp":
                 		addXpCommand(command);
                        break;
                 	default:
                 		handleGeneralCommand(command);
                 }
            }
        }
    }

    // Traitement de la commande /go <Direction>
    private static void handleGoCommand(String command) {
        // Sépare la commande et la direction
        String[] parts = command.split(" ");

        if (parts.length == 2) {
            String direction = parts[1];
            game.goTo(direction);
        } else {
            System.out.println("Incorrect command format. Usage : /go <Direction>");
        }
    }

    // Traitement des commandes /attack <Character Name> et /talk <Character Name>
    private static void handleInteractionCommand(String command) {
        // Sépare la commande et le nom du personnage
        String[] parts = command.split(" ");

        if (parts.length == 2) {
            String characterName = parts[1];

            if (command.startsWith("/attack")) {
                game.attack(characterName);
            } else {
                game.talk(characterName);
            }
        } else {
            System.out.println("Incorrect command format. Usage : "
                    + (command.startsWith("/attack") ? "/attack" : "/talk") + " <Character Name>");
        }

    }

    // Traitement de la commande /quest <Quest Name>
    private static void handleQuestCommand(String command) {
        // Sépare la commande et le nom de la quête
        String[] parts = command.split(" ");

        if (parts.length == 2) {
            String questName = parts[1];
            game.quest(questName);
        } else {
            System.out.println("Incorrect command format. Usage : /quest <Quest Name>");
        }
    }

    // Traitement des commandes générales
    private static void handleGeneralCommand(String command) {
        // Vérifie la commande générale et exécute la méthode correspondante
        Runnable runnable = commandMap.get(command);

        if (runnable != null) {
            runnable.run();
        } else {
            System.out.println("Unrecognized command. Type '/help' to display the list of available commands.");
        }
    }

    private static void addXpCommand(String command) {
        // Sépare la commande et le nom de la quête
        String[] parts = command.split(" ");

        if (parts.length == 2) {
            int xp = Integer.valueOf(parts[1]);
            game.addXp(xp);
        } else {
            System.out.println("Incorrect command format. Usage : /addXp <Number>");
        }
    }

    public static String getName() {
    	String name = "";
        Scanner scanner = new Scanner(System.in);

        while (name == "") {
            System.out.print("Enter your name : ");
            name = scanner.nextLine();
        }

    	return name;
    }
}
