package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

//CITATION: this code is based on JsonSerializationDemo

@ExcludeFromJacocoGeneratedReport
public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            QuoteList ql = reader.readQuotes();
            AuthorList al = reader.readAuthors();
            Score s = reader.readScore();
            assertEquals(ql, ql);
            assertEquals(al, al);
            assertEquals(s, s);
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyQuoteList() {
        JsonReader reader = new JsonReader("./data/Testing/testReaderEmptyQuoteList.json");
        try {
            QuoteList ql = reader.readQuotes();
            assertEquals(0, ql.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralQuoteList() {
        JsonReader reader = new JsonReader("./data/Testing/testReaderGeneralQuoteList.json");
        try {
            QuoteList ql = reader.readQuotes();
            List<Quote> quotes = ql.getQuotes();
            assertEquals(2, quotes.size());
            checkQuote("I am Iron Man", "Tony Stark", quotes.get(0));
            checkQuote("I can do this all day", "Steve Rogers", quotes.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEmptyAuthorList() {
        JsonReader reader = new JsonReader("./data/Testing/testReaderEmptyAuthorList.json");
        try {
            AuthorList al = reader.readAuthors();
            assertEquals(0, al.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralAuthorList() {
        JsonReader reader = new JsonReader("./data/Testing/testReaderGeneralAuthorList.json");
        try {
            AuthorList al = reader.readAuthors();
            List<Author> authors = al.getAuthorList();
            assertEquals(2, authors.size());
            checkAuthor("Evan", authors.get(0));
            checkAuthor("Ani", authors.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderZeroScore() {
        JsonReader reader = new JsonReader("./data/Testing/testReaderZeroScore.json");
        try {
            Score score = reader.readScore();
            checkScore(0, score);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
