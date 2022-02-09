package p1.hangman;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class GameStateTest {
  GameState gameState;
  GameState gameStateMock;

  @Before
  public void setup() {

  }

  @Test
  public void giveHintValidTest() {
    GameOutput gameOutput = mock(GameOutput.class);
    String targetWord = "Alabama";
    gameState = new GameState(targetWord, 10, 2);
    ArrayList<Character> correctLetters = gameState.getCorrectLetters();
    ArrayList<Character> hintsGiven = gameState.getHintsGiven();

    // test hint is valid
    gameState.giveHint(gameOutput);
    char hint = gameState.hintsGiven.get(0);
    verify(gameOutput).printHint(hint); // hint should be printed
    String hintString = gameState.hintsGiven.get(0).toString();
    assertTrue(targetWord.contains(hintString)); // hint should be in the target word

    // test hint not already given or guessed
    String targetWord2 = "Italy";
    GameState gameState2 = new GameState(targetWord2, 10 , 3);
    ArrayList<Character> correctLetters2 = gameState2.getCorrectLetters();
    gameState2.addCorrectLetter('i');
    gameState2.addCorrectLetter('t');
    gameState2.addCorrectLetter('a');
    gameState2.addCorrectLetter('l');

    gameState2.giveHint(gameOutput);
    char hint2 = gameState2.getHintsGiven().get(0);
    verify(gameOutput).printHint(hint2); // hint should be printed
    assertEquals('y', hint2);
    assertFalse(correctLetters2.contains(hint2));

    // test no hints remaining
    gameState.setHintsRemaining(0);
    gameState.giveHint(gameOutput);
    verify(gameOutput).printNoHints();

    // test hints remaining is decremented
    assertEquals(0, gameState.getHintsRemaining());
    assertEquals(2, gameState2.getHintsRemaining());
  }


  @Test
  public void guessWordTest() {
    String targetWord = "Belgium";
    String targetWord2 = "France";
    GameState gameState = new GameState(targetWord, 10, 2);
    GameState gameState2 = new GameState(targetWord2, 10, 2);
    GameOutput gameOutput = mock(GameOutput.class);

    // test user guesses word correctly
    gameState.guessWord(targetWord, false, gameOutput);
    assertEquals(0, gameState.getRemainingLetters());
    verify(gameOutput).printFeedback(true);

    // test user guesses word wrong
    gameState2.guessWord("Germany", false, gameOutput);
    int expectedRemainingLetters = targetWord2.length();
    int actualRemainingLetters = gameState2.getRemainingLetters();
    assertEquals(expectedRemainingLetters, actualRemainingLetters);
    verify(gameOutput).printFeedback(false);
  }

  @Test
  public void guessLetterTest() {
    // test user guesses letter correctly

    // test user guesses final letter and wins game

    // test user runs out of guesses and loses game

  }

  @Test
  public void findOccurrencesTest() {
    // multiple tests using different inputs
  }

  @Test
  public void updateGuessesTest() {

  }

}
