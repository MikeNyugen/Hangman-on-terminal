package p1.hangman;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class GameStateTest {

  @Test
  public void test() {

  }

  @Test
  public void getGuessTest() {
    // if user guesses a word
    String userGuess = "Alabama";
    assertEquals(1,1);
  }

  @Test
  public void giveHintTest() {
    //GameOutput output = new GameOutput(System.in, System.out);
    // case 1: user has not received a hint or made a guess
    GameState gameState = new GameState("Alabama", 10, 2);

   // String hint = gameState.hintsGiven.get(0).toString();
    // checks if the hints given is added to the arraylist
   // assertEquals(1,
     //       gameState.getHintsGiven().size());
    // checks whether the hint is in the target word
   // assertTrue(gameState.getTargetWord().contains(hint));
  }

  @Test
  public void guessWordTest() {


  }

}
