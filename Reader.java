

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("CheckStyle")
public class Reader {
	/**
	 * This method reads sentences from the input file, creates a Sentence object
	 * for each, and returns a Set of the Sentences.
	 * 
	 * @param filename Name of the input file to be read
	 * @return Set containing one Sentence object per sentence in the input file
	 * @throws IllegalArgumentException if filename is null
	 */
	public static Set<Sentence> readFile(String filename) {
		/*
		 * Implement this method in Part 1
		 */
		Set<Sentence> result = new HashSet<>();
		try {
			// read file
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;

			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(" ", 2);
				// error checking - make sure it has score and text
				if (parts.length != 2) {
					continue;
				}

				int score;
				try {
					score = Integer.parseInt(parts[0]);
					if (score > 2 || score < -2) {
						continue;
					}


					String sentence = parts[1];

//					for (Sentence sent : result) {
//						if (result.contains(sent)) {
//							continue;
//						}
////					if ((score == sent.getScore()) && sentence.equals(sent.getText())) {
////						// it's already in set, therefore ignore
////						continue;
////					}
//					}

					Sentence newSentence = new Sentence(score, sentence);
					result.add(newSentence);

				} catch (NumberFormatException e) {
					// skip when it's not an integer
					continue;
				}

			}
		} catch (IOException e) {
			throw new IllegalArgumentException("Trouble reading file");
		}


		return result;
	}
}
