package model;

import org.json.JSONObject;

import persistence.Writable;

//CITATION: parts of this code is based on JsonSerializationDemo

public class Author implements Writable {
    private String author;

    public Author(String name) {
        this.author = name;
    }

    public String getName() {
        return author;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("author", author);
        return json;
    }
}
