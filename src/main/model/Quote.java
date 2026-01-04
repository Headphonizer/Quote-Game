package model;

import org.json.JSONObject;
import persistence.Writable;

//CITATION: parts of this code is based on JsonSerializationDemo

public class Quote implements Writable {
    private String quote;
    private String answer;

    //EFFECTS: each quote has the quote content and its author
    public Quote(String quoteString, String answer) {
        this.quote = quoteString;
        this.answer = answer;
    }

    public String getQuote() {
        return quote;
    }

    public String getAnswer() {
        return answer;
    }
    
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("quote", quote);
        json.put("answer", answer);
        return json;
    }
}
