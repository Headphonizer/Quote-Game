package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

import model.Author;
import model.AuthorList;
import model.Quote;
import model.QuoteList;
import model.Score;

//CITATION: this code is based on JsonSerializationDemo

// Represents a reader that reads QuoteList and AuthorList from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads QuoteList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public QuoteList readQuotes() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseQuoteList(jsonObject);
    }

    // EFFECTS: reads QuoteList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public AuthorList readAuthors() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAuthorList(jsonObject);
    }

    // EFFECTS: reads QuoteList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Score readScore() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseScore(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses QuoteList from JSON object and returns it
    private QuoteList parseQuoteList(JSONObject jsonObject) {
        String category = jsonObject.getString("category");
        QuoteList ql = new QuoteList(category);
        addQuotes(ql, jsonObject);
        return ql;
    }

    // MODIFIES: ql
    // EFFECTS: parses quotes from JSON object and adds them to QuoteList
    private void addQuotes(QuoteList ql, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("quotes");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addQuote(ql, nextThingy);
        }
    }

    // MODIFIES: ql
    // EFFECTS: parses quotes from JSON object and adds it to QuoteList
    private void addQuote(QuoteList ql, JSONObject jsonObject) {
        String quote = jsonObject.getString("quote");
        String answer = jsonObject.getString("answer");
        Quote saveQuote = new Quote(quote, answer);
        ql.addQuote(saveQuote);
    }

    // EFFECTS: parses AuthorList from JSON object and returns it
    private AuthorList parseAuthorList(JSONObject jsonObject) {
        String category = jsonObject.getString("category");
        AuthorList al = new AuthorList(category);
        addAuthors(al, jsonObject);
        return al;
    }

    // MODIFIES: al
    // EFFECTS: parses Author from JSON object and adds them to AuthorList
    private void addAuthors(AuthorList al, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("authors");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addAuthor(al, nextThingy);
        }
    }

    // MODIFIES: al
    // EFFECTS: parses Author from JSON object and adds them to AuthorList
    private void addAuthor(AuthorList al, JSONObject jsonObject) {
        String author = jsonObject.getString("author");
        Author saveAuthor = new Author(author);
        al.addAuthor(saveAuthor);
    }

    // EFFECTS: parses AuthorList from JSON object and returns it
    private Score parseScore(JSONObject jsonObject) {
        int score = jsonObject.getInt("score");
        Score s = new Score(score);
        return s;
    }
}