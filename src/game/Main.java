package game;

public class Main {

	public static void main(String[] args) {
		Game.printSeparation();
		System.out.println("GAME START");
		Game.printSeparation();
		Game game = new Game();
		game.start();
		//Command.handleCommands();
	}

}
