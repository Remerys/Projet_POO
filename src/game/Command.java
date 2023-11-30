package game;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Command {
    private static final Map<String, Runnable> COMMAND_MAP = new HashMap<>();
    private static Game game;

    /**
     * Initialise la map des commandes.
     * C'est ici qu'il faut ajouter les commandes générales qui ne demandent pas de traitement spécifique.
     * Sinon il faut les ajouter dans le switch de handleCommands() et dans une méthode spécifique pour rendre le code plus lisible.
     * @param game
     */
    public static void setGame(Game game) {
        Command.game = game;
        COMMAND_MAP.put("/help", Game::displayAvailableCommands);
        COMMAND_MAP.put("/heal", game::heal);
        COMMAND_MAP.put("/inventory", game::displayInventory);
        COMMAND_MAP.put("/stop", game::stop);
        COMMAND_MAP.put("/stats", game::stats);
        COMMAND_MAP.put("/quests", game::quests);
        COMMAND_MAP.put("/map", game::map);
        COMMAND_MAP.put("/addSword", game::addSword);
    }

    /**
     * Gestionnaire des commandes
     */
    public static void handleCommands() {
        while (true) {
            // Attend la commande de l'utilisateur
            System.out.print("Enter a command : ");
            String command = Game.SCANNER.nextLine();

            Game.printSeparation();

            // Vérifie la commande et exécute la méthode correspondante
            String start = command.split(" ")[0];

            // Commandes qui demandent un traitement spécifique
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
                    handleXpCommand(command);
                    break;
                case "/use":
                    handleItemCommand(command);
                    break;
                case "/take":
                    handleItemCommand(command);
                    break;
                case "/unlock":
                    handleUnlockCodeCommand(command);
                    break;
                default:
                    handleGeneralCommand(command);
            }

            Game.printSeparation();
        }
    }

    /**
     * Traitement de la commande /go <Map Name>
     * @param command
     */
    private static void handleGoCommand(String command) {
        // Sépare la commande et le nom de la map
        String[] parts = command.split(" ");
        int partsLength = parts.length;

        if (parts.length != 1) {
            String mapName = "";

            for (int i = 1; i < parts.length; i++) {
                if (i < partsLength - 1) {
                    mapName += parts[i] + " ";
                } else {
                    mapName += parts[i];
                }
            }

            game.goTo(mapName);
        } else {
            System.out.println("Incorrect command format. Usage : /go <Map Name>");
        }
    }

    /**
     * Traitement des commandes /attack <Character Name> et /talk <Character Name>
     * @param command
     */
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

    /**
     * Traitement de la commande /quest <Quest Name>
     * @param command
     */
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

    /**
     * Traitement de la commande /use <Item Name> et /take <Item Name>
     * @param command
     */
    private static void handleItemCommand(String command) {
        // Sépare la commande et le nom de l'item
        String[] parts = command.split(" ");

        if (parts.length == 2) {
            String itemName = parts[1];

            if (command.startsWith("/use")) {
                game.use(itemName);
            } else {
                try {
                    game.take(itemName);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Incorrect command format. Usage : "
                    + (command.startsWith("/use") ? "/use" : "/take") + " <Item Name>");
        }
    }

    /**
     * Traitement des commandes générales
     * @param command
     */
    private static void handleGeneralCommand(String command) {
        // Vérifie la commande générale et exécute la méthode correspondante
        Runnable runnable = COMMAND_MAP.get(command);

        if (runnable != null) {
            runnable.run();
        } else {
            System.out.println("Unrecognized command. Type '/help' to display the list of available commands.");
        }
    }

    /**
     * Traitement de la commande /addXp <amount>
     * @param command
     */
    private static void handleXpCommand(String command) {
        // Sépare la commande et le nom de la quête
        String[] parts = command.split(" ");

        if (parts.length == 2) {
            int xp = Integer.valueOf(parts[1]);
            game.addXp(xp);
        } else {
            System.out.println("Incorrect command format. Usage : /addXp <Number>");
        }
    }

    /**
     * Traitement de la commande /unlock <exit>
     * @param command
     */
    private static void handleUnlockCodeCommand(String command) {
        // Sépare la commande et le nom de la quête
        String[] parts = command.split(" ");


        if (parts.length != 1) {
            String code = parts[1];
            String mapName = "";

            for (int i = 2; i < parts.length; i++) {
                if (i < parts.length - 1) {
                    mapName += parts[i] + " ";
                } else {
                    mapName += parts[i];
                }
            }

            game.unlockExitWithCode(mapName, code);
        } else {
            System.out.println("Incorrect command format. Usage : /unlock <Code> <Location name>");
        }
    }
}
