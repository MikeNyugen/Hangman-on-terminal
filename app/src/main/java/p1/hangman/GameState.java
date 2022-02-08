package p1.hangman;

import java.util.ArrayList;
import java.util.Scanner;

public class GameState {
    public String targetWord;
    public int guessesMade;
    public int guessesRemaining;
    public int hintsRemaining;

    ArrayList<Character> correctLetters;
    int remainingLetters;
    ArrayList<Character> hintsGiven;

    public Scanner sc = new Scanner(System.in).useDelimiter("\n");

    public GameState(String targetWord, int guesses, int hints) {
        this.targetWord = targetWord;
        this.guessesMade = 0;
        this.guessesRemaining = guesses;
        this.hintsRemaining = hints;

        remainingLetters = targetWord.length();
        correctLetters = new ArrayList<Character>();
        hintsGiven = new ArrayList<Character>();
    }

    public void showTargetWord() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < targetWord.length(); i++) {
            if (correctLetters.contains(Character.toLowerCase(targetWord.charAt(i)))) {
                sb.append(targetWord.charAt(i));
            } else {
                sb.append('-');
            }
        }
        System.out.println(sb.toString());
    }

    public void getGuess() {
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
            char hint = Character.toLowerCase(targetWord.charAt((int) (Math.random() * targetWord.length())));
            while (hintsGiven.contains(hint) || correctLetters.contains(hint)) {
                hint = targetWord.charAt((int) (Math.random() * targetWord.length()));
            }
            System.out.println("Try: " + hint);
            hintsGiven.add(hint);
            hintsRemaining--;
        }
    }

    private void guessWord(String userGuess, boolean guessCorrect) {
        if (userGuess.equalsIgnoreCase(targetWord)) {
            remainingLetters = 0;
            guessCorrect = true;
        }
        printFeedback(guessCorrect);
        updateGuesses(guessCorrect);
    }

    public void guessLetter(char letter, boolean guessCorrect) {
        for (int i = 0; i < targetWord.length(); i++) {
            if (Character.toLowerCase(targetWord.charAt(i)) == letter && !correctLetters.contains(letter)) {
                System.out.println(i);
                ArrayList<Integer> occurrences = findOccurrences(letter, targetWord);
                guessCorrect = true;
                correctLetters.add(letter);
                System.out.println(correctLetters.get(0));
                for (int j = 0; j < occurrences.size(); j++) {
                    remainingLetters--;
                }
            }
        }
        printFeedback(guessCorrect);
        updateGuesses(guessCorrect);
    }

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

    private void updateGuesses(boolean guessCorrect) {
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
