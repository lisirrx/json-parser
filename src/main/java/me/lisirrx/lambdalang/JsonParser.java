package me.lisirrx.lambdalang;

import me.lisirrx.lambdalang.json.*;

public class JsonParser {

    private JsonTokenReader jsonTokenReader;

    public JsonParser(JsonTokenReader jsonTokenReader) {
        this.jsonTokenReader = jsonTokenReader;
    }


    public JsonElement parser() {
        if (jsonTokenReader.eof()) {
            return null;
        }
        Token current = jsonTokenReader.next();
        if (TokenType.OBJECT_START.equals(current.getType())) {
            return parserObject();
        }
        expected("{", current.getValue());
        return null;
    }

    private JsonElement parserObject() {
        Token current = jsonTokenReader.next();
        JsonObject res = new JsonObject();

        if (!TokenType.STR.equals(current.getType())) {
            expected("STRING", current.getValue());
            return null;
        }

        while (TokenType.STR.equals(current.getType())) {
            String key = current.getValue();
            skipColon();
            res.put(key, parserElement());
            if (skipCommaOrObjectEnd()){
                return res;
            }
            current = jsonTokenReader.next();
        }
        return res;
    }


    private JsonElement parserElement() {
        Token c = jsonTokenReader.peak();
        switch (c.getType()) {
            case OBJECT_START:
                jsonTokenReader.next();
                return parserObject();
            case ARRAY_START:
                jsonTokenReader.next();
                return parseArray();
            case STR:
                return new JsonString(jsonTokenReader.next().getValue());
            case NUM:
                return new JsonNumber(jsonTokenReader.next().getValue());
            default:
                expected("{ or [ or STRING or NUMBER", c.getValue());
                return null;
        }
    }

    private JsonElement parseArray() {
        JsonArray jsonArray = new JsonArray();
        Token c = jsonTokenReader.peak();

        while (!c.getType().equals(TokenType.ARRAY_END)) {
            jsonArray.add(parserElement());
            if (skipCommaOrArrayEnd()) {
                return jsonArray;
            }
            c = jsonTokenReader.next();
        }
        return jsonArray;
    }


    private void skipColon() {
        Token c = jsonTokenReader.next();
        if (!c.getType().equals(TokenType.COLON)) {
            expected(":", c.getValue());
        }
    }

    private Boolean skipCommaOrArrayEnd() {
        Token c = jsonTokenReader.next();
        if (c.getType().equals(TokenType.ARRAY_END)) {
            return true;
        } else if (c.getType().equals(TokenType.COMMA)) {
            return false;
        } else {
            expected(", or ]", c.getValue());
            return null;
        }
    }

    private Boolean skipCommaOrObjectEnd() {
        Token c = jsonTokenReader.next();
        if (c.getType().equals(TokenType.OBJECT_END)) {
            return true;
        } else if (c.getType().equals(TokenType.COMMA)) {
            return false;
        } else {
            expected(", or ]", c.getValue());
            return null;
        }
    }

    private void expected(String expected, String actual) {
        throw new RuntimeException("Expected " + expected + " Actual " + actual);
    }
}

