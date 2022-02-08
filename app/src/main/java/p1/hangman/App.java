package p1.hangman;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        CommandOptions options = new CommandOptions(args);
        startGame(input, options);
    }

    static void startGame(Scanner input, CommandOptions options) {
        printOptions();
        GameState gameState;
        int maxGuesses = options.getMaxGuesses();
        int maxHints = options.getMaxHints();
        String wordSource = options.getWordSource();
        if (wordSource.equals("")) {
            int userInput = returnValidInput(input);

            gameState = new GameState(Words.returnRandomWord(userInput), maxGuesses, maxHints);
        } else {
            gameState = new GameState(Words.returnRandomWord(wordSource), maxGuesses, maxHints);
        }
        gameLoop(gameState);
    }

    public static void printOptions() {
        System.out.print(
                """
                          1. Counties
                          2. Countries
                          3. Cities
                          4. States

                        Pick a category
                        """);
    }

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

    public static void gameLoop(GameState gameState) {
        while (!gameState.won() && !gameState.lost()) {
            gameState.showTargetWord();
            System.out.println("Guesses remaining: " + gameState.guessesRemaining);
            gameState.getGuess();
        }
        if (gameState.won()) {
            System.out.print("Well done!\nYou took " + gameState.guessesMade + " guess(es)\n");
            System.out.println("The word was " + gameState.getTargetWord());
        } else {
            System.out.print("You lost!\nThe word was " + gameState.targetWord + "\n");
        }
    }
}
