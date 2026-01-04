package persistence;

import org.json.JSONObject;

//CITATION: this code is based on JsonSerializationDemo

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
