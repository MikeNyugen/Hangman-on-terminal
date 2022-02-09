package p1.hangman;

import java.util.ArrayList;

/**
 * Handles the variables and methods associated with the game state.
 */
public class GameState {
  private String targetWord;
  private int guessesMade;
  private int guessesRemaining;
  private int hintsRemaining;

  private ArrayList<Character> correctLetters;
  private ArrayList<Character> hintsGiven;
  private int remainingLetters;

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
   * Queries the user's guess.
   */
  public void getGuess(GameOutput output) {
    output.printPrompt();
    String userGuess = output.nextLine();

    if (userGuess.isBlank()) {
      output.printBlankInput();
    } else {
      char letter = Character.toLowerCase(userGuess.charAt(0));
      if (userGuess.length() > 1) {
        guessWord(userGuess, output);
      } else if (letter == '?') {
        giveHint(output);
      } else {
        guessLetter(letter, output);
      }
    }
  }

  /**
   * Provides the user with a hint.
   * The hint will be a letter that the user has not already guessed and,
   * a letter that has not previously been given as a hint.
   */
  public void giveHint(GameOutput output) {
    if (hintsRemaining == 0) {
      output.printNoHints();
    } else {
      int randomNum = (int) (Math.random() * targetWord.length());
      char hint = Character.toLowerCase(targetWord.charAt(randomNum));
      while (hintsGiven.contains(hint) || correctLetters.contains(hint)) {
        hint = Character.toLowerCase(targetWord.charAt((int) (Math.random() * targetWord.length())));
      }
      output.printHint(hint);
      hintsGiven.add(hint);
      hintsRemaining--;
    }
  }

  /**
   * Handles the logic when the user attempts to guess the word.
   *
   * @param userGuess    the word that the user guessed
   * @param output  deals with io
   */
  public void guessWord(String userGuess, GameOutput output) {
    boolean guessCorrect = false;
    if (userGuess.equalsIgnoreCase(targetWord)) {
      remainingLetters = 0;
      guessCorrect = true;
    }
    output.printFeedback(guessCorrect);
    updateGuesses();
  }

  /**
   * Handles the logic when the user attempts to guess a letter.
   *
   * @param userGuess    the letter that the use guessed
   * @param output deals with io
   */
  public void guessLetter(char userGuess, GameOutput output) {
    boolean guessCorrect = false;
    for (int i = 0; i < targetWord.length(); i++) {
      boolean guessIsCorrect = Character.toLowerCase(targetWord.charAt(i)) == userGuess;
      boolean alreadyGuessed = correctLetters.contains(userGuess);

      if (guessIsCorrect && !alreadyGuessed) {
        final ArrayList<Integer> occurrences = findOccurrences(userGuess, targetWord);
        guessCorrect = true;
        correctLetters.add(userGuess);
        for (int j = 0; j < occurrences.size(); j++) {
          remainingLetters--;
        }
      }
    }
    output.printFeedback(guessCorrect);
    updateGuesses();
  }

  /**
   * Finds the index position of occurrences of a letter in a given string.
   *
   * @param letter     letter to be searched
   * @param targetWord word in which the letter is searched
   * @return arrayList containing the index positions of all occurrences
   */
  public ArrayList<Integer> findOccurrences(char letter, String targetWord) {
    ArrayList<Integer> occurrences = new ArrayList<>();
    String lowerCaseWord = targetWord.toLowerCase();
    int index = lowerCaseWord.indexOf(letter);

    while (index >= 0) {
      occurrences.add(index);
      index = targetWord.indexOf(letter, index + 1);
    }
    return occurrences;
  }

  public void updateGuesses() {
    guessesMade++;
    guessesRemaining--;
  }

  public boolean won() {
    return remainingLetters == 0;
  }

  public boolean lost() {
    return remainingLetters > 0 && guessesRemaining == 0;
  }

  public int getRemainingLetters() {
    return remainingLetters;
  }

  public String getTargetWord() {
    return targetWord;
  }

  public ArrayList<Character> getCorrectLetters() {
    return correctLetters;
  }

  public void addCorrectLetter(char letter) {
    correctLetters.add(letter);
    remainingLetters--;
  }

  public ArrayList<Character> getHintsGiven() {
    return hintsGiven;
  }

  public int getHintsRemaining() {
    return hintsRemaining;
  }

  public void setHintsRemaining(int hintsRemaining) {
    this.hintsRemaining = hintsRemaining;
  }

  public int getGuessesRemaining() {
    return guessesRemaining;
  }

  public int getGuessesMade() {
    return guessesMade;
  }
}
