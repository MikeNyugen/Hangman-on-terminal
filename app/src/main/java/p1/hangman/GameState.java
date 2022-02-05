package p1.hangman;

import java.util.ArrayList;
import java.util.Scanner;
public class GameState {
	public String targetWord; 
	public int guessesMade;
	public int guessesRemaining;
	public int hintsRemaining;
	
	ArrayList<Integer> correctLetters;
	ArrayList<Integer> remainingLetters = new ArrayList<Integer>();
	
	public Scanner sc = new Scanner(System.in).useDelimiter("\n");
	
	public GameState(String targetWord, int guesses, int hints) {
		this.targetWord = targetWord;
		this.guessesMade = 0; 
		this.guessesRemaining = guesses; 
		this.hintsRemaining = hints;

		correctLetters = new ArrayList<Integer>();
		
		for (int i = 0; i < targetWord.length(); ++i) {
			remainingLetters.add(i);
		}
	}
	
	void showTargetWord(String targetWord) {
		for (int i = 0; i < targetWord.length(); ++i) {
			if (correctLetters.contains(i)) {
				System.out.print(targetWord.charAt(i));
			} else {
				System.out.print("-");
			}
		}
		System.out.println("");
	}
	
	boolean guessLetter() {
		
		System.out.print("Guess a letter or word (? for a hint): ");
		
		String userGuess = sc.next();
		
		if (userGuess.length() > 1) {
			if (userGuess.equals(targetWord)) {
				remainingLetters.clear();
				return true;
			} else return false;
		}
		
		char letter = userGuess.charAt(0);
		
		if (letter == '?') {
			giveHint();
			return false;
		}
		
		boolean flag = false;

		for (int i = 0; i < remainingLetters.size(); ++i) {
			if (Character.toLowerCase(targetWord.charAt(remainingLetters.get(i))) == letter) {
				flag = true;
				correctLetters.add(remainingLetters.remove(i));
			}
		}

		guessesMade++; 
		guessesRemaining--;
		return flag;
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
			System.out.print("Try: ");
			System.out.println(targetWord.charAt((int)(Math.random()*targetWord.length())));
			hintsRemaining--;	
		}
	}
}
