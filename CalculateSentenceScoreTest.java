import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CalculateSentenceScoreTest {

    // ask about this method is denominator size of map or valid words
    // in sentence
    @Test
    public void normalSentenceTest() {
        Map<String, Double> wordScores = new HashMap<>();
        wordScores.put("dogs", 1.0);
        wordScores.put("are", 0.5);
        wordScores.put("cute", 2.0);

        String sentence = "dogs are cute cute";
        double expected = (1.0 + 0.5 + 2.0 + 2.0) / 4;

        double actual = Analyzer.calculateSentenceScore(wordScores, sentence);
        assertEquals(expected, actual, 0.001);

    }


    @Test
    public void nullSentenceTest() {
        Map<String, Double> wordScores = new HashMap<>();
        double actual = Analyzer.calculateSentenceScore(wordScores, null);
        assertEquals(0.0, actual, 0.01);

    }


    @Test
    public void emptySentenceTest() {
        String sentence = "";
        Map<String, Double> wordScores = new HashMap<>();
        double actual = Analyzer.calculateSentenceScore(wordScores, sentence);
        assertEquals(0.0, actual, 0.01);

    }

    @Test
    public void nullWordScoreTest() {
        Map<String, Double> wordScores = null;
        double actual = Analyzer.calculateSentenceScore(wordScores, "The girl is hot");
        assertEquals(0.0, actual, 0.01);

    }

    @Test
    public void emptyWordScoreTest() {
        Map<String, Double> wordScores = new HashMap<>();
        double actual = Analyzer.calculateSentenceScore(wordScores, "The girl is hot");
        assertEquals(0.0, actual, 0.01);

    }


    // check test
    @Test
    public void nonLetterTest() {
        Map<String, Double> wordScores = new HashMap<>();
        wordScores.put("dogs", 1.0);
        wordScores.put("are", 0.5);
        wordScores.put("cute", 2.0);

        String sentence = "dogs 'ate -- are cute funny";
        double expected = (1.0 + 0.5 + 2.0) / 4;

        double actual = Analyzer.calculateSentenceScore(wordScores, sentence);
        assertEquals(expected, actual, 0.001);

    }

    @Test
    public void nonMatchTest() {
        Map<String, Double> wordScores = new HashMap<>();
        wordScores.put("dogs", 1.0);
        wordScores.put("are", 0.5);
        wordScores.put("cute", 2.0);

        String sentence = " 'ate --";
        double expected = 0.0;

        double actual = Analyzer.calculateSentenceScore(wordScores, sentence);
        assertEquals(expected, actual, 0.001);

    }
}