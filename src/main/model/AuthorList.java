package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

//CITATION: parts of this code is based on JsonSerializationDemo

public class AuthorList implements Writable {
    private String category;
    private List<Author> authors;

    public AuthorList(String category) {
        this.category = category;
        this.authors = new ArrayList<>();
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void removeAuthor(String answer) {
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i).getName() == answer) {
                authors.remove(authors.get(i));
            }
        }
    }

    public Author getAuthor(int i) {
        return authors.get(i);
    }

    public int getSize() {
        return authors.size();
    }

    public List<Author> getAuthorList() {
        return authors;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("category", category);
        json.put("authors", authorsToJson());
        return json;
    }

    // EFFECTS: returns things in this AuthorList as a JSON array
    private JSONArray authorsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Author a : authors) {
            jsonArray.put(a.toJson());
        }

        return jsonArray;
    }
}