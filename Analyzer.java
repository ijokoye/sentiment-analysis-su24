

import java.util.*;

public class Analyzer {
	

	/**
	 * This method calculates the weighted average for each word in all the Sentences.
	 * This method is case-insensitive and all words should be stored in the Map using
	 * only lowercase letters.
	 * 
	 * @param sentences Set containing Sentence objects with words to score
	 * @return Map of each word to its weighted average; or an empty Map if the Set of
	 * Sentences is empty or null.
	 */
	public static Map<String, Double> calculateWordScores(Set<Sentence> sentences) {
		/*
		 * Implement this method in Part 2
		 */

		// error handling
		if (sentences == null || sentences.isEmpty()) {
			return new HashMap<>();
		}

		Map<String, Double> wordScore = new HashMap<>();
		Map<String, Integer> wordFreq = new HashMap<>();

		for (Sentence sentence : sentences) {
			int score = sentence.getScore();
			String text = sentence.getText();

			// error handling
			if (text == null || text.isEmpty()) {
				continue;
			}

			StringTokenizer tokenizer = new StringTokenizer(text);

			// while there are more "words" in text
			while (tokenizer.hasMoreTokens()) {
				// change "word" to lower case
				String token = tokenizer.nextToken().toLowerCase();

				// only if there is something and the first character is a letter
				if (!token.isEmpty() && Character.isLetter(token.charAt(0))) {
					// increase frequency by one or 0 if word exists
					wordScore.put(token, wordScore.getOrDefault(token, 0.0) + score);
					wordFreq.put(token, wordFreq.getOrDefault(token, 0) + 1);
				}

			}


		}

		for (Map.Entry<String, Double> entry : wordScore.entrySet()) {
			int wordFrequency = wordFreq.get(entry.getKey());
			double totalSum = entry.getValue();
			double average = totalSum / wordFrequency;
			wordScore.put(entry.getKey(), average);
		}
		return wordScore;
	}
	
	/**
	 * This method determines the sentiment of the input sentence using the average of the
	 * scores of the individual words, as stored in the Map.
	 * This method is case-insensitive and all words in the input sentence should be
	 * converted to lowercase before searching for them in the Map.
	 * 
	 * @param wordScores Map of words to their weighted averages
	 * @param sentence Text for which the method calculates the sentiment
	 * @return Weighted average scores of all words in input sentence; or 0 if any error occurs
	 */
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {
		/*
		 * Implement this method in Part 3
		 */

		// Check for null or empty input
		if (sentence == null || sentence.isEmpty() || wordScores == null || wordScores.isEmpty()) {
			return 0.0;
		}

		StringTokenizer tokenizer = new StringTokenizer(sentence);

		double runningTotal = 0.0;
		int wordCount = 0;
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			if (!token.isEmpty() && Character.isLetter(token.charAt(0))) {
				// get words score or set to 0 if not found
				// then add to running total
				double wordScore = wordScores.getOrDefault(token, 0.0);
				runningTotal += wordScore;
				wordCount++;

			}
		}

		// calculate average, return 0 if there are no valid words
		if (wordCount == 0) {
			return 0.0;
		}
		return runningTotal / wordCount;
	}


}
