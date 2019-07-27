package CLI;

import java.util.Scanner;

import Logic.Game;

public class CLI {

	private static Game game;

	public static void defineGameSettings(Scanner in) {
		int guardPersona;
		int numOgres;

		do {
			System.out.println("What personality should the guard have?");
			System.out.println("1. Rookie.");
			System.out.println("2. Druken.");
			System.out.println("3. Suspicious.");
			System.out.print("> ");
			guardPersona = in.nextInt();
		} while (guardPersona < 1 || guardPersona > 3);

		do {
			System.out.println("How many ogres should appear(> 0 & <= 5)?");
			System.out.print("> ");
			numOgres = in.nextInt();
		} while (numOgres < 1 || numOgres > 5);

		game = new Game(guardPersona, numOgres);

	}

	public static void startGame(Scanner in) {
		char dir;
		boolean playGame = true;

		drawMap();

		while (playGame) {

			dir = getHeroDirection(in);
			playGame = game.updateGame(dir);
			drawMap();

		}
	}

	public static void drawMap() {
		System.out.println(game.getMap());

	}

	public static char getHeroDirection(Scanner in) {
		String dir;

		do {
			System.out.print("What direction do you want your character to move(w/s/a/d)? ");
			System.out.print("> ");
			dir = in.next();
		} while (dir.equals("w") && dir.equals("a") && dir.equals("s") && dir.equals("d"));

		return dir.charAt(0);
	}

	public static void main(String[] args) {

		System.out.println("Welcome to the Dungeon Keep");
		System.out.println("What do you want to do?");
		System.out.println("1. Play Game");
		System.out.println("2. Close Game");
		System.out.println();

		Scanner in = new Scanner(System.in);
		int userInput;

		boolean playGame = true;
		while (playGame) {
			System.out.print("> ");
			userInput = in.nextInt();

			switch (userInput) {
			case 1:
				defineGameSettings(in);
				startGame(in);
				GameResults();
				playGame = false;
				break;
			case 2:
				System.out.println("Game closed.");
				playGame = false;
				break;
			default:
				System.out.println("Invalid input!");
				break;
			}
		}

		in.close();
	}

	public static void GameResults() {
		int res = game.results();

		switch (res) {
		case 0:
			System.out.println("You Won the Game!");
			break;
		case 1:
			System.out.println("Game Over! The Guard has caught you!");
			break;
		case 2:
			System.out.println("Game Over! A Ogre has killed you!");
			break;

		}

	}

}
