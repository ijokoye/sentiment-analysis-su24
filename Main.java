import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * Main class for the sentiment analysis program.
 * 
 */
public class Main {
	
	public static void main(String[] args) {
		
		/* Implement this in Part 4 */
		if (args.length == 0) {
			System.err.println("no input file argument was passed");
			return;
		}
		Set<Sentence> sentence;
		try {
			String filename = args[0];
//			String filename = "reviews.txt";
			sentence = Reader.readFile(filename);

		} catch (IllegalArgumentException e) {
			System.err.println("File input has a problem - check if valid");
			return;
		}
		Map<String, Double> wordScores = Analyzer.calculateWordScores(sentence);
		Scanner scanner = new Scanner(System.in);
		String singleSentence;
		while (!(singleSentence = scanner.nextLine()).equals("quit")) {
			double average = Analyzer.calculateSentenceScore(wordScores, singleSentence);
			System.out.println("The sentiment score of this sentence is " + average);
		}
		scanner.close();





		
	}

}
