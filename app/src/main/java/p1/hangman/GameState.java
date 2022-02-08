package p1.hangman;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the variables and methods associated with the game state.
 */
public class GameState {
  public String targetWord;
  public int guessesMade;
  public int guessesRemaining;
  public int hintsRemaining;

  ArrayList<Character> correctLetters;
  int remainingLetters;
  ArrayList<Character> hintsGiven;

  public Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8).useDelimiter("\n");

  public GameState(String targetWord, int guessesRemaining, int hintsRemaining) {
    this.targetWord = targetWord;
    this.guessesMade = 0;
    this.guessesRemaining = guessesRemaining;
    this.hintsRemaining = hintsRemaining;

    remainingLetters = targetWord.length();
    correctLetters = new ArrayList<>();
    hintsGiven = new ArrayList<>();
  }

  /**
   * Displays the word that the user has to guess.
   */
  public void showTargetWord() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < targetWord.length(); i++) {
      char correctChar = Character.toLowerCase(targetWord.charAt(i));
      if (correctLetters.contains(correctChar)) {
        sb.append(targetWord.charAt(i));
      } else {
        sb.append('-');
      }
    }
    System.out.println(sb);
  }

  /**
   * Queries the user's guess.
   */
  public void getGuess() {
    String userGuess;

    System.out.print("Guess a letter or word (? for a hint): ");

    userGuess = sc.nextLine();
    if (userGuess.isBlank()) {
      System.out.println("Please enter a guess: ");
    } else {
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
  }

  /**
   * Provides the user with a hint.
   * The hint will be a letter that the user has not already guessed and,
   * a letter that has not previously been given as a hint.
   */
  private void giveHint() {
    if (hintsRemaining == 0) {
      System.out.println("No more hints allowed");
    } else {
      int randomNum = (int) (Math.random() * targetWord.length());
      char hint = Character.toLowerCase(targetWord.charAt(randomNum));
      while (hintsGiven.contains(hint) || correctLetters.contains(hint)) {
        hint = targetWord.charAt((int) (Math.random() * targetWord.length()));
      }
      System.out.println("Try: " + hint);
      hintsGiven.add(hint);
      hintsRemaining--;
    }
  }

  /**
   * Handles the logic when the user attempts to guess the word.
   *
   * @param userGuess  the user's guess
   * @param guessCorrect  whether the user's guess is correct
   */
  private void guessWord(String userGuess, boolean guessCorrect) {
    if (userGuess.equalsIgnoreCase(targetWord)) {
      remainingLetters = 0;
      guessCorrect = true;
    }
    printFeedback(guessCorrect);
    updateGuesses();
  }

  /**
   * Handles the logic when the user attempts to guess a letter.
   *
   * @param userGuess  the user's guess
   * @param guessCorrect  whether the user's guess is correct
   */
  public void guessLetter(char userGuess, boolean guessCorrect) {
    for (int i = 0; i < targetWord.length(); i++) {
      if (targetWord.charAt(i) == userGuess && !correctLetters.contains(userGuess)) {
        final ArrayList<Integer> occurrences = findOccurrences(userGuess, targetWord);
        guessCorrect = true;
        correctLetters.add(userGuess);
        for (int j = 0; j < occurrences.size(); j++) {
          remainingLetters--;
        }
      }
    }
    printFeedback(guessCorrect);
    updateGuesses();
  }

  /**
   * Finds the index position of occurrences of a letter in a given string.
   *
   * @param letter  letter to be searched
   * @param targetWord  word in which the letter is searched
   * @return  arrayList containing the index positions of all occurrences
   */
  public ArrayList<Integer> findOccurrences(char letter, String targetWord) {
    ArrayList<Integer> output = new ArrayList<>();
    String lowerCaseWord = targetWord.toLowerCase();
    int index = lowerCaseWord.indexOf(letter);

    while (index >= 0) {
      output.add(index);
      index = targetWord.indexOf(letter, index + 1);
    }
    return output;
  }

  private void updateGuesses() {
    guessesMade++;
    guessesRemaining--;
  }

  private void printFeedback(boolean guessCorrect) {
    if (guessCorrect) {
      System.out.println("Good guess!");
    } else {
      System.out.println("Wrong guess!");
    }
  }

  public boolean won() {
    return remainingLetters == 0;
  }

  public boolean lost() {
    return remainingLetters > 0 && guessesRemaining == 0;
  }

  public String getTargetWord() {
    return targetWord;
  }
}
