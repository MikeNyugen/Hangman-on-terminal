package p1.hangman;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommandOptsTest {

	@Test
	public void optionsTest() {
		String[] args = { "--guesses", "2", "--hints", "4", "words.txt" };
		CommandOpts opts = new CommandOpts(args);
		assertEquals(opts.maxGuesses, 2);
		assertEquals(opts.maxHints, 4);
		assertEquals(opts.wordSource, "words.txt");
	}
}
