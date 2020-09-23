package me.lisirrx.lambdalang.json;

import java.util.ArrayList;
import java.util.List;

public class JsonArray extends JsonElement{
    private List<JsonElement> value = new ArrayList<>();

    public JsonArray(List<JsonElement> value) {
        this.value = value;
    }

    public JsonArray() {
    }

    public List<JsonElement> getValue() {
        return value;
    }

    public void setValue(List<JsonElement> value) {
        this.value = value;
    }

    public void add(JsonElement element){
        value.add(element);
    }


}
