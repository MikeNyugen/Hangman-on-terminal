package p1.hangman;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * This class is responsible for running the game.
 */
public class App {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in, StandardCharsets.UTF_8);
    CommandOptions options = new CommandOptions(args);
    GameOutput gameOutput = new GameOutput(System.in, System.out);

    startGame(input, options, gameOutput);
  }

  /**
   * Starts the game.
   *
   * @param input   the user input
   * @param options the command line options
   */
  static void startGame(Scanner input, CommandOptions options, GameOutput io) {
    io.printMenuOptions();

    GameState gameState;
    int maxGuesses = options.getMaxGuesses();
    int maxHints = options.getMaxHints();
    String wordSource = options.getWordSource();

    if (wordSource.equals("")) {
      int userInput = returnValidInput(input);

      gameState = new GameState(Words.returnRandomWord(userInput), maxGuesses, maxHints);
    } else {
      gameState = new GameState(Words.returnRandomWord(wordSource, io), maxGuesses, maxHints);
    }
    gameLoop(gameState, io);
  }

  /**
   * Ensures that the user enters a valid input.
   * This means that the user must enter an integer.
   * The integer must correspond to a valid category.
   *
   * @param input the user input
   * @return valid integer entered by the user
   */
  public static int returnValidInput(Scanner input) {
    int userInput;
    try {
      userInput = input.nextInt();
      while (Words.returnRandomWord(userInput).equals("INCORRECT CATEGORY")) {
        System.out.println("INCORRECT CATEGORY");
        userInput = input.nextInt();
      }
    } catch (Exception e) {
      System.out.println("INCORRECT CATEGORY");
      input.nextLine();
      userInput = returnValidInput(input);
    }
    return userInput;
  }

  /**
   * Queries the user's guess and runs the game loop.
   *
   * @param gameState the state of the game
   */
  public static void gameLoop(GameState gameState, GameOutput io) {
    while (!gameState.won() && !gameState.lost()) {
      io.showTargetWord(gameState);
      io.printGuessesRemaining(gameState);
      gameState.getGuess(io);
    }
    if (gameState.won()) {
      io.printWinMessage(gameState);
    } else {
      io.printLoseMessage(gameState);
    }
  }
}
