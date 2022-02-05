package p1.hangman;

import java.io.*;
import java.util.ArrayList;

public class Words {

	static String[] counties = { "Argyll and Bute", "Caithness",  "Kingdom of Fife",
			            "East Lothian", "Highland", "Dumfries and Galloway",
			            "Renfrewshire", "Scottish Borders", "Perth and Kinross" };
	static String[] countries = { "Scotland", "England", "Wales", "Northern Ireland", "Ireland", 
			            "France", "Germany", "Netherlands", "Spain", "Portugal",
			            "Belgium", "Luxembourg", "Switzerland", "Italy", "Greece" };
	static String[] cities = { "St Andrews", "Edinburgh", "Glasgow", "Kirkcaldy", "Perth",
			            "Dundee", "Stirling", "Inverness", "Aberdeen", "Falkirk" };
	static String[] states = { "Alabama", "Florida", "California", "Hawaii", "Michigan", "Montana",
						"Nevada", "Texas", "Oregan", "Utah", "Kentucky", "Indiana" };
	static ArrayList<String> customwords;
	
	public static String returnRandomWord(int category) {
		if (category == 1)
			return counties[(int)(Math.random()*counties.length)];
		if (category == 2)
			return countries[(int)(Math.random()*countries.length)];
		if (category == 3)
			return cities[(int)(Math.random()*cities.length)];
		if (category == 4)
			return states[(int)(Math.random()*states.length)];
		return "INCORRECT CATEGORY";
	}
	
	public static String returnRandomWord(String wordsource) {
		String line;
		customwords = new ArrayList<String>();
		
		try {
			FileReader file = new FileReader(wordsource);
			BufferedReader reader = new BufferedReader(file);
			while((line = reader.readLine()) != null) {
                customwords.add(line);
            }
			return customwords.get((int)(Math.random()*customwords.size()));
		} catch(FileNotFoundException e) {
			System.out.println("File error");
			return "FILE ERROR";
		} catch(IOException e) {
		System.out.println("IO error");
		return "IO ERROR";
		}
	}
}
