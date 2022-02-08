package p1.hangman;

import java.io.*;
import java.util.ArrayList;

public class Words {

	static final String[] COUNTIES = { "Argyll and Bute", "Caithness",  "Kingdom of Fife",
			            "East Lothian", "Highland", "Dumfries and Galloway",
			            "Renfrewshire", "Scottish Borders", "Perth and Kinross" };

	static final String[] COUNTRIES = { "Scotland", "England", "Wales", "Northern Ireland", "Ireland", 
			            "France", "Germany", "Netherlands", "Spain", "Portugal",
			            "Belgium", "Luxembourg", "Switzerland", "Italy", "Greece" };

	static final String[] CITIES = { "St Andrews", "Edinburgh", "Glasgow", "Kirkcaldy", "Perth",
			            "Dundee", "Stirling", "Inverness", "Aberdeen", "Falkirk" };

	static final String[] STATES = { "Alabama", "Florida", "California", "Hawaii", "Michigan", "Montana",
						"Nevada", "Texas", "Oregan", "Utah", "Kentucky", "Indiana" };
						
	static ArrayList<String> customWords;
	
	public static String returnRandomWord(int category) {
		if (category == 1)
			return COUNTIES[(int) (Math.random() * COUNTIES.length)];
		if (category == 2)
			return COUNTRIES[(int) (Math.random() * COUNTRIES.length)];
		if (category == 3)
			return CITIES[(int) (Math.random() * CITIES.length)];
		if (category == 4)
			return STATES[(int) (Math.random() * STATES.length)];
		return "INCORRECT CATEGORY";
	}
	
	public static String returnRandomWord(String wordSource) {
		String line;
		customWords = new ArrayList<String>();
		
		try {
			FileReader file = new FileReader(wordSource);
			BufferedReader reader = new BufferedReader(file);
			while ((line = reader.readLine()) != null) {
                customWords.add(line);
            }
			reader.close();
			return customWords.get((int) (Math.random() * customWords.size()));
		} catch (FileNotFoundException e) {
			System.out.println("File error");
			return "FILE ERROR";
		} catch (IOException e) {
			System.out.println("IO error");
			return "IO ERROR";
		}
	}
}
