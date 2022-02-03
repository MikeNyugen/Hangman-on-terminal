package p1.hangman;

import java.util.Scanner;

public class App {

	static void doStuff(Scanner input, CommandOpts options, GameState gameState) {

		boolean correctGuess;

		if (options.wordsource == "") {

			System.out.print(
					"  1. Counties\n" +
					"  2. Countries\n" +
					"  3. Cities\n" +
					"  4. States\n\n" +
						"Pick a category");

			gameState = new GameState(Words.randomWord(input.nextInt()), options.maxguesses, options.maxhints);
		} else {
			gameState = new GameState(Words.randomWord(options.wordsource), options.maxguesses, options.maxhints);
		}

		while (!gameState.won() && !gameState.lost()) {
			gameState.showWord(gameState.word);

			System.out.println("Guesses remaining: " + gameState.wrong);

			correctGuess = gameState.guessLetter();

			if (correctGuess) {
				System.out.println("Good guess!");
			} else {
				System.out.println("Wrong guess!");
			}
		}
		if (gameState.won()) {
			System.out.print("Well done!\nYou took " + gameState.g + " guesses\n");
		} else {
			System.out.print("You lost!\nThe word was " + gameState.word + "\n");
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		GameState gameState = null;
		CommandOpts options;

		options = new CommandOpts(args);

		doStuff(input, options, gameState);
	}
}
