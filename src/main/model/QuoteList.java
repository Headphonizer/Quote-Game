package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

//CITATION: parts of this code is based on JsonSerializationDemo

public class QuoteList implements Writable {
    private List<Quote> quotes;
    private String category;

    public QuoteList(String category) {
        this.category = category;
        this.quotes = new ArrayList<>();
    }

    public void addQuote(Quote quote) {
        quotes.add(quote);
    }

    public void addLoggedQuote(Quote quote, String category) {
        quotes.add(quote);
        String eventMessage = "New quote addded to " + category + ". Quote: " + quote.getQuote() + " Author: " + quote.getAnswer();
        EventLog.getInstance().logEvent(new Event(eventMessage));
    }

    public String getCategory() {
        return category;
    }

    public Quote getQuote(int i) {
        return quotes.get(i);
    }

    public int getSize() {
        return quotes.size();
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("category", category);
        json.put("quotes", quotesToJson());
        return json;
    }

    // EFFECTS: returns things in this QuoteList as a JSON array
    private JSONArray quotesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Quote q : quotes) {
            jsonArray.put(q.toJson());
        }

        return jsonArray;
    }
}
