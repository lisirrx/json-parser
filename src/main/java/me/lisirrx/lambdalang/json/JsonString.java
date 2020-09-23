package me.lisirrx.lambdalang.json;

public class JsonString extends JsonElement{
    private String value;

    public JsonString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
