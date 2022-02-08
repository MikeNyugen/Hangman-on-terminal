package p1.hangman;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
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
    output.print("""
              1. Counties
              2. Countries
              3. Cities
              4. States

            Pick a category
            """);
  }

  public void printPrompt() {
    output.print("Guess a letter or word (? for a hint): ");
  }

  public void printBlankInput() {
    output.println("Please enter a guess: ");
  }

  public void printIncorrect() {
    output.println("INCORRECT CATEGORY");
  }

  public void printTargetWord(String targetWord) {
    output.println(targetWord);
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
