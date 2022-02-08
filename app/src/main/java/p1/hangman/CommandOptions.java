package p1.hangman;

public class CommandOptions {

	private int maxGuesses;
	private int maxHints;
	private String wordSource;
	
	CommandOptions(String[] args) {
		this.setMaxGuesses(10);
		this.setMaxHints(2);
		this.setWordSource("");
		
		processArguments(args);
	}

	private void processArguments(String[] args) {
		for (int i = 0; i < args.length; ++i) {
			if (args[i].equals("--guesses")) {
				maxGuesses = Integer.parseInt(args[i+1]);
				i++;
			}
			else if (args[i].equals("--hints")) {
				maxHints = Integer.parseInt(args[i+1]);
				i++;
			}
			else wordSource = args[i];
		}
	}

	public int getMaxGuesses() {
		return maxGuesses;
	}

	private void setMaxGuesses(int maxGuesses) {
		this.maxGuesses = maxGuesses;
	}

	public int getMaxHints() {
		return maxHints;
	}

	private void setMaxHints(int maxHints) {
		this.maxHints = maxHints;
	}

	public String getWordSource() {
		return wordSource;
	}

	public void setWordSource(String wordSource) {
		this.wordSource = wordSource;
	}
	
}
