package p1.hangman;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameOutputTest {
  GameOutput gameOutput;
  ByteArrayOutputStream out;

  @Before
  public void setUp() {
    out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
    gameOutput = new GameOutput(System.in, System.out);
  }

  @Test
  public void menuOptionsTest() {
    String expected = "  1. Counties\n" +
            "  2. Countries\n" +
            "  3. Cities\n" +
            "  4. States\n" +

            "Pick a category: ";
    gameOutput.printMenuOptions();
    assertEquals(expected, out.toString());
  }

  @Test
  public void showTargetWordTest() {
    GameState gameStateMock = mock(GameState.class);

    String targetWord = "England";
    String expected = "-------\n";
    when(gameStateMock.getTargetWord()).thenReturn(targetWord);
    gameOutput.showTargetWord(gameStateMock);
    assertEquals(expected, out.toString());

    out.reset();

    String targetWord2 = "Edinburgh";
    String expected2 = "---------\n";
    when(gameStateMock.getTargetWord()).thenReturn(targetWord2);
    gameOutput.showTargetWord(gameStateMock);
    assertEquals(expected2, out.toString());

    out.reset();

    String targetWord3 = "";
    String expected3 = "\n";
    when(gameStateMock.getTargetWord()).thenReturn(targetWord3);
    gameOutput.showTargetWord(gameStateMock);
    assertEquals(expected3, out.toString());
  }

  @Test
  public void noHintsTest() {
    String expected = "No more hints allowed\n";
    gameOutput.printNoHints();
    assertEquals(expected, out.toString());
  }

  @Test
  public void hintTest() {
    char hint = 'a';
    String expected = "Try: " + hint + "\n";
    gameOutput.printHint(hint);
    assertEquals(expected, out.toString());
  }

  @Test
  public void feedbackTest() {
    boolean correctGuess = true;
    String expectedCorrect = "Good guess!\n";
    String expectedWrong = "Wrong guess!\n";

    gameOutput.printFeedback(correctGuess);
    assertEquals(expectedCorrect, out.toString());

    out.reset();

    correctGuess = false;
    gameOutput.printFeedback(correctGuess);
    assertEquals(expectedWrong, out.toString());
  }

  @Test
  public void guessesRemainingTest() {
    GameState gameStateMock = mock(GameState.class);
    when(gameStateMock.getGuessesRemaining()).thenReturn(10);
    String expected = "Guesses remaining: " + gameStateMock.getGuessesRemaining() + "\n";

    gameOutput.printGuessesRemaining(gameStateMock);
    assertEquals(expected, out.toString());
  }

  @Test
  public void winMessageTest() {
    GameState gameStateMock = mock(GameState.class);
    when(gameStateMock.getGuessesMade()).thenReturn(5);
    when(gameStateMock.getTargetWord()).thenReturn("testword");
    String expected = "Well done!\nYou took " + gameStateMock.getGuessesMade() +
            " guess(es)\nThe word was " + gameStateMock.getTargetWord() + "\n";

    gameOutput.printWinMessage(gameStateMock);
    assertEquals(expected, out.toString());
  }

  @Test
  public void loseMessageTest() {
    GameState gameStateMock = mock(GameState.class);
    when(gameStateMock.getTargetWord()).thenReturn("testword");
    String expected = "You lost!\nThe word was " + gameStateMock.getTargetWord() + "\n";

    gameOutput.printLoseMessage(gameStateMock);
    assertEquals(expected, out.toString());
  }

  @Test
  public void fileErrorTest() {
    String expected = "File error\n";

    gameOutput.printFileError();
    assertEquals(expected, out.toString());
  }

  @Test
  public void ioErrorTest() {
    String expected = "IO error\n";

    gameOutput.printIoError();
    assertEquals(expected, out.toString());
  }
}
