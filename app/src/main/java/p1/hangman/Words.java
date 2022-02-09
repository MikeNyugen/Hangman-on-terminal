package p1.hangman;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Responsible for defining the words that the user can guess.
 */
public class Words {

  static final String[] COUNTIES = {"Argyll and Bute", "Caithness", "Kingdom of Fife",
      "East Lothian", "Highland", "Dumfries and Galloway",
      "Renfrewshire", "Scottish Borders", "Perth and Kinross"};

  static final String[] COUNTRIES = {"Scotland", "England", "Wales", "Northern Ireland", "Ireland",
      "France", "Germany", "Netherlands", "Spain", "Portugal",
      "Belgium", "Luxembourg", "Switzerland", "Italy", "Greece"};

  static final String[] CITIES = {"St Andrews", "Edinburgh", "Glasgow", "Kirkcaldy", "Perth",
      "Dundee", "Stirling", "Inverness", "Aberdeen", "Falkirk"};

  static final String[] STATES = {"Alabama", "Florida", "California",
      "Hawaii", "Michigan", "Montana",
      "Nevada", "Texas", "Oregan", "Utah", "Kentucky", "Indiana"};

  static ArrayList<String> customWords;

  /**
   * Provides a random word depending on the chosen category.
   *
   * @param category the category chosen by the user
   * @return a random word in the definied word lists.
   */
  public static String returnRandomWord(int category) {
    if (category == 1) {
      return COUNTIES[(int) (Math.random() * COUNTIES.length)];
    }
    if (category == 2) {
      return COUNTRIES[(int) (Math.random() * COUNTRIES.length)];
    }
    if (category == 3) {
      return CITIES[(int) (Math.random() * CITIES.length)];
    }
    if (category == 4) {
      return STATES[(int) (Math.random() * STATES.length)];
    }
    return "INCORRECT CATEGORY";
  }

  /**
   * Provides a random word from a file defined by the user.
   * Gives the user the option to add custom words.
   *
   * @param wordSource the location of the word file
   * @return a random word from a user defined file
   */
  public static String returnRandomWord(String wordSource, GameOutput output) {
    String line;
    customWords = new ArrayList<String>();

    try {
      FileInputStream inputStream = new FileInputStream(wordSource);
      InputStreamReader file = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
      BufferedReader reader = new BufferedReader(file);
      while ((line = reader.readLine()) != null) {
        customWords.add(line);
      }
      reader.close();
      return customWords.get((int) (Math.random() * customWords.size()));
    } catch (FileNotFoundException e) {
      //System.out.println("File error");
      output.printFileError();
      return "FILE ERROR";
    } catch (IOException e) {
      //System.out.println("IO error");
      output.printIoError();
      return "IO ERROR";
    }
  }
}
