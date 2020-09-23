package me.lisirrx.lambdalang;

import me.lisirrx.lambdalang.json.JsonElement;

import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        InputStream inputStream = new InputStream(readToString("test.mao"));
//        MaoTokenReader tokenReader = new MaoTokenReader(inputStream);
//        Token t;
//        while ((t = tokenReader.readNext()) != null){
//            System.out.println(t);
//        }


        InputStream inputStream = new InputStream(readToString("test.json"));
        JsonTokenReader jsonTokenReader = new JsonTokenReader(inputStream);
        JsonParser jsonParser = new JsonParser(jsonTokenReader);
        JsonElement jsonElement = jsonParser.parser();
        System.out.println(jsonElement);
    }

    public static String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }
}
