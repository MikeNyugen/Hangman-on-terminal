package p1.hangman;

/**
 * Handles the command line arguments.
 */
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

  /**
   * Processes command line arguments from the user.
   * The user can modify the maximum number of hints and guesses.
   * The user can also specify a file containing custom words.
   * Arguments should be in the format:
   * --guesses integer --hints integer wordSource
   * Default values will be used if no arguments specified
   *
   * @param args the command line arguments
   */
  private void processArguments(String[] args) {
    for (int i = 0; i < args.length; ++i) {
      if (args[i].equals("--guesses")) {
        maxGuesses = Integer.parseInt(args[i + 1]);
        i++;
      } else if (args[i].equals("--hints")) {
        maxHints = Integer.parseInt(args[i + 1]);
        i++;
      } else {
        wordSource = args[i];
      }
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
