package me.lisirrx.lambdalang.json;

import java.text.NumberFormat;
import java.text.ParseException;

public class JsonNumber extends JsonElement{
    private Number number;

    public JsonNumber(String number) {
        try {
            this.number = NumberFormat.getInstance().parse(number);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }
}
