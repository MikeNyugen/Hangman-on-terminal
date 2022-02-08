package p1.hangman;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is responsible for all Output operations.
 */
public class GameOutput {

  Scanner sc;
  PrintStream output;

  public GameOutput(InputStream is, OutputStream os) {
    sc = new Scanner(is, StandardCharsets.UTF_8).useDelimiter("\n");
    output = new PrintStream(os);
  }

  public String nextLine() {
    return sc.nextLine();
  }

  public void printMenuOptions() {
    output.println(
              "1. Counties\n" +
              "2. Countries\n" +
              "3. Cities\n" +
              "4. States\n" +

            "Pick a category: "
            );
  }

  public void printPrompt() {
    output.print("Guess a letter or word (? for a hint): ");
  }

  public void printBlankInput() {
    output.println("Please enter a guess: ");
  }
  
  /**
   * Displays the word that the user has to guess.
   */
  public void showTargetWord(GameState gameState) {
    StringBuilder sb = new StringBuilder();
    String targetWord = gameState.getTargetWord();
    ArrayList<Character> correctLetters = gameState.getCorrectLetters();

    for (int i = 0; i < targetWord.length(); i++) {
      char correctChar = Character.toLowerCase(targetWord.charAt(i));
      if (correctLetters.contains(correctChar)) {
        sb.append(targetWord.charAt(i));
      } else {
        sb.append('-');
      }
    }
    output.println(sb);
  }

  public void printNoHints() {
    output.println("No more hints allowed");
  }

  public void printHint(char hint) {
    output.println("Try: " + hint);
  }

  public void printFeedback(boolean correctGuess) {
    if (correctGuess) {
      output.println("Good guess!");
    } else {
      output.println("Wrong guess!");
    }
  }

  public void printGuessesRemaining(GameState gameState) {
    output.println("Guesses remaining: " + gameState.guessesRemaining);
  }

  public void printWinMessage(GameState gameState) {
    output.println("Well done!\nYou took " + gameState.guessesMade + " guess(es)");
    output.println("The word was " + gameState.getTargetWord());
  }

  public void printLoseMessage(GameState gameState) {
    output.println("You lost!\nThe word was " + gameState.targetWord + "\n");
  }

  public void printFileError() {
    output.println("File error");
  }

  public void printIoError() {
    output.println("IO error");
  }
}
