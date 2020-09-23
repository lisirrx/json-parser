package me.lisirrx.lambdalang;

public class InputStream {
    private String input;

    private Integer pos = 0;

    private Integer line = 1;

    private Integer col = 0;

    public InputStream(String input) {
        this.input = input;
    }

    public char next(){

        char current = input.charAt(pos ++);
//        System.out.println("Visit : " + current);
        if (current == '\n'){
            line ++;
            col = 0;
        } else {
            col ++;
        }
        return current;
    }

    public char peak(){
        return input.charAt(pos);
    }

    public boolean eof(){
        return pos >= input.length();
    }

    public void croak(String msg) {
        throw new RuntimeException(msg + " (" + line + ":" + col + ")");
    }
}
