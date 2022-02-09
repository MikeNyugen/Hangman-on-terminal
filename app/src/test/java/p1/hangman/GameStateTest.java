package p1.hangman;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GameStateTest {
  GameState gameState;
  GameState gameState2;
  GameState gameState3;
  GameState gameState4;

  @Test
  public void giveHintValidTest() {
    GameOutput gameOutput = mock(GameOutput.class);
    String targetWord = "Alabama";
    gameState = new GameState(targetWord, 10, 2);

    // test hint is valid
    gameState.giveHint(gameOutput);
    String hintString = gameState.getHintsGiven().get(0).toString();
    assertTrue(targetWord.contains(hintString)); // hint should be in the target word

    // test hint not already given or guessed
    String targetWord2 = "Italy";
    gameState2 = new GameState(targetWord2, 10, 3);
    gameState2.addCorrectLetter('i');
    gameState2.addCorrectLetter('t');
    gameState2.addCorrectLetter('a');
    gameState2.addCorrectLetter('l');

    gameState2.giveHint(gameOutput);
    char hint2 = gameState2.getHintsGiven().get(0);
    assertEquals('y', hint2);
    assertFalse(gameState2.getCorrectLetters().contains(hint2));

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
    gameState = new GameState(targetWord, 10, 2);
    gameState2 = new GameState(targetWord2, 10, 2);
    GameOutput gameOutput = mock(GameOutput.class);

    // test user guesses word correctly
    gameState.guessWord(targetWord, gameOutput);
    assertEquals(0, gameState.getRemainingLetters());

    // test user guesses word wrong
    gameState2.guessWord("Germany", gameOutput);
    int expectedRemainingLetters = targetWord2.length();
    int actualRemainingLetters = gameState2.getRemainingLetters();
    assertEquals(expectedRemainingLetters, actualRemainingLetters);
  }

  @Test
  public void guessLetterTest() {
    GameOutput gameOutput = mock(GameOutput.class);
    String targetWord = "Scotland";
    gameState = new GameState(targetWord, 10, 2);

    // test user guesses letter correctly
    gameState.guessLetter('s', gameOutput);
    ArrayList<Character> expectedCorrectLetters = new ArrayList<>();
    expectedCorrectLetters.add('s');
    ArrayList<Character> actualCorrectLetters = gameState.getCorrectLetters();

    assertEquals(expectedCorrectLetters, actualCorrectLetters);

    // test user guesses letter incorrectly
    String targetWord2 = "England";
    gameState2 = new GameState(targetWord2, 10, 2);
    gameState2.guessLetter('m', gameOutput);

    assertEquals(0, gameState2.getCorrectLetters().size());
    assertEquals(8, targetWord.length());

    // test user guesses final letter and wins game
    gameState2.addCorrectLetter('e');
    gameState2.addCorrectLetter('n');
    gameState2.addCorrectLetter('g');
    gameState2.addCorrectLetter('l');
    gameState2.addCorrectLetter('a');
    gameState2.addCorrectLetter('n');

    gameState2.guessLetter('d', gameOutput);
    assertEquals(0, gameState2.getRemainingLetters());
    assertTrue(gameState2.won());

    // test user runs out of guesses and loses game
    String targetWord3 = "Ireland";
    gameState3 = new GameState(targetWord3, 1, 2);
    gameState3.guessLetter('p', gameOutput);
    assertEquals(0, gameState3.getGuessesRemaining());
    assertTrue(gameState3.lost());

    // test user guesses a non-letter
    String targetWord4 = "Greece";
    gameState4 = new GameState(targetWord4, 10, 2);
    gameState4.guessLetter('9', gameOutput);

    assertEquals(0, gameState4.getCorrectLetters().size());
    assertEquals(6, targetWord4.length());
  }

  @Test
  public void findOccurrencesTest() {
    gameState = new GameState("word", 10, 2);

    String hawaii = "Hawaii";
    // 'i' test
    ArrayList<Integer> output1 = new ArrayList<>(Arrays.asList(4, 5));
    assertEquals(output1, gameState.findOccurrences('i', hawaii));
    // 'a' test
    ArrayList<Integer> output2 = new ArrayList<>(Arrays.asList(1, 3));
    assertEquals(output2, gameState.findOccurrences('a', hawaii));
    // no occurrence test
    ArrayList<Integer> noOccurrence = new ArrayList<>(List.of());
    assertEquals(noOccurrence, gameState.findOccurrences('x', hawaii));

    String aberdeen = "Aberdeen";
    // 'e' test
    ArrayList<Integer> output3 = new ArrayList<>(Arrays.asList(2, 5, 6));
    assertEquals(output3, gameState.findOccurrences('e', aberdeen));
    // 'd' test
    ArrayList<Integer> output4 = new ArrayList<>(List.of(4));
    assertEquals(output4, gameState.findOccurrences('d', aberdeen));

    // empty word test
    String emptyString = "";
    assertEquals(noOccurrence, gameState.findOccurrences('y', emptyString));
    assertEquals(noOccurrence, gameState.findOccurrences('9', emptyString));
    assertEquals(noOccurrence, gameState.findOccurrences('q', emptyString));
  }

  @Test
  public void updateGuessesTest() {
    gameState = new GameState("word", 10, 2);
    // after 1 guess
    gameState.updateGuesses();
    assertEquals(9, gameState.getGuessesRemaining());
    assertEquals(1, gameState.getGuessesMade());

    // after 3 guesses
    gameState.updateGuesses();
    gameState.updateGuesses();
    assertEquals(7, gameState.getGuessesRemaining());
    assertEquals(3, gameState.getGuessesMade());
  }
}
