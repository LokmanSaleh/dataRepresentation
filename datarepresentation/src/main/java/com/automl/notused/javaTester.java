package com.automl.notused;

import java.util.Map;
import java.util.TreeMap;

public class javaTester {

	public static void main(String args[]) {

		Map<String, String> gfg = new TreeMap<String, String>();

		// enter name/url pair
		gfg.put("GFG", "geeksforgeeks.org");
		gfg.put("Practice", "practice.geeksforgeeks.org");
		gfg.put("Code", "code.geeksforgeeks.org");
		gfg.put("Quiz", "quiz.geeksforgeeks.org");

		// using for-each loop for
		// iteration over TreeMap.entrySet()
		for (Map.Entry<String, String> entry : gfg.entrySet())
			System.out.println("[" + entry.getKey() + ", " + entry.getValue() + "]");

	}
}