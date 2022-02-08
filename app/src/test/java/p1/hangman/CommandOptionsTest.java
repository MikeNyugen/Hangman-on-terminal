package p1.hangman;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommandOptionsTest {

	@Test
	public void optionsTest() {
		String[] args = { "--guesses", "2", "--hints", "4", "words.txt" };
		CommandOptions opts = new CommandOptions(args);
		assertEquals(opts.getMaxGuesses(), 2);
		assertEquals(opts.getMaxHints(), 4);
		assertEquals(opts.wordSource, "words.txt");
	}
}
