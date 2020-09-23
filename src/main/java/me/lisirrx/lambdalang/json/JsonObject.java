package me.lisirrx.lambdalang.json;

import java.util.HashMap;
import java.util.Map;

public class JsonObject extends JsonElement {
    private Map<String, JsonElement> value = new HashMap<>();

    public JsonObject(Map<String, JsonElement> value) {
        this.value = value;
    }

    public JsonObject() {
    }

    public Map<String, JsonElement> getValue() {
        return value;
    }

    public void setValue(Map<String, JsonElement> value) {
        this.value = value;
    }

    public void put(String key, JsonElement jsonElement) {
        value.put(key, jsonElement);
    }

    public JsonElement get(String key) {
        return value.get(key);
    }
}
