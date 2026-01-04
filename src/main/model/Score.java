package model;

import org.json.JSONObject;
import persistence.Writable;

//CITATION: parts of this code is based on JsonSerializationDemo

public class Score implements Writable {
    private int score;

    public Score(int score) {
        this.score = score;
    }

    // MODIFIES: this
    // EFFECTS: increase score when the player gets the right answer
    public void addScore() {
        score += 10;
    }

    public void addLoggedScore(String category) {
        addScore();
        String eventMessage = "Player got 1 question correct in " + category;
        EventLog.getInstance().logEvent(new Event(eventMessage));
    }

    public void addSuperScore() {
        score += 1000;
    }

    public void addLoggedSuperScore(String category) {
        addSuperScore();
        String eventMessage = "Player picked the God of this game in " + category;
        EventLog.getInstance().logEvent(new Event(eventMessage));
    }

    // MODIFIES: this
    // EFFECTS: increase score when the player gets the right answer
    public void resetScore() {
        score = 0;
    }

    public void resetLoggedScore(String category) {
        resetScore();
        String eventMessage = "Player lost every single point in " + category;
        EventLog.getInstance().logEvent(new Event(eventMessage));
    }

    public int getScore() {
        return score;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("score", score);
        return json;
    }
}
