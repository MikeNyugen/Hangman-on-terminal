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
	ArrayList<Character> hintsGiven;

	public Scanner sc = new Scanner(System.in).useDelimiter("\n");

	public GameState(String targetWord, int guesses, int hints) {
		this.targetWord = targetWord;
		this.guessesMade = 0;
		this.guessesRemaining = guesses;
		this.hintsRemaining = hints;

		correctLetters = new ArrayList<Integer>();
		remainingLetters = new ArrayList<Integer>();
		hintsGiven = new ArrayList<Character>();

		for (int i = 0; i < targetWord.length(); ++i) {
			remainingLetters.add(i);
		}
	}

	public void showTargetWord() {
		for (int i = 0; i < targetWord.length(); ++i) {
			if (correctLetters.contains(i)) {
				System.out.print(targetWord.charAt(i));
			} else {
				System.out.print("-");
			}
		}
		System.out.println();
	}

	public void guess() {
		System.out.print("Guess a letter or word (? for a hint): ");

		String userGuess = sc.nextLine();
		char letter = Character.toLowerCase(userGuess.charAt(0));
		boolean guessCorrect = false;

		if (userGuess.length() > 1) {
			guessWord(userGuess, guessCorrect);
		} else if (letter == '?') {
			giveHint();
		} else {
			guessLetter(letter, guessCorrect);
		}
	}
	
	private void giveHint() {
		if (hintsRemaining == 0) {
			System.out.println("No more hints allowed");
		} else {
			char hint =  Character.toLowerCase(targetWord.charAt((int) (Math.random() * targetWord.length())));
			while (hintsGiven.contains(hint)) {
				 hint = targetWord.charAt((int) (Math.random() * targetWord.length()));
			}
			System.out.println("Try: " + hint);
			hintsGiven.add(hint);
			hintsRemaining--;
		}
	}

	private void guessWord(String userGuess, boolean guessCorrect) {
		if (userGuess.equalsIgnoreCase(targetWord)) {
			remainingLetters.clear();
			guessCorrect = true;
		}
		printFeedback(guessCorrect);
		updateGuesses(guessCorrect);
	}

	private void guessLetter(char letter, boolean guessCorrect) {	
		for (int i = 0; i < remainingLetters.size(); i++) {
			if (Character.toLowerCase(targetWord.charAt(remainingLetters.get(i))) == letter) {
				guessCorrect = true;
				correctLetters.add(remainingLetters.remove(i));
			}
		}
		printFeedback(guessCorrect);
		updateGuesses(guessCorrect);
	}

	private void updateGuesses(boolean guessCorrect) {
		if (!guessCorrect) {
			guessesMade++;
			guessesRemaining--;
		}
	}

	private void printFeedback(boolean guessCorrect) {
		if (guessCorrect) {
			System.out.println("Good guess!");
		} else {
			System.out.println("Wrong guess!");
		}
	}

	public boolean won() {
		return remainingLetters.size() == 0;
	}

	public boolean lost() {
		return remainingLetters.size() > 0 && guessesRemaining == 0;
	}
}
