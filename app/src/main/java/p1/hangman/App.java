package p1.hangman;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		GameState gameState = null;
		CommandOpts options = new CommandOpts(args);
	
		doStuff(input, options, gameState);
	}

	static void doStuff(Scanner input, CommandOpts options, GameState gameState) {

		if (options.wordSource == "") {
			printOptions();
			
			while (!input.hasNextInt()) {
				System.out.println("INCORRECT CATEGORY");
				input.next();
			}
			gameState = new GameState(Words.returnRandomWord(input.nextInt()), options.maxGuesses, options.maxHints);
		} else {
			gameState = new GameState(Words.returnRandomWord(options.wordSource), options.maxGuesses, options.maxHints);
		}

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

	public static void printOptions() {
		System.out.print(
			"  1. Counties\n" +
			"  2. Countries\n" +
			"  3. Cities\n" +
			"  4. States\n\n" +
				"Pick a category\n");
	}
}
