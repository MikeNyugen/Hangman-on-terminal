package p1.hangman;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		GameState gameState = null;
		CommandOpts options = new CommandOpts(args);

		startGame(input, options, gameState);
	}

	static void startGame(Scanner input, CommandOpts options, GameState gameState) {
		printOptions();
		parseInput(input);
		if (options.wordSource == "") {
			gameState = new GameState(Words.returnRandomWord(input.nextInt()), options.maxGuesses, options.maxHints);
		} else {
			gameState = new GameState(Words.returnRandomWord(options.wordSource), options.maxGuesses, options.maxHints);
		}
		gameLoop(gameState);
	}

	public static void printOptions() {
		System.out.print(
			"  1. Counties\n" +
			"  2. Countries\n" +
			"  3. Cities\n" +
			"  4. States\n\n" +
				"Pick a category\n");
	}

	public static void parseInput(Scanner input) {
		while (!input.hasNextInt()) {
			System.out.println("INCORRECT CATEGORY");
			input.next();
		}
	}
	public static void gameLoop(GameState gameState) {
		while (!gameState.won() && !gameState.lost()) {
			gameState.showTargetWord();

			System.out.println("Guesses remaining: " + gameState.guessesRemaining);

			gameState.guess();
		}
		if (gameState.won()) {
			System.out.print("Well done!\nYou took " + gameState.guessesMade + " guesses\n");
		} else {
			System.out.print("You lost!\nThe word was " + gameState.targetWord + "\n");
		}
	}
}
