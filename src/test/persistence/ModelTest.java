package persistence;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import model.Author;
import model.AuthorList;
import model.Quote;
import model.QuoteList;
import model.Score;

public class ModelTest {
    private Quote testQuote;
    private Author testAuthor;
    private Author testAuthor2;
    private Score testScore;
    private QuoteList testQuoteList;
    private AuthorList testAuthorList;

    @BeforeEach
    void runBefore() {
        testQuote = new Quote("I love you!", "Evan");
        testAuthor = new Author("Evan");
        testAuthor2 = new Author("Ani");
        testScore = new Score(0);
        testQuoteList = new QuoteList("TestQuoteList");
        testAuthorList = new AuthorList("TestAuthorList");
    }

    @Test
    void testQuote() {
        assertEquals("I love you!", testQuote.getQuote());
        assertEquals("Evan", testQuote.getAnswer());
    }

    @Test
    void testAuthor() {
        assertEquals("Evan", testAuthor.getName());
    }

    @Test
    void testQuoteList() {
        assertEquals("TestQuoteList", testQuoteList.getCategory());
        assertEquals("TestAuthorList", testAuthorList.getCategory());
        assertEquals(0, testQuoteList.getSize());
        testQuoteList.addQuote(testQuote);
        assertEquals(1, testQuoteList.getSize());
        assertEquals(testQuote, testQuoteList.getQuote(0));
        testQuoteList.addLoggedQuote(testQuote, testQuoteList.getCategory());
        assertEquals(2, testQuoteList.getSize());
        assertEquals(testQuote, testQuoteList.getQuote(1));
    }

    @Test
    void testAuthorList() {
        assertEquals(0, testAuthorList.getSize());
        testAuthorList.addAuthor(testAuthor);
        assertEquals(1, testAuthorList.getSize());
        assertEquals(testAuthor, testAuthorList.getAuthor(0));
        assertEquals(testAuthor, testAuthorList.getAuthorList().get(0));
        testAuthorList.removeAuthor(testAuthor.getName());
        assertEquals(0, testAuthorList.getSize());
    }

    

    @Test
    void testRemove() {
        assertEquals(0, testAuthorList.getSize());
        testAuthorList.addAuthor(testAuthor);
        testAuthorList.addAuthor(testAuthor2);
        assertEquals(2, testAuthorList.getSize());
        testAuthorList.removeAuthor(testQuote.getAnswer());
        testAuthorList.removeAuthor("a");
        assertEquals(1, testAuthorList.getSize());
    }

    @Test
    void testScore() {
        assertEquals(0, testScore.getScore());
        testScore.addScore();
        assertEquals(10, testScore.getScore());
        testScore.addScore();
        testScore.addScore();
        testScore.addScore();
        assertEquals(40, testScore.getScore());
        testScore.addLoggedScore(testQuoteList.getCategory());
        assertEquals(50, testScore.getScore());
        testScore.resetScore();
        assertEquals(0, testScore.getScore());
        testScore.addSuperScore();
        testScore.addLoggedSuperScore(testQuoteList.getCategory());
        assertEquals(2000, testScore.getScore());
        testScore.resetLoggedScore(testQuoteList.getCategory());
        assertEquals(0, testScore.getScore());
    }
}
