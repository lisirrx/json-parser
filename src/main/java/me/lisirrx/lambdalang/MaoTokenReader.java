package me.lisirrx.lambdalang;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class MaoTokenReader extends BaseTokenReader{


    private static Set<String> keyWords = new HashSet<>();

    static {
        keyWords.add("int");
        keyWords.add("double");
        keyWords.add("print");
    }

    public MaoTokenReader(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public Token readNext() {
        readWhile(this::isBlank);
        if (inputStream.eof()) {
            return null;
        }
        char current = inputStream.peak();
        if (isNum(current)) {
            return readNum();
        }

        if (isStringStart(current)) {
            return readString();
        }

        if (isPunc(current)) {
            return toPunc(current);
        }

        if (isOpStart(current)) {
            return readOp();
        }

        if (isIdStart(current)) {
            return readIdenOrKw();
        }

        inputStream.croak("Error");
        return null;
    }


    private Token readIdenOrKw() {
        String res = readWhile(this::isIdStart);
        if (keyWords.contains(res)) {
            return new Token(TokenType.KW, res);
        } else {
            return new Token(TokenType.VAR, res);
        }
    }


    private Token readOp() {
        return new Token(TokenType.OP, readWhile(this::isOpStart));
    }

    private boolean isOpStart(char current) {
        switch (current) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '=':
                return true;
        }
        return false;
    }

    private Token toPunc(char current) {
        return typedToken(TokenType.PUNC);
    }

    private boolean isPunc(char current) {
        switch (current) {
            case ',':
            case ';':
            case '(':
            case ')':
                return true;
        }
        return false;
    }






}
