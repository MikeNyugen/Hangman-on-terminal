package p1.hangman;

import java.util.ArrayList;
import java.util.Scanner;

public class GameState {
	public String targetWord;
	public int guessesMade;
	public int guessesRemaining;
	public int hintsRemaining;

	ArrayList<Integer> correctLetters;
	ArrayList<Integer> remainingLetters;

	public Scanner sc = new Scanner(System.in).useDelimiter("\n");

	public GameState(String targetWord, int guesses, int hints) {
		this.targetWord = targetWord;
		this.guessesMade = 0;
		this.guessesRemaining = guesses;
		this.hintsRemaining = hints;

		correctLetters = new ArrayList<Integer>();
		remainingLetters = new ArrayList<Integer>();

		for (int i = 0; i < targetWord.length(); ++i) {
			remainingLetters.add(i);
		}
	}

	void showTargetWord() {
		for (int i = 0; i < targetWord.length(); ++i) {
			if (correctLetters.contains(i)) {
				System.out.print(targetWord.charAt(i));
			} else {
				System.out.print("-");
			}
		}
		System.out.println("");
	}

	void guess() {
		System.out.print("Guess a letter or word (? for a hint): ");

		String userGuess = sc.next();
		char letter = userGuess.charAt(0);
		boolean guessCorrect = false;

		if (letter == '?') {
			giveHint();
		} else if (userGuess.length() > 1) {
			if (userGuess.equals(targetWord)) {
				guessCorrect = true;
				remainingLetters.clear();
				printFeedback(guessCorrect);
				guessesMade++;
				guessesRemaining--;
			}
		} else {
			for (int i = 0; i < remainingLetters.size(); ++i) {
				if (Character.toLowerCase(targetWord.charAt(remainingLetters.get(i))) == letter) {
					guessCorrect = true;
					correctLetters.add(remainingLetters.remove(i));
				}
			}
			printFeedback(guessCorrect);
			guessesMade++;
			guessesRemaining--;
		}
	}

	void printFeedback(boolean guessCorrect) {
		if (guessCorrect) {
			System.out.println("Good guess!");
		} else {
			System.out.println("Wrong guess!");
		}
	}

	boolean won() {
		return remainingLetters.size() == 0;
	}

	boolean lost() {
		return remainingLetters.size() > 0 && guessesRemaining == 0;
	}

	void giveHint() {
		if (hintsRemaining == 0) {
			System.out.println("No more hints allowed");
		} else {
			System.out.println("Try: " + targetWord.charAt((int) (Math.random() * targetWord.length())));
			hintsRemaining--;
		}
	}
}
