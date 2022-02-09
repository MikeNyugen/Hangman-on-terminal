package p1.hangman;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandOptionsTest {

  @Test
  public void optionsTest() {
    String[] args = {"--guesses", "2", "--hints", "4", "words.txt"};
    CommandOptions opts = new CommandOptions(args);
    assertEquals(opts.getMaxGuesses(), 2);
    assertEquals(opts.getMaxHints(), 4);
    assertEquals(opts.getWordSource(), "words.txt");

    String[] args2 = {"--guesses", "14", "--hints", "10", "moreWords.txt"};
    CommandOptions opts2 = new CommandOptions(args2);
    assertEquals(opts2.getMaxGuesses(), 14);
    assertEquals(opts2.getMaxHints(), 10);
    assertEquals(opts2.getWordSource(), "moreWords.txt");
  }
}
