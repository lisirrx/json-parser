package me.lisirrx.lambdalang;

public class JsonTokenReader extends BaseTokenReader {
    public JsonTokenReader(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    Token readNext() {
        readWhile(super::isBlank);
        if (inputStream.eof()) {
            return null;
        }
        char current = inputStream.peak();

        if (isObjectStart(current)){
            return typedToken(TokenType.OBJECT_START);
        }

        if (isObjectEnd(current)){
            return typedToken(TokenType.OBJECT_END);
        }

        if (isArrayStart(current)){
            return typedToken(TokenType.ARRAY_START);
        }

        if (isArrayEnd(current)){
            return typedToken(TokenType.ARRAY_END);
        }

        if (isIndex(current)) {
            return typedToken(TokenType.COLON);
        }

        if (isStringStart(current)) {
            return readString();
        }

        if (isNum(current)) {
            return readNum();
        }

        if (isComma(current)) {
            return typedToken(TokenType.COMMA);
        }
        inputStream.croak("ERROR");
        return null;
    }

    private boolean isArrayEnd(char current) {
        return current == ']';
    }

    private boolean isArrayStart(char current) {
        return current == '[';
    }

    private boolean isObjectEnd(char current) {
        return current == '}';
    }

    private boolean isObjectStart(char current) {
        return current == '{';
    }


    private boolean isIndex(char s) {
        return s == ':';
    }

    private boolean isComma(char s) {
        return s == ',';
    }


}
