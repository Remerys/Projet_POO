package game;

import java.util.Scanner;

public class Command {
    public static void handleCommands() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                // Attend la commande de l'utilisateur
                System.out.print("Enter a command : ");
                String command = scanner.nextLine();

                // Vérifie la commande et affiche la liste des commandes disponibles si nécessaire
                if ("/help".equals(command)) {
                    Game.displayAvailableCommands();
                } else if ("/quests".equals(command)) {
                    Game.quests();
                }
                else {
                    System.out.println("Unrecognized command. Type '/help' to display the list of available commands.");
                }
            }
        }
    }
}
