package game;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Command {
    private static final Map<String, Runnable> commandMap = new HashMap<>();

    static {
        // Initialise la map des commandes
        // Ici qu'il faut ajouter les commandes générales qui ne demandent pas de traitement spécifique
        // Sinon il faut les ajouter dans un else if de handleCommands() et dans une méthode spécifique pour rendre le code plus lisible
        commandMap.put("/help", Game::displayAvailableCommands);
        commandMap.put("/heal", Game::heal);
        commandMap.put("/inventory", Game::displayInventory);
        commandMap.put("/stop", Game::stop);
        commandMap.put("/stats", Game::stats);
        commandMap.put("/quests", Game::quests);
    }

    // Gestionnaire des commandes
    public static void handleCommands() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                // Attend la commande de l'utilisateur
                System.out.print("Enter a command : ");
                String command = scanner.nextLine();

                // Vérifie la commande et exécute la méthode correspondante
                if (command.startsWith("/go")) {
                    handleGoCommand(command);
                } else if (command.startsWith("/attack") || command.startsWith("/talk")) {
                    handleInteractionCommand(command);
                } else if (command.startsWith("/quest") && !command.endsWith("s")) { // !command.endsWith("s") --> Sinon ça rentre toujours dans cette condition
                    handleQuestCommand(command);
                } else {
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
            Game.goTo(direction);
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
                Game.attack(characterName);
            } else {
                Game.talk(characterName);
            }
        } else {
            System.out.println("Incorrect command format. Usage : " + (command.startsWith("/attack") ? "/attack" : "/talk") + " <Character Name>");
        }

    }

    // Traitement de la commande /quest <Quest Name>
    private static void handleQuestCommand(String command) {
        // Sépare la commande et le nom de la quête
        String[] parts = command.split(" ");

        if (parts.length == 2) {
            String questName = parts[1];
            Game.quest(questName);
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
}
