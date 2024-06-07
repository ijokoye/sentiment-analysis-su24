import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class CalculateWordScoresTest {
    // i expect to have 6 tests to cover all possible branches

    @Test
    public void normalTest() {
        Set<Sentence> sentences = new HashSet<>();
        sentences.add(new Sentence(2, "I like cake and eat cake ."));
        sentences.add(new Sentence(1, "I hope the dog eat cake ."));


        Map<String, Double> expected = new HashMap<>();
        expected.put("i", 1.5);
        expected.put("like", 2.0);
        expected.put("cake", 1.667);
        expected.put("and", 2.0);
        expected.put("eat", 1.5);
        expected.put("hope", 1.0);
        expected.put("the", 1.0);
        expected.put("dog", 1.0);

        Map<String, Double> actual = Analyzer.calculateWordScores(sentences);
        assertEquals(expected.size(), actual.size());
        for (String word : expected.keySet()) {
            assertEquals(expected.get(word), actual.get(word), 0.01);
        }
    }

    @Test
    public void nullTest() {
        Set<Sentence> sentences = null;
        Map<String, Double> actual = Analyzer.calculateWordScores(sentences);
        assertTrue(actual.isEmpty());
    }

    @Test
    public void emptyTest() {
        Set<Sentence> sentences = new HashSet<>();
        Map<String, Double> actual = Analyzer.calculateWordScores(sentences);
        assertTrue(actual.isEmpty());
    }

    @Test
    public void lowerCaseTest() {
        Set<Sentence> sentences = new HashSet<>();
        sentences.add(new Sentence(2, "I Like eAt Cake ."));
        sentences.add(new Sentence(1, "I like to Eat pie"));

        Map<String, Double> expected = new HashMap<>();
        expected.put("i", 1.5);
        expected.put("like", 1.5);
        expected.put("eat", 1.5);
        expected.put("cake", 2.0);
        expected.put("to", 1.0);
        expected.put("pie", 1.0);

        Map<String, Double> actual = Analyzer.calculateWordScores(sentences);
        assertEquals(expected.size(), actual.size());
        for (String word : expected.keySet()) {
            assertEquals(expected.get(word), actual.get(word));
        }
    }

    @Test
    public void nonLettersTest() {
        Set<Sentence> sentences = new HashSet<>();
        sentences.add(new Sentence(2, "I Like 'eAt' Cake ."));
        sentences.add(new Sentence(1, "I like .to Eat pie"));

        Map<String, Double> expected = new HashMap<>();
        expected.put("i", 1.5);
        expected.put("like", 1.5);
        expected.put("eat", 1.0);
        expected.put("cake", 2.0);
        expected.put("pie", 1.0);

        Map<String, Double> actual = Analyzer.calculateWordScores(sentences);
        assertEquals(expected.size(), actual.size());
        for (String word : expected.keySet()) {
            assertEquals(expected.get(word), actual.get(word));
        }
    }

    @Test
    public void emptyTextTest() {
        Set<Sentence> sentences = new HashSet<>();
        sentences.add(new Sentence(2, "I Like 'eAt' Cake ."));
        sentences.add(new Sentence(1, "I like .to Eat pie"));
        sentences.add(new Sentence(1, ""));

        Map<String, Double> expected = new HashMap<>();
        expected.put("i", 1.5);
        expected.put("like", 1.5);
        expected.put("eat", 1.0);
        expected.put("cake", 2.0);
        expected.put("pie", 1.0);

        Map<String, Double> actual = Analyzer.calculateWordScores(sentences);
        assertEquals(expected.size(), actual.size());
        for (String word : expected.keySet()) {
            assertEquals(expected.get(word), actual.get(word));
        }
    }


//    @Test(expected = IllegalArgumentException.class)
//    public void testIllegalFile() {
//        Analyzer.calculateWordScores(null);
//
//    }


}