package persistence;

import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

//CITATION: this code is based on JsonSerializationDemo

@ExcludeFromJacocoGeneratedReport
public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/Testing/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyQuoteList() {
        try {
            QuoteList ql = new QuoteList("Test");
            JsonWriter writer = new JsonWriter("./data/Testing/testWriterEmptyQuoteList.json");
            writer.open();
            writer.writeQuote(ql);
            writer.close();

            JsonReader reader = new JsonReader("./data/Testing/testWriterEmptyQuoteList.json");
            ql = reader.readQuotes();
            assertEquals(0, ql.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralQuoteList() {
        try {
            QuoteList ql = new QuoteList("Test");
            ql.addQuote(new Quote("I am Iron Man", "Tony Stark"));
            ql.addQuote(new Quote("I can do this all day", "Steve Rogers"));
            JsonWriter writer = new JsonWriter("./data/Testing/testWriterGeneralQuoteList.json");
            writer.open();
            writer.writeQuote(ql);
            writer.close();

            JsonReader reader = new JsonReader("./data/Testing/testWriterGeneralQuoteList.json");
            ql = reader.readQuotes();
            List<Quote> quotes = ql.getQuotes();
            assertEquals(2, quotes.size());
            checkQuote("I am Iron Man", "Tony Stark", quotes.get(0));
            checkQuote("I can do this all day", "Steve Rogers", quotes.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyAuthorList() {
        try {
            AuthorList al = new AuthorList("Test");
            JsonWriter writer = new JsonWriter("./data/Testing/testWriterEmptyAuthorList.json");
            writer.open();
            writer.writeAuthor(al);
            writer.close();

            JsonReader reader = new JsonReader("./data/Testing/testWriterEmptyAuthorList.json");
            al = reader.readAuthors();
            assertEquals(0, al.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralAuthorList() {
        try {
            AuthorList al = new AuthorList("Test");
            al.addAuthor(new Author("Evan"));
            al.addAuthor(new Author("Ani"));
            JsonWriter writer = new JsonWriter("./data/Testing/testWriterGeneralAuthorList.json");
            writer.open();
            writer.writeAuthor(al);
            writer.close();

            JsonReader reader = new JsonReader("./data/Testing/testWriterGeneralAuthorList.json");
            al = reader.readAuthors();
            List<Author> authors = al.getAuthorList();
            assertEquals(2, authors.size());
            checkAuthor("Evan", authors.get(0));
            checkAuthor("Ani", authors.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterZeroScore() {
        try {
            Score score = new Score(0);
            JsonWriter writer = new JsonWriter("./data/Testing/testWriterZeroScore.json");
            writer.open();
            writer.writeScore(score);
            writer.close();

            JsonReader reader = new JsonReader("./data/Testing/testWriterZeroScore.json");
            score = reader.readScore();
            checkScore(0, score);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
