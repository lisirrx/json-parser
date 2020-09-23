package me.lisirrx.lambdalang;

import java.util.function.Predicate;

public abstract class BaseTokenReader {
    protected InputStream inputStream;

    protected Token current;

    public BaseTokenReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    abstract Token readNext();

    public Token next(){
        Token token = peak();
        current = readNext();

        return token;
    }
    public Token peak(){
        if (current == null){
            current = readNext();
        }
        return current;
    }

    public boolean eof(){
        return peak() == null;
    }

    protected String readWhile(Predicate<Character> predicate) {
        StringBuilder tokenBuilder = new StringBuilder();
        while (!inputStream.eof() && predicate.test(inputStream.peak())) {
            tokenBuilder.append(inputStream.next());
        }
        return tokenBuilder.toString();
    }


    protected boolean isBlank(Character s) {
        return s.equals(' ') || s.equals('\n') || s.equals('\t');
    }

    protected String readUntil(char end) {
        StringBuilder tokenBuilder = new StringBuilder();
        while (!inputStream.eof()) {
            char current = inputStream.next();
            if (current == end) {
                return tokenBuilder.toString();
            } else {
                tokenBuilder.append(current);
            }
        }

        return tokenBuilder.toString();
    }

    protected Token readString() {
        inputStream.next();
        return new Token(TokenType.STR, readUntil('"'));
    }

    protected boolean isStringStart(char current) {
        return current == '"';
    }

    protected boolean isIdStart(char current) {
        return (current >= 65 && current <= 90)
                || (current >= 97 && current <= 122)
                || current == '_';
    }


    protected Token readNum() {
        return new Token(TokenType.NUM, readWhile(this::isNum));
    }

    protected boolean isNum(char current) {
        switch (current) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return true;
        }
        return false;
    }

    protected Token typedToken(TokenType comma) {
        return new Token(comma, String.valueOf(inputStream.next()));
    }
}
