package persistence;

import model.Quote;
import model.Author;
import model.Score;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

//CITATION: this code is based on JsonSerializationDemo

@ExcludeFromJacocoGeneratedReport
public class JsonTest {
    protected void checkQuote(String quoteName, String answer, Quote quote) {
        assertEquals(quoteName, quote.getQuote());
        assertEquals(answer, quote.getAnswer());
    }

    protected void checkAuthor(String authorName, Author author) {
        assertEquals(authorName, author.getName());
    }

    protected void checkScore(int scoreValue, Score score) {
        assertEquals(scoreValue, score.getScore());
    }
}
