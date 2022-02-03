package p1.hangman;

import java.util.ArrayList;
import java.util.Scanner;
public class GameState {
	public String targetWord; 
	public int guessesMade;
	public int guessesRemaining;
	public int hintsRemaining;
	
	ArrayList<Integer> correctLetters;
	ArrayList<Integer> not = new ArrayList<Integer>();
	
	public Scanner sc = new Scanner(System.in).useDelimiter("\n");
	
	public GameState(String targetWord, int guesses, int hints) {
		this.targetWord = targetWord;
		this.guessesMade = 0; 
		this.guessesRemaining = guesses; 
		this.hintsRemaining = hints;

		correctLetters = new ArrayList<Integer>();
		
		for (int i = 0; i < targetWord.length(); ++i) {
			not.add(i);
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
			if (userGuess==targetWord) {
				not.clear();
				return true;
			} else return false;
		}
		
		char letter = userGuess.charAt(0);
		
		if (letter == '?') {
			giveHint();
			return false;
		}
		
		for (int i = 0; i < not.size(); ++i) {
			if (Character.toLowerCase(targetWord.charAt(not.get(i))) == letter) {
				correctLetters.add(not.remove(i));
				guessesMade++;
				return true;
			}
		}

		guessesMade++; 
		guessesRemaining--;
		return false;
	}
	
	boolean won() {
		return not.size() == 0;
	}

	boolean lost() {
		return not.size() > 0 && guessesRemaining == 0;
	}

	void giveHint() {
		if (hintsRemaining == 0) {
			System.out.println("No more hints allowed");
		}
		System.out.print("Try: ");
		System.out.println(targetWord.charAt((int)(Math.random()*targetWord.length())));
	}
}
